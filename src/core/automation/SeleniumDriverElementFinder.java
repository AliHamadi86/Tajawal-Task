package core.automation;

import org.openqa.selenium.support.events.EventFiringWebDriver;

public class SeleniumDriverElementFinder {

	private EventFiringWebDriver driver;

	public SeleniumDriverElementFinder(EventFiringWebDriver driver) {
		this.driver = driver;
	}

	public Element findElement(String xpath) {

			Element element=new Element(xpath,driver);
			return element;

	}

}
