package com.preonboarding.wanted;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.preonboarding.wanted.domain.Apply;
import com.preonboarding.wanted.domain.Company;
import com.preonboarding.wanted.domain.Member;
import com.preonboarding.wanted.domain.Recruit;
import com.preonboarding.wanted.domain.dto.request.ApplyRequestDto;
import com.preonboarding.wanted.repository.ApplyRepository;
import com.preonboarding.wanted.repository.CompanyRepository;
import com.preonboarding.wanted.repository.MemberRepository;
import com.preonboarding.wanted.repository.RecruitRepository;
import com.preonboarding.wanted.service.ApplyService;

@SpringBootTest
@Transactional
public class ApplyTest {
	
	@Autowired
	private CompanyRepository companyRepository;
	
	@Autowired
	private RecruitRepository recruitRepository;
	
	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	private ApplyRepository applyRepository;
	
	@Autowired
	private ApplyService applyService;
	

	@Test
    void 지원하기() {

        // given
        Member member = new Member("김코딩");
        memberRepository.save(member);

        Company company = new Company("원티드랩");
        companyRepository.save(company);

        Recruit recruit = Recruit.builder()
                .company(company)
                .position("백엔드")
                .bonus(100000)
                .content("원티드랩에서 백엔드 주니어 개발자를 채용합니다. 자격요건은..")
                .stack("Python")
                .build();
        recruitRepository.save(recruit);

        ApplyRequestDto request = ApplyRequestDto.builder()
                .memberId(member.getId())
                .recruitId(recruit.getId())
                .build();

        // when
        applyService.applyToRecruit(request);

        // then
        Apply apply = applyRepository.findAll().get(0);
        assertThat(apply).isNotNull();
        assertThat(apply.getMember()).isEqualTo(member);
        assertThat(apply.getRecruit()).isEqualTo(recruit);
    }
}
