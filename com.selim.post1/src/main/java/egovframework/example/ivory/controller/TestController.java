package egovframework.example.ivory.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import egovframework.example.ivory.service.ReplyService;
import egovframework.example.ivory.service.TestService;
import egovframework.example.ivory.vo.ReplyVo;
import egovframework.example.ivory.vo.Search;
import egovframework.example.ivory.vo.TestVo;
import egovframework.example.ivory.vo.testFileUploadVo;

@Controller
//controller는 아래의 클래스가 컨트롤러 기능 수행
public class TestController {

	@Inject
	private TestService testService;

	@Inject
	private ReplyService replyService;

	// 로그인 페이지
	@RequestMapping(value = "/testLogin.do")
	public String testLoginDo(TestVo testVo, Model model) throws Exception {
		return "main";
	}

	// 글 상세페이지
	@RequestMapping(value = "/testDetail.do")
	public String viewForm(@ModelAttribute("testVo") TestVo testVo,@ModelAttribute("testFileUploadVo") testFileUploadVo fileVo, Model model, HttpServletRequest request,
			@RequestParam String testId) throws Exception {
//		,@RequestParam(value= "rowNum", required=false)String rowNum
		try {
			testVo.setTestId(testId);

			testVo = testService.selectDetail(testId);
			model.addAttribute("vo", testVo);
			
		

			fileVo.setTestId(testId);
			fileVo = testService.fileDetail(testId);
			model.addAttribute("file", fileVo);

			List<ReplyVo> replyList = replyService.replyList(testId);
			model.addAttribute("reply", replyList);
			
	
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return "testDetail";

	}

	// 글작성페이지
	@RequestMapping(value = "/testRegister.do")
	public String testRegister() {
		return "testRegister";
	}

	// 글쓰기
		@RequestMapping(value = "/insertTest.do")
		public ModelAndView insertTest(MultipartFile file, @ModelAttribute("testVo") TestVo testVo,
				 testFileUploadVo fileVo, Model model) throws Exception {

			// 1.파라미터 세팅
			ModelAndView mv = new ModelAndView();
			
			// 2.로직
			try {
				int result = testService.insertTest(testVo, fileVo, file);
				if (result == 1) {
							mv.addObject("msg", "정상적으로 등록되었습니다.");
							mv.addObject("testId", testVo.getTestId());
							mv.addObject("url", "/testDetail.do");
							mv.setViewName("forward:/forward.do");
							return mv;
					} 
				 else {
					mv.addObject("msg", "정상적으로 등록되지 않았습니다.");
					mv.addObject("url", "/testRegister.do");
					mv.setViewName("forward:/forward.do");
					return mv;
				}
			 }catch(Exception e) {
				 e.printStackTrace();
			}
			return mv;
		}
			
			
	// 글수정
	@RequestMapping(value = "/updateTest.do")
	public ModelAndView updateTest(MultipartFile file, @ModelAttribute("testVo") TestVo testVo,
			@ModelAttribute("testFileUploadVo") testFileUploadVo fileVo) throws Exception {
		// 1. 파라미터 세팅
		ModelAndView mv = new ModelAndView();
		
		// 2. 로직
		try {
			int result = testService.updateTest(testVo, fileVo, file);
			if (result == 1) {
						mv.addObject("msg", "정상적으로 수정되었습니다.");
						mv.addObject("testId", testVo.getTestId());
						mv.addObject("url", "testDetail.do");
						mv.setViewName("forward:/forward.do");
						return mv;
				} 
			 else {
				mv.addObject("msg", "정상적으로 수정되지 않았습니다.");
				mv.addObject("url", "/testList.do");
				mv.setViewName("forward:/forward.do");
				return mv;
			}
		 }catch(Exception e) {
			 mv.addObject("msg", "ERROR");
			 e.printStackTrace();
			 
		}
		return mv;
	} 
	

	// 첨부파일 삭제
	@RequestMapping(value = "/deleteFile.do")
	public ModelAndView deleteFile(HttpServletRequest request,@ModelAttribute("testVo") TestVo testVo, @ModelAttribute("fileVo") testFileUploadVo fileVo) throws Exception {
		String fileNo= request.getParameter("fileNo");
		ModelAndView mv = new ModelAndView();
		try {
			int result = testService.deleteFile(fileNo);
			if(result ==1 ) {
				mv.addObject("testId", testVo.getTestId());
				mv.addObject("url", "testDetail.do");
				mv.setViewName("forward:/forward.do");
				return mv;
			} else {
				mv.addObject("msg", "삭제가 되지 않았습니다. 다시 시도해주세요.");
				mv.addObject("testId", testVo.getTestId());
				mv.addObject("url", "/testDetail.do");
				mv.setViewName("forward:/forward.do");
				return mv;
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return mv;	

	}

	// 글삭제
	@RequestMapping(value = "/deleteTest.do")
	public ModelAndView deleteTest(HttpServletRequest request
			, @ModelAttribute("replyVo") ReplyVo replyVo) throws Exception {
		// 1.파라미터 세팅
		ModelAndView mv = new ModelAndView();
		String testId = request.getParameter("testId");
		
		// 2.로직
		try {
			int result = testService.deleteTest(testId);
			if (result == 1) {
				mv.addObject("msg", "삭제되었습니다.");
				mv.addObject("url", "/testList.do");
				mv.setViewName("forward:/forward.do");
				return mv;
			} else {
				mv.addObject("msg", "삭제가 되지 않았습니다. 다시 시도해주세요.");
				mv.addObject("url", "/testRegister.do");
				mv.setViewName("forward:/forward.do");
				return mv;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mv;
	}

	// 글목록페이지,페이징,검색
	//검색 조건을 받기위해 파라미터 세팅
	@RequestMapping(value = "/testList.do")
	public String testListDo(Model model, @RequestParam(required = false, defaultValue = "1") int page,
			@RequestParam(required = false, defaultValue = "1") int range,
			@RequestParam(required = false, defaultValue = "testTitle") String searchType,
			@RequestParam(required = false) String keyword, @ModelAttribute("search") Search search, @ModelAttribute("testVo") TestVo testVo) throws Exception {
		
		
		// 검색
		model.addAttribute("search", search);
		search.setSearchType(searchType);
		search.setKeyword(keyword);

		// 전체 개시글 개수를 얻어와 listcnt에 저장
		//service에 pagination대신 search를 인자로 보냄.
		int listCnt = testService.getBoardListCnt(search);
		// 검색 후 페이지
		// view에서 전달받은 파마리터(현재 페이지정보, 페이지 범위)와 전체 게시글의 개수를 가지고 페이지 정보를 셋팅
		search.pageInfo(page, range, listCnt);
		// 페이징
		model.addAttribute("pagination", search);
		// 게시글 화면 출력
		model.addAttribute("list", testService.selectTest(search));

		return "boardList";
	}

	// 파일 다운로드
	@RequestMapping(value = "/fileDown.do")
	public void fileDown(testFileUploadVo fileVo, HttpServletResponse response) throws Exception {
		// 파라미터 세팅
		testFileUploadVo fileVo2 = new testFileUploadVo();
		try {
			fileVo2 = testService.fileDownload(fileVo);

			String orgName = fileVo2.getOrgName();
			String saveName = fileVo2.getSaveName();
			orgName = orgName.substring(orgName.lastIndexOf("\\") + 1);
			String encordedFilename = URLEncoder.encode(orgName, "UTF-8").replace("+", "%20");
			// 파일명 지정
			response.setHeader("Content-Disposition",
					"attachment;filename=" + encordedFilename + ";filename*= UTF-8''" + encordedFilename);
			OutputStream os = response.getOutputStream();
			String path = "C:\\upload";
			FileInputStream fis = new FileInputStream(path + File.separator + saveName);
			int n = 0;
			byte[] b = new byte[512];
			while ((n = fis.read(b)) != -1) {
				os.write(b, 0, n);
			}
			fis.close();
			os.close();
	
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	// 댓글작성
	@RequestMapping(value = "/replyWrite.do")
	public ModelAndView replyWrite(@ModelAttribute("replyVo") ReplyVo replyVo) throws Exception {
		// 1. 파라미터 세팅
		ModelAndView mv = new ModelAndView();

		String replyNo = replyService.selectReplyNo(); // 댓글 키 생성
		replyVo.setReplyNo(replyNo);
		try {
			// 2.로직
			int result = replyService.replyWrite(replyVo);
			if (result == 1) {
				mv.addObject("testId", replyVo.getTestId());
				mv.addObject("url", "/testDetail.do");
				mv.setViewName("forward:/forward.do");
				return mv;
			} else {
				mv.addObject("msg", "오류가 발생했습니다. 다시 시도해주세요.");
				mv.addObject("testId", replyVo.getTestId());
				mv.addObject("url", "testDetail.do");
				mv.setViewName("forward:/forward.do");
				return mv;
			}
	
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}

	// 댓글삭제
	@RequestMapping(value = "/replyDelete.do")
	public ModelAndView replyDelete(@ModelAttribute("replyVo") ReplyVo replyVo, HttpServletRequest request)
			throws Exception {

		ModelAndView mv = new ModelAndView();
		try {
			int result = replyService.replyDelete(replyVo.getReplyNo());
			if (result == 1) {
				mv.addObject("url", "testDetail.do");
				mv.setViewName("forward:/forward.do");
				return mv;

			} else {
				mv.addObject("msg", "삭제가 되지 않았습니다. 다시 시도해주세요.");
				mv.addObject("url", "testList.do");
				mv.setViewName("forward:/forward.do");
				return mv;
			}	
		} catch(Exception e) {
			e.printStackTrace();
		}
 		return mv;
	}

	/**
	 * 메시지를 alert 뿌리고 url에 해당하는 페이지로 이동한다.
	 * @param url
	 * @param msg
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "forward.do")
	public String forward(HttpServletRequest req) throws Exception {
		// type - BACK: 메세지 출력 > 뒤로가기
		// POP: 메세지 출력 >부모창 url이동 > 현재 팝업창 닫기
		// 그 외: 메세지 출력 > 현재 창 url 이동

		req.setAttribute("type", (req.getAttribute("type") == null) ? (String) req.getParameter("type")
				: (String) req.getAttribute("type"));
		req.setAttribute("msg", (req.getAttribute("msg") == null) ? (String) req.getParameter("msg")
				: (String) req.getAttribute("msg"));
		req.setAttribute("url", (req.getAttribute("url") == null) ? (String) req.getParameter("url")
				: (String) req.getAttribute("url"));
		req.setAttribute("testId", (req.getAttribute("testId") == null) ? (String) req.getParameter("testId")
				: (String) req.getAttribute("testId"));
		return "forward";
	}

}