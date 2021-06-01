package kr.or.ddit;

import java.util.List;
import java.util.Map;

public interface BookService {
	//메소드 시그니처
	int create(Map<String, Object> map);

	Map<String, Object> detail(Map<String, Object> map);

	BOOKVo detailVo(Map<String, Object> map);

	int edit(Map<String, Object> map);

	int remove(Map<String, Object> map);
	
	List<Map<String, Object>> list(Map<String, Object> map);
}






