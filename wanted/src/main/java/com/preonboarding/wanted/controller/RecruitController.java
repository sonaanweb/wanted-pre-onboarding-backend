package com.preonboarding.wanted.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.preonboarding.wanted.domain.dto.request.RecruitRequestDto;
import com.preonboarding.wanted.service.RecruitService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/recruit")
public class RecruitController {

	private final RecruitService recruitService;
	
	// 등록
	@PostMapping
	public void saveRecruit(@RequestBody RecruitRequestDto RecruitRequestDto) {
		recruitService.saveRecruit(RecruitRequestDto);
	}
}
