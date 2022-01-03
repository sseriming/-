package egovframework.example.ivory.service.impl;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import egovframework.example.ivory.vo.MemberVo;
import egovframework.example.ivory.dao.MemberDao;
import egovframework.example.ivory.service.MemberService;

@Service
public class MemberServiceImpl implements MemberService {
	
	@Inject MemberDao dao;
	
	
	//회원 로그인 체크
	public boolean loginCheck(MemberVo vo, HttpSession session) {
		boolean result = dao.loginCheck(vo);

		if(result) { //true일 경우 session에 등록
			MemberVo vo2= viewMember(vo);
			//세션 변수 등록
			session.setAttribute("userId", vo2.getUserId());
			session.setAttribute("userPw", vo2.getUserPw());
		}
		return result;
	}
	
	//회원로그인 정보
	@Override
	public MemberVo viewMember(MemberVo vo) {
		return MemberDao.viewMember(vo);
	}
	
	
	//회원 로그아웃
	public void logout(HttpSession session) {
		//세션 정보를 초기화
		session.invalidate();
	}
	
	@Override
	public void register(MemberVo vo) throws Exception {
		System.out.println("hi");
		System.out.println(vo.getUserId());
		
		dao.register(vo);
		
	}




	
}