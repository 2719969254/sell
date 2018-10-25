package com.kfzx.sell.enums;

import lombok.Getter;

/**
 * 商品状态
 * @author MR.Tian
 */
@Getter
public enum ProductStatusEnum {
	/**
	 * 商品在架
	 */
	UP(0,"在架"),
	/**
	 * 商品下架
	 */
	DOWN(1,"下架")
	;
	private Integer code;
	private String message;

	ProductStatusEnum(Integer code, String message) {
		this.code = code;
		this.message = message;
	}
}
