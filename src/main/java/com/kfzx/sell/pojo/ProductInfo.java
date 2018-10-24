package com.kfzx.sell.pojo;

import lombok.Data;

import javax.jnlp.IntegrationService;
import javax.persistence.Entity;
import java.math.BigDecimal;

/**
 * 买家商品pojo
 *
 * @author VicterTian
 * @version V1.0
 * @Date 2018/10/24
 */
@Entity
@Data
public class ProductInfo {
	/**
	 * 商品id
	 */
	private String productId;
	/**
	 * 商品名称
	 */
	private String productName;
	/**
	 * 商品价格
	 */
	private BigDecimal productPrice;
	/**
	 * 商品库存
	 */
	private Integer productStock;
	/**
	 * 商品描述
	 */
	private String productDesc;
	/**
	 * 商品图片
	 */
	private String productIcon;
	/**
	 * 商品类型
	 */
	private Integer productType;
	/**
	 * 商品状态
	 */
	private Integer productStatus;
	/**
	 * 创建时间
	 */
	private String createTime;
	/**
	 * 更新时间
	 */
	private String updateTime;
}
