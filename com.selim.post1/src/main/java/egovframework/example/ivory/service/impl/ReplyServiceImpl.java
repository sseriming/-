package egovframework.example.ivory.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.example.ivory.dao.ReplyDao;
import egovframework.example.ivory.service.ReplyService;
import egovframework.example.ivory.vo.ReplyVo;

@Service
public class ReplyServiceImpl implements ReplyService{

	@Autowired
    private ReplyDao replyDao;
	
	@Override
	public List<ReplyVo> replyList(String testId) throws Exception {
		
		return replyDao.replyList(testId);
	}

	@Override
	public int replyWrite(ReplyVo replyVo) throws Exception {
		return replyDao.replyWrite(replyVo);
	}

	@Override
	public int replyModify(ReplyVo replyVo) throws Exception {
		return replyDao.replyModify(replyVo);
	}

	@Override
	public int replyDelete(String replyNo) throws Exception {
		return replyDao.replyDelete(replyNo);
	}
	
	@Override
	public String selectReplyNo() {
		return replyDao.selectReplyNo();
	}
	
}