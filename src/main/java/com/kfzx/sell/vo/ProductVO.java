package com.kfzx.sell.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * @author VicterTian
 * @version V1.0
 * @Date 2018/10/25
 */
@Data
public class ProductVO {
	@JsonProperty("name")
	private String categoryName;
	@JsonProperty("type")
	private Integer categoryType;
	@JsonProperty("foods")
	private List<ProductInfoVO> productInfoVOList;
}
