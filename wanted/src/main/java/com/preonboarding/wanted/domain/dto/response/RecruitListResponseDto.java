package com.preonboarding.wanted.domain.dto.response;

import com.preonboarding.wanted.domain.Recruit;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RecruitListResponseDto {
	
	// 리스트 content 제외
	
	private Long recruitId;
    private Long companyId;
    private String companyName;
    private String position;
    private int bonus;
    private String stack;

    public RecruitListResponseDto(Recruit recruit) {
        this.recruitId = recruit.getId();
        this.companyId = recruit.getCompany().getId();
        this.companyName = recruit.getCompany().getCompanyName();
        this.position = recruit.getPosition();
        this.bonus = recruit.getBonus();
        this.stack = recruit.getStack();
    }

}
