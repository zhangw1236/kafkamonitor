package com.roger.monitor;

import javax.servlet.ServletException;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import org.apache.log4j.PropertyConfigurator;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

public class KafkaMonitor {
	private static Logger logger = LoggerFactory.getLogger(KafkaMonitor.class);

	public static void main(String[] args) {
		String path = KafkaMonitor.class.getResource("/").getPath();
		//PropertyConfigurator.configureAndWatch(path + "log4j.properties", 5 * 60000);
		
		Tomcat tomcat = new Tomcat();
		tomcat.setPort(8088);
		tomcat.getConnector().setURIEncoding("UTF-8");

		try {
			tomcat.addWebapp("", path.substring(0, path.indexOf("target")) + "src/main/webapp");
			tomcat.start();

			logger.info("Started tomcat");
			tomcat.getServer().await();
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (LifecycleException e) {
			e.printStackTrace();
		}
	}

}
