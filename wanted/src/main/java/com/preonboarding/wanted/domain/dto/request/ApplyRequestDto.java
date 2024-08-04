package com.preonboarding.wanted.domain.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Builder
public class ApplyRequestDto {

	private Long memberId;
	private Long recruitId;
	
	public ApplyRequestDto(Long memberId, Long recruitId) {
		this.memberId = memberId;
		this.recruitId = recruitId;
	}
}
