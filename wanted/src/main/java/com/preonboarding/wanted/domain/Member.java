package com.preonboarding.wanted.domain;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
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
	private long id;
	
	private String memberName;
	
	@OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
	private List<Apply> applyList;
	
	@Builder
	public Member(String memberName) {
		this.memberName = memberName;
	}
}
