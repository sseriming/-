package egovframework.example.ivory.controller;
 
import java.util.HashMap;
import java.util.Map;

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
import org.springframework.web.servlet.ModelAndView;

import egovframework.example.ivory.dao.UserRepository;
import egovframework.example.ivory.service.MemberService;
import egovframework.example.ivory.vo.MemberVo;

@Controller
public class MemberController {
	
	
	@Autowired
	private MemberService service;
	
	@Autowired
	private UserRepository userRepository;
	
	
	
	//로그인 화면
//	@RequestMapping("/login.do")
//	public String login() {
//		return "login";
//	}
	


	//로그인 처리
	@RequestMapping(value = "/loginCheck.do",method = RequestMethod.POST)
	public void loginCheck(
			@RequestParam String userPw,
			@RequestParam String userId,
			HttpServletRequest request, HttpSession httpSession, HttpServletResponse response,Model model) throws Exception {
//		String userId= request.getParameter("userId");
//		String userPw= request.getParameter("userPw");
		
//		System.out.println("userId :"+userId+","+"userPw: "+userPw);
		MemberVo memberVo= new MemberVo();
		HttpSession session = request.getSession();
		
//		session.setAttribute("userName",memberVo.getUserName());
//		String userId = (String) session.getAttribute("userId");
//		String userName = (String) session.getAttribute("userName");
//		String userPw= (String) session.getAttribute("userPw");
		
		memberVo.setUserId(userId);
		memberVo.setUserPw(userPw);
		
		int login = service.loginCheck(memberVo);
		
		
		if(login >= 1) {			
			session.setAttribute("userId",memberVo.getUserId());
//			session.setAttribute("userPw",memberVo.getUserPw());
				model.addAttribute("login",login);
				response.getWriter().print(true);
		}else {
				response.getWriter().print(false);
			}		
	}
	
	
	//로그아웃 처리
	@RequestMapping("logout.do")
	public ModelAndView logout(HttpSession session) throws Exception {
		service.logout(session);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("main");
		return mav;
	}
	
	//회원가입 get
	@RequestMapping(value = "/register.do", method = RequestMethod.GET)
	public void getRegister(HttpSession session) throws Exception { 
//		logger.info("get register");
		System.out.println("회원가입 페이지 진입");
		System.out.println("session id: "+session.getId());
	}
	
	//회원가입 post
	@RequestMapping(value = "/register.do", method = RequestMethod.POST)
	public String postRegister(MemberVo memberVo, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//세션
		HttpSession session = request.getSession();
		//가입자 정보
		String userId= memberVo.getUserId();
		String userName = memberVo.getUserName();
		String userPw = memberVo.getUserPw();
		String userEmail= memberVo.getUserEmail();
		
		//회원정보 저장
		Map<String, String> userInfoMap = new HashMap<String, String>();
		userInfoMap.put("userId", userId);
		userInfoMap.put("userName", userName);
		userInfoMap.put("userPw", userPw);
		userInfoMap.put("userEmail", userEmail);
		
		//db에 저장
		userRepository.saveUser(userInfoMap);
		service.register(memberVo);
		
		
		session.setAttribute("userSession", userId);
		
		return "main";
	}
	

	
	// 아이디 중복 검사
	@RequestMapping(value = "/memberIdChk.do", method = RequestMethod.POST)
	@ResponseBody
	public String memberIdChkPOST(String userId) throws Exception{
		
//		logger.info("memberIdChk() 진입");
		int result = service.idCheck(userId);
		
//		logger.info("결과값 = " + result);		
		if(result != 0) {
			
			return "fail";	// 중복 아이디가 존재
			
		} else {
			
			return "success";	// 중복 아이디 x
			
		}	
		
	} // memberIdChkPOST() 종료
	
	

}