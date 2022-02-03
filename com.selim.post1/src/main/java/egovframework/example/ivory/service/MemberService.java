package egovframework.example.ivory.service;

import javax.servlet.http.HttpSession;

import egovframework.example.ivory.vo.LoginDto;
import egovframework.example.ivory.vo.MemberVo;

public interface MemberService {
	
	//회원가입
	public void register(MemberVo vo)throws Exception ;
	
	//로그인 체크
	public int loginCheck(MemberVo vo);		


	
	//회원 로그인 정보
	public MemberVo viewMember(MemberVo vo)throws Exception;
	
	//회원 로그아웃
	public void logout(HttpSession session)throws Exception;
	//id 검사
	public int idCheck(String userId) throws Exception;


	MemberVo login(LoginDto loginDto) throws Exception;
	
}