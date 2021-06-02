package ddit.di.xml.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import ddit.di.xml.Hello;
import ddit.di.xml.Printer;

public class HelloBeanJunitTest {
	private ApplicationContext context;
	
	@Before
	public void init() {
		//IoC 컨테이너를 생성
		//1.ApplicationContext 객체 생성
		context = new GenericXmlApplicationContext("config/beans.xml");
	}
	
	@Test 
	public void test1() {
		//2.getBean() 호출
		Hello hello = (Hello)context.getBean("hello2");
		//3. Hello의 sayHello() 호출
		assertEquals("Hello Spring", hello.sayHello());
		//4.Hello의 printer() 호출
		hello.print();
		
		
		//list DI
		assertEquals(3,  hello.getNames().size());
		List<String> list = hello.getNames();
		for(String value : list) {
			System.out.println(value);
		}	
		
		//StringPrinter getBean()
		Printer printer = (Printer)context.getBean("printer");
		assertEquals("Hello Spring", printer.toString());
	}
	@Test @Ignore
	public void test2() {
		Printer printer = (Printer)context.getBean("printer");
		Printer printer2 = context.getBean("printer",Printer.class);
		
		assertSame(printer, printer2);
		
	}
}
