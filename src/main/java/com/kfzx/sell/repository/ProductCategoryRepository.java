package com.kfzx.sell.repository;

import com.kfzx.sell.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * ProductCategoryDao接口
 *
 * @author VicterTian
 * @version V1.0
 * @Date 2018/10/24
 */
public interface ProductCategoryRepository extends JpaRepository<ProductCategory,Integer> {
	/**
	 * 方法
	 * @param categoryTypeList categoryTypeList
	 * @return List<ProductCategory>
	 */
	List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);
}
