package kr.or.bio;

import java.util.List;

public class bioVo {
	private String receiptNo;
	private String receiptName;
	private BIOService bioService;
	private List<String> bioList;
	
	//기본 생성자
	public bioVo() {}
	
	//일반 생성자
	public bioVo(String receiptNo, String receiptName, BIOService bioService, List<String> bioList) {
		this.receiptNo = receiptNo;
		this.receiptName = receiptName;
		this.bioService = bioService;
		this.bioList = bioList;
	}

	public String getReceiptNo() {
		return receiptNo;
	}

	public void setReceiptNo(String receiptNo) {
		this.receiptNo = receiptNo;
	}

	public String getReceiptName() {
		return receiptName;
	}

	public void setReceiptName(String receiptName) {
		this.receiptName = receiptName;
	}

	public BIOService getBioService() {
		return bioService;
	}

	public void setBioService(BIOService bioService) {
		this.bioService = bioService;
	}

	public List<String> getBioList() {
		return bioList;
	}

	public void setBioList(List<String> bioList) {
		this.bioList = bioList;
	}
	
	public String sayHello() {
		return "bio " + receiptName;	//bio 화이자
	}
	
	public void print() {
		this.bioService.print(sayHello());
	}
	
}





