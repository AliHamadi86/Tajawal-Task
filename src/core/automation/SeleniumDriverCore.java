package core.automation;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.events.WebDriverEventListener;
import org.openqa.selenium.support.ui.Select;

import com.google.common.io.Files;

public class SeleniumDriverCore {

	protected RemoteWebDriver driver;
	public SeleniumDriverBrowser browser;
	public SeleniumDriverAssertion assertion;
	public SeleniumDriverElementFinder elementFinder;
	public SeleniumDriverWaits waits;
	public SeleniumFrame frame;
	public WaitValue waitValue;
	private Properties config;
	public String browserType;
	public EventFiringWebDriver eventDriver;
	//private EventListener eventListener;

	protected int eleTime;

	public SeleniumDriverCore(String appConfig,WebDriverEventListener eventListener) {

		this.waitValue = new WaitValue();
		this.config = new Properties();
		this.driver = lunchDriver(appConfig);
		this.eventDriver = new EventFiringWebDriver(driver);
		//this.eventListener = new EventListener();
		this.eventDriver.register(eventListener);
		this.browser = new SeleniumDriverBrowser(this.eventDriver);
		this.assertion = new SeleniumDriverAssertion(this.eventDriver, this.waitValue);
		this.elementFinder = new SeleniumDriverElementFinder(this.eventDriver);
		this.waits = new SeleniumDriverWaits(this.eventDriver, this.waitValue);
		this.frame = new SeleniumFrame(driver);
	}

	public void takePicture(String path) {
		try {
			File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			File fileDir = new File(path);
			Files.copy(srcFile, fileDir);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public RemoteWebDriver lunchDriver(String configLocation) {

		try {
			this.config.load(new FileInputStream(configLocation));
			browserType = this.config.getProperty("browser");
			String urlHost = this.config.getProperty("urlHost");
			this.waitValue.setAjaxTimeOut(Integer.parseInt(this.config.getProperty("ajaxTimeOut")));
			this.waitValue.setPageLoadTimeOut(Integer.parseInt(this.config.getProperty("pageLoadTimeOut")));
			this.waitValue.setElementTimeOut(Integer.parseInt(this.config.getProperty("elementTimeOut")));
			this.waitValue
					.setElementNegativeTimeOut(Integer.parseInt(this.config.getProperty("elementNegativeTimeOut")));
			this.waitValue.setSikuliTimeOut(Integer.parseInt(this.config.getProperty("sikuliTimeOut")));
			this.waitValue.setSikuliNegativeTimeOut(Integer.parseInt(this.config.getProperty("sikuliNegativeTimeOut")));

			switch (browserType) {
			case "fireFox":
				driver = this.lunchDriver(urlHost, Browsers.FIREFOX);
				break;
			case "chrome":
				driver = this.lunchDriver(urlHost, Browsers.CHROME);
				break;
			case "opera":
				driver = this.lunchDriver(urlHost, Browsers.OPERA);
				break;
			case "mobile":
				driver = this.lunchDriver(urlHost, Browsers.CHROME_MOBILE);
				break;
			case "ie":
				driver = this.lunchDriver(urlHost, Browsers.IE);
				break;
			case "edge":
				driver = this.lunchDriver(urlHost, Browsers.EDGE);
				break;
			default:
				System.out.println("please select the right browser!");
				break;
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		return this.driver;
	}

	public RemoteWebDriver lunchDriver(String url, Browsers browser) throws MalformedURLException {

		try {
			URL _url = new URL(url);
			switch (browser) {
			case FIREFOX:
				DesiredCapabilities capability = DesiredCapabilities.firefox();
				// capability.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR,
				// UnexpectedAlertBehaviour.DISMISS);
				this.driver = new RemoteWebDriver(_url, capability);
				break;
			case CHROME:
				DesiredCapabilities capabilityCh = DesiredCapabilities.chrome();
				this.driver = new RemoteWebDriver(_url, capabilityCh);
				break;
			case CHROME_MOBILE:
				Map<String, Object> mobileEmulation = new HashMap<String, Object>();
				mobileEmulation.put("deviceName", "Nexus 5");
				Map<String, Object> chromeOptions = new HashMap<String, Object>();
				chromeOptions.put("mobileEmulation", mobileEmulation);
				DesiredCapabilities capabilityMob = DesiredCapabilities.chrome();
				capabilityMob.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
				this.driver = new RemoteWebDriver(_url, capabilityMob);
				break;
			case OPERA:
				DesiredCapabilities capabilityOpera = DesiredCapabilities.operaBlink();
				this.driver = new RemoteWebDriver(_url, capabilityOpera);
				break;
			case EDGE:
				DesiredCapabilities capabilityEdge = DesiredCapabilities.edge();
				this.driver = new RemoteWebDriver(_url, capabilityEdge);
				break;
			case IE:
				DesiredCapabilities capabilityIe = DesiredCapabilities.internetExplorer();
				capabilityIe.setCapability(CapabilityType.SUPPORTS_JAVASCRIPT, true);
				capabilityIe.setJavascriptEnabled(true);
				capabilityIe.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
				capabilityIe.setCapability(CapabilityType.ENABLE_PROFILING_CAPABILITY, true);
				capabilityIe.setCapability("enableElementCacheCleanup", false);
				capabilityIe.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
						true);
				this.driver = new RemoteWebDriver(_url, capabilityIe);
				break;
			default:
				throw new InvalidParameterException("Not valid parameter");
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return this.driver;
	}

	// webdriver overridden API methods
	public Select select(Element element) {
		try {
			Select selected = new Select(element.webElement());
			return selected;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

	}

	public void selectSearchedValueByXpath(Element menu, Element txtField, String value) {
		this.click(menu);
		this.type(txtField, value);
		this.pressKey(txtField, Keys.ENTER);

	}

	public List<String> getDropDownListOptions(Element menu) {
		ArrayList<String> dropDownMenu = new ArrayList<String>();
		List<WebElement> menuOptions = this.select(menu).getOptions();
		for (WebElement elements : menuOptions) {
			dropDownMenu.add(elements.getText());
		}
		return dropDownMenu;
	}

	public void pressKey(Element locator, Keys key) {
		try {
			locator.webElement().sendKeys(key);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public void hoverToElement(Element element) {
		try {
			Actions builder = new Actions(this.driver);
			builder.moveToElement(element.webElement()).build().perform();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public String[] getDropDownOptions(Element elementr) {

		ArrayList<String> dropDownMenu = new ArrayList<String>();
		Select dropDown = select(elementr);
		List<WebElement> options = dropDown.getOptions();
		for (WebElement elements : options) {
			dropDownMenu.add(elements.getText());
		}
		String[] values = new String[dropDownMenu.size()];
		values = dropDownMenu.toArray(values);
		return values;
	}

	public void dragAndDrop(Element from, Element to) {
		try {
			Actions builder = new Actions(this.driver);
			Action dragThenDrop = builder.clickAndHold(from.webElement()).moveToElement(to.webElement())
					.release(to.webElement()).build();
			dragThenDrop.perform();

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public void clickAndHoldThenRelease(Element element) {
		Actions builder = new Actions(this.driver);
		builder.moveToElement(element.webElement()).clickAndHold(element.webElement()).perform();
		builder.release(element.webElement());
	}

	public void releaseElement(Element element) {
		Actions builder = new Actions(this.driver);
		builder.release(element.webElement());
	}

	public void click(Element element) {
		for (int i = 0; i < 2; i++) {
			try {
				element.webElement().click();
				break;
			} catch (Exception e) {
				if (e instanceof StaleElementReferenceException) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e1) {
					}
				} else {
					e.getStackTrace();
					throw e;
				}
			}
		}
	}

	public void click(String element) {
		for (int i = 0; i < 2; i++) {
			try {
				driver.findElementByXPath(element).click();
				break;
			} catch (Exception e) {
				if (e instanceof StaleElementReferenceException) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e1) {
					}
				} else {
					e.getStackTrace();
					throw e;
				}
			}
		}
	}

	public void clickId(Element element) {
		for (int i = 0; i < 2; i++) {
			try {
				element.webElementId().click();
				break;
			} catch (Exception e) {
				if (e instanceof StaleElementReferenceException) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e1) {
					}
				} else {
					e.getStackTrace();
					throw e;
				}
			}
		}
	}

	public void clickTillAction(Element element, String actionElement) {
		for (int i = 0; i < waits.elementTimeOut; i++) {
			this.click(element);
			if (assertion.isElementPresent(actionElement, 1)) {
				break;
			}
		}
	}

	public void doubleClick(Element element) {
		try {
			Actions action = new Actions(this.driver);
			action.doubleClick(element.webElement()).perform();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public void clickStaleElement(Element element) {

		for (int i = 0; i < 5; i++) {
			try {
				element.webElement().click();
				break;
			} catch (Exception e) {
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				System.out.println("click '" + element + "' try number:'" + i + "'");
			}
		}
	}

	public void clear(Element element) {
		try {
			element.webElement().clear();
		} catch (NoSuchElementException e) {

		}
	}

	public void type(Element element, String text) {
		try {
			this.waits.waitForElementTobeVisible(element.webElement());
			this.clear(element);
			element.webElement().sendKeys(text);
		} catch (Exception e) {
			e.getStackTrace();
		}
	}

	public String getWindowsName() {

		return driver.getWindowHandle();
	}

	public void selectWindow() {
		try {
			for (String newWindow : driver.getWindowHandles()) {
				driver.switchTo().window(newWindow);
			}
		} catch (Exception e) {
			System.out.println("The requested window is not valid");
		}
	}

	public void switchToDefaultWindow(String defaultWindow) {
		driver.switchTo().window(defaultWindow);
	}

	public void switchToIframe(Element iframeXpath) {
		try {
			driver.switchTo().frame(iframeXpath.webElement());
		} catch (NoSuchFrameException e) {
			e.printStackTrace();
			throw e;
		}
	}

	public void switchToIframeByID(Element iframeID) {
		try {
			driver.switchTo().frame(iframeID.webElementId());
		} catch (NoSuchFrameException e) {
			e.printStackTrace();
			throw e;
		}
	}

	public void switchToTopWindow() {

		driver.switchTo().defaultContent();
	}

	public void quit() {

		this.driver.quit();

	}

	public void close() {
		this.driver.close();
	}

	public void clickHiddenElement(String className, String title) {

		try {
			((JavascriptExecutor) this.driver).executeScript("var p = document.getElementsByClassName('" + className
					+ "');for (var i = 0; i < p.length; i++) {if (p[i].getAttribute('title') == '" + title
					+ "') {p[i].click();}}");
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public void executeJs(String js) {

		try {
			((JavascriptExecutor) this.driver).executeScript(js);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public void showHiddenElementByID(String id) {

		try {
			((JavascriptExecutor) this.driver).executeScript("$('#" + id + "').show()");
		} catch (Exception e) {
			System.out.println("Show Hidden element is not working");
			;
		}
	}

	public void hideShowenElementByID(String id) {

		try {
			((JavascriptExecutor) this.driver).executeScript("$('#" + id + "').hide()");
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public void checkAlert(int timeOut) {

		try {
			waits.waitforAlertToBePresent(timeOut);
			Alert alert = this.driver.switchTo().alert();
			alert.accept();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public void checkAlert() {

		this.checkAlert(waits.elementTimeOut);
	}

	public String getInputFieldValue(Element element) {

		try {
			return element.webElement().getAttribute("value");

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public String getInnerText(Element element) {
		try {
			return element.webElement().getText();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public String getSelectedValueText(Element element) {

		try {

			Select dropdown = new Select(element.webElement());

			return dropdown.getFirstSelectedOption().getText();

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public void uploadFile(Element element, String fileDest) {
		try {
			driver.setFileDetector(new LocalFileDetector());
			Actions actions = new Actions(driver);
			actions.moveToElement(element.webElement());
			element.webElement().sendKeys(fileDest);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
}