package egovframework.example.ivory.dao.impl;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import egovframework.example.ivory.dao.MemberDao;
import egovframework.example.ivory.vo.LoginDto;
import egovframework.example.ivory.vo.MemberVo;

@Repository
public class MemberDaoImpl implements MemberDao {
	
	@Autowired
	private SqlSession sqlSession;
	

	@Override
	public void register(MemberVo membervo)  {
		sqlSession.insert("register",membervo);
		
	}

	@Override
	public int loginCheck(MemberVo membervo) {
		
		return sqlSession.selectOne("loginCheck", membervo);
	}

	@Override
	public void logout(HttpSession session) {
		
		
	}

	@Override
	public int idCheck(String userId) {
		return sqlSession.selectOne("idCheck",userId);
					
	}

	@Override
	public MemberVo login(LoginDto loginDto) throws Exception {
		return sqlSession.selectOne("login",loginDto);
	}








	

}
