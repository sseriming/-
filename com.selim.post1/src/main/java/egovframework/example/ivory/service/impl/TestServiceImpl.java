package egovframework.example.ivory.service.impl;

import java.io.File;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import egovframework.example.ivory.dao.TestDao;
import egovframework.example.ivory.service.TestService;
import egovframework.example.ivory.vo.Search;
import egovframework.example.ivory.vo.TestVo;
import egovframework.example.ivory.vo.testFileUploadVo;

@Service
public class TestServiceImpl implements TestService {

	@Autowired
	private TestDao testDao;

	@Override
	public List<TestVo> selectTest(Search search) throws Exception {
		return testDao.selectTest(search);
	}

	@Override
	public int insertTest(TestVo testVo, testFileUploadVo fileVo, MultipartFile file) throws Exception {

		String uploadFolder = "C:\\upload"; // 폴더 경로

		String testId = testDao.selectTestId(); // 키 생성
		testVo.setTestId(testId);
		
		try {
			int result = testDao.insertTest(testVo, fileVo, file);
			if (result == 1) {
				if (!file.isEmpty()) {
					// 서버에 파일 저장
					File Folder = new File(uploadFolder);
					// 해당 디렉토리가 없을 경우 디렉토리 생성
					if (!Folder.exists()) {
						try {
							Folder.mkdir();// 폴더 생성
							System.out.println("폴더 생성");
						} catch (Exception e) {
							e.getStackTrace();
						}
					}
					fileVo.setTestId(testId);
					

					String fileNo = testDao.selectFileNo();
					fileVo.setFileNo(fileNo);

					String orgName = file.getOriginalFilename();

					orgName = orgName.substring(orgName.lastIndexOf("\\") + 1);

					UUID uuid = UUID.randomUUID();
					String saveName = uuid.toString();

					File saveFile = new File(uploadFolder, saveName);
					fileVo.setSaveName(saveName);
					try {
						file.transferTo(saveFile);
					} catch (Exception e) {
						e.printStackTrace();
					}
					testDao.insertFile(fileVo);
					System.out.println("등록 완료");
				} else {
					System.out.println("등록 실패");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return 1;
	}

	@Override
	public TestVo selectDetail(String testId) throws Exception {
		return testDao.selectDetail(testId);
	}

	// 수정
	@Override
	public int updateTest(TestVo testVo, testFileUploadVo fileVo, MultipartFile file) throws Exception {

		String uploadFolder = "C:\\upload"; // 폴더 경로
		File Folder = new File(uploadFolder);
		// 해당 디렉토리가 없을 경우 디렉토리 생성
		if (!Folder.exists()) {
			try {
				Folder.mkdir();// 폴더 생성
				System.out.println("폴더 생성");
			} catch (Exception e) {
				e.getStackTrace();
			}
		}

		String fileNo = testDao.selectFileNo(); // 파일키 생성
		fileVo.setFileNo(fileNo);
		try {
			int result = testDao.updateTest(testVo, fileVo, file);
			if (result == 1) {

				if (!file.isEmpty()) {
					// 서버에 파일 저장
					String saveName = file.getOriginalFilename();
					saveName = saveName.substring(saveName.lastIndexOf("\\") + 1);
					UUID uuid = UUID.randomUUID();
					saveName = uuid.toString();

					File saveFile = new File(uploadFolder, saveName);
					fileVo.setSaveName(saveName);

					try {
						file.transferTo(saveFile);
					} catch (Exception e) {
						e.printStackTrace();
					}
					int result2 = testDao.updateFile(fileVo);
					if (result2 == 1) {
						System.out.println("수정 완료");
					} else {
						 testDao.insertFile(fileVo);
						 System.out.println("수정 완료");
					}
				} 
			} else {
				System.out.println("수정 실패");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 1;
	}

	@Override
	public int deleteTest(String testId) throws Exception {

		return testDao.deleteTest(testId);
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

	@Override
	public String selectRowNum() {
		return testDao.selectRowNum();
	}

}
