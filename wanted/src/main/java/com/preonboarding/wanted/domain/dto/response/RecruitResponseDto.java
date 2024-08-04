package com.preonboarding.wanted.domain.dto.response;

import com.preonboarding.wanted.domain.Recruit;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RecruitResponseDto {

	private Long recruitId;
	private Long companyId;
	private String position;
	private int bonus;
	private String content;
	private String stack;
	
	// 채용 게시글 반환 데이터 필드
	public RecruitResponseDto(Recruit recruit) {
		this.recruitId = recruit.getId();
		this.companyId = recruit.getCompany().getId();
		this.position = recruit.getPosition();
		this.bonus = recruit.getBonus();
		this.content = recruit.getContent();
		this.stack = recruit.getStack();
	}
}
