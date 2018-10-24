package com.kfzx.sell.dao;

import com.kfzx.sell.pojo.ProductCategory;
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
public class ProductCategoryDaoTest {
	@Autowired
	private ProductCategoryDao productCategoryDao;

	@Test
	public void selectOne(){
		Optional<ProductCategory> productCategory = productCategoryDao.findById(1);
		System.out.println("----------------->"+productCategory.toString());
	}
	@Test
	public void saveTest(){
		ProductCategory productCategory = new ProductCategory();
		productCategory.setCategoryName("222");
		productCategory.setCategoryType(3);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		productCategory.setCreateTime(df.format(new Date()));
		productCategory.setUpdateTime(df.format(new Date()));
		productCategoryDao.save(productCategory);
	}

	@Test
	public void updateTest(){
		ProductCategory productCategory;
		productCategory = productCategoryDao.findById(1).get();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		productCategory.setCreateTime(df.format(new Date()));
		productCategory.setUpdateTime(df.format(new Date()));
		productCategoryDao.save(productCategory);
	}
	@Test
	public void findByCategoryTypeInTest(){
		List<Integer> list = Arrays.asList(2,3,4);
		List<ProductCategory> byCategoryTypeIn = productCategoryDao.findByCategoryTypeIn(list);
		System.out.println(byCategoryTypeIn);
		Assert.assertNotEquals(0, byCategoryTypeIn.size());
	}
}