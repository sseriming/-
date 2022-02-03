package egovframework.example.ivory.service.impl;
 
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.example.ivory.dao.TestDao;
import egovframework.example.ivory.service.TestService;
import egovframework.example.ivory.util.FileUtils;
import egovframework.example.ivory.vo.Search;
import egovframework.example.ivory.vo.TestVo;
import egovframework.example.ivory.vo.testFileUploadVo;
 
@Service
public class TestServiceImpl implements TestService{
	
	
    @Autowired
    private TestDao testDao;
    @Resource(name="fileUtils")
	private FileUtils fileUtils;

    
    @Override
    public List<TestVo> selectTest(Search search) throws Exception {
        return testDao.selectTest(search);
    }
 

    @Override
    public int insertTest(TestVo testVo) throws Exception {
    	 return testDao.insertTest(testVo);
    }
 
    @Override
    public TestVo selectDetail(String testId) throws Exception {
        return testDao.selectDetail(testId);
    }
 

    @Override
    public int updateTest(TestVo testVo) throws Exception {
    	return testDao.updateTest(testVo);
    }
 

    @Override
    public int deleteTest(String testId) throws Exception {
    	
        return  testDao.deleteTest(testId);
    }
 
    @Override
    public int getBoardListCnt(Search search) throws Exception {
        return testDao.getBoardListCnt(search);
    }
    @Override
	public int insertFile(testFileUploadVo fileVo) throws Exception {
    	return testDao.insertFile(fileVo);
	}


	@Override
	public String selectTestId() {
		return testDao.selectTestId();
	}


	@Override
	public testFileUploadVo fileDetail(String testId) throws Exception {
		return testDao.fileDetail(testId);
	}


	@Override
	public int updateFile(testFileUploadVo fileVo) throws Exception {
		return testDao.updateFile(fileVo);
	}


	@Override
	public int deleteFile(String fileNo) throws Exception {
		return testDao.deleteFile(fileNo);
	}


	@Override
	public String selectFileNo() {
		return testDao.selectFileNo();
	}


	@Override
	public testFileUploadVo fileDownload(testFileUploadVo fileVo) throws Exception {
		
	 return testDao.fileDownload(fileVo);
	}


    
}
