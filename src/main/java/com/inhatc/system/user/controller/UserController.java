package com.inhatc.system.user.controller;

import java.io.PrintWriter;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.inhatc.system.user.service.UserService;
import com.inhatc.system.user.vo.UserVO;

@Controller
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	UserService service;

	@Autowired
	BCryptPasswordEncoder passEncoder;
	
	/*go to the login*/
	@RequestMapping(value = "/loginHome", method = RequestMethod.GET)
	public String loginHome(UserVO uservo) throws Exception {
		
		logger.info("로그인화면입니다. IT기자재지원실 & PC지원실 관리 프로그램입니다.");
		return "user/login";
	}

	/*click the login button*/
	@ResponseBody
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String loginInqr(@ModelAttribute("uservo") UserVO uservo, HttpSession ses, ModelMap model) throws Exception {

		String resultCode = "";

		logger.info("(UserControllor) 로그인한 사람의 아이디는 " + uservo.getId());
		logger.info("(UserControllor) 로그인한 사람의 비밀번호는 " + uservo.getPw());

		UserVO loginModel = service.loginInqr(uservo, ses);
		
			logger.info("(UserControllor) 로그인한 사람의 이름은 " + loginModel.getManager_name());

			resultCode = loginModel.getLoginType();
		
		return resultCode;
	}

	/*click the logout button*/
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public void logout(HttpSession ses, HttpServletResponse response) throws Exception {
		
		logger.info("감사합니다. 로그아웃을 성공적으로 하였습니다.");
		ses.invalidate();
		
		response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<script>alert('성공적으로 로그아웃 되었습니다.'); window.opener.location.reload();</script>");  
        out.println("<script>location.href = '/'</script>");
        out.flush();
	}
	
	/*go to the join*/
	@RequestMapping(value = "/joinAction", method = RequestMethod.GET)
	public String joinGET(UserVO uservo) throws Exception {
		
		logger.info("회원정보 입력 페이지로 이동합니다.");
		logger.info("(UserControllor) 회원등록 GET방법");
		logger.info("(UserControllor)  내용은 " + uservo.toString());
		
		return "user/join";
	}
	
	/* show member list */
	@RequestMapping(value = "/showUser", method = RequestMethod.POST)
	public String ShowLogin(UserVO uservo, HttpSession ses, Model model) throws Exception {

		logger.info("회원정보 리스트 페이지입니다..");
		model.addAttribute("UserList", service.UserList(uservo, ses));
		
		return "user/showUser";
	}

	/*search the id*/
	@ResponseBody
	@RequestMapping(value = "/user/list", method = RequestMethod.POST)
	public UserVO userList(String id, Model model) throws Exception {
	
			
		logger.info("(UserControllor) user테이블에서 아이디를 가져옴." + id);

		UserVO result = service.find_id(id);

		

	     return result;
		
	}
	
	/* insert the member */
	@RequestMapping(value = "/userInsert", method = RequestMethod.POST)
	public void userInsertPOST(UserVO uservo, HttpSession ses,  HttpServletResponse response) throws Exception {
		logger.info("(UserControllor) 회원등록 POST방법");
		logger.info(uservo.toString());
		System.out.println("(UserControllor) 이름은 : " + uservo.getManager_name()); // 전화번호
		System.out.println("(UserControllor) 소속은 : " + uservo.getManager_belong()); //의뢰인 소속
		System.out.println("(UserControllor) 아이디는 : " + uservo.getId()); // 의뢰인 이름
		System.out.println("(UserControllor) 비밀번호는 : " + uservo.getPw()); // 작업 분류
	
		
		service.join(uservo, ses);
		
		response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<script>alert('등록이 완료되었습니다.'); window.opener.location.reload(); window.close();</script>");  
        out.flush();

	}
	
	/* update the member */
	@RequestMapping(value = "/userModify", method = RequestMethod.POST)
	public void userModifyPOST(UserVO uservo, HttpSession ses ,HttpServletResponse response) throws Exception {
		logger.info("(UserControllor) 회원수정 POST방법");
		logger.info(uservo.toString());
		System.out.println("(UserControllor) 이름은 : " + uservo.getManager_name()); // 전화번호
		System.out.println("(UserControllor) 소속은 : " + uservo.getManager_belong()); //의뢰인 소속
		System.out.println("(UserControllor) 아이디는 : " + uservo.getId());
		System.out.println("(UserControllor) 비밀번호는 : " + uservo.getPw()); // 작업 분류
	
		service.userModify(uservo, ses);
		
		response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<script>alert('수정이 완료되었습니다.'); window.opener.location.reload(); window.close();</script>");        
        out.flush();
	}
	
	/* delete the member */
	@RequestMapping(value = "/userDelete", method = RequestMethod.POST)
	public void userDeletePOST(UserVO uservo, HttpServletResponse response) throws Exception {
		
		logger.info("(UserControllor) 회원 정보 삭제 POST방법");
		logger.info(uservo.toString());
		System.out.println("(UserControllor) 이름은 : " + uservo.getManager_name()); // 전화번호
		System.out.println("(UserControllor) 소속은 : " + uservo.getManager_belong()); //의뢰인 소속
		System.out.println("(UserControllor) 아이디는 : " + uservo.getId());
		System.out.println("(UserControllor) 비밀번호는 : " + uservo.getPw()); // 작업 분류
		
		service.userDelete(uservo);

		response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<script>alert('삭제가 완료되었습니다.'); window.opener.location.reload(); window.close();</script>");  
        out.flush();
	}
	
}
