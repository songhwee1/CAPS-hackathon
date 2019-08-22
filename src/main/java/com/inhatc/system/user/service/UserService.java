package com.inhatc.system.user.service;

import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.transaction.annotation.Transactional;
import com.inhatc.system.user.vo.UserVO;



@Transactional
public interface UserService {
 
	
	public int login(UserVO uservo)throws Exception;

    /**
     * INHATC System 로그인
     * @param UserVO - 사용자
     * @return resultCode
     * @throws Exception 
     */
	
	public UserVO loginInqr(UserVO uservo, HttpSession ses)throws Exception;
	
	 /**
     * INHATC System 로그인가능여부 
     * @param UserVO - 사용자
     * @return UserVO
     * @throws Exception 
     */
	
	public void join(UserVO uservo, HttpSession ses) throws Exception;

	 /**
    * INHATC System 회원 추가
    * @param UserVO - 사용자
    * @return resultCode
    * @throws Exception 
    */
	
	public List<UserVO> UserList(UserVO uservo, HttpSession ses) throws Exception;

	 /**
	 * INHATC System 회원 목록
	 * @param UserVO - 사용자
	 * @return List<UserVO>
	 * @throws Exception 
	 */
	
	public UserVO find_id(String id) throws Exception;

	 /**
	 * INHATC System id 검색
	 * @param UserVO - 사용자
	 * @return UserVO
	 * @throws Exception 
	 */
	
	public void userModify(UserVO uservo, HttpSession ses) throws Exception;

	 /**
	 * INHATC System 회원 정보 수정
	 * @param UserVO - 사용자
	 * @return resultCode
	 * @throws Exception 
	 */
	
	public void userDelete(UserVO uservo) throws Exception;
	
	 /**
	 * INHATC System 회원 정보 삭제
	 * @param UserVO - 사용자
	 * @return resultCode
	 * @throws Exception 
	 */
	
}
