package egovframework.example.ivory.dao.impl;
 
import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
 
import egovframework.example.ivory.dao.TestDao;
import egovframework.example.ivory.service.TestMapper;
import egovframework.example.ivory.vo.Search;
import egovframework.example.ivory.vo.TestVo;
import egovframework.example.ivory.vo.testFileUploadVo;
 
@Repository
public class TestDaoImpl implements TestDao {
 
    @Inject
    private SqlSession sqlSession;
    

    @Override
    public List<TestVo> selectTest(Search search) throws Exception {
        TestMapper mapper = sqlSession.getMapper(TestMapper.class);
        return mapper.selectTest(search);
    }
 
    @Override
    public int insertTest(TestVo testVo) throws Exception {
        TestMapper mapper = sqlSession.getMapper(TestMapper.class);
        return mapper.insertTest(testVo);
    }
 
    @Override
    public TestVo selectDetail(String testId) throws Exception {
        TestMapper mapper = sqlSession.getMapper(TestMapper.class);
        return mapper.selectDetail(testId);
    }
 
    @Override
    public int updateTest(TestVo testVo) throws Exception {
        TestMapper mapper = sqlSession.getMapper(TestMapper.class);
        return mapper.updateTest(testVo);
    }
 
    @Override
    public int deleteTest(String testId) throws Exception {
        TestMapper mapper = sqlSession.getMapper(TestMapper.class);
        return mapper.deleteTest(testId);
    }
    
    @Override
    public int getBoardListCnt(Search search) throws Exception {
        TestMapper mapper = sqlSession.getMapper(TestMapper.class);
        return mapper.getBoardListCnt(search);
    }
    @Override
	public int insertFile(testFileUploadVo fileVo) throws Exception {
		TestMapper mapper = sqlSession.getMapper(TestMapper.class);
		return mapper.insertFile(fileVo);
	}

	@Override
	public String selectTestId() {
		TestMapper mapper = sqlSession.getMapper(TestMapper.class);
		return mapper.selectTestId();
	}

	@Override
	public testFileUploadVo fileDetail(String testId) throws Exception {
		TestMapper mapper = sqlSession.getMapper(TestMapper.class);
		return mapper.fileDetail(testId);
	}

	@Override
	public int updateFile(testFileUploadVo fileVo) {
		TestMapper mapper = sqlSession.getMapper(TestMapper.class);
		return mapper.updateFile(fileVo);
	}

	@Override
	public int deleteFile(String fileNo) {
		TestMapper mapper = sqlSession.getMapper(TestMapper.class);
		return mapper.deleteFile(fileNo);
	}

	@Override
	public String selectFileNo() {
		TestMapper mapper = sqlSession.getMapper(TestMapper.class);
		return mapper.selectFileNo();
	}

	@Override
	public testFileUploadVo fileDownload(testFileUploadVo fileVo) throws Exception {
		TestMapper mapper = sqlSession.getMapper(TestMapper.class);
		return mapper.fileDownload(fileVo);
//		return sqlSession.selectOne("TestMa pper.fileDownload",fileNo);
	}

	
 
}