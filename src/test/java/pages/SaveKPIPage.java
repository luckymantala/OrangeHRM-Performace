package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class SaveKPIPage {
	
	WebDriver driver;
	JavascriptExecutor executor;
	
	@FindBy(xpath = "//form/fieldset/ol/li[1]/select")
	public WebElement selectJobTitle;
	
	@FindBy(xpath = "//form/fieldset/ol/li[2]/input")
	public WebElement txtKPI;
	
	@FindBy(xpath = "//form/fieldset/p/input[1]")
	public WebElement btnSave;
	
	@FindBy(xpath = "//form/fieldset/p/input[2]")
	public WebElement btnCancel;
	
	public SaveKPIPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}	
	
	public void selectJobTitle(String title) {
		Select s = new Select(selectJobTitle);
		s.selectByVisibleText(title);
	}
	
	public void enterKPI(String KPI) {
		txtKPI.click();
		txtKPI.sendKeys(KPI);
	}
	
	public void clickSaveBtn() {
		btnSave.click();
	}
	
	public void clickCancelBtn() {
		btnCancel.click();
	}
}
