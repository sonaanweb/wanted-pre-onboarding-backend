package com.preonboarding.wanted.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.preonboarding.wanted.domain.Recruit;

public interface RecruitRepository extends JpaRepository<Recruit, Long> {
	
	List<Recruit> findAll(); // 채용 공고 리스트 반환
	
}
