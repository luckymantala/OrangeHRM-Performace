# PerformanceModule-CucumberTesting
OrangeHRM - Performance Module Testing using Cucumber and JUnit/TestNG

<h1>Module - Performance</h1>

Framework Used:-

1) Selenium - used for browser automation
2) Cucumber - Behaviour Driven Development
			  I have tried to use all possible features of gherkin language in this project like
			  Background, Scenario, Scenario Outline - Example,
			  Hooks - Brfore & After, Given, When, Then, And, Tags
3) TestNG - to run the features and step definations
4) Maven - this project in developed in maven style and can be executed using command line
		   mvn clean  //to clean target folder)
		   mvn test verify  //to run tests and generate cucumber maven reports	 		
5) Cucumer Maven Report - After the executinon of all the steps, report is generated in target/cucumber-report-html folder/*
6) Log4j - for loggng the steps in log file. Logs are stored in target/log/*
