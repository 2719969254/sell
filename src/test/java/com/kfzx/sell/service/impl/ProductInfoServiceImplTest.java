package com.kfzx.sell.service.impl;

import com.kfzx.sell.enums.ProductStatusEnum;
import com.kfzx.sell.entity.ProductInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoServiceImplTest {
	@Autowired
	private ProductInfoServiceImpl productInfoService;
	@Test
	public void findOne(String OrderId) {
		Optional<ProductInfo> one = productInfoService.findOne(OrderId);
		System.out.println(one);
	}

	@Test
	public void findUpAll() {
		List<ProductInfo> upAll = productInfoService.findUpAll();
		System.out.println(upAll);
	}

	@Test
	public void findAll() {
		PageRequest pageRequest = new PageRequest(0,1);
		Page<ProductInfo> all = productInfoService.findAll(pageRequest);
		System.out.println(all.getTotalElements());
	}

	@Test
	public void save() {
		ProductInfo productInfo = new ProductInfo();
		productInfo.setProductId("123");
		productInfo.setProductName("hhh123");
		productInfo.setProductPrice(new BigDecimal(3.14));
		productInfo.setProductStock(500);
		productInfo.setProductDesc("desc");
		productInfo.setProductIcon("1,hhh");
		productInfo.setCategoryType(3);
		productInfo.setProductStatus(ProductStatusEnum.UP.getCode());
		productInfoService.save(productInfo);
	}
}