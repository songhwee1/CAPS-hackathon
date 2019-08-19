package com.inhatc.system.chart.dao;

import java.util.List;
import com.inhatc.system.chart.vo.ChartVO;

public interface ChartDAO {
	public List<ChartVO> getChartData(ChartVO vo) throws Exception;

	public List<String> getinstrumentListDataName();

	public List<Integer> getinstrumentListData(ChartVO chartVO);
}