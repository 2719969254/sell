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
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
		BeanUtils.copyProperties(orderDTO, orderMaster);
		orderMaster.setOrderId(orderId);
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
	public OrderDTO cancel(OrderDTO orderDTO) {
		return null;
	}

	/**
	 * 完结订单.
	 *
	 * @param orderDTO orderDTO
	 */
	@Override
	public OrderDTO finish(OrderDTO orderDTO) {
		return null;
	}

	/**
	 * 支付订单.
	 *
	 * @param orderDTO orderDTO
	 */
	@Override
	public OrderDTO paid(OrderDTO orderDTO) {
		return null;
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
