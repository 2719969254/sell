package com.kfzx.sell.service;

import com.kfzx.sell.entity.ProductCategory;

import java.util.List;
import java.util.Optional;

/**
 * ProductCategoryService接口
 *
 * @author VicterTian
 * @version V1.0
 * @Date 2018/10/24
 */
public interface ProductCategoryService {
	/**
	 * 通过id查找类目对象
	 * @param id 类目对象id
	 * @return ProductCategory
	 */
	Optional<ProductCategory> findOne(Integer id);

	/**
	 * 查询所有类目对象
	 * @return List<ProductCategory>
	 */
	List<ProductCategory> findAll();

	/**
	 * 根据条件查找类目对象
	 * @param categoryTypeList 查询条件
	 * @return List<ProductCategory>
	 */
	List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

	/**
	 * 保存类目信息
	 * @param productCategory 类目对象
	 * @return ProductCategory
	 */
	ProductCategory save(ProductCategory productCategory);

}
