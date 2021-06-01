package kr.or.ddit;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

//Repository 어노테이션 : 데이터에 접근하는 클래스임을 명시
//				스프링이 인지를 해서 자바 빈으로 등록해서 관리함.
@Repository
public class BookDao {
	//매퍼 XML을 실행시키기 위해 SqlSessionTemplate 객체를 멤버 변수로 선언
	//new 키워드를 통해 직접 생성하지 않고, 대신 의존성 주입을 통해 주입받음.
	//의존성 주입 : Dependency Injection(DI). 
	//			BookDao객체에 sqlSessionTemplate 객체를 넣음
	//			필드 인젝션
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	public int insert(Map<String, Object> map) {
		//sqlSessionTemplate 객체는 tomcat이 시작할 때 root-context.xml
		//을 미리 읽어서 객체를 인스턴스화 해 두었음.
		//.insert("namespace.id", 파라미터)
		//book.insert : 매퍼 쿼리 이름
		return this.sqlSessionTemplate.insert("book.insert", map);
	}
	
	public Map<String, Object> selectDetail(Map<String, Object> map){
		//selectOne() : 1 행 결과 가져올 때 사용
		//				0 행 =>  null 반환
		//				여러행 => TooManyResultsException 예외 던짐
		return this.sqlSessionTemplate.selectOne("book.select_detail",map);
	}

	public BOOKVo selectDetailVo(Map<String, Object> map) {
		//selectOne() : 1 행 결과 가져올 때 사용
		//				0 행 =>  null 반환
		//				여러행 => TooManyResultsException 예외 던짐
		return this.sqlSessionTemplate.selectOne("book.select_detailVo",map);
	}

	/*
	 * {"bookId":6,"title":"만화책2","category":"만화2","price","20000"}
	 */
	public int edit(Map<String, Object> map) {
		return this.sqlSessionTemplate.update("book.edit", map);
	}
	//{"bookId":6}
	public int remove(Map<String, Object> map) {
		return this.sqlSessionTemplate.delete("book.remove", map);
	}
	//sqlSessionTemplate.selectList() : 결과 집합 목록을 반환
	//결과 집합 타입인 Map<String, Object>의 목록 List 타입으로 읽을 수 있음
	public List<Map<String, Object>> selectList(Map<String, Object> map){
		return this.sqlSessionTemplate.selectList("book.select_list", map );
	}
}




