package egovframework.example.ivory.service;

import javax.servlet.http.HttpSession;

import egovframework.example.ivory.vo.MemberVo;

public interface MemberService {
	
	public void register(MemberVo vo) throws Exception;
	
//	public MemberVo login(MemberVo vo) throws Exception;
	
	//회원 로그인체크
	public static boolean loginCheck(MemberVo vo, HttpSession session) {
		
		return false;
	}
	
	//회원 로그인 정보
	public MemberVo viewMember(MemberVo vo);
	
	//회원 로그아웃
	public static void logout(HttpSession session) {
		
		
	}
}