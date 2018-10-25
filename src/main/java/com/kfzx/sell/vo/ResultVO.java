package com.kfzx.sell.vo;

import lombok.Data;

/**
 * http请求返回的最外层对象
 *
 * @author VicterTian
 * @version V1.0
 * @Date 2018/10/25
 */
@Data
public class ResultVO<T> {
	/**
	 * 错误码
	 */
	private Integer code;
	/**
	 * 错误信息
	 */
	private String msg;
	/**
	 * 具体内容
	 */
	private T data;

}
