package com.inhatc.system.user.service;

import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.inhatc.system.user.dao.UserDAO;
import com.inhatc.system.user.vo.UserVO;

/**************************************************************************************
*
* @Class Name  : UserServiceImpl.java
* @Description : 사용자를 관리하는 service
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
*   2019.01.19		전세연		회원 추가, 수정, 삭제
*   2019.01.31		전세연		주석 표시   
* 
* </pre> **************************************************************************************/

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDAO;

	/*로그인*/
	@Override
	public int login(UserVO uservo) throws Exception {
		
		int resultCode = 0;
		
		 for(int i=0; i<10; i++) {    //반복된 인증시도 제한 기능
			 
			 resultCode = userDAO.login(uservo);
		
		 }
		
		
		return resultCode;
	}

	/*로그인가능여부*/
	public UserVO loginInqr(UserVO uservo, HttpSession ses) throws Exception {
				
		UserVO loginModel = userDAO.loginInqr(uservo); // db 에서 셀렉트 해온 데이터 -> ㅣoginModel 

		if (loginModel != null) {                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  

			uservo.setLoginType("Y");
			
			ses.setAttribute("LOGIN_ID", loginModel.getId()); 
			ses.setAttribute("LOGIN_PW", loginModel.getPw()); 
			ses.setAttribute("LOGIN_NAME", loginModel.getManager_name()); 
			ses.setAttribute("LOGIN_BELONG", loginModel.getManager_belong());
			ses.setMaxInactiveInterval(60*60*60) ;
			
			System.out.println("(UserServiceImpl) 근로학생의 소속분류는 " + loginModel.getManager_belong());
			System.out.println("(UserServiceImpl) 근로학생의 이름은 " + loginModel.getManager_name());

		} else {
			
			uservo.setLoginType("N");
		}

		return uservo;

	}
	
	/*회원 목록*/
	@Override
	public List<UserVO> UserList(UserVO uservo, HttpSession ses) throws Exception {
		
		return userDAO.UserList(uservo);
	}
	
	/*id 검색*/
	@Override
	public UserVO find_id(String id) throws Exception {
		
		return userDAO.find_id(id);
	}
	
	/*회원 추가*/
	public void join(UserVO uservo, HttpSession ses) throws Exception{
		
		System.out.println("(User_ServiceImpl) 회원 정보 추가");
		
		userDAO.join(uservo);
	}
	
	/*회원 정보 수정*/
	@Override
	public void userModify(UserVO uservo, HttpSession ses) throws Exception {

		System.out.println("(User_ServiceImpl) 회원 정보 수정");

		userDAO.userModify(uservo);
	}
	
	/*회원 정보 삭제*/
	@Override
	public void userDelete(UserVO uservo) throws Exception {

		System.out.println("(User_ServiceImpl) 내선번호 정보 삭제");
		
		userDAO.userDelete(uservo);
	}
	
}
