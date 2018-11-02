package com.kfzx.sell.exception;

import com.kfzx.sell.enums.ResultEnum;

/**
 * @author VicterTian
 * @version V1.0
 * @Date 2018/10/26
 */
public class SellException extends RuntimeException{

	private Integer code;

	public SellException(ResultEnum resultEnum) {
		super(resultEnum.getMessage());

		this.code = resultEnum.getCode();
	}

	public SellException(Integer code, String message) {
		super(message);
		this.code = code;
	}
}

