package com.base;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.SpringBootProjectApplication;

@RunWith(SpringJUnit4ClassRunner.class)  
@SpringBootTest(classes = SpringBootProjectApplication.class)  
public abstract class BaseJunitTest {
	
	protected MockHttpServletRequest request;

	protected MockHttpServletResponse response;

	@Before
	public void setUp() {
		request = new MockHttpServletRequest();
		request.setCharacterEncoding("UTF-8");
		response = new MockHttpServletResponse();
	}
}
