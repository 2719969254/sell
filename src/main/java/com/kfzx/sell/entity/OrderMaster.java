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

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getBuyerName() {
		return buyerName;
	}

	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
	}

	public String getBuyerPhone() {
		return buyerPhone;
	}

	public void setBuyerPhone(String buyerPhone) {
		this.buyerPhone = buyerPhone;
	}

	public String getBuyerAddress() {
		return buyerAddress;
	}

	public void setBuyerAddress(String buyerAddress) {
		this.buyerAddress = buyerAddress;
	}

	public String getBuyerOpenid() {
		return buyerOpenid;
	}

	public void setBuyerOpenid(String buyerOpenid) {
		this.buyerOpenid = buyerOpenid;
	}

	public BigDecimal getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(BigDecimal orderAmount) {
		this.orderAmount = orderAmount;
	}

	public Integer getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Integer getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(Integer payStatus) {
		this.payStatus = payStatus;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
}
