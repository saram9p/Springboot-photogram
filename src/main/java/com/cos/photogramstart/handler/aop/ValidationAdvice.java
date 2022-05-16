package com.cos.photogramstart.handler.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component // RestController, Service 모든 것들이 Component를 상속해서 만들어져 있음.
@Aspect // aop 처리를 할 수 있는 핸들러로 만듬
public class ValidationAdvice { // Advice : 공통기능

	// @After : Controller 에서 어떤 함수가 끝나고나서 실행됨
	// @Before : Controller 에서 어떤 함수가 실행될때 실행직전에 실행됨
	// @Around : Controller 에서 어떤 함수 전에도 실행되고 끝날때까지 관여하고 싶을 때 사용
	
	@Around("execution(* com.cos.photogramstart.web.api.*Controller.*(..))") // web 안에 api에 있는 모든 Controller로 끝나는 모든 것들 안에 있는 모든 메서드의 파라메터가 뭐든 상관없는 것(모든 함수가 실행될 때 작동한다.) 
	public Object apiAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		
		System.out.println("web api 컨트롤러 ======================");
		// proceedingJoinPoint => profile 함수의 모든 곳에 접근할 수 있는 변수
		// profile 함수보다 먼저 실행
		
		return proceedingJoinPoint.proceed(); // profile 함수가 실행됨.
	}
	
	@Around("execution(* com.cos.photogramstart.web.*Controller.*(..))")
	public Object advice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		
		System.out.println("web 컨트롤러 ======================");
		return proceedingJoinPoint.proceed();
	}
}
