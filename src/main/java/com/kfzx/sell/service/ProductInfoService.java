package com.kfzx.sell.service;

import com.kfzx.sell.dto.CartDTO;
import com.kfzx.sell.entity.ProductInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * 商品信息
 *
 * @author VicterTian
 * @version V1.0
 * @Date 2018/10/24
 */
public interface ProductInfoService {
	/**
	 * 根据id查询商品信息
	 * @param id 商品id
	 * @return ProductInfo
	 */
	Optional<ProductInfo> findOne(String id);
	/**
	 * 查询所有在架商品列表
	 * @return List<ProductInfo>
	 */
	List<ProductInfo> findUpAll();

	/**
	 * 查询所有商品信息，并通过分页返回
	 * @param pageable pageable
	 * @return Page<ProductInfo>
	 */
	Page<ProductInfo> findAll(Pageable pageable);

	/**
	 * 保存商品信息
	 * @return ProductInfo
	 */
	ProductInfo save(ProductInfo productInfo);

	/**
	 * 加库存
	 * @param cartDTOList 购物车DTO
	 */
	void increaseStock(List<CartDTO> cartDTOList);

	/**
	 * 减库存
	 * @param cartDTOList 购物车DTO
	 */
	void decreaseStock(List<CartDTO> cartDTOList);
}
