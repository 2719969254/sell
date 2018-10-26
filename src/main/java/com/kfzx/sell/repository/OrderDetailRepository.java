package com.kfzx.sell.repository;

import com.kfzx.sell.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author VicterTian
 * @version V1.0
 * @Date 2018/10/26
 */

public interface OrderDetailRepository extends JpaRepository<OrderDetail, String> {
	/**
	 * 通过orderId查找订单项
	 * @param orderId 订单id
	 * @return List<OrderDetail>
	 */
	List<OrderDetail> findByOrderId(String orderId);
}
