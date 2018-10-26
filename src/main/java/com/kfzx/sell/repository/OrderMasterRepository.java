package com.kfzx.sell.repository;

import com.kfzx.sell.entity.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author VicterTian
 * @version V1.0
 * @Date 2018/10/26
 */
public interface OrderMasterRepository extends JpaRepository<OrderMaster,String> {
	/**
	 * 分页查找用户订单
	 * @param buyerOpenid 用户openid
	 * @param pageable 分页
	 * @return Page<OrderMaster>
	 */
	Page<OrderMaster> findByBuyerOpenid(String buyerOpenid, Pageable pageable);
}
