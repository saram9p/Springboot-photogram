package com.cos.photogramstart.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.photogramstart.domain.subscribe.SubscribeRepository;
import com.cos.photogramstart.handler.ex.CustomApiException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class SubscribeService {

	private final SubscribeRepository subscribeRepository;
	
	@Transactional
	public void 구독하기(int fromUserId, int toUserId) {
		try {
			subscribeRepository.mSubscribe(fromUserId, toUserId); // m은 내가 만들었다는 것
		} catch (Exception e) {
			throw new CustomApiException("이미 구독을 하였습니다");
		}
		
	}
	
	@Transactional
	public void 구독취소하기(int fromUserId, int toUserId) { // 삭제는 실패해도 오류가 안 난다.
		subscribeRepository.mUnSubscribe(fromUserId, toUserId);
	}
}
