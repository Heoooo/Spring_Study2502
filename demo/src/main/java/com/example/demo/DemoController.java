package com.example.demo;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.member.dto.MemberJoinDTO;
import com.example.demo.member.dto.MemberUpdateDTO;
import com.example.demo.member.dto.MemberUpdateUiDTO;
import com.example.demo.member.entity.Member;
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
	
	
	/*
	@GetMapping("/member/read/{idx}")
	@ResponseBody
	public Integer read(@PathVariable("idx") Integer idx ) {
		return idx;
	}
	*/
	
	
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
	public ModelAndView read(@PathVariable("idx") Integer idx) {
		
		ModelAndView mav = new ModelAndView();
		
		try {
			Member member = memberService.read(idx);
			
			mav.addObject("member", member);
			mav.setViewName("member/read");
		}
		catch(NoSuchElementException ex) {
			mav.setStatus(HttpStatus.UNPROCESSABLE_ENTITY);
			mav.addObject("msg", "요청한 엔티티 정보가 없습니다.");
			mav.setViewName("member/error");
		}
		
		return mav;
	}
	
	
	@GetMapping("/member/update/{idx}")
	public ModelAndView updateUi(@PathVariable("idx") Integer idx) {
		
		ModelAndView mav = new ModelAndView();
		
		try {
			MemberUpdateUiDTO memberUpdateUiDTO = memberService.updateUi(idx);
			mav.addObject("memberUpdateUiDTO", memberUpdateUiDTO);
			mav.setViewName("member/memberUpdate");
		}
		catch(NoSuchElementException ex) {
			mav.setStatus(HttpStatus.UNPROCESSABLE_ENTITY);
			mav.addObject("msg", "요청한 엔티티 정보가 없습니다.");
			mav.setViewName("member/error");
		}
		
		return mav;
	}
	
	
	@PostMapping("/member/update/{idx}")
	public ModelAndView update(MemberUpdateDTO memberUpdateDTO) {
		
		memberService.update(memberUpdateDTO);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName(String.format("redirect:/member/read/%s", memberUpdateDTO.getIdx()));
		
		
		return mav;
	}
	
	/*
	@GetMapping("/member/delete/{idx}")
	public String delete(@PathVariable("idx") Integer idx) {
		
		String res = memberService.delete(idx);
		
		return "redirect:" + res;
	}
	*/
	
	
	@GetMapping("/member/delete/{idx}")
	public String deleteUi(Model model, @PathVariable("idx") Integer idx) {
		
		model.addAttribute("idx", idx);
		
		return "member/delete";
	}
	
	
//	@GetMapping("/member/delete-fail/{idx}")
//	public String deleteFail(@PathVariable("idx") Integer idx) {
//		
//		return "member/delete-fail";
//	}
//	
//	@GetMapping("/member/delete-success/{idx}")
//	public String successFail(@PathVariable("idx") Integer idx) {
//		
//		return "member/delete-success";
//	}
}
