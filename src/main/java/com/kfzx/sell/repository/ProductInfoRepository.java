package com.kfzx.sell.repository;

import com.kfzx.sell.entity.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * ProductInfoRepository
 *
 * @author VicterTian
 * @version V1.0
 * @Date 2018/10/24
 */
public interface ProductInfoRepository extends JpaRepository<ProductInfo,String> {
	/**
	 * 方法
	 * @param productStatus productStatus
	 * @return List<ProductInfo>
	 */
	List<ProductInfo> findByProductStatus(Integer productStatus);

}
