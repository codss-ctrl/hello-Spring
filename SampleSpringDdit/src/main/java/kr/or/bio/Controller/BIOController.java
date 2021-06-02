package kr.or.bio.Controller;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import static org.junit.Assert.*;
import kr.or.bio.bioVo;

public class BIOController {
	ApplicationContext context;
	
	@Before
	public void init() {
		//IoC 컨테이너(인터페이스)
		context = 
				new GenericXmlApplicationContext("config/bioBeans.xml");
	}
	
	@Test
	public void bean1() {
		bioVo vo = (bioVo)context.getBean("bioVo");
		//예상값, 실제값 비교
		assertEquals("bio 화이자", vo.sayHello());
		
		bioVo vo2 = context.getBean("bioVo", bioVo.class);
		//싱글턴 체킹(레퍼런스 비교)
		assertSame(vo,vo2);
	}
}




