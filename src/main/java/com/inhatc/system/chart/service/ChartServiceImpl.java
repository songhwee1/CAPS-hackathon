package com.inhatc.system.chart.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inhatc.system.chart.dao.ChartDAO;
import com.inhatc.system.chart.vo.ChartVO;

@Service
public class ChartServiceImpl implements ChartService {

	@Autowired
	private ChartDAO chartDAO;

	@Override
	public List<ChartVO> getChartData(ChartVO chartvo) throws Exception {
		return chartDAO.getChartData(chartvo);
	}

	@Override
	public List<Map<String, Object>> getChartData2(ChartVO chartvo) {
		// TODO Auto-generated method stub
		
		ArrayList<Map<String, Object>> jsonList = new ArrayList<Map<String, Object>>();
		
		List<String> instrumentList = chartDAO.getinstrumentListDataName();
		String ins_name="";
		for(int i = 0; i < instrumentList.size(); i++) {
			Map<String, Object> getChartData2 = new HashMap<String, Object>();
			chartvo.setInstrument(instrumentList.get(i));
			List<Integer> dataList = chartDAO.getinstrumentListData(chartvo);
			int ins_list = Integer.parseInt(instrumentList.get(i));
			
			switch(ins_list) {
			case 1:
				ins_name = "모니터";
				break;
			case 2:
				ins_name = "프린터";
				break;
			case 3: 
				ins_name = "PC";
				break;
			case 4: 
				ins_name = "인터넷";
				break;
			case 5:
				ins_name = "전화기";
				break;
			case 6: 
				ins_name = "프로젝터 빔";
				break;
			case 7:
				ins_name = "앰프";
				break;
			case 8:
				ins_name = "소프트웨어 설치";
				break;
			default: 
				ins_name = "기타";
				break;
			
			}
			getChartData2.put("name", ins_name);
			getChartData2.put("data", dataList.toArray(new Integer[dataList.size()]));
			
			jsonList.add(getChartData2);
			System.out.println(jsonList);
		}
		return jsonList;
	}
	
	

}
