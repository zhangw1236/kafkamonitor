package com.roger.monitor.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.roger.monitor.kafka.MetricsCollector;

@Controller
public class MonitorController {
	private static Logger logger = LoggerFactory.getLogger(MonitorController.class);
	
	@Autowired
	MetricsCollector metricsCollector;
	
	@RequestMapping(value = "monitor", method = RequestMethod.GET)
	public String doGet(Model model) {
		logger.info(metricsCollector.toString());
		return "monitor";
	}
	
	@RequestMapping(value = "monitor", method = RequestMethod.POST)
	public void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = sdf.format(new Date());
		
		JSONObject data = new JSONObject();
		JSONArray ja = new JSONArray();
		data.put("name", time);
		ja.add(time);
		ja.add(metricsCollector.getOffset().get("producer"));
		data.put("value", ja);
		
		PrintWriter writer = null;
		try {
			writer = httpServletResponse.getWriter();
			writer.write(data.toJSONString());
			writer.flush();
		} catch (IOException e) {
			logger.error("Exception: ", e);
		}
	}
}
