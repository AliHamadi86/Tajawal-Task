package bl.listing;

import bl.Base;
import core.automation.SeleniumDriverCore;
import core.reports.LogResults;

public class Listing extends Base {
	public ListingElement element;

	public Listing(SeleniumDriverCore driver, LogResults log) {
		super(driver, log);
		this.element = new ListingElement(driver);
	}
	
	public void selectAnAirLinesCarrier(String airLine) {
		
	}

}
