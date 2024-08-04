package com.preonboarding.wanted.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.preonboarding.wanted.domain.Company;

public interface CompanyRepository extends JpaRepository<Company, Long>{

}
