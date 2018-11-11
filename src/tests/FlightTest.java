package tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import bl.enums.ClassType;
import bl.factory.TajawalObjectFactory;

public class FlightTest extends BaseTest {

	private TajawalObjectFactory tajawal;

	@BeforeMethod(alwaysRun = true)
	@Parameters({ "driverConfig" })
	public void testSetup(@Optional("config/wdConfig") String driverConfig) {

		try {
			this.tajawal = new TajawalObjectFactory(driverConfig, eventListener);
			browserType = tajawal.driver.browserType;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@AfterMethod(alwaysRun = true)
	private void testCleanUp() {
		tajawal.driver.quit();
	}

	@Test(groups = { "flights" })
	public void verifyThatFlightSearchWorksAccordingly() throws Exception {
		tajawal.log.startTest("verify that search functionality works successfully");
		try {
			tajawal.openTajawal(url);
			tajawal.flight.typeOrgine("Sofia");
			tajawal.flight.typeDestination("Dubai");
			tajawal.flight.selectDepartureDate("11", "11");
			//tajawal.flight.selectReturnDate("12", "1");
			tajawal.flight.selectEconomyClassType(ClassType.ECONOMY);
			tajawal.flight.clickPassengerBtnMenu();
			tajawal.flight.clickSearchFlightBtn();

			tajawal.log.verifyTestStatus();
		} catch (Exception e) {
			tajawal.log.endStep(false);
			throw e;
		}
		tajawal.log.endTest();
	}

}
