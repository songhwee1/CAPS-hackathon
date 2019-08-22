package com.inhatc.system.chart.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.transaction.annotation.Transactional;

import com.inhatc.system.chart.vo.ChartVO;


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
