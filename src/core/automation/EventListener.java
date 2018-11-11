package core.automation;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverEventListener;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EventListener implements WebDriverEventListener {

	@Override
	public void afterAlertAccept(WebDriver arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterAlertDismiss(WebDriver arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterChangeValueOf(WebElement arg0, WebDriver arg1, CharSequence[] arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterClickOn(WebElement arg0, WebDriver arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterFindBy(By arg0, WebElement arg1, WebDriver arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterNavigateBack(WebDriver arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterNavigateForward(WebDriver arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterNavigateRefresh(WebDriver arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterNavigateTo(String arg0, WebDriver arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterScript(String arg0, WebDriver arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void beforeAlertAccept(WebDriver arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void beforeAlertDismiss(WebDriver arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void beforeChangeValueOf(WebElement arg0, WebDriver arg1, CharSequence[] arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void beforeClickOn(WebElement arg0, WebDriver arg1) {

	}

	@Override
	public void beforeFindBy(By arg0, WebElement arg1, WebDriver arg2) {
		String awsomeBtn = "//button[@id='congrats_message']";
		String bonusBtn = "//button[@id='bonus_received_message']";
		String onBoardBtn = "//button[starts-with(@class,'joyride-tooltip__close')]";
		String closeCjmNotificationBtn = "//button[starts-with(@class,'tt-btn tt-desktop-notification__button notification-message-action-5')]";
		String closeCjmPopupBtn = "//div[starts-with(@class,'tt-bo-message__btn-container bo-message-action-5')]";
		String marginCallOkBtn = "//div[contains(text(),'The amount of securities')]/../../button[@id='notification_message_ok_button']";
		String tc_accept_btn="//button[@id='tc_accept_button']";
		// String rateChangeOkBtn = "//div[contains(text(),'The position was not opened
		// due to rate changes.')]/../../button[@id='notification_message_ok_button']";
		for (int i = 0; i < 10; i++) {
			if (isElementPresent(awsomeBtn, 0, arg2)) {
				arg2.findElement(By.xpath(awsomeBtn)).click();
			} else if (isElementPresent(bonusBtn, 0, arg2)) {
				arg2.findElement(By.xpath(bonusBtn)).click();
			} else if (isElementPresent(onBoardBtn, 0, arg2)) {
				arg2.findElement(By.xpath(onBoardBtn)).click();
			} else if (isElementPresent(closeCjmNotificationBtn, 0, arg2)) {
				arg2.findElement(By.xpath(closeCjmNotificationBtn)).click();
			} else if (isElementPresent(closeCjmPopupBtn, 0, arg2)) {
				arg2.findElement(By.xpath(closeCjmPopupBtn)).click();
			} else if (isElementPresent(marginCallOkBtn, 0, arg2)) {
				arg2.findElement(By.xpath(marginCallOkBtn)).click();
			}  else if (isElementPresent(tc_accept_btn, 0, arg2)) {
				 arg2.findElement(By.xpath(tc_accept_btn)).click();}
			else {
				break;
			}
		}

	}

	@Override
	public void beforeNavigateBack(WebDriver arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void beforeNavigateForward(WebDriver arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void beforeNavigateRefresh(WebDriver arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void beforeNavigateTo(String arg0, WebDriver arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void beforeScript(String arg0, WebDriver arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onException(Throwable arg0, WebDriver arg1) {
		// TODO Auto-generated method stub

	}

	public boolean isElementPresent(String xpathElement, int timeOut, WebDriver driver) {

		boolean found = true;
		try {

			WebDriverWait wait = new WebDriverWait(driver, timeOut);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathElement)));
		} catch (Exception e) {
			found = false;
		}
		return found;
	}

	@Override
	public <X> void afterGetScreenshotAs(OutputType<X> arg0, X arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterGetText(WebElement arg0, WebDriver arg1, String arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterSwitchToWindow(String arg0, WebDriver arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <X> void beforeGetScreenshotAs(OutputType<X> arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforeGetText(WebElement arg0, WebDriver arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforeSwitchToWindow(String arg0, WebDriver arg1) {
		// TODO Auto-generated method stub
		
	}

}
