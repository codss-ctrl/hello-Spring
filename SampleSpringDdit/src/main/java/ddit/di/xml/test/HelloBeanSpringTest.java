package ddit.di.xml.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import java.util.Iterator;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ddit.di.xml.Hello;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:config/beans.xml") //설정파일 위치 설정
public class HelloBeanSpringTest {
	
	@Autowired //빈 자동생성
	private ApplicationContext context;
	
	@Test
	public void bean1() {
		Hello hello = (Hello)context.getBean("hello");
		assertEquals("Hello "
				+ "Spring", hello.sayHello());
		hello.print();
		
		Hello hello2 = (Hello)context.getBean("hello");
		hello2.print();
		//스프링은 빈 객체를 싱클톤으로 관리
		assertSame(hello, hello2);

		
		
	}
}
