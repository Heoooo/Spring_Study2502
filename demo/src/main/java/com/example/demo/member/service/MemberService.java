package com.example.demo.member.service;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.member.dto.MemberJoinDTO;
import com.example.demo.member.dto.MemberUpdateDTO;
import com.example.demo.member.dto.MemberUpdateUiDTO;
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
	
	
	public Member read(Integer idx) throws NoSuchElementException{
		
		Member member = memberRepository.findById(idx).orElseThrow();
		
		return member;
	}
	
	
	public MemberUpdateUiDTO updateUi(Integer idx) throws NoSuchElementException {
		
		Member member = memberRepository.findById(idx).orElseThrow();
		
		return MemberUpdateUiDTO.makeMember(member);
	}
	
	
	public void update(MemberUpdateDTO memberUpdateDTO) throws NoSuchElementException{
		
		Member member = memberRepository.findById(memberUpdateDTO.getIdx()).orElseThrow();
		
		member = memberUpdateDTO.change(member);
		
		memberRepository.save(member);
	}
	
	/*
	public String delete(Integer idx) throws NoSuchElementException{
		
		Member member = memberRepository.findById(idx).orElseThrow();
		
		memberRepository.delete(member);
		
		return "/member/memberJoin";
	}
	*/
	
	
	public String delete(Integer idx, Integer userPw) throws NoSuchElementException {
		
		Member member = memberRepository.findById(idx).orElseThrow();
		
		if (member.getPw().equals(userPw)) {
			System.out.println("비밀번호가 맞습니다.");
			memberRepository.delete(member);
			return "/member/delete-success";
		}
		else {
			System.out.println("비밀번호가 틀립니다.");
			return "/member/delete-fail";
		}
	}
	
	
	//DB:List
	public Page<Member> memberList(Integer page) {
		
		Pageable pageable = null;
		
		pageable = PageRequest.of(page, 2);
		
		return memberRepository.findAll(pageable);
	}
}
