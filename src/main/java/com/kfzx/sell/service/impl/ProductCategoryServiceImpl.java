package com.kfzx.sell.service.impl;

import com.kfzx.sell.repository.ProductCategoryRepository;
import com.kfzx.sell.entity.ProductCategory;
import com.kfzx.sell.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author VicterTian
 * @version V1.0
 * @Date 2018/10/24
 */
@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {
	@Autowired
	private ProductCategoryRepository productCategoryRepository;

	/**
	 * 通过id查找类目对象
	 *
	 * @param id 类目对象id
	 * @return ProductCategory
	 */
	@Override
	public Optional<ProductCategory> findOne(Integer id) {
		return productCategoryRepository.findById(id);
	}

	/**
	 * 查询所有类目对象
	 *
	 * @return List<ProductCategory>
	 */
	@Override
	public List<ProductCategory> findAll() {
		return productCategoryRepository.findAll();
	}

	/**
	 * 根据条件查找类目对象
	 *
	 * @param categoryTypeList 查询条件
	 * @return List<ProductCategory>
	 */
	@Override
	public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList) {
		return productCategoryRepository.findByCategoryTypeIn(categoryTypeList);
	}

	/**
	 * 保存类目信息
	 *
	 * @param productCategory 类目对象
	 * @return ProductCategory
	 */
	@Override
	public ProductCategory save(ProductCategory productCategory) {
		return productCategoryRepository.save(productCategory);
	}
}
