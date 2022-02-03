package egovframework.example.ivory.dao;
 
import java.util.List;
import org.springframework.stereotype.Repository;

import egovframework.example.ivory.vo.Search;
import egovframework.example.ivory.vo.TestVo;
import egovframework.example.ivory.vo.testFileUploadVo;

@Repository
public interface TestDao {
 
    public List<TestVo> selectTest(Search search) throws Exception;
 
    public int insertTest(TestVo testVo) throws Exception;
 
    public TestVo selectDetail(String testId)throws Exception;
 
    public int updateTest(TestVo testVo) throws Exception;
 
    public int deleteTest(String testId) throws Exception;
    
    public int getBoardListCnt(Search search) throws Exception;
    
    public int insertFile(testFileUploadVo fileVo) throws Exception;

	public String selectTestId();

	public testFileUploadVo fileDetail(String testId)throws Exception;

	public int updateFile(testFileUploadVo fileVo);

	public int deleteFile(String fileNo) throws Exception;

	public String selectFileNo();
	
	public testFileUploadVo fileDownload(testFileUploadVo fileVo) throws Exception;


 
}