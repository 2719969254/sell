package com.kfzx.sell.service.impl;

import com.kfzx.sell.dto.OrderDTO;
import com.kfzx.sell.enums.ResultEnum;
import com.kfzx.sell.exception.SellException;
import com.kfzx.sell.service.BuyerService;
import com.kfzx.sell.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author VicterTian
 * @version V1.0
 * @Date 2018/11/3
 */
@Service
@Slf4j
public class BuyerServiceImpl implements BuyerService {
	@Resource
	private OrderService orderService;
	/**
	 * 查找订单
	 *
	 * @param openid  openid
	 * @param orderId orderId
	 * @return OrderDTO
	 */
	@Override
	public OrderDTO findOrderOne(String openid, String orderId) {
		return checkOrderOwner(openid, orderId);
	}

	/**
	 * 取消订单
	 *
	 * @param openid  openid
	 * @param orderId orderId
	 * @return OrderDTO
	 */
	@Override
	public OrderDTO cancelOrder(String openid, String orderId) {
		OrderDTO orderDTO = checkOrderOwner(openid, orderId);
		if (orderDTO == null) {
			log.error("【取消订单】查不到改订单, orderId={}", orderId);
			throw new SellException(ResultEnum.ORDER_NOT_EXIST);
		}
		return orderService.cancel(orderDTO);
	}
	private OrderDTO checkOrderOwner(String openid, String orderId) {
		OrderDTO orderDTO = orderService.findOne(orderId);
		if (orderDTO == null) {
			return null;
		}
		//判断是否是自己的订单
		if (!orderDTO.getBuyerOpenid().equalsIgnoreCase(openid)) {
			log.error("【查询订单】订单的 openid不一致. openid={}, orderDTO={}", openid, orderDTO);
			throw new SellException(ResultEnum.ORDER_OWNER_ERROR);
		}
		return orderDTO;
	}
}
