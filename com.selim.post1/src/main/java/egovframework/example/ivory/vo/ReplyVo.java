package egovframework.example.ivory.vo;

public class ReplyVo {
	
    private String testId;
    private String replyNo;
    private String writer;
    private String content;
    private String regDate;
    private String showReply;
    
    
	public String getTestId() {
		return testId;
	}
	public void setTestId(String testId) {
		this.testId = testId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getReplyNo() {
		return replyNo;
	}
	public void setReplyNo(String replyNo) {
		this.replyNo = replyNo;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public String getShowReply() {
		return showReply;
	}
	public void setShowReply(String showReply) {
		this.showReply = showReply;
	}
	
	@Override
	public String toString() {
		return "ReplyVo [testId="+testId+", content="+content+",writer="+writer+",replyNo="+replyNo+",regDate="+regDate+",showReply="+showReply+"]";
	}
	
}