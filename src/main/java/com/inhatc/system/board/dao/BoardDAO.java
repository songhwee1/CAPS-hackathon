package com.inhatc.system.board.dao;

import java.util.List;
import com.inhatc.system.board.vo.BoardVO;
import com.inhatc.system.board.vo.Criteria;

/**************************************************************************************
*
* @Class Name  : BoardDao.java
* @Description : 게시판을 관리하는 DAO
*                  
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
* 
* </pre> **************************************************************************************/

public interface BoardDAO {
	
	public void create(BoardVO vo) throws Exception;
	
	/**
	 * INHATC System 글 등록
	 * 
	 * @param BoardVO - 게시판
	 * @return resultCode
	 * @throws Exception
	 */
	
	public void updateIng(BoardVO board) throws Exception;

	/**
	 * INHATC System 처리대기 버튼 -> 처리중 버튼
	 * 
	 * @param BoardVO - 게시판
	 * @return resultCode
	 * @throws Exception
	 */

	public void updateEnd(BoardVO board) throws Exception;

	/**
	 * INHATC System 처리중 버튼 -> 처리완료 버튼
	 * 
	 * @param BoardVO - 게시판
	 * @return resultCode
	 * @throws Exception
	 */
	
	public BoardVO read(Integer bno) throws Exception;

	/**
	 * INHATC System 글 보기
	 * 
	 * @param BoardVO - 게시판
	 * @return BoardVO
	 * @throws Exception
	 */
	
	public void update(BoardVO vo) throws Exception;

	/**
	 * INHATC System 글 수정
	 * 
	 * @param BoardVO - 게시판
	 * @return resultCode
	 * @throws Exception
	 */
	
	public void delete(Integer bno) throws Exception;

	/**
	 * INHATC System 글 삭제
	 * 
	 * @param BoardVO - 게시판
	 * @return resultCode
	 * @throws Exception
	 */
	
	public List<BoardVO> listAll(Criteria cri) throws Exception;

	/**
	 * INHATC System 글 목록
	 * 
	 * @param BoardVO - 게시판
	 * @return List<BoardVO>
	 * @throws Exception
	 */
	
	public int countPaging(Criteria cri) throws Exception;

	/**
	 * INHATC System 글 페이지 관리
	 * 
	 * @param BoardVO - 게시판
	 * @return resultCode
	 * @throws Exception
	 */
	
	public BoardVO find_number(String client_number) throws Exception;

	/**
	 * INHATC System 내선번호 검색 버튼
	 * 
	 * @param BoardVO - 게시판
	 * @return BoardVO
	 * @throws Exception
	 */
	
	public List<BoardVO> BoardList(BoardVO vo) throws Exception;

	/**
	 * INHATC System 내선번호 관리 페이지
	 * 
	 * @param BoardVO - 게시판
	 * @return List<BoardVO>
	 * @throws Exception
	 */
	
	public void managerModify(BoardVO vo) throws Exception;

	/**
	 * INHATC System 내선번호 정보 수정
	 * 
	 * @param BoardVO - 게시판
	 * @return resultCode
	 * @throws Exception
	 */
	
	public void managerInsert(BoardVO boardvo) throws Exception;

	/**
	 * INHATC System 내선번호 정보 추가
	 * 
	 * @param BoardVO - 게시판
	 * @return resultCode
	 * @throws Exception
	 */
	
	public void managerDelete(BoardVO board) throws Exception;

	/**
	 * INHATC System 내선번호 정보 삭제
	 * 
	 * @param BoardVO - 게시판
	 * @return resultCode
	 * @throws Exception
	 */
	
	public List<BoardVO> informationList(BoardVO board) throws Exception;

	/**
	 * INHATC System 내선번호 목록
	 * 
	 * @param BoardVO - 게시판
	 * @return List<BoardVO>
	 * @throws Exception
	 */
	
	public List<BoardVO> listAll_p(BoardVO board) throws Exception;
	
	/**
	 * INHATC System 인쇄
	 * 
	 * @param BoardVO - 게시판
	 * @return List<BoardVO>
	 * @throws Exception
	 */
	
}