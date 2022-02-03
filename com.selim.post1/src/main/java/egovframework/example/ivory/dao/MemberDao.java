package egovframework.example.ivory.dao;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Repository;

import egovframework.example.ivory.vo.LoginDto;
import egovframework.example.ivory.vo.MemberVo;

@Repository
public interface MemberDao {
	
	//회원가입
	public void register(MemberVo membervo) ;
	
	//	아이디 체크
	public int idCheck(String userId);

	//회원 로그인 체크
	public int loginCheck(MemberVo membervo);
	
	
	//회원 로그인 정보
	public static MemberVo viewMember(MemberVo membervo) {
		
		return null;
	}
	
	
	//회원 로그아웃
	public void logout(HttpSession session);
	
	//로그인 처리
	MemberVo login(LoginDto loginDto) throws Exception;
	
}

