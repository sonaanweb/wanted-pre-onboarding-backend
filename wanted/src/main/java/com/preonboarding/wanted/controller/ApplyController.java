package com.preonboarding.wanted.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.preonboarding.wanted.domain.dto.request.ApplyRequestDto;
import com.preonboarding.wanted.service.ApplyService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/apply")
@RequiredArgsConstructor
public class ApplyController {

	private final ApplyService applyService;
	
	@PostMapping
	public void apply(@RequestBody ApplyRequestDto request) {
		applyService.applyToRecruit(request);
	}
}
