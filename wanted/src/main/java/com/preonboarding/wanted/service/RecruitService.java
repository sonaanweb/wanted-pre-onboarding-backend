package com.preonboarding.wanted.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.preonboarding.wanted.domain.Company;
import com.preonboarding.wanted.domain.Recruit;
import com.preonboarding.wanted.domain.dto.request.RecruitRequestDto;
import com.preonboarding.wanted.repository.CompanyRepository;
import com.preonboarding.wanted.repository.RecruitRepository;

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
	@Transactional
    public void updateRecruit(Long recruitId, RecruitRequestDto request) {
        Recruit recruit =  recruitRepository.findById(recruitId)
        		.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글입니다"));
        
        recruit.updateRecruit(
        		request.getPosition(), 
        		request.getBonus(), request.getContent(), 
        		request.getStack());
        
        this.recruitRepository.save(recruit);
    }
	
	// 공고 삭제
	@Transactional
	public void deleteRecruit(Long recruitId) {
	    Recruit recruit = recruitRepository.findById(recruitId)
	    		.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글입니다"));
	    recruitRepository.delete(recruit);
	}
	
	// 공고 리스트 조회
	@Transactional(readOnly = true)
    public List<Recruit> getAllRecruits() {
        return recruitRepository.findAll();
    }
	
	// 검색
    @Transactional(readOnly = true)
    public List<Recruit> searchRecruits(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return List.of(); // 빈 리스트 반환
        }
        return recruitRepository.findByKeyword(keyword.trim().toLowerCase());
    }
	
	// 공고 상세페이지
	public Recruit getRecruitDetail(Long recruitId) {
		return recruitRepository.findById(recruitId).orElse(null);
	}

	
}
