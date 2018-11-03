package com.kfzx.sell.service.impl;

import com.kfzx.sell.converter.OrderMaster2OrderDTOConverter;
import com.kfzx.sell.dto.CartDTO;
import com.kfzx.sell.dto.OrderDTO;
import com.kfzx.sell.entity.OrderDetail;
import com.kfzx.sell.entity.OrderMaster;
import com.kfzx.sell.entity.ProductInfo;
import com.kfzx.sell.enums.OrderStatusEnum;
import com.kfzx.sell.enums.PayStatusEnum;
import com.kfzx.sell.enums.ResultEnum;
import com.kfzx.sell.exception.SellException;
import com.kfzx.sell.repository.OrderDetailRepository;
import com.kfzx.sell.repository.OrderMasterRepository;
import com.kfzx.sell.service.OrderService;
import com.kfzx.sell.service.ProductInfoService;
import com.kfzx.sell.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author VicterTian
 * @version V1.0
 * @Date 2018/10/26
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
	private final ProductInfoService productInfoService;
	private final OrderDetailRepository orderDetailRepository;
	private final OrderMasterRepository orderMasterRepository;

	@Autowired
	public OrderServiceImpl(ProductInfoService productInfoService, OrderDetailRepository orderDetailRepository, OrderMasterRepository orderMasterRepository) {
		this.productInfoService = productInfoService;
		this.orderDetailRepository = orderDetailRepository;
		this.orderMasterRepository = orderMasterRepository;
	}

	/**
	 * 创建订单.
	 *
	 * @param orderDTO 订单DTO对象
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public OrderDTO create(OrderDTO orderDTO) {
		String orderId = KeyUtil.genUniqueKey();
		BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);
//		List<CartDTO> cartDTOList = new ArrayList<>();
		//1.查询商品（数量，价格）
		for (OrderDetail orderDetail : orderDTO.getOrderDetailList()) {
			ProductInfo productInfo = productInfoService.findOne(orderDetail.getProductId()).orElse(null);
			//如果为空，则说明商品不存在
			if (productInfo == null) {
				throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
			}
			//2.计算总价
			orderAmount = productInfo.getProductPrice().multiply(new BigDecimal(orderDetail.getProductQuantity())).add(orderAmount);
			//3.订单详情入库(orderDetail)
			orderDetail.setDetailId(KeyUtil.genUniqueKey());
			orderDetail.setOrderId(orderId);
			BeanUtils.copyProperties(productInfo, orderDetail);
			orderDetailRepository.save(orderDetail);

			/*
			CartDTO cartDTO = new CartDTO(orderDetail.getProductId(),orderDetail.getProductQuantity());
			cartDTOList.add(cartDTO);
			*/
		}
		//4.订单入库（OrderMaster）
		OrderMaster orderMaster = new OrderMaster();
		orderDTO.setOrderId(orderId);
		BeanUtils.copyProperties(orderDTO, orderMaster);
		orderMaster.setOrderAmount(orderAmount);
		orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
		orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
		System.out.println(orderMaster.toString());
		orderMasterRepository.save(orderMaster);
		//扣库存
		List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream().map(e -> new CartDTO(e.getProductId(), e.getProductQuantity())).collect(Collectors.toList());
		productInfoService.decreaseStock(cartDTOList);
		return orderDTO;
	}

	/**
	 * 查询单个订单.
	 *
	 * @param orderId 订单id
	 */
	@Override
	public OrderDTO findOne(String orderId) {
		Optional<OrderMaster> optional = orderMasterRepository.findById(orderId);
		if (optional.orElse(null) == null) {
			throw new SellException(ResultEnum.ORDER_NOT_EXIST);
		}
		List<OrderDetail> orderDetails = orderDetailRepository.findByOrderId(orderId);
		if (orderDetails == null) {
			throw new SellException(ResultEnum.ORDERDETAIL_NOT_EXIST);
		}
		OrderDTO orderDTO = new OrderDTO();
		BeanUtils.copyProperties(optional.get(), orderDTO);
		orderDTO.setOrderDetailList(orderDetails);
		return orderDTO;
	}

	/**
	 * 查询订单列表.
	 *
	 * @param buyerOpenid 用户openid
	 * @param pageable    pageable
	 */
	@Override
	public Page<OrderDTO> findList(String buyerOpenid, Pageable pageable) {
		Page<OrderMaster> orderMasterPage = orderMasterRepository.findByBuyerOpenid(buyerOpenid, pageable);
		List<OrderDTO> orderDTOList = OrderMaster2OrderDTOConverter.convert(orderMasterPage.getContent());
		return new PageImpl<>(orderDTOList, pageable, orderMasterPage.getTotalElements());
	}

	/**
	 * 取消订单.
	 *
	 * @param orderDTO orderDTO
	 */
	@Override
	@Transactional(rollbackFor = SellException.class)
	public OrderDTO cancel(OrderDTO orderDTO) {
		OrderMaster orderMaster = new OrderMaster();

		//判断订单状态
		if(!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())){
			log.error("【订单取消】，订单状态不正确，orderId={}, orderStatus={}", orderDTO.getOrderId(), orderDTO.getOrderStatus());
			throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
		}
		//修改订单状态
		orderDTO.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
		BeanUtils.copyProperties(orderDTO,orderMaster);
		OrderMaster save = orderMasterRepository.save(orderMaster);
		if (save == null) {
			log.error("【取消订单】更新失败, orderMaster={}", orderMaster);
			throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
		}
		//返回库存
		if(CollectionUtils.isEmpty(orderDTO.getOrderDetailList())){
			log.error("【取消订单】订单中无商品详情, orderDTO={}", orderDTO);
			throw new SellException(ResultEnum.ORDER_DETAIL_EMPTY);
		}
		List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream()
				.map(e -> new CartDTO(e.getProductId(), e.getProductQuantity()))
				.collect(Collectors.toList());
		productInfoService.increaseStock(cartDTOList);
		//如果已付款，需要退款
		if (orderDTO.getPayStatus().equals(PayStatusEnum.SUCCESS.getCode())) {
			//TODO
			//payService.refund(orderDTO);
		}
		return orderDTO;
	}

	/**
	 * 完结订单.
	 *
	 * @param orderDTO orderDTO
	 */
	@Override
	@Transactional(rollbackFor = SellException.class)
	public OrderDTO finish(OrderDTO orderDTO) {
		//判断订单状态
		if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
			log.error("【完结订单】订单状态不正确, orderId={}, orderStatus={}", orderDTO.getOrderId(), orderDTO.getOrderStatus());
			throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
		}

		//修改订单状态
		orderDTO.setOrderStatus(OrderStatusEnum.FINISHED.getCode());
		OrderMaster orderMaster = new OrderMaster();
		BeanUtils.copyProperties(orderDTO, orderMaster);
		OrderMaster updateResult = orderMasterRepository.save(orderMaster);
		if (updateResult == null) {
			log.error("【完结订单】更新失败, orderMaster={}", orderMaster);
			throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
		}
		return orderDTO;
	}

	/**
	 * 支付订单.
	 *
	 * @param orderDTO orderDTO
	 */
	@Override
	@Transactional(rollbackFor = SellException.class)
	public OrderDTO paid(OrderDTO orderDTO) {
		//判断订单状态
		if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
			log.error("【订单支付完成】订单状态不正确, orderId={}, orderStatus={}", orderDTO.getOrderId(), orderDTO.getOrderStatus());
			throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
		}
		//判断支付状态
		if (!orderDTO.getPayStatus().equals(PayStatusEnum.WAIT.getCode())) {
			log.error("【订单支付完成】订单支付状态不正确, orderDTO={}", orderDTO);
			throw new SellException(ResultEnum.ORDER_PAY_STATUS_ERROR);
		}
		//修改支付状态
		orderDTO.setPayStatus(PayStatusEnum.SUCCESS.getCode());
		OrderMaster orderMaster = new OrderMaster();
		BeanUtils.copyProperties(orderDTO, orderMaster);
		OrderMaster updateResult = orderMasterRepository.save(orderMaster);
		if (updateResult == null) {
			log.error("【订单支付完成】更新失败, orderMaster={}", orderMaster);
			throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
		}

		return orderDTO;
	}

	/**
	 * 查询订单列表.
	 *
	 * @param pageable orderDTO
	 */
	@Override
	public Page<OrderDTO> findList(Pageable pageable) {
		return null;
	}
}
