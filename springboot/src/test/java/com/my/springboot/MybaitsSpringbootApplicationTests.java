package com.my.springboot;

import com.my.springboot.mybatis.mappers.UserMapper;
import com.my.springboot.mybatis.model.User;
import com.my.springboot.mybatis.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=Application. class)
public class MybaitsSpringbootApplicationTests {

	@Autowired
	UserService userService;

	/*@Test
	public void mybatis() {
		User user = new User();
		user.setUsername("Neil");
		user.setBirthday(new Date());
		user.setAddress("深圳市");
		user.setSex(2);

		userMapper.insert(user);
		int id = user.getId();//获得自动生成的主键

		List<User> userList = userMapper.selectAll();
		for (User user1 : userList) {
			System.out.println(user1);
		}

		user.setUsername("neilUpdate");
		userMapper.update(user);

		User newUser = new User();
		newUser.setId(49);
		userMapper.delete(newUser);

	}*/

	@Test
	public void insert() {
		User user = new User();
		user.setUsername("Neil");
		user.setBirthday(new Date());
		user.setAddress("深圳市");
		user.setSex(2);

		userService.insert(user);
		/*int id = user.getId();
		User selectUser = userService.selectOne(id);
		System.out.println(selectUser);*/
	}

}
