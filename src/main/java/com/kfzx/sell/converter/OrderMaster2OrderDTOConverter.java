package com.kfzx.sell.converter;

import com.kfzx.sell.dto.OrderDTO;
import com.kfzx.sell.entity.OrderMaster;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 订单转换器
 *
 * @author VicterTian
 * @version V1.0
 * @Date 2018/11/2
 */
public class OrderMaster2OrderDTOConverter {
	private static OrderDTO convert(OrderMaster orderMaster) {
		OrderDTO orderDTO = new OrderDTO();
		BeanUtils.copyProperties(orderMaster, orderDTO);
		return orderDTO;
	}

	public static List<OrderDTO> convert(List<OrderMaster> orderMasterList) {
		return orderMasterList.stream().map(OrderMaster2OrderDTOConverter::convert
		).collect(Collectors.toList());
	}
}
