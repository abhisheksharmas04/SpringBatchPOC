package com.batch.app.poc.reader;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.stereotype.Component;



@Component("BDReader")
public class BookDetailsReader implements ItemReader {
	
	String book[] = new String[] {"Core Java", "TIJ", "EJ", "BBJ"};
	int count = 0;

	public BookDetailsReader() {
		System.out.println("BookDetailsReader.BookDetailsReader() 0 Param Constructor");
	}

	@Override
	public Object read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		if(count < book.length) {
			return book[count++];
		}else {
			return null;
		}
		
	}

}
