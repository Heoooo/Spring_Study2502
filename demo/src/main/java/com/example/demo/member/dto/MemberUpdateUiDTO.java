package com.example.demo.member.dto;

import com.example.demo.member.entity.Member;

public class MemberUpdateUiDTO {
	
	private Integer idx;
	private String id;
	private Integer pw;
	
	
	public MemberUpdateUiDTO fromMember(Member member) {
		this.idx = member.getIdx();
		this.id = member.getId();
		this.pw = member.getPw();
		
		return this;
	}
	
	
	public static MemberUpdateUiDTO makeMember(Member member) {
		
		MemberUpdateUiDTO memberUpdateUiDTO = new MemberUpdateUiDTO();
		
		memberUpdateUiDTO.fromMember(member);
		return memberUpdateUiDTO;
	}
	
	
	public Integer getIdx() {
		return idx;
	}
	public void setIdx(Integer idx) {
		this.idx = idx;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Integer getPw() {
		return pw;
	}
	public void setPw(Integer pw) {
		this.pw = pw;
	}
	
}
