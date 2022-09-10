package com.batch.app.poc.runner;

import java.util.Random;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BatchProcessingTestRunner implements CommandLineRunner {
	
	@Autowired
	private JobLauncher laucher;
	
	@Autowired
	private Job job;

	@Override
	public void run(String... args) throws Exception {
		//Preapre Job Parameter
		JobParameters params = new JobParametersBuilder()
							.addLong("jobId", Long.valueOf(new Random().nextInt(10000))).toJobParameters();
		
		JobExecution execution = laucher.run(job, params); // We can take null if we do not want to pass parameter
		System.out.println("Job Executin Status: " + execution.getStatus());
		System.out.println("Steps of Job: " + execution.getExitStatus());
		System.out.println("Job Id: " + execution.getJobId());
	}

}
