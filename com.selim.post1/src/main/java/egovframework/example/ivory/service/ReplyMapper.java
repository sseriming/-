package egovframework.example.ivory.service;
 
import java.util.List;


import egovframework.example.ivory.vo.ReplyVo;
//Mapper namespace 와 ID를 연결할 Interface 를 두어서 interface를 호출하는 방법.
//Mybatis 매핑XML에 기재된 SQL을 호출하기 위한 인터페이스이다.
//SQL id는 인터페이스에 정의된 메서드명과 동일하게 작성한다
public interface ReplyMapper {
 
	public List<ReplyVo> replyList(String testId) throws Exception;
	 
    public int replyWrite(ReplyVo replyVo) throws Exception;
    
    public int replyModify(ReplyVo replyVo) throws Exception;
    
    public int replyDelete(ReplyVo replyVo) throws Exception;
    
	public String selectReplyNo();

}
