package com.kfzx.sell.dao;

import com.kfzx.sell.pojo.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * ProductCategoryDao接口
 *
 * @author VicterTian
 * @version V1.0
 * @Date 2018/10/24
 */
public interface ProductCategoryDao extends JpaRepository<ProductCategory,Integer> {
	List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);
}
