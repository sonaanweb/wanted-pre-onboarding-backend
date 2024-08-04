package com.preonboarding.wanted.domain.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Builder
public class RecruitRequestDto {
	
	private Long companyId;
	private String position;
	private int bonus;
	private String content;
	private String stack;

	public RecruitRequestDto(Long companyId, String position, int bonus, String content, String stack) {
		this.companyId = companyId;
		this.position = position;
		this.bonus = bonus;
		this.content = content;
		this.stack = stack;
	}
}
