package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class EvaluationFormPage {
	WebDriver driver;

	public EvaluationFormPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//form/ol/li[1]/textarea")
	public WebElement txtFinalCmt;
	
	@FindBy(xpath = "//form/ol/li[2]/input")
	public WebElement txtFinalRating;
	
	@FindBy(xpath = "//form/ol/li[3]/input")
	public WebElement dateOfCompletion;
	
	@FindBy(xpath = "//form/p/input[1]")
	public WebElement btnSave;
	
	@FindBy(xpath = "//form/p/input[3]")
	public WebElement btnBack;
	
	@FindBy(xpath = "//form/p/input[2]")
	public WebElement btnComlete;
	
	@FindBy(xpath = "/html/body/div[1]/div[3]/div/div[2]/div[2]/div/div[3]/input[1]")
	public WebElement btnConfirmCompletion;
	
	public void clickConfComp() {
		btnConfirmCompletion.click();
	}
	
	public void enterFinalComment(String comment) {
		txtFinalCmt.click();
		txtFinalCmt.sendKeys(comment);
	}
	
	public void enterFinalRating(String rating) {
		txtFinalRating.click();
		txtFinalRating.sendKeys(rating);
	}
	
	public void enterDate(String date) {
		dateOfCompletion.click();
		dateOfCompletion.sendKeys(date);
		dateOfCompletion.sendKeys(Keys.ENTER);
	}
	
	public void clickOnSaveBtn() {
		btnSave.click();
	}
	
	public void clickOnCompleteBtn() {
		btnComlete.click();
	}
	
	public void clickOnBackBtn() {
		btnBack.click();
	}
	
	
	
	
	
	
	
	
}
