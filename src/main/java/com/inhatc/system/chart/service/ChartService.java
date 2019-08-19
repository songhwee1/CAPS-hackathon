package com.inhatc.system.chart.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.transaction.annotation.Transactional;

import com.inhatc.system.chart.vo.ChartVO;




/**************************************************************************************
 *
 * @Class Name  : MemberService.java
 * @Description : 사용자 서비스
 *                  
 * @Modification Information  
 * <p>
 * <b>NOTE</b>: 
 * @author 박충규
 * @since  2017.09.18
 * @version 1.0
 * @see <pre>
 *  == 개정이력(Modification Information) ==
 *   
 *   수정일                   수정자              수정내용
 *  ------------    --------    ---------------------------
 *   2017.09.18     박충규              최초 생성 
 * 
 * </pre> **************************************************************************************/


@Transactional
public interface ChartService {
 
    /**
     * INHATC MALL 사용자 등록 , 수정
     * @param SysUserModel - 사용자
     * @return None
     * @throws Exception 
     */
	
	public List<ChartVO> getChartData(ChartVO chartvo)throws Exception;

	public List<Map<String, Object>> getChartData2(ChartVO chartvo);

	
}
