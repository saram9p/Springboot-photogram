package com.cos.photogramstart.web.dto.user;

import com.cos.photogramstart.domain.user.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserProfileDto {
	private boolean pageOwnerState; // 이 페이지의 주인인지 아닌지에 대한 여부 데이터.
	private int imageCount;
	private boolean subscribeState; // 구독 상태
	private int subscribeCount; // 구독자 수
	private User user;
}
