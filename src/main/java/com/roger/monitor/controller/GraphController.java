package com.roger.monitor.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

@Controller
@RequestMapping("/graph")
public class GraphController {
	private static Logger logger = LoggerFactory.getLogger(GraphController.class);
	
	@RequestMapping(method = RequestMethod.GET)
	public void doServiceGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		logger.info("get request");
		PrintWriter writer = null;

		try {
			setResponseHeader(httpServletResponse);
			
			writer = httpServletResponse.getWriter();
			writer.write(generateData());
			
			writer.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (null != writer) {
				try {
					writer.close();
				} catch (Exception e) {
				}
			}
		}
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public void doServicePost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		logger.info("post request");
		
		logger.info("name: " + httpServletRequest.getParameter("name"));
		logger.info("para: " + httpServletRequest.getParameter("para"));
		
		PrintWriter writer = null;
		
		try {
			setResponseHeader(httpServletResponse);
			
			writer = httpServletResponse.getWriter();
			writer.write(generateData());
			
			writer.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (null != writer) {
				try {
					writer.close();
				} catch (Exception e) {
				}
			}
		}
	}
	
	private void setResponseHeader(HttpServletResponse response) {
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Access-Control-Allow-Credentials", "false");
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Headers", "Content-Type");
		response.setHeader("Access-Control-Allow-Methods", "GET");
		response.setHeader("Allow", "GET");
		
		response.addHeader("Content-Type", "text/plain;charset=UTF-8");
	}
	
	public String generateData() {
		JSONObject data = new JSONObject();
		JSONArray ja = new JSONArray();
		
		JSONObject data0 = new JSONObject();
		data0.put("genre", "Sports");
		data0.put("sold", 275);
		ja.add(0, data0);
		
		JSONObject data1 = new JSONObject();
		data1.put("genre", "Strategy");
		data1.put("sold", 115);
		ja.add(0, data1);
		
		JSONObject data2 = new JSONObject();
		data2.put("genre", "Action");
		data2.put("sold", 120);
		ja.add(0, data2);
		
		JSONObject data3 = new JSONObject();
		data3.put("genre", "Shooter");
		data3.put("sold", 350);
		ja.add(0, data3);
		
		JSONObject data4 = new JSONObject();
		data4.put("genre", "Other");
		data4.put("sold", 150);
		ja.add(0, data4);
		
		data.put("data", ja);
		
		return data.toJSONString();
	}
	
	public static void main(String[] args) {
		GraphController m = new GraphController();
		System.out.println(m.generateData());
	}
}
