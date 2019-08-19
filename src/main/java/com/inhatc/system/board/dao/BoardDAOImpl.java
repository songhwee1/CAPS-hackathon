package com.inhatc.system.board.dao;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.inhatc.system.board.vo.BoardVO;
import com.inhatc.system.board.vo.Criteria;

/**************************************************************************************
*
* @Class Name  : BoardDAOImpl.java
* @Description : 게시판을 관리하는DAO      
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

@Repository
public class BoardDAOImpl implements BoardDAO{
	
	@Autowired
	private SqlSession session;
	
	private static String namespace="com.inhatc.mapper.BoardMapper";
	
	/*글 등록*/
	@Override
	public void create(BoardVO boardvo) throws Exception{
		
		System.out.println("(Baord_DAOImpl)에서 글쓰기 진행 중");
		
		session.insert("com.inhatc.mapper.BoardMapper.create",boardvo);
	}

	/*글 보기*/
	@Override
	public BoardVO read(Integer bno) throws Exception{
		
		System.out.println("(Baord_DAOImpl)에서 글읽기 진행 중");
		
		return session.selectOne(namespace + ".read", bno);
	}
	
	/*처리대기 버튼 -> 처리중 버튼*/
	@Override
	public void updateIng(BoardVO board) throws Exception{
		
		System.out.println("(Baord_DAOImpl)에서 처리현황을 알려줌, 처리중");
		
		session.update(namespace + ".updateIng", board);
	}
	
	/*처리중 버튼 -> 처리완료 버튼*/
	@Override
	public void updateEnd(BoardVO board) throws Exception{
		
		System.out.println("(Baord_DAOImpl)에서 처리현황을 알려줌, 처리완료");
		
		session.update(namespace + ".updateEnd", board);
	}
	
	/*글 수정*/
	@Override
	public void update(BoardVO vo) throws Exception{
		
		System.out.println("(Baord_DAOImpl)에서 글 수정");
		
		session.update(namespace + ".update", vo);
	}

	/*글 삭제*/
	@Override
	public void delete(Integer bno) throws Exception{
		
		System.out.println("(Baord_DAOImpl)에서 글 삭제 진행 중");
		session.delete(namespace + ".delete", bno);
	}
	
	/*글 목록*/
	@Override
	public List<BoardVO> listAll(Criteria cri) throws Exception{
		
		System.out.println("(Baord_DAOImpl)에서 글 목록 불러오기");
		
		return session.selectList(namespace + ".listAll", cri);
	}
	/*List<BoardVO>: 배열*/
	
	/*글 페이지 관리*/
	@Override
	public int countPaging(Criteria cri) throws Exception{
		
		return session.selectOne(namespace +".countPaging", cri);
	}
	
	/*내선번호 검색 버튼*/
	@Override
	public BoardVO find_number(String client_number) throws Exception{
		
		return session.selectOne(namespace + ".find_number", client_number);
	}
	
	/*내선번호 관리 페이지*/
	@Override
	public List<BoardVO> BoardList(BoardVO vo) throws Exception{
		
		System.out.println("(Baord_DAOImpl)에서 전화번호 목록 불러오기");
		
		return session.selectList(namespace + ".BoardList", vo);
	}

	/*내선번호 정보 수정*/
	@Override
	public void managerModify(BoardVO vo) throws Exception{
		
		System.out.println("(Baord_DAOImpl)에서 내선번호 정보 수정");
		
		session.update(namespace + ".managerUpdate", vo);
	}

	/*내선번호 정보 추가*/
	@Override
	public void managerInsert(BoardVO boardvo) throws Exception{
		
		System.out.println("(Baord_DAOImpl)에서 내선번호 정보 추가");
		
		session.insert("com.inhatc.mapper.BoardMapper.managerInsert",boardvo);
	}
	
	/*내선번호 정보 삭제*/
	@Override
	public void managerDelete(BoardVO board) throws Exception{
		
		System.out.println("(Baord_DAOImpl)에서 내선번호 정보 삭제");
		
		session.delete(namespace + ".managerDelete", board);
	
	}
	
	/*내선번호 목록*/
	@Override
	public List<BoardVO> informationList(BoardVO board) throws Exception{
		
		System.out.println("(Baord_DAOImpl)에서 내선번호 정보 목록 불러오기");
		
		return session.selectList(namespace + ".informationList", board);
	}
	
	/*인쇄*/
	@Override
	public List<BoardVO> listAll_p(BoardVO board) throws Exception{
		
		System.out.println("(Baord_DAOImpl)에서 인쇄부분");
		
		return session.selectList(namespace + ".listAll_p", board);
	}
}
	
