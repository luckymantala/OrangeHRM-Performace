package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class SearchReviewPage {
	WebDriver driver; 
	
	@FindBy(xpath = "//form/fieldset/ol/li[1]/input")
	public WebElement txtEmpName;
	
	@FindBy(xpath = "//form/fieldset/ol/li[2]/select")
	public WebElement jobTitle;
	
	@FindBy(xpath = "//form/fieldset/p/input[1]")
	public WebElement btnSearch;
	
	@FindBy(xpath = "//form/fieldset/p/input[2]")
	public WebElement btnReset;
	
	@FindBy(xpath = "//form/div[1]/input[2]")
	public WebElement btnDelete;
	
	@FindBy(xpath = "//form/div[4]/table/thead/tr/th[1]/input")
	public WebElement allCheckbox;
	
	@FindBy(xpath = "//form/div[1]/input[1]")
	public WebElement btnAdd;
	
	@FindBy(xpath = "/html/body/div[1]/div[3]/div[3]/div[3]/input[1]")
	public WebElement btnConfirmDelete;
	
	
	public SearchReviewPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public List<WebElement> returnRows() {
		try {
			return driver
					.findElements(By.xpath("/html/body/div[1]/div[3]/div[2]/div[2]/form/div[4]/table/tbody/tr"));
		} catch (Exception e) {
			return null;
		}
	}
	
	public List<WebElement> returnCols() {
		try {
			return driver
					.findElements(By.xpath("/html/body/div[1]/div[3]/div[2]/div[2]/form/div[4]/table/tbody/tr[1]/td"));
		} catch (Exception e) {
			return null;
		}
	}
	
	public void clickBtnConfirmDelete() {
		btnConfirmDelete.click();
	}
	
	public void clickAddBtn() {
		btnAdd.click();
	}
	
	public void enterEmpName(String name) {
		txtEmpName.click();
		txtEmpName.sendKeys(name);
	}
	
	public void seletJobTitle(String title) {
		Select s = new Select(jobTitle);
		s.selectByVisibleText(title);
	}

	public void clickSearchBtn() {
		btnSearch.click();
	}
	
	public void clickResetBtn() {
		btnReset.click();
	}
	
	public void clickDeleteBtn() {
		btnDelete.click();
	}
	
	public void selectAllCheckbox() {
		allCheckbox.click();
	}
	
	public void selectCheckbox(int index) {
		driver.findElement(By.xpath(
		"/html/body/div[1]/div[3]/div[2]/div[2]/form/div[4]/table/tbody/tr["+index+"]/td[1]/input")
		).click();
	}
}