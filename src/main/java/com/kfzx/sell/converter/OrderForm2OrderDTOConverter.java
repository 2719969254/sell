package com.kfzx.sell.converter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kfzx.sell.dto.OrderDTO;
import com.kfzx.sell.entity.OrderDetail;
import com.kfzx.sell.enums.ResultEnum;
import com.kfzx.sell.exception.SellException;
import com.kfzx.sell.form.OrderForm;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * OrderForm转换为OrderDTO
 *
 * @author VicterTian
 * @version V1.0
 * @Date 2018/11/3
 */
@Slf4j
public class OrderForm2OrderDTOConverter {
	public static OrderDTO convert(OrderForm orderForm) {
		OrderDTO orderDTO = new OrderDTO();
		Gson gson = new Gson();
		orderDTO.setBuyerName(orderForm.getName());
		orderDTO.setBuyerPhone(orderForm.getPhone());
		orderDTO.setBuyerAddress(orderForm.getAddress());
		orderDTO.setBuyerOpenid(orderForm.getOpenid());
		List<OrderDetail> orderDetailList;
		try {
			orderDetailList = gson.fromJson(orderForm.getItems(),
					new TypeToken<List<OrderDetail>>() {
					}.getType());
		} catch (Exception e) {
			log.error("【对象转换】错误, string={}", orderForm.getItems());
			throw new SellException(ResultEnum.PARAM_ERROR);
		}
		orderDTO.setOrderDetailList(orderDetailList);
		return orderDTO;
	}
}
