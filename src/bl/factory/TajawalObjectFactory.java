package bl.factory;

import org.openqa.selenium.support.events.WebDriverEventListener;

import bl.flights.Flights;
import core.automation.SeleniumDriverCore;
import core.reports.LogResults;

public class TajawalObjectFactory {
	public SeleniumDriverCore driver;
	public LogResults log;
	public Flights flight;

	public TajawalObjectFactory(String driverConfig, WebDriverEventListener eventListener) {
		this.driver = new SeleniumDriverCore(driverConfig, eventListener);
		this.log=new LogResults(driver.eventDriver);
		this.flight=new Flights(driver, log);
	}

	public void openTajawal(String url) {
		this.driver.browser.open(url);
		this.driver.browser.maximize();
		this.driver.waits.waitForPageToLoad();

	}
}
