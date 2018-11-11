package tests;

import java.io.FileInputStream;
import java.util.Properties;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import bl.factory.TajawalObjectFactory;
import core.automation.EventListener;

public class BaseTest {
	EventListener eventListener = new EventListener();
	private Properties config;
	protected TajawalObjectFactory tajawal;
	protected String url;
	protected String browserType;

	
	@BeforeClass(alwaysRun = true)
	@Parameters({ "config" })

	public void testInitialize(@Optional("config/config") String appConfig) {
		try {
			this.config = new Properties();

			this.config.load(new FileInputStream(appConfig));
			this.url = this.config.getProperty("tajawal_ae");
			this.browserType=this.config.getProperty("browser");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void openTajawal(String url) throws InterruptedException {
		tajawal.openTajawal(url);
	}

}
