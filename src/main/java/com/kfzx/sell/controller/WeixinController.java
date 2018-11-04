package com.kfzx.sell.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * 微信相关
 *
 * @author VicterTian
 * @version V1.0
 * @Date 2018/11/4
 */
@RestController
@RequestMapping("/weixin")
@Slf4j
public class WeixinController {
	@GetMapping("/auth")
	public void auth(@RequestParam("code")String code){
		log.info("进入auth方法~~~~~~~~");
		log.info("code={}",code);
		String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=wx423bd3ecf446a34b&secret=df01772e81b64268ec43335102a89702&code="+code+"&grant_type=authorization_code";
		RestTemplate restTemplate = new RestTemplate();
		String response = restTemplate.getForObject(url, String.class);
		log.info("response={}",response);
	}


}
