package core.automation;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver.Options;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public class SeleniumDriverBrowser {

	private EventFiringWebDriver driver;

	public SeleniumDriverBrowser(EventFiringWebDriver driver) {
		this.driver = driver;
	}

	public void open(String url) {

		this.driver.get(url);
	}

	public void browserNavigate(BrowserNavigation navigate) {

		try {
			switch (navigate) {
			case Forword:
				this.driver.navigate().forward();
				break;
			case Back:
				this.driver.navigate().back();
				break;
			case Refresh:
				this.driver.navigate().refresh();
				break;

			default:
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public void maximize() {

		this.driver.manage().window().maximize();
	}

	public void close() {

		this.driver.close();
	}

	public void navigate(String url) {

		this.driver.navigate().to(url);

	}

	public void refresh() {

		this.driver.navigate().refresh();

	}

	public void scrollUp() {

		JavascriptExecutor jse = (JavascriptExecutor) this.driver;
		jse.executeScript("window.scrollBy(0,-250)", "");
	}

	public void scrollDown() {

		JavascriptExecutor jse = (JavascriptExecutor) this.driver;
			jse.executeScript("window.scrollBy(0,1000)", "");
	}

	public void scrollToPageTop() {

		JavascriptExecutor jse = (JavascriptExecutor) this.driver;
		jse.executeScript("window.scrollTo(0, 0)", "");
	}

	public String getCurrentUrl() {

		String currentUrl;
		currentUrl = this.driver.getCurrentUrl();
		return currentUrl;
	}

	public Options manage() {
		return this.driver.manage();
	}

}
