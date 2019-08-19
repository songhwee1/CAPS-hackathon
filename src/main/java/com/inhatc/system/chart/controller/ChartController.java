package com.inhatc.system.chart.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.inhatc.system.chart.service.ChartService;
import com.inhatc.system.chart.vo.ChartVO;

@Controller
public class ChartController {

	@Autowired
	private ChartService service;

	@RequestMapping(value = "/chart", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {

		return "chart/main";
	}

	@ResponseBody
	@RequestMapping(value = "/chart", method = RequestMethod.POST)
	public String getChartData(ChartVO vo, Locale locale, Model model) throws Exception {
		System.out.println(vo.getYear());
		List<ChartVO> ChartData_sum = service.getChartData(vo);

		JSONObject jsonObject = new JSONObject();
		JSONArray classificationArray = new JSONArray();
		JSONObject classificationInfo = new JSONObject();

		List list = new ArrayList<String>();
		List list2 = new ArrayList<String>();
		List list3 = new ArrayList<String>();
		List list4 = new ArrayList<String>();
		for (int a = 0; a < 12; a++) {
			list.add('0');
			list2.add('0');
			list3.add('0');
			list4.add('0');
		}
		for (int i = 0; i < ChartData_sum.size(); i++) {
			int getYear = Integer.parseInt(ChartData_sum.get(i).getYear()) - 1;
			list.remove(Integer.parseInt(ChartData_sum.get(i).getYear()) - 1);
			list2.remove(Integer.parseInt(ChartData_sum.get(i).getYear()) - 1);
			list3.remove(Integer.parseInt(ChartData_sum.get(i).getYear()) - 1);
			list4.remove(Integer.parseInt(ChartData_sum.get(i).getYear()) - 1);
			list.add(getYear, Integer.parseInt(ChartData_sum.get(i).getResearch_classification()));
			list2.add(getYear, Integer.parseInt(ChartData_sum.get(i).getPractice_classification()));
			list3.add(getYear, Integer.parseInt(ChartData_sum.get(i).getNormal_classification()));
			list4.add(getYear, Integer.parseInt(ChartData_sum.get(i).getSum_classification()));

		}
		classificationInfo.put("data", list);
		classificationInfo.put("name", "행정연구실");
		classificationArray.add(classificationInfo);
		
		classificationInfo = new JSONObject();
		classificationInfo.put("data", list2);
		classificationInfo.put("name", "실습실");
		classificationArray.add(classificationInfo);
		
		classificationInfo = new JSONObject();
		classificationInfo.put("data", list3);
		classificationInfo.put("name", "일반강의실");
		classificationArray.add(classificationInfo);
		classificationInfo = new JSONObject();
		classificationInfo.put("data", list4);
		classificationInfo.put("name", "총합");
		classificationArray.add(classificationInfo);

		String jsonInfo = classificationArray.toJSONString();
		System.out.println(jsonInfo);

		return jsonInfo;

	}
	
	@ResponseBody
	@RequestMapping(value = "/chart2", method = RequestMethod.POST)
	public List<Map<String, Object>> getChartData2(ChartVO vo, Locale locale, Model model) throws Exception {
		System.out.println(vo.getYear());
		return service.getChartData2(vo);

	}
}