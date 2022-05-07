package com.cos.photogramstart.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.cos.photogramstart.handler.ex.CustomApiException;
import com.cos.photogramstart.handler.ex.CustomValidationApiException;
import com.cos.photogramstart.handler.ex.CustomValidationException;
import com.cos.photogramstart.util.Script;
import com.cos.photogramstart.web.dto.CMRespDto;

@RestController // 데이터를 리턴하는 컨트롤러
@ControllerAdvice // 모든 exception을 다 낚아챈다.
public class ControllerExceptionHandler {

	@ExceptionHandler(CustomValidationException.class) // RuntimeException이 발동하는 모든 Exception을 이 함수가 가로챈다. 
	public String vaildationException(CustomValidationException e) {
		// CMRespDto, Script 비교
		// 1. 클라이언트에게 응답할 때는 Script 좋음.
		// 2. Ajax 통신 - CMRespDto
		// 3. Android 통신 - CMRespDto
		
		if(e.getErrorMap() == null) {
			return Script.back(e.getMessage());
		}else {
			return Script.back(e.getErrorMap().toString());
		}
		
	}
	
	// 데이터로 응답
	@ExceptionHandler(CustomValidationApiException.class) // RuntimeException이 발동하는 모든 Exception을 이 함수가 가로챈다. 
	public ResponseEntity<CMRespDto<?>> vaildationApiException(CustomValidationApiException e) {
		return new ResponseEntity<>(new CMRespDto<>(-1, e.getMessage(), e.getErrorMap()), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(CustomApiException.class) // RuntimeException이 발동하는 모든 Exception을 이 함수가 가로챈다. 
	public ResponseEntity<CMRespDto<?>> apiException(CustomApiException e) {
		return new ResponseEntity<>(new CMRespDto<>(-1, e.getMessage(), null), HttpStatus.BAD_REQUEST);
	}
}
