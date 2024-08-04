package com.preonboarding.wanted.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.preonboarding.wanted.domain.Recruit;
import com.preonboarding.wanted.domain.dto.request.RecruitRequestDto;
import com.preonboarding.wanted.domain.dto.response.RecruitListResponseDto;
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
	
	// 조회
    @GetMapping
    public List<RecruitListResponseDto> getRecruitList() {
        List<Recruit> recruits = recruitService.getAllRecruits();
        return recruits.stream()
                       .map(RecruitListResponseDto::new)
                       .collect(Collectors.toList());
    }

	
    // 수정
   
	
	// 삭제
	@DeleteMapping("/{recruitId}")
	public void deleteRecruit(@PathVariable("recruitId") Long recruitId) {
		recruitService.deleteRecruit(recruitId);
	}
	
}
