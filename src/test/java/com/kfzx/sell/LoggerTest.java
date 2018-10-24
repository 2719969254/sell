package com.kfzx.sell;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 测试日志
 *
 * @author VicterTian
 * @version V1.0
 * @Date 2018/10/23
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class LoggerTest {
	@Test
	public void test1(){
		String name = "tz";
		String password = "22222";
		log.debug("debug.....");
		log.info("debug.....");
		log.error("error.....");
		log.info("name:{}, password:{}",name,password);
	}
}
