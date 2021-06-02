package kr.or.bio;

public class BIOServiceImpl implements BIOService {
	//Dao(Mapper) : CRUD 대신에 사용함
	private StringBuffer buffer = new StringBuffer();
	
	@Override
	public void print(String sayHello) {
		this.buffer.append(sayHello);
	}
	//buffer에 담긴 정보를 문자열로 리턴해줌
	public String toString() {
		return this.buffer.toString();
	}

}
