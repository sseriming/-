package egovframework.example.ivory.vo;

import org.springframework.web.multipart.MultipartFile;

public class testFileUploadVo {

	private MultipartFile uploadFile;

	private String fileNo;
	private String orgName;
	private String saveName;
	private String fileSize;
	private String createDate;
	private String testId;

//    private String fileName;
//    private MultipartFile uploadFile;
	
	public String getFileNo() {
		return fileNo;
	}
	public void setFileNo(String fileNo) {
		this.fileNo = fileNo;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getSaveName() {
		return saveName;
	}
	public void setSaveName(String saveName) {
		this.saveName = saveName;
	}
	public String getFileSize() {
		return fileSize;
	}
	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getTestId() {
		return testId;
	}
	public void setTestId(String testId) {
		this.testId = testId;
	}
	public MultipartFile getUploadFile() {
		return uploadFile;
	}
	public void setUploadFile(MultipartFile uploadFile) {
		this.uploadFile = uploadFile;
	}

	@Override
	public String toString() {
		return "testFileUploadVo [fileNo="+fileNo+"testId="+testId+", orgName="+orgName+",saveName="+saveName+",fileSize="+fileSize+",createDate="+createDate+"]";
	}

}









