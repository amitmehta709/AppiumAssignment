package com.assignment.appium.utilities;

import java.io.IOException;
import java.util.Properties;

public class AutomationProperties {

	private static Properties p = null;

	private synchronized static void init() {
		try {
			p = new Properties();
			p.load(AutomationProperties.class.getClassLoader().getResourceAsStream("init.properties"));
		} catch (IOException e) {
			System.out.println(e.getStackTrace());
		}
	}

	public static String getProperty(String key) {
		if (p == null) {
			init();
		}

		return p.getProperty(key);

	}
}
