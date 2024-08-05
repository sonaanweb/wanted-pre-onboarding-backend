package com.preonboarding.wanted.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.preonboarding.wanted.domain.Recruit;

public interface RecruitRepository extends JpaRepository<Recruit, Long> {
	
	List<Recruit> findAll(); // 채용 공고 리스트 반환
	
    @Query("SELECT r FROM Recruit r "
            + "WHERE LOWER(r.stack) LIKE LOWER(CONCAT('%', :kw, '%')) "
            + "OR LOWER(r.position) LIKE LOWER(CONCAT('%', :kw, '%'))"
            + "OR LOWER(r.company.companyName) LIKE LOWER(CONCAT('%', :kw, '%'))")
    List<Recruit> findByKeyword(@Param("kw") String keyword);
}
