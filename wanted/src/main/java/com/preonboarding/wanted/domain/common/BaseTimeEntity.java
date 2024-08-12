package com.preonboarding.wanted.domain.common;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;

@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
@Getter
public class BaseTimeEntity {
	
	// 생성
	@CreatedDate
	private LocalDateTime createdDate;
	
	// 수정
	@LastModifiedDate
	private LocalDateTime modifiedDate;

}
