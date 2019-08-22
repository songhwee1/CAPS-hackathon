package com.inhatc.system.board.dao;

import java.util.List;
import com.inhatc.system.board.vo.BoardVO;
import com.inhatc.system.board.vo.Criteria;


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