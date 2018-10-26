package com.kfzx.sell.enums;

import lombok.Getter;

/**
 * @author MR.Tian
 */

@Getter
public enum OrderStatusEnum {
	/**
	 * 新订单
	 */
	NEW(0,"新订单"),
	/**
	 * 订单完成
	 */
	FINISHED(1,"订单完成"),
	/**
	 * 已取消
	 */
	CANCEL(2,"已取消"),
	;
	private Integer code;
	private  String message;

	OrderStatusEnum(Integer code, String message) {
		this.code = code;
		this.message = message;
	}
}
