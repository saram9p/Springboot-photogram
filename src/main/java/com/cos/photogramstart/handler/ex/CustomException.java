package com.cos.photogramstart.handler.ex;

public class CustomException extends RuntimeException {

	// 객체를 구분할 때 씀, JVM한테 중요함
	private static final long serialVersionUID = 1L;
	
	public CustomException(String message) {
		super(message); // message를 부모한테 던짐
	}
}
