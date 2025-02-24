package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.member.dto.MemberJoinDTO;
import com.example.demo.member.service.MemberService;

@Controller
public class DemoController {
	
	@Autowired
	private MemberService memberService;
	
	
	
	@GetMapping("/member/memberJoin")
	public String hello() {
		return "member/memberJoin";
	}
	
	
	@PostMapping("/member/memberJoinOk")
	@ResponseBody
	public String insert(MemberJoinDTO memberJoinDTO) {
		
		Integer idx = memberService.insert(memberJoinDTO);
		
		return String.format("Member idx => %s", idx);
	}
	
	
	@GetMapping("/member/read/{idx}")
	@ResponseBody
	public Integer read(@PathVariable("idx") Integer idx ) {
		return idx;
	}
	
	
	@GetMapping("/member/read/{idx}/{id}")
	@ResponseBody
	public String findByIdxAndId(@PathVariable("idx") Integer idx, @PathVariable("id") String id) {
		return "{idx: "+idx+", id: "+id+"}";
	}
	
	
	//Model 사용
	@GetMapping("/member/intro1")
	public String intro1(Model model) {
		
		model.addAttribute("msg", "모델 이용해서 데이터 추가하고 뷰로 이동");
		model.addAttribute("msg2", "두 번째 데이터 추가");
		
		return "member/intro";
	}
	
	
	//ModelAndView 사용
	@GetMapping("/member/intro2")
	public ModelAndView intro2() {
		//mav 객체 생성
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("msg", "(mav)모델 이용해서 데이터 추가하고 뷰로 이동");
		mav.addObject("msg2", "(mav)두 번째 데이터 추가");
		mav.setViewName("member/intro");
		
		return mav;		
	}
	
	
	@GetMapping("/member/read/{idx}")
}
