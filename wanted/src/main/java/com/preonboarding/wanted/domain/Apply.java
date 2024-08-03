package com.preonboarding.wanted.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class Apply {

	@Id
	@Column(name = "apply_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	// 중복 지원 불가
	@ManyToOne
	@JoinColumn(name = "recruit_id", nullable = false)
	private Recruit recruit;

	@ManyToOne
	@JoinColumn(name = "member_id", nullable = false)
	private Member member;
	// ------------
	
	public Apply (Recruit recruit, Member member) {
		this.member = member;
		this.recruit = recruit;
	}
}
