package com.inhatc.system.board.service;

import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.inhatc.system.board.dao.BoardDAO;
import com.inhatc.system.board.vo.BoardVO;
import com.inhatc.system.board.vo.Criteria;

/**************************************************************************************
*
* @Class Name  : BoardServiceImpl.java
* @Description : 게시판을 관리하는 service      
* @Modification Information  
* <p>
* <b>NOTE</b>: 
* @author 전세연
* @since  2018.09.18
* @version 1.0
* @see <pre>
*  == 개정이력(Modification Information) ==
*   
*   수정일                   수정자              수정내용
*  ------------    --------    ---------------------------
*   2018.09.18      전세연              최초 생성 
*   2019.01.10 		전세연		Console 정보 수정
*   2019.01.22		전세연		게시판 수정, 삭제 
*   2019.01.29		전세연		교내 내선번호 추가, 수정, 삭제
*   2019.01.31		전세연		주석 표시

* </pre> **************************************************************************************/

@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private BoardDAO dao;

	/*글 등록*/
	@Override
	public void regist(BoardVO board, HttpSession ses) throws Exception {
		
		String resultWriter = "";

		resultWriter = (String) ses.getAttribute("LOGIN_NAME");

		board.setWriter(resultWriter);

		System.out.println("(Board_ServiceImpl) 작성자는 : " + resultWriter);

		dao.create(board);
	}
	
	/*처리대기 버튼 -> 처리중 버튼*/
	@Override
	public void updateIng(BoardVO board, HttpSession ses) throws Exception {
		
		String resultCompleteId = "";

		resultCompleteId = (String) ses.getAttribute("LOGIN_NAME");

		board.setCompleteId(resultCompleteId);

		System.out.println("(Board_ServiceImpl) 처리할 사람의 이름은: " + resultCompleteId);

		dao.updateIng(board);
	}
	
	/*처리중 버튼 -> 처리완료 버튼*/
	@Override
	public void updateEnd(BoardVO board, HttpSession ses) throws Exception {
		
		String resultCompleteId = "";

		resultCompleteId = (String) ses.getAttribute("LOGIN_NAME");

		board.setCompleteId(resultCompleteId);

		System.out.println("(Board_ServiceImpl) 처리중인 사람의 이름은: " + resultCompleteId);

		dao.updateEnd(board);
	}
	
	/*글 보기*/
	@Override
	public BoardVO read(Integer bno) throws Exception {
		
		System.out.println("(Board_ServiceImpl) 글 보기");
		
		return dao.read(bno);
	}

	/*글 수정*/
	@Override
	public void modify(BoardVO board, HttpSession ses) throws Exception {

		System.out.println("(Board_ServiceImpl) 글 수정");

		dao.update(board);
	}
	
	/*글 삭제*/
	@Override
	public void delete(Integer bno) throws Exception {
		
		System.out.println("(Board_ServiceImpl) 글 삭제");
		
		dao.delete(bno);
	}

	/*글 목록*/
	@Override
	public List<BoardVO> listAll(BoardVO board, Criteria cri, HttpSession ses) throws Exception {
		
		String sessionBelong = "";
		
		String resultWriter = "";
				
		if(ses.getAttribute("LOGIN_BELONG") != null) {
		sessionBelong = (String) ses.getAttribute("LOGIN_BELONG");

		cri.setManager_belong(sessionBelong);

		System.out.println("(Board_ServiceImpl) 로그인한 유저의 classification은 : " + sessionBelong);

		}
		
		if(ses.getAttribute("LOGIN_NAME") != null){

		resultWriter = (String) ses.getAttribute("LOGIN_NAME");

		cri.setWriter(resultWriter);

		System.out.println("(Board_ServiceImpl) 작성자는 : " + resultWriter);
		
		}else {
			
			cri.setManager_belong("all");
			cri.setWriter("익명");
		}
		return dao.listAll(cri);
	}

	/*글 페이지 관리*/
	@Override
	public int listCountCriteria(Criteria cri, HttpSession ses) throws Exception {
		
		if(ses.getAttribute("LOGIN_BELONG") != null) {
		String sessionBelong = "";

		sessionBelong = (String) ses.getAttribute("LOGIN_BELONG");
		
		cri.setManager_belong(sessionBelong);
		}
		else {
			cri.setManager_belong("all");
		}
		return dao.countPaging(cri);
	}

	/*내선번호 검색 버튼*/
	@Override
	public BoardVO find_number(String client_number) throws Exception {
		
		System.out.println("(Board_ServiceImpl) 내선번호 정보 검색");
		
		return dao.find_number(client_number);
	}
	
	/*내선번호 관리 페이지*/
	@Override
	public List<BoardVO> boardList(BoardVO vo, HttpSession ses) throws Exception {
		
		System.out.println("(Board_ServiceImpl) 내선번호 정보 관리 페이지");
		
		return dao.BoardList(vo);
	}
	
	/*내선번호 정보 수정*/
	@Override
	public void managerModify(BoardVO board, HttpSession ses) throws Exception {

		System.out.println("(Board_ServiceImpl) 내선번호 정보 수정");

		dao.managerModify(board);
	}
	
	/*내선번호 정보 추가*/
	@Override
	public void managerInsert(BoardVO board, HttpSession ses) throws Exception {

		System.out.println("(Board_ServiceImpl) 내선번호 정보 추가");

		dao.managerInsert(board);
	}
	
	/*내선번호 정보 삭제*/
	@Override
	public void managerDelete(BoardVO board) throws Exception {

		System.out.println("(Board_ServiceImpl) 내선번호 정보 삭제");
		
		dao.managerDelete(board);
	}
	
	/*내선번호 목록*/
	@Override
	public List<BoardVO> informationList(BoardVO board, HttpSession ses) throws Exception {
		
		System.out.println("(Board_ServiceImpl) 내선번호 정보 목록");
		
		return dao.informationList(board);
	}
	
	/*인쇄*/
	@Override
	public List<BoardVO> listAll_p(BoardVO board,  HttpSession ses) throws Exception{
		
		System.out.println("(Board_ServiceImpl) 출력 목록");
		
		return dao.listAll_p(board);
	}
	
}