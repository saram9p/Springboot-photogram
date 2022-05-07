package com.cos.photogramstart.domain.subscribe;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.cos.photogramstart.domain.user.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor // 전체 생성자
@NoArgsConstructor // 빈 생성자
@Data
@Entity // DB에 테이블을 생성
@Table(
		uniqueConstraints = {
				@UniqueConstraint(
						name="subscribe_uk", // unique 제약조건
						columnNames = {"fromUserId", "toUserId"} // 실제 데이터베이스의 컬럼명을 적어야 한다, 두 개를 복합적으로 unique 건다
				)
		}
)
public class Subscribe {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private  int id;
	
	@JoinColumn(name = "fromUserId") // 이렇게 컬럼명 만들어! 니 맘대로 만들지 말고!!
	@ManyToOne
	private User fromUser; // 구독하는 유저
	
	@JoinColumn(name = "toUserId")
	@ManyToOne
	private User toUser; // 구독받는 유저
	
	private LocalDateTime createDate;
	
	@PrePersist // DB에 INSERT 되기 직전에 실행
	public void createDate() {
		this.createDate = LocalDateTime.now();
	}
}
