package com.age.back.jms.message;

@SuppressWarnings("serial")
public class IdObject implements Id<String>{
	private String id;
	
	public IdObject(){}
	
	public IdObject(String id){
		this.id = id;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

}
