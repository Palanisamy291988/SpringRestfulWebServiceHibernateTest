package com.javaspringclub.controller;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestController {
	
	static final Logger logger = LoggerFactory.getLogger(TestController.class);
	
	public static void main(String[] args) {
		
		Date date = new Date();
		
		logger.info("TestController is called.");
		
		logger.debug("TestController debug:"+ date);
		
		System.out.println("TestController debug:"+date);
		
		
		logger.info("info end");
	}

}
