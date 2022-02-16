package egovframework.example.ivory.vo;

public class TestVo {
    
    private String testId;
    private String testTitle;
    private String testContent;
    private String testName;
    private String testDate;
//    private String rowNum;
    private String showTest;
    


    
    public String getTestId() {
        return testId;
    }
    public void setTestId(String testId) {
        this.testId = testId;
    }
    public String getTestTitle() {
        return testTitle;
    }
    public void setTestTitle(String testTitle) {
        this.testTitle = testTitle;
    }
    public String getTestContent() {
        return testContent;
    }
    public void setTestContent(String testContent) {
        this.testContent = testContent;
    }
    public String getTestName() {
        return testName;
    }
    public void setTestName(String testName) {
        this.testName = testName;
    }
    public String getTestDate() {
        return testDate;
    }
    public void setTestDate(String testDate) {
        this.testDate = testDate;
    }
//	public String getRowNumber() {
//		return rowNumber;
//	}
//	public void setRowNumber(String rowNumber) {
//		this.rowNumber = rowNumber;
//	}
	public String getShowTest() {
		return showTest;
	}
	public void setShowTest(String showTest) {
		this.showTest = showTest;
	}
	
	@Override
	public String toString() {
		return "TestVo [testId="+testId+", testTitle="+testTitle+",testContent="+testContent+",testName="+testName+",testDate="+testDate+",showTest="+showTest+"]";
	}
//	public String getRowNum() {
//		return rowNum;
//	}
//	public void setRowNum(String rowNum) {
//		this.rowNum = rowNum;
//	}

}









