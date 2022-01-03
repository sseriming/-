package egovframework.example.ivory.controller;
 
import javax.servlet.http.HttpServletRequest;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
 
import egovframework.example.ivory.service.TestService;
import egovframework.example.ivory.vo.TestVo;
 
@Controller  
//controller는 아래의 클래스가 컨트롤러 기능 수행
public class TestController {
    
    @Autowired
    private TestService testService;
    
    //로그인 페이지
    @RequestMapping(value="/testLogin.do")
    public String testLoginDo(TestVo testVo, Model model) throws Exception{
    	model.addAttribute("list",testService.selectTest(testVo));
    	return "main";
    }
    
    
    //회원가입 페이지
    @RequestMapping(value="/register.do")
    public String registDo(TestVo testVo, Model model) throws Exception{
    	model.addAttribute("list",testService.selectTest(testVo));
		return "register";
    }
   
    //글 목록페이지
    @RequestMapping(value="/testList.do")
    //requestmapping은 뒤에 오는 url로 라우팅하겠다는 의미
    public String testListDo(TestVo testVo, Model model) throws Exception{
        
        model.addAttribute("list", testService.selectTest(testVo));
        
        return "boardList";
    }
    
    //글 상세페이지
    @RequestMapping(value="/testDetail.do")
    public String viewForm(Model model, HttpServletRequest request) throws Exception{
        int testId = Integer.parseInt(request.getParameter("testId"));
        
        TestVo testVo = testService.selectDetail(testId);
        model.addAttribute("vo", testVo);
        
        return "testDetail";
    }
    
    //글작성페이지
    @RequestMapping(value="/testRegister.do")
    public String testRegister(){
        return "testRegister";
    }
    
    //글쓰기
    @RequestMapping(value="/insertTest.do")
    public String write(@ModelAttribute("testVo") TestVo testVo) throws Exception {
        testService.insertTest(testVo);
        return "redirect:testList.do";
    }
    
    //글수정
    @RequestMapping(value="/updateTest.do")
    public String updateTest(@ModelAttribute("testVo") TestVo testVo) throws Exception {
        testService.updateTest(testVo);
        return "redirect:testDetail.do?testId="+testVo.getTestId();
    }
    
    //글삭제
    @RequestMapping(value="/deleteTest.do")
    public String deleteTest(HttpServletRequest request) throws Exception {
        int testId = Integer.parseInt(request.getParameter("testId"));
        testService.deleteTest(testId);
        return "redirect:testList.do";
    }
    
    
}