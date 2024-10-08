package com.preonboarding.wanted.domain;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class Member {

	// Member: 지원자
	
	@Id
	@Column(name = "member_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private String memberName;
	
	@OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
	private List<Apply> applyList = new ArrayList<>();
	
	public Member(String memberName) {
		this.memberName = memberName;
	}
	
	// 중복지원 검증
	public boolean AppliedTo(Recruit recruit) {
		return applyList.stream().anyMatch(apply -> apply.getRecruit().equals(recruit));
	}
	
}
