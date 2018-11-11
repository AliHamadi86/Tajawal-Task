package bl;

import java.util.Map;

import org.testng.internal.collections.Pair;

import core.automation.Element;
import core.automation.SeleniumDriverCore;
import core.reports.LogResults;

public class BaseAssertion {
	protected static LogResults log;
	protected SeleniumDriverCore driver;
	
	private BaseElement element;
	public BaseAssertion(SeleniumDriverCore driver,LogResults log) {
		this.log = log;
		this.driver = driver;
		this.element=new BaseElement(driver);
		
	}
	
	protected void verifyDisplayedWebElements(
			Map<String, Pair<Element, Boolean>> elementsMap)
			throws Exception {

		for (Map.Entry<String, Pair<Element, Boolean>> element : elementsMap
				.entrySet()) {

			String elementDescription = element.getKey();
			boolean shouldBeVisible = element.getValue().second();
			Element targetElement = element.getValue().first();

			log.resultStep("Verify that the element "
					+ elementDescription
					+ ((shouldBeVisible) ? " Is Displayed"
							: "' Is NOT Displayed"));
			log.endStep((shouldBeVisible) ? driver.assertion
					.isElementPresent(targetElement) : !driver.assertion
					.isElementPresent(targetElement,
							driver.waits.elementNegativeTimeOut));
		}
	}

}
