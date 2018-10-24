package com.kfzx.sell.pojo;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * ProductCategory实体类
 *
 * @author VicterTian
 * @version V1.0
 * @Date 2018/10/24
 */
@Entity
@DynamicUpdate
@Data
public class ProductCategory {
	/**
	 * 类目id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer categoryId;
	/**
	 * 类目名称
	 */
	private String categoryName;

	/**
	 * 类目类型
	 */
	private Integer categoryType;

	/**
	 * 创建时间
	 */
	private String createTime;

	/**
	 * 更新时间
	 */
	private String updateTime;
}
