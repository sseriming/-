package egovframework.example.ivory.dao.impl;
 
import java.util.List;
 
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
 
import egovframework.example.ivory.dao.TestDao;
import egovframework.example.ivory.service.TestMapper;
import egovframework.example.ivory.vo.TestVo;
 
@Repository
public class TestDaoImpl implements TestDao {
 
    @Autowired
    private SqlSession sqlSession;
    
    @Override
    public List<TestVo> selectTest(TestVo testVo) throws Exception {
        TestMapper mapper = sqlSession.getMapper(TestMapper.class);
        return mapper.selectTest(testVo);
    }
 
    @Override
    public void insertTest(TestVo testVo) throws Exception {
        TestMapper mapper = sqlSession.getMapper(TestMapper.class);
        mapper.insertTest(testVo);
    }
 
    @Override
    public TestVo selectDetail(int testId) throws Exception {
        TestMapper mapper = sqlSession.getMapper(TestMapper.class);
        return mapper.selectDetail(testId);
    }
 
    @Override
    public void updateTest(TestVo testVo) throws Exception {
        TestMapper mapper = sqlSession.getMapper(TestMapper.class);
        mapper.updateTest(testVo);
    }
 
    @Override
    public void deleteTest(int testId) throws Exception {
        TestMapper mapper = sqlSession.getMapper(TestMapper.class);
        mapper.deleteTest(testId);
    }
 
}