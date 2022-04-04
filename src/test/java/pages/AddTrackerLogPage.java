package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class AddTrackerLogPage {

	WebDriver driver;

	public AddTrackerLogPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "btnAdd")
	public WebElement btnAdd;

	@FindBy(id = "addperformanceTrackerLog_log")
	public WebElement txtLog;

	@FindBy(id = "addperformanceTrackerLog_achievement")
	public WebElement achivment;

	@FindBy(id = "addperformanceTrackerLog_comment")
	public WebElement txtComment;

	@FindBy(id = "saveBtn")
	public WebElement btnSave;

	@FindBy(id = "resetBtn")
	public WebElement btnReset;

	public void enterLog(String log) {
		txtLog.sendKeys(log);
	}

	public void selectAchievement(String val) {
		Select s = new Select(achivment);
		s.selectByVisibleText(val);
	}

	public void enterComment(String comment) {
		txtComment.sendKeys(comment);
	}
	
	public void clickAddBtn() {
		btnAdd.click();
	}
	
	public void clickSaveBtn() {
		btnSave.click();
	}
	
	public void clickClearBtn() {
		btnSave.click();
	}
	
	
	
	
	
	
	
	
	
}
