package kr.or.ddit.bio01.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

//Controller 어노테이션 : 스프링이 브라우저의 요청을 받아들이는 컨트롤러라고 인지.
//자바 빈으로 등록해서 관리
@Controller
public class BIO01F010Controller {
	// /bio01/BIO01F010.jsp
	//RequestMapping어노테이션 : 웹 브라우저의 요청에 실행되는 자바 메소드를 지정
	//value : 요청되는 브라우저의 주소(URI)를 지정
	@RequestMapping(value="/bio01/BIO01F010", method=RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName(null);
		return mav;
	}
	@RequestMapping(value="/bio01/BIO01F010", method=RequestMethod.POST)
	public ModelAndView createPost(@RequestParam Map<String, Object> map) {
		ModelAndView mav = new ModelAndView();
		
		return mav;
	}
}



