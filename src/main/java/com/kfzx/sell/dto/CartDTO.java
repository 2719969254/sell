package com.kfzx.sell.dto;

import lombok.Data;

/**
 * 购物车
 *
 * @author VicterTian
 * @version V1.0
 * @Date 2018/10/26
 */
@Data
public class CartDTO {
	/**
	 * 商品id
	 */
	private String productId;
	/**
	 * 购物车商品数量
	 */
	private Integer productQuantity;

	public CartDTO(String productId, Integer productQuantity) {
		this.productId = productId;
		this.productQuantity = productQuantity;
	}

	public CartDTO() {
	}
}
