package egovframework.example.ivory.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.example.ivory.vo.MemberVo;


@Repository
public class UserRepository {
	//유저 저장소
	private final List<Map<String, String>> userList= new ArrayList<>();
	
	public UserRepository() {
//		System.out.println("UserRepository 초기화");
		MemberVo memberVo = new MemberVo();
		
		Map<String, String> userInfoOne = new HashMap<String, String>();
		userInfoOne.put("userId", memberVo.getUserId());
		userInfoOne.put("userPw", memberVo.getUserPw());
		userInfoOne.put("userName", memberVo.getUserName());
		userInfoOne.put("userEmail", memberVo.getUserEmail());

		userList.add(userInfoOne);
	}
	
	public void saveUser(Map<String, String> userInfoMap) throws Exception {
		userList.add(userInfoMap);
	}
}