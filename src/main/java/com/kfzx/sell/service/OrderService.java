package com.kfzx.sell.service;

import com.kfzx.sell.dto.OrderDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * 订单service
 *
 * @author VicterTian
 * @version V1.0
 * @Date 2018/10/26
 */
public interface OrderService {

	/**
	 * 创建订单.
	 */
	OrderDTO create(OrderDTO orderDTO);

	/**
	 * 查询单个订单.
	 * @param orderId 订单id
	 * @return OrderDTO
	 */
	OrderDTO findOne(String orderId);

	/**
	 * 查询订单列表.
	 */
	Page<OrderDTO> findList(String buyerOpenid, Pageable pageable);

	/**
	 * 取消订单.
	 */
	OrderDTO cancel(OrderDTO orderDTO);

	/**
	 * 完结订单.
	 */
	OrderDTO finish(OrderDTO orderDTO);

	/**
	 * 支付订单.
	 */
	OrderDTO paid(OrderDTO orderDTO);

	/**
	 * 查询订单列表.
	 */
	Page<OrderDTO> findList(Pageable pageable);

}

