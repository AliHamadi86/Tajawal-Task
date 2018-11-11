package bl;

import core.automation.Element;
import core.automation.SeleniumDriverCore;

public class BaseElement {
	protected SeleniumDriverCore driver;

	public BaseElement(SeleniumDriverCore driver) {
		this.driver = driver;
	}

	public Element flightsPage() {
		return driver.elementFinder.findElement("//a[@id='nav-flights-cta']");
	}
	
	public Element hotelsPage() {
		return driver.elementFinder.findElement("//a[@id='nav-hotels-cta']");
	}
	
	public Element holidaysPage() {
		return driver.elementFinder.findElement("//a[@id='nav-holidays-cta']");
	}
	
	public Element offerssPage() {
		return driver.elementFinder.findElement("//a[@id='nav-offers-cta']");
	}

}
