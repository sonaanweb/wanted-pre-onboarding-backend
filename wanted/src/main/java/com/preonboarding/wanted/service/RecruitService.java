package com.preonboarding.wanted.service;

import org.springframework.stereotype.Service;

import com.preonboarding.wanted.domain.Company;
import com.preonboarding.wanted.domain.Recruit;
import com.preonboarding.wanted.domain.dto.request.RecruitRequestDto;
import com.preonboarding.wanted.repository.CompanyRepository;
import com.preonboarding.wanted.repository.RecruitRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class RecruitService {

	private final RecruitRepository recruitRepository;
	private final CompanyRepository companyRepository;
	
	// 공고 등록 (company 임시 데이터)
	@Transactional
	public void saveRecruit(RecruitRequestDto request) {
		
		// company 유무
		Company company = companyRepository.findById(request.getCompanyId())
	            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회사입니다"));

	    Recruit recruit = Recruit.builder()
	            .company(company)
	            .position(request.getPosition())
	            .bonus(request.getBonus())
	            .content(request.getContent())
	            .stack(request.getStack())
	            .build();

	    recruitRepository.save(recruit);
		
	}
	
	
	// 공고 수정
	
	
	// 공고 삭제
	public void deleteRecruit(Long recruitId) {
	    Recruit recruit = recruitRepository.findById(recruitId)
	    		.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글입니다"));
	    recruitRepository.delete(recruit);
	}
	
}
