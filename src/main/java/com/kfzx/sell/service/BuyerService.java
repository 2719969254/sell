package com.kfzx.sell.service;

import com.kfzx.sell.dto.OrderDTO;

/**
 * @author VicterTian
 * @version V1.0
 * @Date 2018/11/3
 */
public interface BuyerService {
	/**
	 * 查找订单
	 * @param openid openid
	 * @param orderId orderId
	 * @return OrderDTO
	 */
	OrderDTO findOrderOne(String openid, String orderId);

	/**
	 * 取消订单
	 * @param openid openid
	 * @param orderId orderId
	 * @return OrderDTO
	 */
	OrderDTO cancelOrder(String openid, String orderId);

}
