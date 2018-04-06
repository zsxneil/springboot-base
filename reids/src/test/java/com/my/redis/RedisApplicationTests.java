package com.my.redis;

import com.my.redis.model.UserInfo;
import com.my.redis.service.ISerialNumberService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisApplicationTests {

	private static final Logger log = LoggerFactory.getLogger(RedisApplicationTests.class);

	/*@Autowired
	StringRedisTemplate stringRedisTemplate;*/

	@Autowired
	RedisTemplate<String, UserInfo> template;

	@Autowired
	RedisTemplate redisTemplate;

	@Autowired
	ISerialNumberService serialNumberService;

	/*@Test
	public void saveStr(){
		stringRedisTemplate.opsForValue().set("zzp", "big z");
		Assert.assertEquals("big z", stringRedisTemplate.opsForValue().get("zzp"));
	}*/

	@Test
	public void selfDefineSerializerTest() {
		UserInfo userInfo = new UserInfo("123456", "neil", "123456");
		template.opsForValue().set("user:neil", userInfo);
		Assert.assertEquals(true, template.hasKey("user:neil"));
		Assert.assertEquals(userInfo, template.opsForValue().get("user:neil"));
	}

	@Test
	public void jsonSerializerTest() throws  Exception {
		UserInfo user = new UserInfo("15201803745","snow","112358");
		ValueOperations<String,UserInfo> operations = redisTemplate.opsForValue();
		operations.set("User:zzp",user);
		Thread.sleep(1000);
		operations.set("User:zzq",user);
		Thread.sleep(1000);
		Assert.assertEquals(true,redisTemplate.hasKey("User:zzp"));
		Assert.assertEquals(true,redisTemplate.hasKey("User:zzq"));
	}

	/**
	 * 测试业务流水号
	 * @throws Exception
	 */
	@Test
	public void serialNumTest() throws Exception {
		String bizCode = "R";
		String number = serialNumberService.genrerate(bizCode);
		System.out.println(number);
	}

}
