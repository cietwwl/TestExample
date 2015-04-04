package cn.dyz.log_test;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.xml.DOMConfigurator;

public class Log4jApp {
	public static void main(String[] args) {
		DOMConfigurator.configure("D:/git_project/TestExample/src/main/java/cn/dyz/log_test/log4j.xml");// 加载.xml文件
		// PropertyConfigurator.configure("E:/study/log4j/log4j.properties");//加载.properties文件
		Logger log = Logger.getLogger("org.zblog.test");
		log.info("test syslog4j");
	}
}