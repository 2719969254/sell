package com.kfzx.sell.controller;

import com.kfzx.sell.converter.OrderForm2OrderDTOConverter;
import com.kfzx.sell.dto.OrderDTO;
import com.kfzx.sell.enums.ResultEnum;
import com.kfzx.sell.exception.SellException;
import com.kfzx.sell.form.OrderForm;
import com.kfzx.sell.service.BuyerService;
import com.kfzx.sell.service.OrderService;
import com.kfzx.sell.utils.ResultVOUtil;
import com.kfzx.sell.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.criterion.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author VicterTian
 * @version V1.0
 * @Date 2018/11/3
 */
@RestController
@Slf4j
@RequestMapping("/buyer/order")
public class BuyerOrderController {
	@Resource
	private OrderService orderService;
	@Resource
	private BuyerService buyerService;

	/**
	 * 创建订单
	 */
	@PostMapping("/create")
	public ResultVO<Map<String, String>> create(@Valid OrderForm orderForm, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			log.error("【创建订单】参数不正确, orderForm={}", orderForm);
			throw new SellException(ResultEnum.PARAM_ERROR.getCode(), bindingResult.getFieldError().getDefaultMessage());
		}
		OrderDTO orderDTO = OrderForm2OrderDTOConverter.convert(orderForm);
		if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())) {
			log.error("【创建订单】购物车不能为空");
			throw new SellException(ResultEnum.CART_EMPTY);
		}
		OrderDTO createResult = orderService.create(orderDTO);

		Map<String, String> map = new HashMap<>();
		map.put("orderId", createResult.getOrderId());

		return ResultVOUtil.success(map);
	}

	/**
	 * 获取订单列表
	 */
	@GetMapping("/list")
	public ResultVO<List<OrderDTO>> list(@RequestParam("openid") String openid,
	                                     @RequestParam(value = "page", defaultValue = "0") Integer page,
	                                     @RequestParam(value = "size", defaultValue = "10") Integer size) {
		if (StringUtils.isEmpty(openid)) {
			log.error("【查询订单列表】openid为空");
			throw new SellException(ResultEnum.PARAM_ERROR);
		}
		PageRequest request = new PageRequest(page, size);
		Page<OrderDTO> orderDTOPage = orderService.findList(openid, request);
		return ResultVOUtil.success(orderDTOPage.getContent());
	}
	/**
	 * 订单详情
	 */
	@GetMapping("/detail")
	public ResultVO<OrderDTO> detail(@RequestParam("openid") String openid,
                                     @RequestParam("orderId") String orderId){
		OrderDTO orderDTO = buyerService.findOrderOne(openid, orderId);
		return ResultVOUtil.success(orderDTO);
	}
	/**
	 * 取消订单
	 */
	@PostMapping("/cancel")
	public ResultVO cancel(@RequestParam("openid") String openid,
	                       @RequestParam("orderId") String orderId) {
		buyerService.cancelOrder(openid, orderId);
		return ResultVOUtil.success();
	}
}