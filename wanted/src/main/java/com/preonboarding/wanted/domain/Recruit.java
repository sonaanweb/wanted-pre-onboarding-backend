package com.preonboarding.wanted.domain;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class Recruit {
	
	// Recruit: 채용공고
	
	@Id
	@Column(name = "recruit_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String position;
	
	@Column(nullable = false)
	private int bonus;
	
	@Column(nullable = false)
	private String content;
	
	@Column(nullable = false)
	private String stack;
	
	/*
	 * private LocalDateTime createDate;
	 * private LocalDateTime modifyDate;
	 */
	
	// n:1 - 회사는 여러개의 채용공고 등록가능
	@ManyToOne
	@JoinColumn(name = "company_id")
	private Company company;
	
	@OneToMany(mappedBy = "recruit", cascade = CascadeType.REMOVE)
	private List<Apply> applyList;
	
	@Builder
	public Recruit(Company company, String position, int bonus, String content, String stack) {		
		this.company = company;
		this.position = position;
		this.bonus = bonus;
		this.content = content;
		this.stack = stack;
	}
}
