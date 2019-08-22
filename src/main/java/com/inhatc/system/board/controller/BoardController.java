package com.inhatc.system.board.controller;

import java.io.PrintWriter;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.inhatc.system.board.service.BoardService;
import com.inhatc.system.board.vo.BoardVO;
import com.inhatc.system.board.vo.Criteria;
import com.inhatc.system.board.vo.PageMaker;

@Controller
public class BoardController {
	
	 public String toFilterString(String in){
		  if(in == null || in.length() < 1){
		   return in;
		  }
		  StringBuffer out = new StringBuffer();
		  
		  for(int i = 0; in != null && i < in.length(); i++){
		   char c = in.charAt(i);
		   if(c == '\''){
		    out.append(" ");
		   } else if(c == '\"'){
		    out.append(" ");
		   } else if(c == '<'){
		    out.append(" ");
		   } else if(c == '>'){
		    out.append(" ");
		   } else if(c == '&'){
		    out.append(" ");
		   } else if(c == '('){
		    out.append(" ");
		   } else if(c == ')'){
		    out.append(" ");
		   } else {
		    out.append(c);
		   }
		  }
		  return out.toString();
		 }

	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

	@Autowired
	private BoardService service;

	/* board main GET */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getHome(@ModelAttribute("cri") Criteria cri, BoardVO vo, Locale locale, Model model, HttpSession ses)
			throws Exception {

		logger.info("환영합니다. IT기자재지원실 & PC지원실 관리 프로그램입니다.");
		logger.info("현재 페이지와 페이지에 있는 글의 개수는 " + cri.toString());

		model.addAttribute("boardList", service.listAll(vo, cri, ses));

		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);

		pageMaker.setTotalCount(service.listCountCriteria(cri, ses));

		model.addAttribute("pageMaker", pageMaker);

		return "board/board";
	}
	
	/* board main POST */
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String postHome(@ModelAttribute("cri") Criteria cri, BoardVO vo, Locale locale, Model model, HttpSession ses)
			throws Exception {
		logger.info("환영합니다. IT기자재지원실 & PC지원실 관리 프로그램입니다.");
		logger.info("현재 페이지와 페이지에 있는 글의 개수는 " + cri.toString());

		model.addAttribute("boardList", service.listAll(vo, cri, ses));

		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);

		pageMaker.setTotalCount(service.listCountCriteria(cri, ses));

		model.addAttribute("pageMaker", pageMaker);

		return "board/board";
	}

	/*go to URL, board write */
	@RequestMapping(value = "/board/write", method = RequestMethod.GET)
	public String write(BoardVO board) throws Exception {

		logger.info("(BoardControllor) 글 작성 GET방법");
		logger.info("(BoardControllor) 글 작성 내용은 " + board.toString());

		return "board/write";
	}

	/* board register */
	@RequestMapping(value = "/board/register", method = RequestMethod.POST)
	public void registerPOST(BoardVO board, HttpSession ses, HttpServletResponse response) throws Exception {
		logger.info("(BoardControllor) 글 등록 POST방법");
		logger.info(board.toString());
		System.out.println("(BoardControllor) 전화번호는 : " + board.getClient_number()); // 전화번호
		System.out.println("(BoardControllor) 소속은 : " + board.getClient_belong()); // 의뢰인 소속
		System.out.println("(BoardControllor) 이름은 : " + board.getClient_name()); // 의뢰인 이름
		System.out.println("(BoardControllor) 담당소속은 : " + board.getManager_belong()); // 작업 분류

		service.regist(board, ses);

		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<script>alert('등록이 완료되었습니다.'); window.opener.location.reload(); window.close();</script>");
		out.flush();
	}

	/*change updateStart -> updateIng*/
	@RequestMapping(value = "/board/updateIng", method = RequestMethod.POST)
	public void updateIng(BoardVO board, RedirectAttributes rttr, HttpSession ses, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		service.updateIng(board, ses);

		rttr.addFlashAttribute("msg", "success");

		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<script>if(confirm('처리 하시겠습니까?') == true){"
				+ "alert('처리가 신청되었습니다.'); "
				+ "window.opener.location.reload(); "
				+ "window.close();"
				+ "} else{ "
				+ "return false; "
				+ "}</script>");
		out.flush();

	}

	/*change updateIng -> updateEnd*/
	@RequestMapping(value = "/board/updateEnd", method = RequestMethod.POST)
	public void updateEnd(BoardVO board, RedirectAttributes rttr, HttpSession ses, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		service.updateEnd(board, ses);

		rttr.addFlashAttribute("msg", "success");

		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		out.println("<script>alert('처리가 완료되었습니다.'); window.opener.location.reload(); window.close();</script>");
		out.flush();
	}

	/* board read */
	@RequestMapping(value = "/board/read", method = RequestMethod.GET)
	public void read(@RequestParam("bno") int bno, Model model) throws Exception {

		model.addAttribute(service.read(bno));
	}

	/* board delete */
	@RequestMapping(value = "/board/delete", method = RequestMethod.POST)
	public void delete(@RequestParam("bno") int bno, RedirectAttributes rttr, HttpServletResponse response)
			throws Exception {

		service.delete(bno);

		rttr.addFlashAttribute("msg", "success");

		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		out.println("<script> alert('삭제가 완료되었습니다.'); window.opener.location.reload(); window.close();</script>");
		out.flush();

	}

	/* board modify */
	@RequestMapping(value = "/board/modify", method = RequestMethod.POST)
	public void modifyActionPOST(BoardVO board, HttpSession ses, HttpServletResponse response) throws Exception {
		logger.info("(BoardControllor) 글 수정 POST방법");
		logger.info(board.toString());
		System.out.println("(BoardControllor) 내선번호는 : " + board.getClient_number());
		System.out.println("(BoardControllor) 소속은 : " + board.getClient_belong());
		System.out.println("(BoardControllor) 이름은 : " + board.getClient_name());
		System.out.println("(BoardControllor) 담당소속은 : " + board.getManager_belong());
		System.out.println("(BoardControllor) 작업분류는 : " + board.getManager_classification());
		System.out.println("(BoardControllor) 위치는 : " + board.getClient_local());
		System.out.println("(BoardControllor) 기기종류는 : " + board.getInstrument());
		System.out.println("(BoardControllor) 작성자는 : " + board.getWriter());
		System.out.println("(BoardControllor) 내용은 : " + board.getContent());

		service.modify(board, ses);

		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<script>alert('수정이 완료되었습니다.'); window.opener.location.reload(); window.close();</script>");
		out.flush();

	}
	
	/*When board write, bring the information of school phone number*/
	@ResponseBody
	@RequestMapping(value = "/board/information", method = RequestMethod.POST)
	public BoardVO information(String client_number, Model model) throws Exception {

		logger.info("(BoardControllor) information테이블에서 전화번호를 가져옴." + client_number);
		BoardVO result = service.find_number(client_number);

		return result;
	}
	
	/*manage to information  of school phone number*/ 
	@RequestMapping(value = "/managerAction", method = RequestMethod.GET)
	public String managerGET(BoardVO board, HttpSession ses, Model model) throws Exception {

		logger.info("인하공업전문대학 학과(학부) 전화번호 관리 페이지로 이동합니다.");
		logger.info("(BoardControllor) 전화번호 관리 POST방법");
		logger.info("(BoardControllor)  내용은 " + board.toString());

		model.addAttribute("boadList", service.boardList(board, ses));
		
		return "board/manager";
	}

	/* update to information of school phone number */
	@RequestMapping(value = "/managerModify", method = RequestMethod.POST)
	public void managerModifyPOST(BoardVO board, HttpSession ses, HttpServletResponse response) throws Exception {
		logger.info("(BoardControllor) 내선번호 정보 수정 POST방법");
		logger.info(board.toString());
		System.out.println("(BoardControllor) 내선번호는 : " + board.getClient_number());
		System.out.println("(BoardControllor) 소속은 : " + board.getClient_belong());
		System.out.println("(BoardControllor) 이름은 : " + board.getClient_name());
		System.out.println("(BoardControllor) 위치는 : " + board.getClient_local());

		service.managerModify(board, ses);

		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<script>alert('수정이 완료되었습니다.'); window.opener.location.reload(); window.close();</script>");
		out.flush();
	}

	/* insert to information of school phone number */
	@RequestMapping(value = "/managerInsert", method = RequestMethod.POST)
	public void managerInsertPOST(BoardVO board, HttpSession ses, HttpServletResponse response) throws Exception {
		logger.info("(BoardControllor) 내선번호 정보 추가 POST방법");
		logger.info(board.toString());
		System.out.println("(BoardControllor) 전화번호는 : " + board.getClient_number()); // 전화번호
		System.out.println("(BoardControllor) 소속은 : " + board.getClient_belong()); // 의뢰인 소속
		System.out.println("(BoardControllor) 이름은 : " + board.getClient_name()); // 의뢰인 이름
		System.out.println("(BoardControllor) 위치는 : " + board.getClient_local()); // 작업 분류

		service.managerInsert(board, ses);

		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<script>alert('등록이 완료되었습니다.'); window.opener.location.reload(); window.close();</script>");
		out.flush();

	}

	/* delete to information of school phone number */
	@RequestMapping(value = "/managerDelete", method = RequestMethod.POST)
	public void managerDeletePOST(BoardVO board, HttpServletResponse response) throws Exception {

		logger.info("(BoardControllor) 내선번호 정보 삭제 POST방법");
		logger.info(board.toString());
		System.out.println("(BoardControllor) 전화번호는 : " + board.getClient_number()); // 전화번호
		System.out.println("(BoardControllor) 소속은 : " + board.getClient_belong()); // 의뢰인 소속
		System.out.println("(BoardControllor) 이름은 : " + board.getClient_name()); // 의뢰인 이름
		System.out.println("(BoardControllor) 위치는 : " + board.getClient_local()); // 작업 분류

		service.managerDelete(board);

		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<script>alert('삭제가 완료되었습니다.'); window.opener.location.reload(); window.close();</script>");
		out.flush();
	}

	/* show information of school phone number */
	@RequestMapping(value = "/showInformation", method = RequestMethod.POST)
	public String ShowInformation(BoardVO board, HttpSession ses, Model model) throws Exception {

		logger.info("교내 내선번호 정보 리스트 페이지입니다..");
		model.addAttribute("informationList", service.informationList(board, ses));

		return "board/showInformation";
	}
	
	
	
	@RequestMapping(value = "/board/print", method = RequestMethod.POST)
	public String print(BoardVO board, HttpSession ses, Model model) throws Exception {

		logger.info("인쇄부분 페이지입니다.");
		model.addAttribute("boardList", service.listAll_p(board, ses));


		return "board/print_page";
	}
	
	@RequestMapping(value = "/print", method = RequestMethod.GET)
	public String printGET(BoardVO board, HttpSession ses, Model model) throws Exception {

		logger.info("인쇄");
		logger.info("(BoardControllor) 인쇄 GET방법");
		logger.info("(BoardControllor)  내용은 " + board.toString());

		
		return "/board/print_page";
	}

}
