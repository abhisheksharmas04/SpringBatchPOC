package com.batch.app.poc.writer;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

@Component("BDWriter")
public class BookDetailsWriter implements ItemWriter<String> {

	@Override // This List of String represent processed Items
	public void write(List<? extends String> items) throws Exception {
		System.out.println("BookDetailsWriter.write()");
		 items.forEach(System.out::println);
	}

}
