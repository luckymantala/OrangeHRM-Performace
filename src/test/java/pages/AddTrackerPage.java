package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class AddTrackerPage {

	WebDriver driver;

	@FindBy(xpath = "//form/fieldset/ol/li[1]/input")
	public WebElement txtTrackerName;

	@FindBy(xpath = "//form/fieldset/ol/li[2]/input[1]")
	public WebElement txtEmpName;

	@FindBy(xpath = "//form/fieldset/ol/table/tbody/tr[2]/td[1]/select")
	public WebElement selectReviewerToAdd;

	@FindBy(xpath = "//form/fieldset/ol/table/tbody/tr[2]/td[2]/input[1]")
	public WebElement btnAddReviewer;

	@FindBy(xpath = "//form/fieldset/ol/table/tbody/tr[2]/td[2]/input[2]")
	public WebElement btnRemoveReviewer;

	@FindBy(xpath = "//form/fieldset/ol/table/tbody/tr[2]/td[3]/select")
	public WebElement selectReviewerAdded;

	@FindBy(xpath = "//form/fieldset/p/input[1]")
	public WebElement btnSave;

	@FindBy(xpath = "//form/fieldset/p/input[2]")
	public WebElement btnCancel;
	
	@FindBy(xpath = "//form/div[1]/input[1]")
	public WebElement btnAdd;

	@FindBy(xpath = "//form/div[1]/input[2]")
	public WebElement btnDelete;

	@FindBy(xpath = "/html/body/div[1]/div[3]/div[3]/div[3]/input[1]")
	public WebElement btnConfirmDelete;
	
	@FindBy(xpath = "//form/div[4]/table/thead/tr/th[1]/input")
	public WebElement allCheckbox;
	
	public AddTrackerPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void clickAddBtn() {
		btnAdd.click();
	}
	
	public void clickDeleteBtn() {
		btnDelete.click();
	}
	
	public void clickConfirmDeleteBtn() {
		btnConfirmDelete.click();
	}
	
	public void enterTracker(String t) {
		txtTrackerName.clear();
		txtTrackerName.click();
		txtTrackerName.sendKeys(t);
	}

	public void enterEmpName(String name) {
		txtEmpName.clear();
		txtEmpName.click();
		txtEmpName.sendKeys(name);
	}

	public void selectReviewerToAdd(String name) {
		Select s = new Select(selectReviewerToAdd);
		s.selectByVisibleText(name);
	}

	public void selectReviewerToRemove(String name) {
		Select s = new Select(selectReviewerAdded);
		s.selectByVisibleText(name);
	}

	public void clickAddReviewerBtn() {
		btnAddReviewer.click();
	}
	

	public void clickRemoveBtn() {
		btnRemoveReviewer.click();
	}
	
	public void clickSaveBtn() {
		btnSave.click();	
	}

	public void selectAllCheckboxes() {
		allCheckbox.click();
	}

	public void selectCheckBox(int index) {
		driver.findElement(By
				.xpath("/html/body/div[1]/div[3]/div[2]/div[2]/form/div[4]/table/tbody/tr[" + index + "]/td[1]/input"))
				.click();
	}

}
