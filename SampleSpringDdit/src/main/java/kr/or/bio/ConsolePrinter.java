package kr.or.bio;

public class ConsolePrinter implements BIOService {

	@Override
	public void print(String sayHello) {
		System.out.println(sayHello);
	}

}
