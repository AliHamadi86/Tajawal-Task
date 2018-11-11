package core.automation;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

public class SeleniumFrame {

	private RemoteWebDriver driver;

	public SeleniumFrame(RemoteWebDriver driver) {
		this.driver = driver;
	}

	public void switchToEelementIframe(String element) {

		List<WebElement> elements = driver.findElementsByXPath("//iframe");
		for (WebElement frameElement : elements) {
			if(findElementInFrame(frameElement, element)){
				break;
			}
		}

	}

	private boolean findElementInFrame(WebElement frameElement, String element) {
		driver.switchTo().frame(frameElement);
		boolean isFound = false;
		try {
			isFound = driver.findElementByXPath(element) != null;
		} catch (Exception e) {
		}
		if (isFound) {
			return isFound;
		}
		List<WebElement> elements = driver.findElements(By.xpath("//iframe"));
		for (WebElement frame : elements) {
			System.out.println(frame.getAttribute("id"));
			findElementInFrame(frame, element);
		}
		System.out.println("element could not be found in any frame");
		return isFound;
	}

}
