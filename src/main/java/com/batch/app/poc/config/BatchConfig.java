package com.batch.app.poc.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.JobFactory;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.batch.app.poc.listener.JobMonitoringListener;
import com.batch.app.poc.processor.BookDetailsProcessor;
import com.batch.app.poc.reader.BookDetailsReader;
import com.batch.app.poc.writer.BookDetailsWriter;

/*AutoConfiguration based Autowiring*/

@Configuration // Every Configuration class is spring bean internally
@EnableBatchProcessing // This will give spring batch features through Autoconfiguration like giving
//InMemoryJobRepository, JobBuilderFactory,StepBuilderFactory and etc.
public class BatchConfig {
	@Autowired
	private JobBuilderFactory jobFactory;
	
	@Autowired
	private StepBuilderFactory stepFactory;
	
	@Autowired
	private BookDetailsWriter bdWriter;
	
	@Autowired
	private BookDetailsReader bdReader;
	
	@Autowired
	private BookDetailsProcessor bdProcessor;
	
	@Autowired
	private JobMonitoringListener jobListener;
	
	// create the step object using StepBuilderFactory
	@SuppressWarnings("unchecked")
	@Bean(name="step1")
	public Step createStep1() {
		System.out.println("BatchConfig.createStep1()");
		return stepFactory.get("step1")
					.<String,String>chunk(2)
					.reader(bdReader)
					.writer(bdWriter)
					.processor(bdProcessor)
					.build();
	}
	
	//create Job using JobBuilderFactory
	@Bean(name = "job1")
	public Job createJob1() {
		return jobFactory.get("job1")
				.incrementer(new RunIdIncrementer())
				.listener(jobListener)
				.start(createStep1())
				.build();
	}
}
