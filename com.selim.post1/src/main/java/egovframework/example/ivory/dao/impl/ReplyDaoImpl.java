package egovframework.example.ivory.dao.impl;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import egovframework.example.ivory.dao.ReplyDao;
import egovframework.example.ivory.service.ReplyMapper;
import egovframework.example.ivory.vo.ReplyVo;

@Repository
public class ReplyDaoImpl implements ReplyDao {
	

    @Inject
    private SqlSession sqlSession;
	
	@Override
	public List<ReplyVo> replyList(String testId) throws Exception {
		 ReplyMapper mapper = sqlSession.getMapper(ReplyMapper.class);
	     return mapper.replyList(testId);
	}

	@Override
	public int replyWrite(ReplyVo replyVo) throws Exception {
		 ReplyMapper mapper = sqlSession.getMapper(ReplyMapper.class);
	     return mapper.replyWrite(replyVo);
	}

	@Override
	public int replyModify(ReplyVo replyVo) throws Exception {
		 ReplyMapper mapper = sqlSession.getMapper(ReplyMapper.class);
	     return mapper.replyModify(replyVo);
	}

	@Override
	public int replyDelete(ReplyVo replyVo) throws Exception {
		 ReplyMapper mapper = sqlSession.getMapper(ReplyMapper.class);
	     return mapper.replyDelete(replyVo);
	}

	@Override
	public String selectReplyNo() {
		 ReplyMapper mapper = sqlSession.getMapper(ReplyMapper.class);
		return mapper.selectReplyNo();

	}
	
}