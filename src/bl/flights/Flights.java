package bl.flights;

import java.security.InvalidParameterException;

import org.openqa.selenium.Keys;

import bl.Base;
import bl.enums.ClassType;
import bl.enums.FlightSearchPages;
import core.automation.SeleniumDriverCore;
import core.reports.LogResults;

public class Flights extends Base {

	private FlightsElement element;
	private FlightsAssertion assertion;

	public Flights(SeleniumDriverCore driver, LogResults log) {
		super(driver, log);
		this.element = new FlightsElement(driver);
		this.assertion = new FlightsAssertion();
	}

	public void navigateToFlightSearchTabs(FlightSearchPages tab) {
		log.startStep("navigate to the '" + tab.name() + "' tab");
		switch (tab) {
		case ONE_WAY:
			driver.click(this.element.oneWayTab());
			break;
		case ROUND_TRIP:
			driver.click(this.element.roundTripTab());
			break;
		case MULTI_CITY:
			driver.click(this.element.multiCityTab());
			break;
		default:
			throw new InvalidParameterException("The following parameter is invalid: " + tab.name());
		}
		driver.waits.waitForAjaxToComplete();
		log.endStep();
	}

	public void typeOrgine(String orgine) {
		log.startStep("type in '" + orgine + "' in the orgine search box");
		driver.type(this.element.orgineSearchTxtBox(), orgine);
		driver.pressKey(element.orgineSearchTxtBox(), Keys.ENTER);
		log.endStep();

	}

	public void typeDestination(String destination) {
		log.startStep("type in '" + destination + "' in the destination search box");
		driver.type(this.element.destinationSearchTxtBox(), destination);
		driver.pressKey(element.destinationSearchTxtBox(), Keys.ENTER);
		log.endStep();
	}

	private void clickFlightClassBtnMenu() {
		log.startStep("click on the class flight button");
		driver.click(this.element.flightClassMenuBtn());
		log.endStep();
	}

	public void clickPassengerBtnMenu() {
		log.startStep("click on the class flight button");
		driver.click(this.element.passengerMenuBtn());
		log.endStep();
	}

	public void clickSearchFlightBtn() {
		log.startStep("click on the class flight button");
		driver.click(this.element.searchFlightBtn());
		log.endStep();
	}

	public void selectEconomyClassType(ClassType type) {
		clickFlightClassBtnMenu();
		log.startStep("select '" + type.name() + "' from the class type menu");
		switch (type) {
		case ECONOMY:
			driver.click(this.element.economyOption());
			break;
		case PREMIUM_ECONOMY:
			driver.click(this.element.premiumOption());
			break;
		case BUISNESS:
			driver.click(this.element.buisnessOption());
			break;
		case FIRST:
			driver.click(this.element.firstOption());
			break;
		default:
			throw new InvalidParameterException("The following parameter is invalid: " + type.name());
		}
		driver.waits.waitForAjaxToComplete();
		log.endStep();
	}

	public void selectDepartureDate(String month, String day) {
		log.startStep("select '" + month + "/" + day + "' as a departure date");
		driver.click(this.element.departureDateBtn());
		driver.waits.waitForAjaxToComplete();
		driver.click(this.element.dateValue(month, day));
		log.endStep();

	}

	public void selectReturnDate(String month, String day) {
		log.startStep("select '" + month + "/" + day + "' as a return date");
		driver.click(this.element.returnDateBtn());
		driver.click(this.element.dateValue(month, day));
		log.endStep();

	}

}
