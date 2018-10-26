package com.kfzx.sell.repository;

import com.kfzx.sell.entity.OrderMaster;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterRepositoryTest {
	@Autowired
	private OrderMasterRepository orderMasterRepository;

	@Test
	public void saveTest() {
		OrderMaster orderMaster = new OrderMaster();
		orderMaster.setOrderId("1234567");
		orderMaster.setBuyerName("师兄");
		orderMaster.setBuyerPhone("123456789123");
		orderMaster.setBuyerAddress("幕课网");
		orderMaster.setBuyerOpenid("110110");
		orderMaster.setOrderAmount(new BigDecimal(2.5));
		OrderMaster result = orderMasterRepository.save(orderMaster);
		Assert.assertNotNull(result);
	}

	@Test
	public void findByBuyerOpenid() {
		PageRequest request = new PageRequest(1, 3);
		Page<OrderMaster> result = orderMasterRepository.findByBuyerOpenid("110110", request);
		Assert.assertNotEquals(0, result.getTotalElements());
	}
}