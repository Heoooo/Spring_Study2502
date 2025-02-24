package com.example.demo.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.member.dto.MemberJoinDTO;
import com.example.demo.member.entity.Member;
import com.example.demo.member.entity.MemberRepository;

@Service
public class MemberService {
	
	@Autowired
	private MemberRepository memberRepository;
		
	public Integer insert(MemberJoinDTO memberJoinDTO) {
		
		Member member = new Member();
		
		member.setId(memberJoinDTO.getId());
		member.setPw(memberJoinDTO.getPw());
		
		memberRepository.save(member);
		
		return member.getIdx();
	}
	

}
