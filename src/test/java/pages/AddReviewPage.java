package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddReviewPage {
	
	WebDriver driver;

	@FindBy(xpath = "//form/fieldset/ol/li/input")
	public WebElement txtEmpName;
	
	@FindBy(xpath = "//form/fieldset/div/ol[1]/li/input")
	public WebElement txtSupName;
	
	@FindBy(xpath = "//form/fieldset/div/ol[2]/li/input")
	public WebElement dateStart;
	
	@FindBy(xpath = "//form/fieldset/div/ol[2]/li[2]/input")
	public WebElement dateEnd;
	
	@FindBy(xpath = "//form/fieldset/div/ol[2]/li[3]/input")
	public WebElement dateDue;
	
	@FindBy(xpath = "//form/fieldset/div/p/input[1]")
	public WebElement btnSave;
	
	@FindBy(xpath = "//form/fieldset/div/p/input[2]")
	public WebElement btnActivate;
	
	@FindBy(xpath = "//form/fieldset/div/p/input[3]")
	public WebElement btnBack;
	
//	@FindBy(xpath = "//*[@id=\"btnAdd\"]")
//	WebElement btnAdd;
	
	public AddReviewPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void enterEmpName(String name) {
		txtEmpName.click();
		txtEmpName.sendKeys(name);
		txtEmpName.sendKeys(Keys.ENTER);
	}
	
	public void enterSupName(String name) {
//		txtSupName.click();
		txtSupName.sendKeys(name);
//		txtSupName.sendKeys(Keys.ENTER);
	}
	
	public void enterStartDate(String date) {
		dateStart.click();
		dateStart.sendKeys(date);
		dateStart.sendKeys(Keys.ENTER);
	}
	
	public void enterEndDate(String date) {
		dateEnd.click();
		dateEnd.sendKeys(date);
		dateEnd.sendKeys(Keys.ENTER);
	}
	
	public void enterDueDate(String date) {
		dateDue.click();
		dateDue.sendKeys(date);
		dateDue.sendKeys(Keys.ENTER);
		
	}
	
	public void clickSaveBtn() {
		btnSave.click();
		
	}
	
	public void clickActivateBtn() {
		btnActivate.click();
	}
	
	public void clickBackBtn() {
		btnBack.click();
	}
	
//	public void clickAddBtn() {
//		btnAdd.click();
//	}
}
