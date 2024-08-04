package com.preonboarding.wanted;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.preonboarding.wanted.domain.Company;
import com.preonboarding.wanted.domain.Recruit;
import com.preonboarding.wanted.domain.dto.request.RecruitRequestDto;
import com.preonboarding.wanted.repository.CompanyRepository;
import com.preonboarding.wanted.repository.RecruitRepository;

@SpringBootTest
@Transactional
public class RecruitTest {
	
	@Autowired
	private RecruitRepository recruitRepository;
	
	@Autowired
	private CompanyRepository companyRepository;
	
	@Test
	public void 채용등록()throws Exception{
		
		// given
		Company company = new Company("원티드랩");
		companyRepository.save(company);
		
		// when
		RecruitRequestDto recruitRequestDto = RecruitRequestDto.builder()
				.companyId(company.getId())
				.position("백엔드")
				.bonus(100000)
				.content("원티드랩에서 백엔드 주니어 개발자를 채용합니다. 자격요건은..")
				.stack("Python")
				.build();

		Recruit recruit = Recruit.builder()
				.company(company)
                .position(recruitRequestDto.getPosition())
                .bonus(recruitRequestDto.getBonus())
                .content(recruitRequestDto.getContent())
                .stack(recruitRequestDto.getStack())
                .build();
		
		 recruitRepository.save(recruit);
		 
		// then
        Recruit savedRecruit = recruitRepository.findById(recruit.getId()).orElse(null);

        assertThat(savedRecruit).isNotNull();
        assertThat(savedRecruit.getPosition()).isEqualTo(recruitRequestDto.getPosition());
        assertThat(savedRecruit.getBonus()).isEqualTo(recruitRequestDto.getBonus());
        assertThat(savedRecruit.getContent()).isEqualTo(recruitRequestDto.getContent());
        assertThat(savedRecruit.getStack()).isEqualTo(recruitRequestDto.getStack());
	}
	
	@Test
	public void 채용삭제()throws Exception {

        // given
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

        // when
        recruitRepository.delete(recruit);

        // then
        Recruit deletedRecruit = recruitRepository.findById(recruit.getId()).orElse(null);

        assertThat(deletedRecruit).isNull(); // 삭제 확인 추가
	}
	
    @Test
    public void 채용리스트() throws Exception {
    	
        // given
        Company company = new Company("원티드랩");
        companyRepository.save(company);

        Recruit recruit1 = Recruit.builder()
                .company(company)
                .position("백엔드")
                .bonus(100000)
                .content("원티드랩에서 백엔드 주니어 개발자를 채용합니다. 자격요건은..")
                .stack("Python")
                .build();

        Recruit recruit2 = Recruit.builder()
                .company(company)
                .position("프론트엔드")
                .bonus(200000)
                .content("원티드랩에서 프론트엔드 주니어 개발자를 채용합니다. 자격요건은..")
                .stack("Java")
                .build();

        recruitRepository.save(recruit1);
        recruitRepository.save(recruit2);

        // when
        List<Recruit> recruitList = recruitRepository.findAll();

        // then
        assertThat(recruitList).isNotEmpty();
        assertThat(recruitList).hasSize(2); // 사이즈
        assertThat(recruitList).extracting("position").contains("백엔드", "프론트엔드"); // 키워드 확인
    }
	
}
