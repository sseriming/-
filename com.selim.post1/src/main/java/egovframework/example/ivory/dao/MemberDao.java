package egovframework.example.ivory.dao;

import javax.servlet.http.HttpSession;

import egovframework.example.ivory.vo.MemberVo;

public interface MemberDao {
	//회원가입
	public void register(MemberVo vo) ;
	

	//회원 로그인 체크
	public boolean loginCheck(MemberVo vo);
	
	//회원 로그인 정보
	public static MemberVo viewMember(MemberVo vo) {
		
		return null;
	}
	
	//회원 로그아웃
	public void logout(HttpSession session);
	
	
}