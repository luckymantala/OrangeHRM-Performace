package steps;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.Status;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import pages.AddReviewPage;
import pages.AddTrackerLogPage;
import pages.AddTrackerPage;
import pages.DashboardPage;
import pages.EvaluationFormPage;
import pages.LoginPage;
import pages.SaveKPIPage;
import pages.SearchKPIPage;
import pages.SearchReviewPage;
import utils.FailedScenario;
import utils.JScriptExecUtils;
import utils.XLUtils;



public class PerformanceSteps {

	WebDriver driver;
	Properties props;

	LoginPage loginpage;
	SaveKPIPage saveKPI ;
	SearchKPIPage searchKPI ;
	DashboardPage dbPage;
	AddTrackerPage addTracker;
	AddReviewPage addReview;
	SearchReviewPage searchReview;
	EvaluationFormPage evaluationForm;
	AddTrackerLogPage trackerLog;


	SoftAssert softAssert;
	Actions action;
	JScriptExecUtils exec;

	String jobtitle;
	String kpi;
	public static Logger logger;
	
	@Before
	public void initlog() {
		logger = Logger.getLogger("Performance");
		PropertyConfigurator.configure("log4j.properties");
	}
	
	//	Background

	@Given("Admin opens {string} and enters url")
	public void admin_opens_and_enters_url(String browser) {

		logger.info("Initializing the Driver...");
		if(browser.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (browser.equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		
		props = new Properties();
		FileInputStream inputStream;
		try {
			inputStream = new FileInputStream(
					System.getProperty("user.dir")+"/src/test/java/config/testProps.properties");
			props.load(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}

		loginpage = new LoginPage(driver);
		saveKPI = new SaveKPIPage(driver);
		searchKPI = new SearchKPIPage(driver);
		dbPage = new DashboardPage(driver);
		addTracker = new AddTrackerPage(driver);
		action = new Actions(driver);
		addReview = new AddReviewPage(driver);
		searchReview = new SearchReviewPage(driver);
		evaluationForm = new EvaluationFormPage(driver);
		trackerLog = new AddTrackerLogPage(driver);

		softAssert = new SoftAssert();
		exec = new JScriptExecUtils(driver);

		driver.get(props.getProperty("url"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
	}

	@When("he enters username and password")
	public void he_enters_username_and_password() {
		logger.info("Logging into the OrangeHRM Application...");
		loginpage.enterUsername(props.getProperty("username"));
		loginpage.enterPass(props.getProperty("pass"));
	}

	@Then("he logged in to the application")
	public void he_logged_in_to_the_application() throws InterruptedException {
		loginpage.clickLoginBtn();
		Thread.sleep(2000);
	}

	@After
	public void teardown(Scenario scenario) {
		Status s = scenario.getStatus();
		System.out.println(s);
		if(scenario.getStatus().toString().equals("FAILED")) {
			FailedScenario.capture(driver, scenario.getName());
			logger.error("Scenario - "+ scenario.getName() + " failed. Screenshot Captured at : target/screenshot/*");		
		}
		WebElement welcomeLink = driver.findElement(By.id("welcome"));
		WebElement logoutLink = driver.findElement(By.xpath("//*[@id=\"welcome-menu\"]/ul/li[3]/a"));
		exec.jsClick(welcomeLink);
		exec.jsClick(logoutLink);
		driver.quit();
	}

	//	@MenuCheck

	@When("admin hovers over performance menu")
	public void admin_hovers_over_performance_menu() {
		action.moveToElement(driver.findElement(By.xpath("/html/body/div[1]/div[2]/ul/li[7]/a/b"))).perform();
	}

	@Then("all sub menus should be visible")
	public void all_sub_menus_should_be_visible() {
		if(!dbPage.trackerListMenu.isDisplayed()) {
			softAssert.assertEquals(dbPage.trackerListMenu.getText(), "Employee Trackers");
		}
		if(!dbPage.configMenu.isDisplayed()) {
			softAssert.assertEquals(dbPage.configMenu.getText(), "Configure");
		}
		if(!dbPage.manageReviewMenu.isDisplayed()) {
			softAssert.assertEquals(dbPage.manageReviewMenu.getText(), "Manage Reviews");
		}
		softAssert.assertAll();
	}

	//	@SearchKPI

	@When("admin navigates to kpi page to search")
	public void admin_navigates_to_kpi_page_to_search() {
		dbPage.goToSearchKPIPage();
	}

	@And("admin search KPI with job title")
	public void admin_search_kpi_with_job_title() throws InterruptedException {
		searchKPI.selectJobTitle("IT Manager");
		exec.jsClick(searchKPI.btnSearch);
	}

	@Then("KPI should be listed with selected job title")
	public void kpi_should_be_listed_with_selected_job_title() {
		List<WebElement> rows = searchKPI.returnRows();
		List<WebElement> cols = searchKPI.returnCols();

		if(!(cols.get(0).getText().equals("No Records Found"))) {
			for(int i=1;i<rows.size()+1;i++) {
				softAssert.assertEquals(
						driver.findElement(By.xpath("//table/tbody/tr["+i+"]/td[3]")).getText(),
						"IT Manager"
						);
			}
		} 
		softAssert.assertAll();
	}

	//	@AddKPI

	@When("admin naviageta to kpi page to add")
	public void admin_naviageta_to_kpi_page_to_add() throws InterruptedException {
		dbPage.goToSearchKPIPage();
		exec.jsClick(searchKPI.btnAdd);
	}

	@And("he enters job title as {string} and KPI as {string}")
	public void he_enters_job_title_as_and_kpi_as(String title, String kpiArgs) throws InterruptedException {
		saveKPI.selectJobTitle(title);
		exec.jsSendKeys(saveKPI.txtKPI, kpiArgs);
		exec.jsClick(saveKPI.btnSave);
	}

	@Then("KPI added to the list")
	public void kpi_added_to_the_list() {
		String msg = driver.findElement(By.cssSelector(".message")).getText();
		softAssert.assertEquals(msg, "Successfully Saved");
		softAssert.assertAll();
	}

	//	@AddReview

	@When("admin navigates to manage review page to add")
	public void admin_navigates_to_manage_review_page_to_add() {
		dbPage.goToManageReviewPage();
	}

	@And("he activates the review")
	public void he_activates_the_review() throws InterruptedException {
		List<String> data;


		try {				
			exec.jsClick(searchReview.btnAdd);
			data = XLUtils.getRowsData("src\\test\\resources\\EmployeeData\\empData.xlsx", "review", 1);

			addReview.enterEmpName(data.get(0));
			//			Thread.sleep(2000);
			//			String supName = data.get(1).trim().toString();
			exec.jsSendKeys(addReview.txtSupName, data.get(1));
			addReview.enterStartDate(data.get(2));
			addReview.enterEndDate(data.get(3));
			addReview.enterDueDate(data.get(4));
			addReview.clickActivateBtn();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Then("review should be activated")
	public void review_should_be_activated() {
		if(driver.findElement(By.xpath("//*[@id=\"reviewCreationBody\"]/ol[1]/li/span")).isDisplayed()) {
			Assert.fail();
		} else {
			String msg = driver.findElement(By.cssSelector(".message")).getText();
			softAssert.assertEquals(msg,"Successfully Saved");
		}
	}

	//	

	@When("admin navigates to manage review page to evaluate")
	public void admin_navigates_to_manage_review_page_to_evaluate() {
		dbPage.goToManageReviewPage();
	}

	@Then("he evaluates employee and review status update as Approved")
	public void he_evaluates_employee_and_review_status_update_as_Approved() throws InterruptedException {
		int reviewNum = 0;
		if(!driver.getPageSource().contains("No Records Found")) {
			int size = driver.findElements(By.xpath("//form/div[4]/table/tbody/tr")).size();
			for(int i=1;i<=size;i++) {
				WebElement row = driver.findElement(By.xpath("//form/div[4]/table/tbody/tr["+i+"]/td[6]"));
				if(row.getText().equals("Activated") || row.getText().equals("In Progress")) {
					exec.jsClick(driver.findElement(By.
							xpath("//*[@id=\"resultTable\"]/tbody/tr["+i+"]/td[7]/a")));

					action.moveToElement(evaluationForm.btnComlete).perform();
					exec.jsSendKeys(evaluationForm.txtFinalCmt, "Good");
					exec.jsSendKeys(evaluationForm.txtFinalRating, "80");
					exec.jsSendKeys(evaluationForm.dateOfCompletion, "2021-02-02");

					exec.jsClick(evaluationForm.btnComlete);
					exec.jsClick(evaluationForm.btnConfirmCompletion);
					reviewNum = i;
					break;
				}
			}
			dbPage.goToManageReviewPage();
			if(reviewNum!=0) {
				if(!driver.findElement(By
						.xpath("//form/div[4]/table/tbody/tr["+reviewNum+"]/td[6]"))
						.getText().equals("Approved")) {
					Assert.fail();
				}
			}
		}
	}

	//	@SearchReview

	@When("admin navigates to manage review page to search review")
	public void admin_navigates_to_manage_review_page_to_search_review() {
		dbPage.goToManageReviewPage();
	}

	@And("he searches for an employee review")
	public void he_searches_for_an_employee_review() {
		searchReview.enterEmpName("Cassidy Hope");
		searchReview.clickSearchBtn();
	}

	@Then("searched employee reviews should be listed")
	public void searched_employee_reviews_should_be_listed() {
		List<WebElement> rows = searchReview.returnRows();
		List<WebElement> cols = searchReview.returnCols();

		if(cols.get(0).getText().equals("No Records Found")) {
			System.out.println("No KPI for " + jobtitle);
		} else {
			for(int i=1;i<rows.size()+1;i++) {
				softAssert.assertEquals(
						driver.findElement(By.xpath("//table/tbody/tr["+i+"]/td[2]")).getText(),
						jobtitle
						);
			}
		}
	}

	//	@DeleteReview

	@When("admin navigates to manage review page to delete review")
	public void admin_navigates_to_manage_review_page_to_delete_review() {
		dbPage.goToManageReviewPage();
	}

	@And("admin selects review clicks on delete button")
	public void admin_selects_review_clicks_on_delete_button() {
		searchReview.selectCheckbox(1);
		exec.jsClick(searchReview.btnDelete);
		exec.jsClick(searchKPI.btnConfirmDelete);
	}

	@Then("Review should be deleted.")
	public void review_should_be_deleted() {
		String msg = driver.findElement(By.cssSelector(".message")).getText();
		softAssert.assertEquals(msg,"Successfully Deleted");
	}

	//	@AddValidTracker

	@When("admin navigates to tracker page to add tracker")
	public void admin_navigates_to_tracker_page_to_add_tracker() {
		dbPage.goToAddTrackerPage();
	}

	@When("he enters tracker as {string} employee as {string} reviewer as {string}")
	public void he_enters_tracker_as_employee_as_reviewer_as(String tracker, String ename, String reviewer) throws InterruptedException {

		exec.jsClick(addTracker.btnAdd);
		exec.jsSendKeys(addTracker.txtTrackerName, tracker);
		Thread.sleep(2000);
		addTracker.enterEmpName(ename);
		addTracker.selectReviewerToAdd(reviewer);
		exec.jsClick(addTracker.btnAddReviewer);
		exec.jsClick(addTracker.btnSave);
	}

	@Then("tracker added to the list")
	public void tracker_added_to_the_list() {
		String msg = driver.findElement(By.cssSelector(".message")).getText();
		softAssert.assertEquals(msg,"Successfully Saved"); 
	}

	//	@AddInvalidTracker

	@When("admin navigates to tracker page to add invalid tracker")
	public void admin_navigates_to_tracker_page_to_add_invalid_tracker() {
		dbPage.goToAddTrackerPage();	
	}

	@And("he try to add tracker with empty fields")
	public void he_try_to_add_tracker_with_empty_fields() {
		exec.jsClick(addTracker.btnAdd);
		exec.jsClick(addTracker.btnSave);
	}

	@Then("tracker form fields displyed error message")
	public void tracker_form_fields_displyed_error_message() {
		String errorMsg = driver.findElement(By.xpath("//form/fieldset/ol/li[1]/span")).getText();
		softAssert.assertEquals(errorMsg, "Required");
	}

	//	@DeleteTracker

	@When("admin navigates to tracker page to delete tracker")
	public void admin_navigates_to_tracker_page_to_delete_tracker() {
		dbPage.goToAddTrackerPage();
	}

	@And("selects tracker to delete and click delete button")
	public void selects_tracker_to_delete_and_click_delete_button() {

		addTracker.selectCheckBox(2);
		exec.jsClick(addTracker.btnDelete);
		exec.jsClick(addTracker.btnConfirmDelete);
	}

	@Then("tracker should be deleted")
	public void tracker_should_be_deleted() {
		String msg = driver.findElement(By.cssSelector(".message")).getText();
		softAssert.assertEquals(msg,"Successfully Deleted"); 
	}

	//	@AddTrackerLog

	@When("admin navigates to tracker list page")
	public void admin_navigates_to_tracker_list_page() {
		dbPage.goToTrackerListPage();
	}

	@And("clicks on employee name to add log")
	public void clicks_on_employee_name_to_add_log() {
		exec.jsClick(driver.findElement(By.xpath("//form/div[3]/table/tbody/tr[1]/td[1]/a")));
	}

	@And("admin adds the log as following")
	public void admin_adds_the_log_as_following(DataTable dataTable) {
		List<List<String>> logs = dataTable.asLists(String.class);
		exec.jsClick(driver.findElement(By.id("btnAdd")));

		for(List<String> log : logs) {
			exec.jsClick(trackerLog.btnAdd);
			exec.jsSendKeys(trackerLog.txtLog, log.get(0));
			trackerLog.selectAchievement(log.get(1));
			exec.jsSendKeys(trackerLog.txtComment, log.get(2));
			exec.jsClick(trackerLog.btnSave);
		}
	}

	@Then("log is added in employee log list")
	public void log_is_added_in_employee_log_list() throws InterruptedException {		
		String msg = driver.findElement(By.cssSelector(".message:nth-child(3)")).getText();
		softAssert.assertEquals(msg, "Successfully Saved");

		softAssert.assertAll();
	}

}
