package com.preonboarding.wanted.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.preonboarding.wanted.domain.Company;

public interface CompanyRepository extends JpaRepository<Company, Long>{

}
