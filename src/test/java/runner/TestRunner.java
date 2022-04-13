package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
			features = {"src/test/resources/Features"},
			glue = {"steps"},
			plugin = {"pretty",
	                "html:target/cucumber-reports/cucumber.html",
	                "json:target/cucumber-reports/cucumber.json"},
//			tags = "@MenuCheck",
			
			monochrome = true
		)
public class TestRunner extends AbstractTestNGCucumberTests{

	
}
