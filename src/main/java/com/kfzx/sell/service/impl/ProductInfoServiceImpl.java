package com.kfzx.sell.service.impl;

import com.kfzx.sell.repository.ProductInfoRepository;
import com.kfzx.sell.enums.ProductStatusEnum;
import com.kfzx.sell.entity.ProductInfo;
import com.kfzx.sell.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author VicterTian
 * @version V1.0
 * @Date 2018/10/24
 */
@Service
public class ProductInfoServiceImpl implements ProductInfoService {
	@Autowired
	private ProductInfoRepository productInfoRepository;

	/**
	 * 根据id查询商品信息
	 *
	 * @param id 商品id
	 * @return ProductInfo
	 */
	@Override
	public Optional<ProductInfo> findOne(String id) {
		return productInfoRepository.findById(id);
	}

	/**
	 * 查询所有在架商品列表
	 *
	 * @return List<ProductInfo>
	 */
	@Override
	public List<ProductInfo> findUpAll() {
		return productInfoRepository.findByProductStatus(ProductStatusEnum.UP.getCode());
	}

	/**
	 * 查询所有商品信息，并通过分页返回
	 *
	 * @return Page<ProductInfo>
	 */
	@Override
	public Page<ProductInfo> findAll(Pageable pageable) {
		return productInfoRepository.findAll(pageable);
	}

	/**
	 * 保存商品信息
	 *
	 * @param productInfo 商品信息
	 * @return ProductInfo
	 */
	@Override
	public ProductInfo save(ProductInfo productInfo) {
		return productInfoRepository.save(productInfo);
	}
}