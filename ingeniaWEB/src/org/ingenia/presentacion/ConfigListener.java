package org.ingenia.presentacion;

import javax.servlet.ServletContextEvent;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConfigListener {
	static Logger logger = LogManager.getLogger(ConfigListener.class);

	public void contextInitialized(ServletContextEvent event) {
		logger.debug("org.apache.el.parser.COERCE_TO_ZERO:"
				+ System.getProperty("org.apache.el.parser.COERCE_TO_ZERO"));
		System.setProperty("org.apache.el.parser.COERCE_TO_ZERO", "false");
	}

	public void contextDestroyed(ServletContextEvent event) {
		return;
	}
}
