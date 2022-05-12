package com.cos.photogramstart.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.photogramstart.config.auth.PrincipalDetails;
import com.cos.photogramstart.domain.image.Image;
import com.cos.photogramstart.domain.image.ImageRepository;
import com.cos.photogramstart.web.dto.image.ImageUploadDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ImageService {
	
	@Value("${file.path}")
	private String uploadFolder;
	
	private final ImageRepository imageRepository;
	
	@Transactional(readOnly = true) // 영속성 컨텍스트 변경 감지를 해서 ,더티체킹, flush(반영) X
	public Page<Image> 이미지스토리(int principalId, Pageable pageable){
		Page<Image> images = imageRepository.mStory(principalId, pageable);
		
		// 2(cos) 로그인 (2번이 구독하고 있는 이미지들을 가지고 옴)
		// for 문을 돌려서 이미지를 하나씩 뽑아낸다.
		// 첫번째 이미지를 좋아요하고 있는 정보를 다 가져와서 
		// 2이 좋아요한건지 그것만 찾아내면 된다.
		// images에 좋아요 상태 담기
		images.forEach((image)->{
			System.out.println(image.getLikes());
			image.getLikes().forEach((like)->{
				if(like.getUser().getId() == principalId) { // 해당 이미지에 좋아요한 사람들을 찾아서 현재 로그인한 사람이 좋아요 한것인지 비교
					like.setLikeState(true);
				}
			});
			
		});
		
		return images;
	}
	
	@Transactional
	public void 사진업로드(ImageUploadDto imageUploadDto, PrincipalDetails principalDetails) {
		UUID uuid = UUID.randomUUID(); // uuid
		String imageFileName = uuid + "_" + imageUploadDto.getFile().getOriginalFilename(); // 1.jpg
		System.out.println("이미지 파일이름 : " + imageFileName);
		
		Path imageFilePath = Paths.get(uploadFolder + imageFileName);
		
		// 통신, I/O(하드디스크에 기록, 읽을때) -> 예외가 발생할 수 있다.
		try {
			Files.write(imageFilePath, imageUploadDto.getFile().getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// image 테이블에 저장
		Image image = imageUploadDto.toEntity(imageFileName, principalDetails.getUser()); // 5e3beb68-bfdb-4d45-852b-00eba6088b73_2.jpg
		imageRepository.save(image);
		
		//System.out.println(imageEntity);
		
	}
}
