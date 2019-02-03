package kklazy.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kklazy.security.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:/beans/**/*.xml" })
public class JunitTest {
	
	@Autowired
	private UserService userService;
	
	@Test
	public void testSearch() {
		System.out.println(userService.findByUsername("kk"));
	}
	
}
