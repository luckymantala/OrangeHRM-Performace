package utils;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class FailedScenario {
	
	public static void capture(WebDriver driver, String name) {
		Date date = new Date();
		String currentDate = date.toString().replace(" ", "-").replace(":", "-");
		File ss = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(ss, new File("target/screenshot/"+name+"-"+currentDate+".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
