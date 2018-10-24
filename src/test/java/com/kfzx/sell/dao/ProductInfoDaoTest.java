package com.kfzx.sell.dao;

import com.kfzx.sell.pojo.ProductInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoDaoTest {
	@Autowired
	private ProductInfoDao productInfoDao;
	@Test
	public void findById(){
		Optional<ProductInfo> productInfo = productInfoDao.findById("1");
		System.out.println(productInfo.toString());
	}
	@Test
	public void saveTest(){
		ProductInfo productInfo = new ProductInfo();
		productInfo.setProductId("11221");
		productInfo.setProductName("hhh");
		productInfo.setProductPrice(new BigDecimal(2.3));
		productInfo.setProductStock(100);
		productInfo.setProductDesc("hh444");
		productInfo.setProductIcon("ert.jpg");
		productInfo.setProductStatus(1);
		productInfo.setCategoryType(1);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		productInfo.setCreateTime(df.format(new Date()));
		productInfo.setUpdateTime(df.format(new Date()));
		productInfoDao.save(productInfo);
	}
}