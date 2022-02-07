package egovframework.example.ivory.service;
 
import java.util.List;


import egovframework.example.ivory.vo.ReplyVo;


public interface ReplyService {
	
	public List<ReplyVo> replyList(String testId) throws Exception;
	 
    public int replyWrite(ReplyVo replyVo) throws Exception;
    
    public int replyModify(ReplyVo replyVo) throws Exception;
    
    public int replyDelete(String replyNo) throws Exception;
    
	public String selectReplyNo();
 
	
}
