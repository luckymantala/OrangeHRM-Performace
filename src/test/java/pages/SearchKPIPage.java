package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class SearchKPIPage {

	WebDriver driver;

	@FindBy(xpath = "//form/fieldset/ol/li/select")
	public WebElement jobTitle;

	@FindBy(xpath = "//form/fieldset/p/input")
	public WebElement btnSearch;

	@FindBy(xpath = "//form/div[1]/input[1]")
	public WebElement btnAdd;

	@FindBy(xpath = "//form/div[1]/input[2]")
	public WebElement btnDelete;

	@FindBy(xpath = "//*[@id=\"resultTable\"]/tbody/tr")
	public List<WebElement> KPIListRows;

	@FindBy(xpath = "//*[@id=\"resultTable\"]/tbody/tr[1]/td")
	public List<WebElement> KPIListCols;

	@FindBy(xpath = "/html/body/div[1]/div[3]/div[3]/div[3]/input[1]")
	public WebElement btnConfirmDelete;

	@FindBy(xpath = "//input[@id='dialogDeleteBtn']//following::input")
	public WebElement btnCancelDelete;

	@FindBy(id = "ohrmList_chkSelectAll")
	public WebElement chkBoxSelectAll;
	
	public SearchKPIPage(WebDriver driver) {
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

	public void selectJobTitle(String title) {
		Select s = new Select(jobTitle);
		s.selectByVisibleText(title);
	}

	public void clickSearchBtn() {
		btnSearch.click();
	}

	public void checkAll() {
		chkBoxSelectAll.click();
	}

	public void checkKPI(int index) {
		driver
		.findElement(By.xpath("//*[@id=\"resultTable\"]/tbody/tr[" + index + "]/td[1]/input"))
		.click();
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

	public void clickCancelDeleteBtn() {
		btnCancelDelete.click();
	}

	

}
