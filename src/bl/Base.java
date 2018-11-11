package bl;

import java.security.InvalidParameterException;

import core.automation.SeleniumDriverCore;
import core.reports.LogResults;
import core.utilities.RandomValuesGenerator;
import bl.enums.Pages;

public class Base {
	protected SeleniumDriverCore driver;
	protected RandomValuesGenerator generator;
	protected BaseElement element;
	protected LogResults log;

	public Base(SeleniumDriverCore driver,LogResults log) {
		this.driver = driver;
		this.element = new BaseElement(driver);
		this.log = log;
		this.generator = new RandomValuesGenerator();
	}
	
	
	public void navigateToPage(Pages page){
		log.startStep("navigate to the '"+page.name()+"' page");
		switch(page){
		case FLIGHTS:
			driver.click(this.element.flightsPage());
			break;
		case HOTELS:
			driver.click(this.element.hotelsPage());
			break;
		case HOLIDAYS:
			driver.click(this.element.holidaysPage());
			break;
		case OFFERS:
			driver.click(this.element.offerssPage());
			break;
		default:
			throw new InvalidParameterException(
					"The following parameter is invalid: " + page.name());
		}
		driver.waits.waitForPageToLoad();
		driver.waits.waitForAjaxToComplete();
		log.endStep();
	}

}
