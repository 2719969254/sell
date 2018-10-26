package com.kfzx.sell.repository;

import com.kfzx.sell.entity.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryRepositoryTest {
	@Autowired
	private ProductCategoryRepository productCategoryRepository;

	@Test
	public void selectOne() {
		Optional<ProductCategory> productCategory = productCategoryRepository.findById(1);
		System.out.println("----------------->" + productCategory.toString());
	}

	@Test
	public void saveTest() {
		ProductCategory productCategory = new ProductCategory();
		productCategory.setCategoryName("222");
		productCategory.setCategoryType(3);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		productCategory.setCreateTime(df.format(new Date()));
		productCategory.setUpdateTime(df.format(new Date()));
		productCategoryRepository.save(productCategory);
	}

	/*@Test
	public void updateTest() {
		ProductCategory productCategory = null;

		//productCategory = productCategoryRepository.findById(1).get();
		Optional<ProductCategory> byId = productCategoryRepository.findById(1);
		if (byId.isPresent()) {
			productCategory = byId.get();
		}
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		assert productCategory != null;
		productCategory.setCreateTime(df.format(new Date()));
		productCategory.setUpdateTime(df.format(new Date()));
		productCategoryRepository.save(productCategory);
	}*/

	@Test
	public void findByCategoryTypeInTest() {
		List<Integer> list = Arrays.asList(2, 3, 4);
		List<ProductCategory> byCategoryTypeIn = productCategoryRepository.findByCategoryTypeIn(list);
		System.out.println(byCategoryTypeIn);
		Assert.assertNotEquals(0, byCategoryTypeIn.size());
	}
}