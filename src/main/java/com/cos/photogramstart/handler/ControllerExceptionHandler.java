package com.cos.photogramstart.handler;

import java.util.Map;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.cos.photogramstart.handler.ex.CustomValidationException;

@RestController // 데이터를 리턴하는 컨트롤러
@ControllerAdvice // 모든 exception을 다 낚아챈다.
public class ControllerExceptionHandler {

	@ExceptionHandler(CustomValidationException.class) // RuntimeException이 발동하는 모든 Exception을 이 함수가 가로챈다. 
	public Map<String, String> vaildationException(CustomValidationException e) {
		return e.getErrorMap();
	}
}
