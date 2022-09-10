package com.batch.app.poc.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component("BDProcessor")
public class BookDetailsProcessor implements ItemProcessor<String, String> {

	public BookDetailsProcessor() {
		System.out.println("BookDetailsProcessor.BookDetailsProcessor() 0 Param Constructor");
	}

	@Override
	public String process(String item) throws Exception {
		String bookWithTitle = null;

		if (item.equalsIgnoreCase("Core Java")) {
			bookWithTitle = item + " by HS and PN";
		} else if (item.equalsIgnoreCase("TIJ")) {
			bookWithTitle = item + " by BF";
		} else if (item.equalsIgnoreCase("EJ")) { // Effective Java Book Reader is must
			bookWithTitle = item + " by KS";
		} else if (item.equalsIgnoreCase("BBJ")) {
			bookWithTitle = item + " by JB";
		} else {
			bookWithTitle = null;
		}
		return bookWithTitle;
	}

}
