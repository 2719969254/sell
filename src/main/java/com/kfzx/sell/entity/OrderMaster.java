package com.kfzx.sell.entity;

import com.kfzx.sell.enums.OrderStatusEnum;
import com.kfzx.sell.enums.PayStatusEnum;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * @author VicterTian
 * @version V1.0
 * @Date 2018/10/26
 */
@Entity
@Data
public class OrderMaster {
	/**
	 * 买家id
	 */
	@Id
	private String orderId;
	/**
	 * 买家姓名
	 */
	private String buyerName;
	/**
	 * 买家电话
	 */
	private String buyerPhone;
	/**
	 * 买家地址
	 */
	private String buyerAddress;
	/**
	 * 买家微信id
	 */
	private String buyerOpenid;
	/**
	 * 订单总金额
	 */
	private BigDecimal orderAmount;
	/**
	 * 订单状态
	 */
	private Integer orderStatus = OrderStatusEnum.NEW.getCode();
	/**
	 * 支付状态
	 */
	private Integer payStatus = PayStatusEnum.WAIT.getCode();
	/**
	 * 创建时间
	 */
	private String createTime;
	/**
	 * 更新时间
	 */
	private String updateTime;


}
