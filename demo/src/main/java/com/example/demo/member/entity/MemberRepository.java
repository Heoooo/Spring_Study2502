package com.example.demo.member.entity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Integer> {

	//List
	public Page<Member> findAll(Pageable pageable);
	//Page: 페이징 처리를 위한 기본 클래스
	//Pageable: 인터페이스(페이징 관련)
	//PageRequest: 구현체(구현 클래스)->of()사용
}
