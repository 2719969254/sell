package com.kfzx.sell.form;

import lombok.Data;

import javax.validation.MessageInterpolator;
import javax.validation.constraints.NotEmpty;

/**
 * @author VicterTian
 * @version V1.0
 * @Date 2018/11/3
 */
@Data
public class OrderForm {
	/**
	 * 买家姓名
	 */
	@NotEmpty(message = "姓名不能为空")
	private String name;
	/**
	 * 买家电话
	 */
	@NotEmpty(message = "电话不能为空")
	private String phone;
	/**
	 * 用户地址
	 */
	@NotEmpty(message = "address不能为空")
	private String address;
	/**
	 * 用户微信的openid
	 */
	@NotEmpty(message = "openid不能为空")
	private String openid;
	/**
	 * 购物车
	 */
	@NotEmpty(message = "购物车不能为空")
	private String items;

}
