package zw.co.mitech.mtutor.util;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="mt")
public class TxtXml {
	
	private String id;
	
	private String msg;
	
	private String sessionstate;
	
	private String customerstate;
	
	

	public TxtXml() {
		super();
	}
	
	
	public TxtXml(Txt txt) {
		
		this.setId(txt.getId());
		this.setMsg(txt.getMessage());
		this.setSessionstate(txt.getSessionState());
		this.setCustomerstate(txt.getCustomerState());
		
	}
	

	public String getId() {
		return id;
	}

	@XmlElement
	public void setId(String id) {
		this.id = id;
	}

	public String getMsg() {
		return msg;
	}

	@XmlElement
	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getSessionstate() {
		return sessionstate;
	}

	@XmlElement
	public void setSessionstate(String sessionstate) {
		this.sessionstate = sessionstate;
	}

	public String getCustomerstate() {
		return customerstate;
	}

	@XmlElement
	public void setCustomerstate(String customerstate) {
		this.customerstate = customerstate;
	} 
	
	
	

}
