package com.preonboarding.wanted.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.preonboarding.wanted.domain.Apply;
import com.preonboarding.wanted.domain.Member;
import com.preonboarding.wanted.domain.Recruit;
import com.preonboarding.wanted.domain.dto.request.ApplyRequestDto;
import com.preonboarding.wanted.repository.ApplyRepository;
import com.preonboarding.wanted.repository.MemberRepository;
import com.preonboarding.wanted.repository.RecruitRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ApplyService {
	
	// apply / member / recruit
	
	private final ApplyRepository applyRepository;
	private final MemberRepository memberRepository;
	private final RecruitRepository recruitRepository;
	
	@Transactional
	public void applyToRecruit(ApplyRequestDto request) {
		
		// 유효성
        Member member = memberRepository.findById(request.getMemberId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "존재하지 않는 사용자입니다."));
        Recruit recruit = recruitRepository.findById(request.getRecruitId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "존재하지 않는 게시글입니다."));

        
        // 중복지원 검증
        if (member.AppliedTo(recruit)) { //true=중복 false=save
            throw new IllegalArgumentException("이미 지원한 채용공고입니다.");
        } else {
            Apply apply = Apply.builder()
                    .member(member)
                    .recruit(recruit)
                    .build();

            applyRepository.save(apply); // 완료
        }
    }
}
