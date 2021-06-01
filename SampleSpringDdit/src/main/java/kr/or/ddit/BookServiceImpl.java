package kr.or.ddit;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//서비스 클래스? 비즈니스 클래스. 스프링 MVC 구조에서 Controller와 DAO를 연결해줌
//Service 어노테이션을 붙여서 스프링에 서비스 클래스임을 알려줌
//서비스 레이어는 interface(BookService)와 class(BookServiceImpl)를 함께 사용
//스프링은 직접 클래스를 생성하는 것을 싫어함. interface를 통해 접근하는 것을 좋아함.
@Service
public class BookServiceImpl implements BookService{
	//데이터베이스에 접근하기 위해 BookDao 인스턴스를 주입받음
	@Autowired
	BookDao bookDao;
	@Override
	public int create(Map<String, Object> map) {
		int affectRowCount = this.bookDao.insert(map);
		
		return affectRowCount;
	}
	//Controller에서 전달 받은 map을 dao로 파라미터로써 보냄
	@Override
	public Map<String, Object> detail(Map<String, Object> map){
		return this.bookDao.selectDetail(map);
	}
	
	@Override
	public BOOKVo detailVo(Map<String, Object> map) {
		return this.bookDao.selectDetailVo(map);
	}
	/*
	 * {"bookId":6,"title":"만화책2","category":"만화2","price","20000"}
	 */
	@Override
	public int edit(Map<String, Object> map) {
		return this.bookDao.edit(map);
	}
	@Override
	public int remove(Map<String, Object> map) {
		return this.bookDao.remove(map);	//{"bookId":6}
	}
	//{"selOpt":"제목","keyword":"만화"}
	public List<Map<String, Object>> list(Map<String, Object> map){
		return this.bookDao.selectList(map);
	}
}





