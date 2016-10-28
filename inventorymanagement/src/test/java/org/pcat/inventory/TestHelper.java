package org.pcat.inventory;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;

public class TestHelper {

	//TODO:  This is not threadsafe and needs refactored
	private Level saveRootLoggerLevel = null;
	private Level testRootLoggerLevel = null;

	public TestHelper() {
		super();
	}

	public Level getRootTestLoggerLevel() {
		return testRootLoggerLevel;
	}

	public void resetSaveRootLoggerLevel() {
		Logger root = (Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
		root.setLevel(saveRootLoggerLevel);
	}

	public void saveCurrentRootLogging() {
		saveRootLoggerLevel = ((Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME)).getLevel();
	}

	public void setRootTestLoggerLevel(Level level) {
		Logger root = (Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
		root.setLevel(level);
	}

}
