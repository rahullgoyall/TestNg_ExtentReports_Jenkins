package utility;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;



public class LocalDriverFactory {
	static Logger logger = Logger.getLogger(LocalDriverFactory.class);
	
	
	
	public static WebDriver createInstance(String browserName, String baseUrl){
		WebDriver driver = null;
		if (System.getProperty("browser")!=null)
			browserName = System.getProperty("browser");
		
		String hubUrl = "http://localhost:4444/wd/hub";
		
		switch (browserName) {
		
		 case "headless_chrome":
			 //Copy drivers/chrome/chromedriver to /usr/local/bin/
			 WebDriverManager.chromedriver().setup();
			 ChromeOptions options = new ChromeOptions();
		     options.addArguments("headless");
		     options.addArguments("window-size=1200x600");
		     
		     driver = new ChromeDriver(options);
			 break;
			 
		 case "headless_firefox":
			//Copy drivers/chrome/geckodriver to /usr/local/bin/
			 FirefoxOptions ff_options = new FirefoxOptions();
			 ff_options.setHeadless(true);
			 driver = new FirefoxDriver(ff_options);
			 break;
		 case "chrome":
			 WebDriverManager.chromedriver().setup();
			//Copy drivers/chrome/chromedriver to /usr/local/bin/
			 String downloadPath = System.getProperty("user.dir")+File.separator+"downloadFiles";
			 ChromeOptions cc_options = new ChromeOptions();
			 HashMap<String, Object> chromePref = new HashMap<String, Object>();
			//To Turns off multiple download warning
			 chromePref.put("profile.default_content_settings.popups", 0);
			 chromePref.put( "profile.content_settings.pattern_pairs.*.multiple-automatic-downloads", 1 );
			 chromePref.put("download.default_directory", downloadPath);
			 cc_options.setExperimentalOption("prefs", chromePref);
			 driver = new ChromeDriver(cc_options);
			 break;

		 case "firefox":
			//Copy drivers/chrome/geckodriver to /usr/local/bin/
			 WebDriverManager.firefoxdriver().setup();
			 driver = new FirefoxDriver();
			 break;

		 case "grid_chrome":
			// WebDriverManager.chromedriver().setup();
			//Copy drivers/chrome/chromedriver to /usr/local/bin/ on node machine
			 DesiredCapabilities capabilities = DesiredCapabilities.chrome();
			 capabilities.setBrowserName("chrome");
			 capabilities.setPlatform(Platform.LINUX);
			 capabilities.setCapability("headless", true);
			 capabilities.setCapability("name", "testCaseTitle");
			 try {
				driver = new RemoteWebDriver(new URL(hubUrl),capabilities);
			} catch (MalformedURLException e1) {
				// TODO Auto-generated catch block
			}
			 break;
		
		 case "grid_firefox":
			//Copy drivers/chrome/geckodriver to /usr/local/bin/ on node machine
			 DesiredCapabilities capabilities_ff = DesiredCapabilities.firefox();
			 capabilities_ff.setBrowserName("firefox");
			 capabilities_ff.setPlatform(Platform.LINUX);
			 capabilities_ff.setCapability("headless", true);
			 try {
				driver = new RemoteWebDriver(new URL(hubUrl),capabilities_ff);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 break;
			 
		 case "edge":
				 driver = new EdgeDriver();
				 break;		 
		default:
			logger.error("Invalid Browser set in database configuration table");
			break;
		}
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(baseUrl);
		LocalDriverManager.setWebDriver(driver);
		return driver;
	}
	
	public static void closeBrowser(){
		if(LocalDriverManager.getWebDriver()!=null){
			try{
				Thread.sleep(2000);
			}catch (Exception e) {
				// TODO: handle exception
			}
			LocalDriverManager.getWebDriver().quit();
		}
	}

}
