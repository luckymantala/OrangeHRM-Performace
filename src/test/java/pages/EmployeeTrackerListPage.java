package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class EmployeeTrackerListPage {
	WebDriver driver;

	public EmployeeTrackerListPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void selectEmployeName(String name) {

		List<WebElement> rows = driver.findElements(By.xpath("//*[@id=\"resultTable\"]/tbody/tr"));
		List<WebElement> cols = driver.findElements(By.xpath("//*[@id=\"resultTable\"]/tbody/tr[1]/td"));
		for (int i = 0; i < rows.size(); i++) {
			for (int j = 0; j < cols.size(); j++) {
				WebElement empName = driver
						.findElement(By.xpath("//*[@id=\"resultTable\"]/tbody/tr[" + i + "]/td[" + j + "]/a"));
				if (empName.getText().equals(name)) {
					empName.click();
				}
			}
		}
	}
}
