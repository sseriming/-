package egovframework.example.ivory.controller;
 
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
 
import egovframework.example.ivory.service.MemberService;
import egovframework.example.ivory.vo.MemberVo;

@Controller
@RequestMapping("/member/*")
public class MemberController {
	
	//로깅을 위한 변수
	private static final  Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@Inject
	MemberService service;
	//로그인 화면
	@RequestMapping("login.do")
	public String login() {
		return "login";
	}
	
	
	//로그인 처리
	@RequestMapping(value = "loginCheck.do")
	public ModelAndView loginCheck(@ModelAttribute MemberVo vo, HttpSession session) {
		boolean result = MemberService.loginCheck(vo,session);
		ModelAndView mav= new ModelAndView();
		if(result==true) { //로그인 성공
			//list로 이동
			mav.setViewName("boardList");
			mav.addObject("msg","success");
			}else { //로그인 실패
				mav.setViewName("main");
				mav.addObject("msg","failure");
			}
		return mav;
	}
	
//	@RequestMapping(value="jquery/login",method=RequestMethod.POST)
//	@ResponseBody
//	public int login(MemberVo vo,HttpSession session) {
//		int result = 0;
//		String col=null;
//		col="userId";
//		MemberVo userIdCheck = MemberService.getUserOne(vo.getUserId(),col);
//	}
//	
	//로그아웃 처리
	@RequestMapping("logout.do")
	public ModelAndView logout(HttpSession session) {
		MemberService.logout(session);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("main");
		mav.addObject("msg","logout");
		return mav;
	}
	
	//회원가입 get
//	@RequestMapping(value = "/register", method = RequestMethod.GET)
//	public void getRegister() throws Exception { 
//		logger.info("get register");
//	}
	
	//회원가입 post
	@RequestMapping(value = "/register.do")
	public String postRegister(MemberVo vo) throws Exception {
		logger.info("post register");
		logger.info(vo.getUserId());
		
		service.register(vo);
		
		return "redirect:/testLogin.do";
	}
	
	@RequestMapping(value = "main.do", method = RequestMethod.POST)
	public String login(MemberVo vo, HttpServletRequest req, RedirectAttributes rttr) throws Exception{
		logger.info("post login");
		
		HttpSession session = req.getSession();
//		MemberVo login = service.login(vo);
		
//		if(login == null) {
//			session.setAttribute("member", null);
//			rttr.addFlashAttribute("msg", false);
//		}else {
//			session.setAttribute("member", login);
//		}
		
		return "redirect:/";
	}
	

}
