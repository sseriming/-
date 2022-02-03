package egovframework.example.ivory.util;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import egovframework.example.ivory.vo.TestVo;

@Component("fileUtils")
public class FileUtils {
	private static final String filePath = "C:\\mp\\file\\"; // 파일이 저장될 위치
	
	public List<Map<String, Object>> parseInsertFileInfo(Map<String,Object> map, HttpServletRequest req) throws Exception{
		
		/*
			Iterator은 데이터들의 집합체? 에서 컬렉션으로부터 정보를 얻어올 수 있는 인터페이스입니다.
			List나 배열은 순차적으로 데이터의 접근이 가능하지만, Map등의 클래스들은 순차적으로 접근할 수가 없습니다.
			Iterator을 이용하여 Map에 있는 데이터들을 while문을 이용하여 순차적으로 접근합니다.
		*/
		MultipartHttpServletRequest mpRequest = (MultipartHttpServletRequest)req;
		Iterator<String> iterator = mpRequest.getFileNames();
		
		MultipartFile multipartFile = null;
		String orgName = null;
		String originalFileExtension = null;
		String saveName = null;
		
		List<Map<String, Object>> filelist = new ArrayList<Map<String,Object>>();
		Map<String, Object> fileMap = null;
		
		String testId = (String) map.get("testId").toString();
		
		File file = new File(filePath);
		if(file.exists() == false) {
			file.mkdirs();
		}
		
		while(iterator.hasNext()) {
			multipartFile = mpRequest.getFile(iterator.next());
			if(multipartFile.isEmpty() == false) {
				orgName = multipartFile.getName();
				originalFileExtension = orgName.substring(orgName.lastIndexOf("."));
				saveName = getRandomString() + originalFileExtension;
				
				file = new File(filePath + saveName);
				multipartFile.transferTo(file);
				fileMap = new HashMap<String, Object>();
				fileMap.put("testId", testId);
				fileMap.put("orgName", orgName);
				fileMap.put("saveName", saveName);
				fileMap.put("fileSize", multipartFile.getSize());
				filelist.add(fileMap);
			}
		}
		return filelist;
	}
	
	public static String getRandomString() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
}