package egovframework.example.ivory.service.impl;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import egovframework.example.ivory.vo.LoginDto;
import egovframework.example.ivory.vo.MemberVo;
import egovframework.example.ivory.dao.MemberDao;
import egovframework.example.ivory.service.MemberService;

@Service
public class MemberServiceImpl implements MemberService {
		
	@Inject
	private MemberDao dao;
	
	
	
	//회원 로그인 체크
	public int loginCheck(MemberVo membervo) {
		return dao.loginCheck(membervo);

		
	}
	
	//회원로그인 정보
	@Override
	public MemberVo viewMember(MemberVo membervo) {
		return MemberDao.viewMember(membervo);
	}
	
	
	//회원 로그아웃
	public void logout(HttpSession session) {
		//세션 정보를 초기화
		session.invalidate();
	}
	
	//회원가입
	@Override
	public void register(MemberVo membervo) {
		
		dao.register(membervo);
		
	}

	//아이디 중복 검사
	@Override
	public int idCheck(String userId) throws Exception {
	
		return dao.idCheck(userId);
	}

	@Override
	public MemberVo login(LoginDto loginDto) throws Exception {
		return dao.login(loginDto);
	}

	
}