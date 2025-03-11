package com.example.demo.member.dto;

import com.example.demo.member.entity.Member;

public class MemberUpdateDTO {
	
	private Integer idx;
	private String id;
	private String pw;
	
	
	public Member change(Member member) {
		member.setId(this.id);
		member.setIdx(this.idx);
		member.setPw(this.pw);
		
		return member;
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
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	
	
}
