package utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JScriptExecUtils {

	WebDriver driver;
	JavascriptExecutor executor;
	
	public JScriptExecUtils(WebDriver driver) {
		this.driver = driver;
		executor = (JavascriptExecutor)driver;
	}
	
	public void jsClick(WebElement element) {
		executor.executeScript("arguments[0].click();", element);
	}
	
	public  void jsSendKeys(WebElement element, String text) {
		executor.executeScript("arguments[0].value= \'"+text+"\'", element);
	}
	
	
}
