package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DashboardPage {

	WebDriver driver;
	@FindBy(xpath = "/html/body/div[1]/div[2]/ul/li[7]/a")
	public WebElement performanceMenu;
	
	@FindBy(xpath = "/html/body/div[1]/div[2]/ul/li[7]/ul/li[1]")
	public WebElement configMenu;

	@FindBy(xpath = "/html/body/div[1]/div[2]/ul/li[7]/ul/li[1]/ul/li[1]/a")
	public WebElement KPIMenu;

	@FindBy(xpath = "/html/body/div[1]/div[2]/ul/li[7]/ul/li[1]/ul/li[2]/a")
	public WebElement trackerMenu;

	@FindBy(xpath = "/html/body/div[1]/div[2]/ul/li[7]/ul/li[2]/a")
	public WebElement manageReviewMenu;
	
	@FindBy(xpath = "/html/body/div[1]/div[2]/ul/li[7]/ul/li[2]/ul/li[1]/a")
	public WebElement manageReviewMenuLink;
	
	@FindBy(xpath = "/html/body/div[1]/div[2]/ul/li[8]/a")
	public WebElement dashboardMenu;
	
	@FindBy(id = "menu_performance_viewEmployeePerformanceTrackerList")
	public WebElement trackerListMenu;

	public DashboardPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void goToSearchKPIPage() {
		Actions action = new Actions(driver);

		action.moveToElement(performanceMenu).perform();
		action.moveToElement(configMenu).perform();
		action.click(KPIMenu).perform();

	}

	public void goToAddTrackerPage( ) {
		Actions action = new Actions(driver);
		action.moveToElement(performanceMenu).perform();
		action.moveToElement(configMenu).perform();
		action.click(trackerMenu).perform();
	}

	public void goToManageReviewPage() {
		Actions action = new Actions(driver);
		action.moveToElement(performanceMenu).perform();
		action.moveToElement(manageReviewMenu).perform();
		action.click(manageReviewMenuLink).perform();
	}
	
	public void goToTrackerListPage() {
		Actions action = new Actions(driver);
		action.moveToElement(performanceMenu).perform();
		action.click(trackerListMenu).perform();
	}

	public void goToDashboard() {
		dashboardMenu.click();
	}
}
