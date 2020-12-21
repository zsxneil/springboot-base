package com.my.webflux;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.ReactiveRedisConnection;
import org.springframework.test.context.junit4.SpringRunner;

import java.nio.ByteBuffer;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WebfluxApplicationTests {

	@Autowired
	private ReactiveRedisConnection redisConnection;

	@Test
	public void redisTest() {
		redisConnection.stringCommands()
				.set(ByteBuffer.wrap("h".getBytes()), ByteBuffer.wrap("w".getBytes()))
				.subscribe(System.out::println);
	}

	@Test
	public void test() {

	}

}
