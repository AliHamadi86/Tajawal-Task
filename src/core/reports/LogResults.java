package core.reports;

import java.io.File;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.Reporter;

import com.google.common.io.Files;

import core.utilities.RandomValuesGenerator;

public class LogResults {

	private final String screenshotPath = "./target/test-output/screenshots";
	private String stepDescription;
	public boolean testStatus;
	private int stepNumber;
	private EventFiringWebDriver driver;

	public LogResults(EventFiringWebDriver driver) {
		this.driver = driver;
	}

	// Login reports
	public void startTest(String test) {

		Reporter.log("<h2>" + test + "</h2>");
		Reporter.log("<ol type='1'>");

		stepNumber = 0;
		testStatus = true;
	}

	public void resultStep(String step) {

		stepDescription = "<b><big><font color='DarkBlue'>" + step + "</font></b></big>";
	}

	public void endTest() {

		Reporter.log("</ol>");
		// cleanUpIntermediateScreenshot();
		// checkForVerificationErrors();

	}

	public void startStep(String step) {

		// cleanUpIntermediateScreenshot();
		stepDescription = step;
		stepNumber++;
	}

	public void endStep(boolean condition) {
		String logLine;
		logLine = "<li>" + stepDescription;

		if (condition) {
			logLine = logLine + "...<b><font color='green'>OK</font></b>";
		} else {
			String screenshotName = new RandomValuesGenerator().getDateTimeMillisecondsAccuracy();
			String picName=screenshotName + ".png";
			takeScreen(screenshotPath,picName);

			logLine = logLine + "...<a href=screenshots/" + picName
					+ "><b><font color='red'>FAILED!</font></b></a>";
			testStatus = false;
		}
		logLine = logLine + "</li>";
		Reporter.log(logLine);
	}

	public void endStep() {

		endStep(true);
	}

	public void verifyTestStatus() throws Exception {
		resultStep("Did the test pass?");
		if (!testStatus) {
			throw new Exception();
		} else {
			endStep();
		}
	}

	public void takeScreen(String path, String name) {
		try {
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			File destFile = new File(path);
			if (!destFile.exists()) {
				destFile.mkdir();
			}
			Files.copy(scrFile, new File(path + File.separator + name));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
