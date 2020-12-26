package com.searchsuggestions;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.searchsuggestions.hibernate.mapping.Address;
import com.searchsuggestions.hibernate.mapping.User;
import com.searchsuggestions.service.UserService;

@RunWith(SpringRunner.class)
//@ContextConfiguration(classes = {UserService.class})
//@ComponentScan("com.searchsuggestions.*")
@SpringBootTest
//@TestConfiguration()
public class UserServiceTest {

	@Autowired
	private UserService userService;
	
	@Test
	public void test1() {
		
		
		userService.saveUser(createUser("Delhi"));
		userService.saveUser(createUser("Delhi"));
		userService.saveUser(createUser("Noida"));
		userService.saveUser(createUser("Mumbai"));
		userService.saveUser(createUser("Delhi"));

		
		
		User u = userService.findByCity("Delhi");
		assertTrue(u.getAddress().getCity().equals("Delhi"));	
		
		assertTrue("Must return 3 user for delhi city",userService.findByUserCity("Delhi").size() == 3);
		
				
		
	}

	private User createUser(String city) {
		return User.builder()
				.address(Address.builder().city(city).build())
				.build();
	}
}
