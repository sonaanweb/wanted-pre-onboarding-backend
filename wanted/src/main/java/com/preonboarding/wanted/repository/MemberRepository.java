package com.preonboarding.wanted.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.preonboarding.wanted.domain.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {

}
