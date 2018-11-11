package core.automation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Element {

	public String elementString;
	private EventFiringWebDriver driver;

	public Element(String elementString, EventFiringWebDriver driver) {
		this.driver = driver;
		this.elementString = elementString;
	}

	public WebElement webElement() {
		return webElement(SearchType.XPATH);
	}

	public WebElement webElementId() {
		return webElement(SearchType.ID);
	}

	public WebElement webElement(SearchType type) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 2);
			if (type == SearchType.XPATH) {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(elementString)));
				return this.driver.findElement(By.xpath(elementString));
			} else if (type == SearchType.ID) {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.id((elementString))));
				return this.driver.findElement(By.xpath(elementString));
			} else {
				return null;
			}
		} catch (

		Exception e) {
			throw e;
		}
	}

}
