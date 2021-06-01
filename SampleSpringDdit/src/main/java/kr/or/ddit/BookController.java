package kr.or.ddit;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

//Controller 어노테이션 : 스프링이 브라우저의 요청을 받아들이는
//컨트롤러라고 인지해서 자바 빈(java bean)으로 등록해서 관리
@Controller
public class BookController {
	//서비스 호출을 위해 BookService(인터페이스)를 의존성 주입함.
	@Autowired
	BookService bookService;
	
	/*
	 * ModleAndView : Controller가 return할 
	 * 데이터를 담당하는 모델(Model)과(And) 
	 * 화면을 담당하는 뷰(View)의 경로를 합쳐놓은 객체.
	 * ModelAndView의 생성자에 문자열 타입 파라미터가 입력되면 뷰의 경로라고 간주
	 */
	/*
	 * RequestMapping 어노테이션은 브라우저의 요청에 실행되는 자바 메소드를 지정
	 * GET? 데이터를 변경하지 않는 경우
	 * POST? 데이터가 변경될 때 사용됨
	 */
	//따라서, create 메소드는 웹 브라우저에서 /create 주소가 GET 방식으로
	//입력되었을 때 book/create 경로의 뷰를 보여줌
	@RequestMapping(value="/create", method=RequestMethod.GET)
	public ModelAndView create() {
		return new ModelAndView("book/create");
	}
	//RequestParam 어노테이션 : HTTP 파라미터를 map 변수에 자동으로 바인딩함
	//HTTP 파라미터? 웹 브라우저에서 Tomcat으로 전달하는 데이터
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public ModelAndView createPost(
			@RequestParam Map<String, Object> map) {
		int bookId = this.bookService.create(map);//1
		//ModelAndView 클래스
		//데이터와 화면을 모두 저장하는 클래스
		//Autowired를 안씀******
		//Service 인터페이스에서 처리 후 Controller로 넘김
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("redirect:/detail?bookId="+map.get("bookId"));
		
		return mav;
	}
	// /detail?bookId=3 => map {bookId:3}
	@RequestMapping(value="/detail",method=RequestMethod.GET)
	public ModelAndView detail(
			@RequestParam Map<String, Object> map) {
		Map<String, Object> detailMap = this.bookService.detail(map);	//size 5인 map
		
		ModelAndView mav = new ModelAndView();
		//뷰로 전달할 Map 데이터를 담음
		mav.addObject("data", detailMap);
		String bookId = map.get("bookId").toString();	//3
		mav.addObject("bookId", bookId);
		mav.setViewName("/book/detail");
		
		return mav;
	}
	
	// /update?bookId=6 => map {bookId:6}
	@RequestMapping(value="/update",method=RequestMethod.GET)
	public ModelAndView update(@RequestParam Map<String, Object> map, 
				Map<String, Boolean> errors) {
		BOOKVo bookVo = this.bookService.detailVo(map);
		
		ModelAndView mav = new ModelAndView();
		if(!errors.isEmpty()) {
			mav.addObject("data", map);
			mav.addObject("errors", errors);
			mav.setViewName("/book/update");
		}else {		
			mav.addObject("data", bookVo);
			mav.setViewName("/book/update");
		}
		
		return mav;
	}
	/*
	 *  parameter 4개.
	 *  title, category, price, bookId => map
	 *  RequestParam 어노테이션은 GET인지 POST인지 상관없이 map안에 넣어줌
	 *  {"bookId":6,"title":"만화책2","category":"만화2","price","20000"}
	 */
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public ModelAndView updatePost(
			@RequestParam Map<String, Object> map) {
		ModelAndView mav = new ModelAndView();
		
		//2021-05-26. 송중호. 입력폼 빈칸 체크 기능 추가 시작////////////////////////////
		String title = map.get("title").toString();
		String category = map.get("category").toString();
		String price = map.get("price").toString();
		
		Map<String, Boolean> errors = new HashMap<>();
		
		if(title==null||title.isEmpty()) {
			errors.put("title", Boolean.TRUE);
		}
		if(category==null||category.isEmpty()) {
			errors.put("category", Boolean.TRUE);
		}
		if(price==null||price.isEmpty()) {
			errors.put("price", Boolean.TRUE);
		}
		//2021-05-26. 송중호. 입력폼 빈칸 체크 기능 추가 끝////////////////////////////
		
		if(!errors.isEmpty()) {	//입력폼 빈칸 문제 발생
			mav.addObject("errors", errors);
			mav = this.update(map,errors);
		}else {
			int updateResult = this.bookService.edit(map);
			//수정이 잘 되었으면 /detail?bookId=6 
			if(updateResult>0) {
				String bookId = map.get("bookId").toString();	//6
				mav.setViewName("redirect:/detail?bookId="+bookId);
			}else {
			//수정이 안되었으면 this.update(map) <- 이 클래스 안에 있는 update메소드
				mav = this.update(map,null);
			}//end inner if
		}//end outer if
		return mav;
	}
	
	// /delete => (post)bookId=6 => map로 변환
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public ModelAndView deletePost(
			@RequestParam Map<String, Object> map, ModelAndView mav) {
		
		int deleteResult = this.bookService.remove(map);
		if(deleteResult>0) {	//삭제 성공 : 목록으로 이동
			mav.setViewName("redirect:/list");
		}else {//삭제 실패 : 다시 상세 페이지로 이동
			String bookId = map.get("bookId").toString();
			mav.setViewName("redirect:/detail?bookId=" + bookId);
		}
		
		return mav;
	}
	///list?selOpt=제목&keyword=만화 => map {"selOpt":"title","keyword":"만화"}
	@RequestMapping(value="list")
	public ModelAndView list(ModelAndView mav,
			@RequestParam Map<String, Object> map) {
		
		List<Map<String, Object>> list = this.bookService.list(map);
		//결과 데이터를 뷰에 전달할 수 있도록 mav 객체에 넣어줌
		mav.addObject("data", list);
		mav.addObject("selOpt", map.get("selOpt"));
		mav.addObject("keyword", map.get("keyword"));
		// /book/list 뷰를 리턴하도록 해줌
		mav.setViewName("/book/list");
		
		return mav;
	}
	
	
	
	
	
	
}
