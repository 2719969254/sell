package com.kfzx.sell.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author VicterTian
 * @version V1.0
 * @Date 2018/10/25
 */
@Data
public class ProductInfoVO {
	@JsonProperty("id")
	private String productId;
	@JsonProperty("name")
	private String productName;
	@JsonProperty("price")
	private BigDecimal productPrice;
	@JsonProperty("description")
	private String productDescription;
	@JsonProperty("icon")
	private String productIcon;
}
