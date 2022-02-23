package egovframework.example.ivory.controller;
 

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.example.ivory.service.MemberService;
import egovframework.example.ivory.vo.MemberVo;

@Controller
public class MemberController {
	
	
	@Autowired
	private MemberService service;
	
	
	//로그인 처리
	@RequestMapping(value = "/loginCheck.do", method = RequestMethod.POST)
	public void loginCheck(
			@RequestParam String userPw,
			@RequestParam String userId,
			HttpServletRequest request, HttpSession httpSession, HttpServletResponse response, Model model) throws Exception {
		
		//1. 파라미터 세팅
		MemberVo memberVo= new MemberVo();
		HttpSession session = request.getSession();
		
		memberVo.setUserId(userId);
		memberVo.setUserPw(userPw);
	
		//2. 로직
		try {
			int login = service.loginCheck(memberVo);
			if(login >= 1) {			
					session.setAttribute("userId",memberVo.getUserId());
					model.addAttribute("login",login);
					System.out.println("로그인 성공");
					response.getWriter().print(true);
			}else {
					System.out.println("로그인 실패");
					response.getWriter().print(false);
				}	
		} catch(Exception e) {
			e.printStackTrace();
		}
				
	}
	
	
	//로그아웃 처리
	@RequestMapping("logout.do")
	public String logout(HttpServletRequest request) throws Exception {
		try {
			HttpSession session = request.getSession();
			session.invalidate();
			return "main";
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	//회원가입 get
	@RequestMapping(value = "/register.do", method = RequestMethod.GET)
	public void getRegister(HttpSession session) throws Exception { 
	}
	
	//회원가입 post
	@RequestMapping(value = "/register.do", method = RequestMethod.POST)
	public String postRegister(MemberVo memberVo) throws Exception {
		
		//로직
		service.register(memberVo);
		return "main";
		
	}
	

	
	// 아이디 중복 검사
	@RequestMapping(value = "/memberIdChk.do", method = RequestMethod.POST)
	@ResponseBody
	public String memberIdChkPOST(String userId) throws Exception{
		
		try {
		int result = service.idCheck(userId);
			if(result != 0) {
				
				return "fail";	// 중복 아이디가 존재
				
			} else {
				System.out.println("중복아이디 없음.");
				return "success";	// 중복 아이디 x
			}	
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		
	} // memberIdChkPOST() 종료
	
	

}