package com.preonboarding.wanted.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.preonboarding.wanted.domain.Recruit;
import com.preonboarding.wanted.domain.dto.request.RecruitRequestDto;
import com.preonboarding.wanted.domain.dto.response.RecruitListResponseDto;
import com.preonboarding.wanted.domain.dto.response.RecruitResponseDto;
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
	
	// 리스트 조회
    @GetMapping
    public List<RecruitListResponseDto> getRecruitList() {
        List<Recruit> recruits = recruitService.getAllRecruits();
        return recruits.stream()
                       .map(RecruitListResponseDto::new)
                       .collect(Collectors.toList());
    }

    // 상세 페이지 조회
    @GetMapping("/{recruitId}")
    public ResponseEntity<RecruitResponseDto> getRecruitDetail(@PathVariable("recruitId") Long recruitId) {
        Recruit recruit = recruitService.getRecruitDetail(recruitId);
        
        if (recruit == null) { // 존재하지 않는 공고일 시
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "존재하지 않는 채용공고입니다. " + recruitId);
        }
        return ResponseEntity.ok(new RecruitResponseDto(recruit));
    }
    
    // 수정
   
	
	// 삭제
	@DeleteMapping("/{recruitId}")
	public void deleteRecruit(@PathVariable("recruitId") Long recruitId) {
		recruitService.deleteRecruit(recruitId);
	}
	
}
