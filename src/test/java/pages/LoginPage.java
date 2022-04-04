package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	static WebDriver driver;
	
	@FindBy(id = "txtUsername")	
	WebElement txtUsername;
	
	@FindBy(id = "txtPassword")
	WebElement txtPass;
	
	@FindBy(id = "btnLogin")
	WebElement btnLogin;
	
	public LoginPage(WebDriver driver) {
		LoginPage.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void enterUsername(String name) {
		txtUsername.sendKeys(name);
	}
	
	public void enterPass(String pass) {
		txtPass.sendKeys(pass);
	}
	
	public void clickLoginBtn() {
		btnLogin.click();
	}
	
	public void logout( ) {
		driver.findElement(By.xpath("/html/body/div[1]/div[1]/a[2]")).click();
		driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[10]/ul/li[3]/a")).click();
	}
	
}
