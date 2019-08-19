package com.inhatc.system.user.dao;

import java.util.List;
import javax.inject.Inject;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;
import com.inhatc.system.user.vo.UserVO;


/**************************************************************************************
 *
 * @Class Name  : UserDaoImpl.java
 * @Description : 사용자를 관리하는 DAO
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
 *   2018.09.18     전세연              최초 생성 
 *   2019.01.10 	전세연		Console 정보 수정
 *   2019.01.19		전세연		회원 추가, 수정, 삭제
 *   2019.01.31		전세연		주석 표시
 *   
 * </pre> **************************************************************************************/

@Service
public class UserDAOImpl implements UserDAO {
	
	@Inject
	private SqlSession session;
	private static String namespace="com.inhatc.mapper.UserMapper";
	
	/*로그인*/
   @Override
	public int login(UserVO uservo) throws Exception{
	
		return session.selectOne(namespace + ".login", uservo);
	}
   
   /*로그인가능여부*/
   @Override
   public UserVO loginInqr(UserVO uservo) throws Exception {
	   
	   System.out.println("(UserDAOImpl)에서 로그인 한 정보를 가져오는 중");
	   
       return (UserVO)session.selectOne(namespace +".loginInqr", uservo);   //많이 없을때 selectOne 한줄만 받겠다. 
       
    }
   
   /*회원 목록*/
	@Override
	public List<UserVO> UserList(UserVO uservo) throws Exception{
		
		System.out.println("(UserDAOImpl)에서 회원정보 목록 불러오기");
		
		return session.selectList(namespace + ".UserList", uservo);
	}
	
	/*id 검색*/
	@Override
	public UserVO find_id(String id) throws Exception{
		
		System.out.println("(UserDAOImpl)에서 회원정보 가져오기");
		
		return session.selectOne(namespace + ".find_id", id);
	}
	
	/*회원 추가*/
   @Override
   public void join(UserVO uservo) throws Exception{
	   
	   System.out.println("(UserDAOImpl)에서 회원등록 진행 중");
	   
		session.insert("com.inhatc.mapper.UserMapper.join",uservo);
   }
	
   /*회원 정보 수정*/
	@Override
	public void userModify(UserVO uservo) throws Exception{
		
		System.out.println("(UserDAOImpl)에서 회원정보 수정");
		
		session.update(namespace + ".userUpdate", uservo);
	}
	
	/*회원 정보 삭제*/
	@Override
	public void userDelete(UserVO uservo) throws Exception{
		
		System.out.println("(UserDAOImpl)에서 회원 정보 삭제");
		
		session.delete(namespace + ".userDelete", uservo);
	
	}
}