package egovframework.example.ivory.vo;

public class TestVo {
    
    private String testId;
    private String testTitle;
    private String testContent;
    private String testName;
    private String testDate;
    private String rowNum;
    private String showTest;
    private String testTime;
    
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
	public String getShowTest() {
		return showTest;
	}
	public void setShowTest(String showTest) {
		this.showTest = showTest;
	}
	public String getRowNum() {
		return rowNum;
	}
	public void setRowNum(String rowNum) {
		this.rowNum = rowNum;
	}
	public String getTestTime() {
		return testTime;
	}
	public void setTestTime(String testTime) {
		this.testTime = testTime;
	}
	
	@Override
	public String toString() {
		return "TestVo [testId="+testId+", testTitle="+testTitle+",testContent="+testContent+",testName="+testName+",testDate="+testDate+",showTest="+showTest+",testTime="+testTime+",rowNum="+rowNum+"]";
	}

}









