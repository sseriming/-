package egovframework.example.ivory.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import egovframework.example.ivory.service.ReplyService;
import egovframework.example.ivory.service.TestService;
import egovframework.example.ivory.vo.MemberVo;
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
	public String viewForm(@ModelAttribute("testVo") TestVo testVo, Model model, HttpServletRequest request,
			@RequestParam String testId) throws Exception {

		testVo.setTestId(testId);

		testVo = testService.selectDetail(testId);
		model.addAttribute("vo", testVo);

		testFileUploadVo fileVo = testService.fileDetail(testVo.getTestId());
		model.addAttribute("file", fileVo);

		List<ReplyVo> replyList = replyService.replyList(testId);
		model.addAttribute("reply", replyList);

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
			String uploadFolder = "C:\\upload";

			String testId = testService.selectTestId(); // 키 생성
			testVo.setTestId(testId);
			

			// 2.로직
			int result = testService.insertTest(testVo);
			if (result == 1) {
				
				if(!file.isEmpty()) {
						//서버에 파일 저장 
						fileVo.setTestId(testId);
						
						String fileNo = testService.selectFileNo();
						fileVo.setFileNo(fileNo);
						
						String saveName = file.getOriginalFilename();
						saveName = saveName.substring(saveName.lastIndexOf("\\") + 1);

						UUID uuid = UUID.randomUUID();
						saveName = uuid.toString();

						File saveFile = new File(uploadFolder, saveName);
						fileVo.setSaveName(saveName);
						try {
							file.transferTo(saveFile);
						} catch (Exception e) {
							mv.addObject("msg", "파일이 저장되지 않았습니다. 다시 시도해주세요.");
						}
					
					int result2 = testService.insertFile(fileVo);
					if (result2 == 1) {
						mv.addObject("msg", "정상적으로 등록되었습니다.1");
						mv.addObject("testId", testVo.getTestId());
						mv.addObject("url", "/testDetail.do?testId=" + testVo.getTestId());
						mv.setViewName("forward:/forward.do");
						return mv;

					}	 else {
						mv.addObject("msg", "첨부파일에 문제가 있습니다.");
						mv.addObject("url", "/testRegister.do");
						mv.setViewName("forward:/forward.do");
						return mv;
					}
			
				} else {
					mv.addObject("msg", "정상적으로 등록되었습니다.2");
					mv.addObject("testId", testVo.getTestId());
					mv.addObject("url", "/testDetail.do?testId=" + testVo.getTestId());
					mv.setViewName("forward:/forward.do");
					return mv;
				}
				
			} else {
				mv.addObject("msg", "정상적으로 등록되지 않았습니다.");
				mv.addObject("url", "/testRegister.do");
				mv.setViewName("forward:/forward.do");
				return mv;
			}

			// 3.

		}
	

	// 글수정
	@RequestMapping(value = "/updateTest.do")
	public ModelAndView updateTest(MultipartFile file, @ModelAttribute("testVo") TestVo testVo,
			@ModelAttribute("testFileUploadVo") testFileUploadVo fileVo, MultipartHttpServletRequest request,
			Model model) throws Exception {
		// 1. 파라미터 세팅
		ModelAndView mv = new ModelAndView();
		String uploadFolder = "C:\\upload";
		
		String fileNo = testService.selectFileNo(); //파일키
		fileVo.setFileNo(fileNo);

		// 2. 로직
		int result = testService.updateTest(testVo);
		if (result == 1) {
			if(!file.isEmpty()) {
				//서버에 파일 저장 
				String saveName = file.getOriginalFilename();
				saveName = saveName.substring(saveName.lastIndexOf("\\") + 1);

				UUID uuid = UUID.randomUUID();
				saveName = uuid.toString();

				File saveFile = new File(uploadFolder, saveName);
				fileVo.setSaveName(saveName);
				try {
					file.transferTo(saveFile);
				} catch (Exception e) {
					mv.addObject("msg", "파일이 저장되지 않았습니다. 다시 시도해주세요.");
				}
				int result2 = testService.insertFile(fileVo);
				if (result2 == 1) {
					mv.addObject("msg", "정상적으로 수정되었습니다.1");
					mv.addObject("testId", testVo.getTestId());
					mv.addObject("url", "testDetail.do?testId=" + testVo.getTestId());
					mv.setViewName("forward:/forward.do");
					return mv;
				} else {
					mv.addObject("msg", "오류가 발생했습니다.ERR-01");
					mv.addObject("url", "testList.do");
					mv.setViewName("forward:/forward.do");
					return mv;
				}
			}else {
				mv.addObject("msg", "정상적으로 수정 되었습니다.2");
				mv.addObject("testId", testVo.getTestId());
				mv.addObject("url", "testDetail.do?testId=" + testVo.getTestId());
				mv.setViewName("forward:/forward.do");
				return mv;
			}
		} else {
				mv.addObject("msg", "오류가 발생했습니다.ERR-02");
				mv.addObject("url", "testList.do");
				mv.setViewName("forward:/forward.do");
				return mv;
			}
		} 
	

	// 첨부파일 삭제
	@RequestMapping(value = "/deleteFile.do", method = RequestMethod.POST)
	public String deleteFile(HttpServletRequest request, @ModelAttribute("fileVo") testFileUploadVo fileVo)
			throws Exception {

		testService.deleteFile(fileVo.getTestId());
		return "redirect:/testDetail.do?testId=" + fileVo.getTestId();

	}

	// 글삭제
	@RequestMapping(value = "/deleteTest.do")
	public ModelAndView deleteTest(HttpServletRequest request, @ModelAttribute("replyVo") ReplyVo replyVo)
			throws Exception {
		// 1.파라미터 세팅
		ModelAndView mv = new ModelAndView();
		String testId = request.getParameter("testId");

		// 2.로직
		int result = testService.deleteTest(testId);
		if (result == 1) {
			testService.deleteFile(testId);
			replyService.replyDelete(testId);
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
	}

	// 글목록페이지,페이징,검색
	@RequestMapping(value = "/testList.do")
	public String testListDo(Model model, @RequestParam(required = false, defaultValue = "1") int page,
			@RequestParam(required = false, defaultValue = "1") int range,
			@RequestParam(required = false, defaultValue = "testTitle") String searchType,
			@RequestParam(required = false) String keyword, @ModelAttribute("search") Search search) throws Exception {
		// 검색
		model.addAttribute("search", search);
		search.setSearchType(searchType);
		search.setKeyword(keyword);

		// 전체 개시글 개수
		int listCnt = testService.getBoardListCnt(search);
		// 검색 후 페이지
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
	}

	// 댓글작성
	@RequestMapping(value = "/replyWrite.do")
	public ModelAndView replyWrite(@ModelAttribute("replyVo") ReplyVo replyVo) throws Exception {
		// 1. 파라미터 세팅
		ModelAndView mv = new ModelAndView();

		String replyNo = replyService.selectReplyNo(); // 댓글 키 생성
		replyVo.setReplyNo(replyNo);

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

	}

	// 댓글삭제
	@RequestMapping(value = "/replyDelete.do")
	public ModelAndView replyDelete(@ModelAttribute("replyVo") ReplyVo replyVo, HttpServletRequest request)
			throws Exception {

		ModelAndView mv = new ModelAndView();

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
	}

	/**
	 * 메시지를 alert 뿌리고 url에 해당하는 페이지로 이동한다.
	 * 
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