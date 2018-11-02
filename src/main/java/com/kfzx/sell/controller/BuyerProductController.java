package com.kfzx.sell.controller;

import com.kfzx.sell.entity.ProductCategory;
import com.kfzx.sell.entity.ProductInfo;
import com.kfzx.sell.service.ProductCategoryService;
import com.kfzx.sell.service.ProductInfoService;
import com.kfzx.sell.utils.ResultVOUtil;
import com.kfzx.sell.vo.ProductInfoVO;
import com.kfzx.sell.vo.ProductVO;
import com.kfzx.sell.vo.ResultVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 买家商品
 *
 * @author VicterTian
 * @version V1.0
 * @Date 2018/10/25
 */
@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {
	private final ProductInfoService productInfoService;
	private final ProductCategoryService productCategoryService;

	@Autowired
	public BuyerProductController(ProductInfoService productInfoService, ProductCategoryService productCategoryService) {
		this.productInfoService = productInfoService;
		this.productCategoryService = productCategoryService;
	}

	@SuppressWarnings("AlibabaRemoveCommentedCode")
	@GetMapping("/list")
	public ResultVO list() {
		//1.查询所有上架商品
		List<ProductInfo> productInfoList = productInfoService.findUpAll();
		/*2.查询类目
		//传统方法：
		List<Integer> categoryTypeList = new ArrayList<>();
		for (ProductInfo productInfo : productInfoList) {
			categoryTypeList.add(productInfo.getCategoryType());
		}
		//传统方法结束
		//精简方法（java8 lambda表达式）
		*/
		List<Integer> categoryTypeList = productInfoList.stream().map(ProductInfo::getCategoryType).collect(Collectors.toList());
		//获取类目list
		List<ProductCategory> productCategoryList = productCategoryService.findByCategoryTypeIn(categoryTypeList);

		//3.数据封装
		List<ProductVO> productVOList = new ArrayList<>();
		//逐个封装
		for (ProductCategory productCategory : productCategoryList) {
			ProductVO productVO = new ProductVO();
			//设置data的name和type
			productVO.setCategoryType(productCategory.getCategoryType());
			productVO.setCategoryName(productCategory.getCategoryName());
			List<ProductInfoVO> productInfoVOList = new ArrayList<>();
			for (ProductInfo productInfo : productInfoList) {
				//查询同样类目的商品
				if (productInfo.getCategoryType().equals(productCategory.getCategoryType())) {
					ProductInfoVO productInfoVO = new ProductInfoVO();
					//复制类目信息
					BeanUtils.copyProperties(productInfo, productInfoVO);
					productInfoVOList.add(productInfoVO);
				}
			}
			productVO.setProductInfoVOList(productInfoVOList);
			productVOList.add(productVO);
		}

		return ResultVOUtil.success(productVOList);
	}
}
