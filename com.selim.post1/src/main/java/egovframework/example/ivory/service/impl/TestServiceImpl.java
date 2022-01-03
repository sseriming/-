package egovframework.example.ivory.service.impl;
 
import java.util.List;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
import egovframework.example.ivory.dao.TestDao;
import egovframework.example.ivory.service.TestService;
import egovframework.example.ivory.vo.TestVo;
 
@Service
public class TestServiceImpl implements TestService{
 
    @Autowired
    private TestDao testDao;
    
    @Override
    public List<TestVo> selectTest(TestVo testVo) throws Exception {
        return testDao.selectTest(testVo);
    }
 
    @Override
    public void insertTest(TestVo testVo) throws Exception {
        testDao.insertTest(testVo);
    }
 
    @Override
    public TestVo selectDetail(int testId) throws Exception {
        return testDao.selectDetail(testId);
    }
 
    @Override
    public void updateTest(TestVo testVo) throws Exception {
        testDao.updateTest(testVo);
    }
 
    @Override
    public void deleteTest(int testId) throws Exception {
        testDao.deleteTest(testId);
    }
 
}
