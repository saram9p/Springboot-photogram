package com.cos.photogramstart.domain.likes;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import com.cos.photogramstart.domain.image.Image;
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
						name="likes_uk", // unique 제약조건
						columnNames = {"imageId", "userId"} // 실제 데이터베이스의 컬럼명을 적어야 한다, 두 개를 복합적으로 unique 건다
				)
		}
)
public class Likes {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	// 무한 참조됨
	@JoinColumn(name = "imageId")
	@ManyToOne
	private Image image; // 어떤 이미지를
	
	// 오류가 터지고 나서 잡아봅시다.
	@JoinColumn(name = "userId")
	@ManyToOne
	private User user;  // 누가
	
	private LocalDateTime createDate;
	
	@Transient // DB에 칼럼이 만들어지지 않는다.
	private boolean likeState;
	
	@PrePersist // DB에 INSERT 되기 직전에 실행
	public void createDate() {
		this.createDate = LocalDateTime.now();
	}
}
