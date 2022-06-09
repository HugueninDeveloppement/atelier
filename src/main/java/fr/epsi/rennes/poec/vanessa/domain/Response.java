package fr.epsi.rennes.poec.vanessa.domain;

public class Response <T>{
	
	private T data;
	private boolean success = true;
	
	
	public Response(T data) {
		super();
		this.data = data;
	}
	
	public Response(boolean success) {
		super();
		this.success = success;
	}
	
	public T getData() {
		return data;
	}
	
	public void setData(T data) {
		this.data = data;
	}
	
	public boolean isSuccess() {
		return success;
	}
	
	public void setSuccess(boolean success) {
		this.success = success;
	}
	
}
