package com.inhatc.system.chart.dao;



import java.util.List;


import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.inhatc.system.chart.vo.ChartVO;

@Repository
public class ChartDAOImpl implements ChartDAO{
	
	@Autowired
	private SqlSession session;
	
	private static String namespace="com.inhatc.mapper.ChartMapper";
	

	@Override
	public List<ChartVO> getChartData(ChartVO vo) throws Exception {
		// TODO Auto-generated method stub
		return session.selectList("com.inhatc.mapper.ChartMapper.getChartData",vo);
	}


	@Override
	public List<String> getinstrumentListDataName() {
		// TODO Auto-generated method stub
		
		return session.selectList("com.inhatc.mapper.ChartMapper.getinstrumentListDataName");
	}


	@Override
	public List<Integer> getinstrumentListData(ChartVO chartVO) {
		// TODO Auto-generated method stub
		return session.selectList("com.inhatc.mapper.ChartMapper.getinstrumentListData" , chartVO);
	}


	
}
	
