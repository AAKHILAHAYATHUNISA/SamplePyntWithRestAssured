package com.Assesment.runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "src/test/resources/com/Assesment/feature",
		glue="com.Assesment.steps",
	 	dryRun = false,
	 	tags = "@api"
//	 	plugin= {"pretty"}
		)

public class Runner {

}
