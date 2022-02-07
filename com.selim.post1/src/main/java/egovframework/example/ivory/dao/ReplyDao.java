package egovframework.example.ivory.dao;
 
import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.example.ivory.vo.ReplyVo;

@Repository
public interface ReplyDao {
	
//	public int replyList(String tesId) throws Exception;
    public List<ReplyVo> replyList(String testId) throws Exception;
	 
    public int replyWrite(ReplyVo replyVo) throws Exception;
 
    public int replyModify(ReplyVo replyVo) throws Exception;
 
    public int replyDelete(String replyNo) throws Exception;
    
	public String selectReplyNo();
}
