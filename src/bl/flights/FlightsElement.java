package bl.flights;

import bl.BaseElement;
import core.automation.Element;
import core.automation.SeleniumDriverCore;

public class FlightsElement extends BaseElement {

	public FlightsElement(SeleniumDriverCore driver) {
		super(driver);
	}

	public Element oneWayTab() {
		return driver.elementFinder.findElement("//input[@id='flights-search-type-one-way-inp']");
	}

	public Element roundTripTab() {
		return driver.elementFinder.findElement("//input[@id='flights-search-type-round-trip-inp']");
	}

	public Element multiCityTab() {
		return driver.elementFinder.findElement("//input[@id='flights-search-type-multi-city-inp']");
	}

	public Element orgineSearchTxtBox() {
		return driver.elementFinder.findElement("//input[@id='flights-search-origin-1']");
	}

	public Element destinationSearchTxtBox() {
		return driver.elementFinder.findElement("//input[@id='flights-search-destination-1']");
	}

	public Element departureDateBtn() {
		return driver.elementFinder.findElement("//input[@id='flights-search-departureDate-1']");
	}

	public Element returnDateBtn() {
		return driver.elementFinder.findElement("//input[@id='flights-search-returnDate-1']");
	}

	public Element flightClassMenuBtn() {
		return driver.elementFinder.findElement("//div[@id='flights-search-open-cabin-btn']");
	}

	public Element passengerMenuBtn() {
		return driver.elementFinder.findElement("//div[@id='flights-search-open-pax-btn']");
	}

	public Element searchFlightBtn() {
		return driver.elementFinder.findElement("//a[@id='flights-search-cta']");
	}

	public Element economyOption() {
		return driver.elementFinder.findElement("//a[@id='flights-search-cabin-Economy-btn']");

	}

	public Element premiumOption() {
		return driver.elementFinder.findElement("//a[@id='flights-search-cabin-Premium-Economy-btn']");

	}

	public Element buisnessOption() {
		return driver.elementFinder.findElement("//a[@id='flights-search-cabin-Business-btn']");

	}

	public Element firstOption() {
		return driver.elementFinder.findElement("//a[@id='flights-search-cabin-First-btn']");

	}

	public Element dateValue(String month, String day) {
		return driver.elementFinder
				.findElement("//td[@data-day='" + day + "']/button[@data-pika-month='" + month + "']");
	}

}
