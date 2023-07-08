package base;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Hashtable;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.paulhammant.ngwebdriver.ByAngular;
import com.paulhammant.ngwebdriver.NgWebDriver;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.github.bonigarcia.wdm.WebDriverManager;
import utilities.ExcelReader;
import utilities.ExtentManager;
import utilities.TestUtil;

public class TestBase {

	public static WebDriver driver;
	public static NgWebDriver ngDriver;
	public static JavascriptExecutor jsDriver;
	public static Properties config = new Properties();
	public static Properties OR = new Properties();
	public static FileInputStream fis;
	public static Logger log = Logger.getLogger("devpinoyLogger");
	public static ExcelReader excel = new ExcelReader(
			System.getProperty("user.dir") + "/src/test/resources/excel/testdata.xlsx");
	public static WebDriverWait wait;
	public ExtentReports rep = ExtentManager.getInstance();
	public static ExtentTest test;
	public static String browser;

	@BeforeSuite
	public void setUp() {

		// Initialize the Config and OR Properties file

		if (driver == null) {

			try {
				fis = new FileInputStream(
						System.getProperty("user.dir") + "/src/test/resources/properties/Config.properties");
			} catch (FileNotFoundException e) {

				e.printStackTrace();
			}
			try {
				config.load(fis);
				log.info("Config file is loaded successfully!!!");
				System.out.println("Config file is loaded successfully!!!");
			} catch (IOException e) {

				e.printStackTrace();
			}

			try {
				fis = new FileInputStream(
						System.getProperty("user.dir") + "/src/test/resources/properties/OR.properties");
			} catch (FileNotFoundException e) {

				e.printStackTrace();
			}
			try {
				OR.load(fis);
				log.info("OR file is loaded successfully!!!");
				System.out.println("OR file is loaded successfully!!!");
			} catch (IOException e) {

				e.printStackTrace();
			}
		}

		// Initialize the browsers

		if (config.getProperty("browser").equals("firefox")) {

			WebDriverManager.firefoxdriver().setup();
			System.setProperty("webdriver.gecko.driver",
					System.getProperty("user.dir") + "/src/test/resources/executables/geckodriver.exe");

			WebDriverManager.firefoxdriver().setup();
			FirefoxOptions options = new FirefoxOptions();
			options.addArguments("headless");
			options.addArguments("window-size=1200x600");
			options.addArguments("--no-sandbox");
			options.addArguments("--disable-dev-shm-usage");
			options.addArguments("--headless");
			driver = new FirefoxDriver(options);
			jsDriver = (JavascriptExecutor) driver;
			ngDriver = new NgWebDriver(jsDriver);
			log.info("FireFox launched successfully!!!");
			System.out.println("FireFox launched successfully!!!");

		} else if (config.getProperty("browser").equals("chrome")) {

//			WebDriverManager.chromedriver().setup();
//			driver = new ChromeDriver();
//			jsDriver = (JavascriptExecutor) driver;
//			ngDriver = new NgWebDriver(jsDriver);

			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("headless");
			options.addArguments("window-size=1366x768");
			options.addArguments("--no-sandbox");
			options.addArguments("--disable-dev-shm-usage");
			options.addArguments("--headless");
			driver = new ChromeDriver(options);
			jsDriver = (JavascriptExecutor) driver;
			ngDriver = new NgWebDriver(jsDriver);
			log.info("Chrome launched successfully!!!");
			System.out.println("Chrome launched successfully!!!");
		}

		// Initialize the Application URL

		driver.get(config.getProperty("testsiteurl"));
		ngDriver.waitForAngularRequestsToFinish();
		log.info("Test site launched successfully!!!" + config.getProperty("testsiteurl"));
		System.out.println("Test site launched successfully!!! " + config.getProperty("testsiteurl"));

//		ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
//		driver.switchTo().window(tabs2.get(1));

		driver.manage().window().maximize();
		log.info("The window maximize successfully!!!");
		System.out.println("The window maximize successfully!!!");
		driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implecit.wait")),
				TimeUnit.SECONDS);
		wait = new WebDriverWait(driver, 45);

	}

	// For click on the particular element

	public void click(String locator) {

		if (locator.endsWith("_CSS")) {
			driver.findElement(By.cssSelector(OR.getProperty(locator))).click();
		} else if (locator.endsWith("_XPATH")) {
			driver.findElement(By.xpath(OR.getProperty(locator))).click();
		} else if (locator.endsWith("_ID")) {
			driver.findElement(By.id(OR.getProperty(locator))).click();
		} else if (locator.endsWith("_MODEL")) {
			driver.findElement(ByAngular.model(OR.getProperty(locator))).click();
		} else if (locator.endsWith("_BTNTEXT")) {
			driver.findElement(ByAngular.buttonText(OR.getProperty(locator))).click();
		}

		test.log(LogStatus.INFO, "Clicking on : " + locator);
		System.out.println("Clicking on : " + locator);
	}

	// For typing the text into the text field

	public void type(String locator, String value) {

		if (locator.endsWith("_CSS")) {
			driver.findElement(By.cssSelector(OR.getProperty(locator))).sendKeys(value);
		} else if (locator.endsWith("_XPATH")) {
			driver.findElement(By.xpath(OR.getProperty(locator))).sendKeys(value);
		} else if (locator.endsWith("_ID")) {
			driver.findElement(By.id(OR.getProperty(locator))).sendKeys(value);
		} else if (locator.endsWith("_MODEL")) {
			driver.findElement(ByAngular.model(OR.getProperty(locator))).sendKeys(value);
		} else if (locator.endsWith("_BTNTEXT")) {
			driver.findElement(ByAngular.buttonText(OR.getProperty(locator))).sendKeys(value);
		}
		test.log(LogStatus.INFO, "Typing on : " + locator + " and entered value as " + "\"" + value + "\"");
		System.out.println("Typing on : " + locator + " and entered value as " + "\"" + value + "\"");
	}

	// Select value from the dropdown

	static WebElement dropdown;

	public void select(String locator, String value) {

		if (locator.endsWith("_CSS")) {
			dropdown = driver.findElement(By.cssSelector(OR.getProperty(locator)));
		} else if (locator.endsWith("_XPATH")) {
			dropdown = driver.findElement(By.xpath(OR.getProperty(locator)));
		} else if (locator.endsWith("_ID")) {
			dropdown = driver.findElement(By.id(OR.getProperty(locator)));
		} else if (locator.endsWith("_MODEL")) {
			driver.findElement(ByAngular.model(OR.getProperty(locator))).sendKeys(value);
		} else if (locator.endsWith("_BTNTEXT")) {
			driver.findElement(ByAngular.buttonText(OR.getProperty(locator))).sendKeys(value);
		}

		Select select = new Select(dropdown);
		select.selectByVisibleText(value);

		test.log(LogStatus.INFO, "Selecting from dropdown : " + locator + " value as " + "\"" + value + "\"");
		System.out.println("Selecting from dropdown : " + locator + " value as " + "\"" + value + "\"");

	}

	// Select random value from the dropdown

	static WebElement dropdownRandom;

	public void selectRandom(String locator) {

		if (locator.endsWith("_CSS")) {
			dropdownRandom = driver.findElement(By.cssSelector(OR.getProperty(locator)));
		} else if (locator.endsWith("_XPATH")) {
			dropdownRandom = driver.findElement(By.xpath(OR.getProperty(locator)));
		} else if (locator.endsWith("_ID")) {
			dropdownRandom = driver.findElement(By.id(OR.getProperty(locator)));
		}

		// Using Select Class to fetch the count
		Select select = new Select(dropdownRandom);
		List<WebElement> weblist = select.getOptions();

		// Taking the count of items
		int iCnt = weblist.size();

		// Using Random class to generate random values
		Random num = new Random();
		int iSelect = num.nextInt(iCnt);

		// Selecting value from DropDownList
		select.selectByIndex(iSelect);

		// Selected Value
		String finalValue = dropdownRandom.getAttribute("value");
		System.out.println(finalValue);

		test.log(LogStatus.INFO, "Selecting from dropdown : " + locator + " value as " + "\"" + finalValue + "\"");
		System.out.println("Selecting from dropdown : " + locator + " value as " + "\"" + finalValue + "\"");

	}

	// For clear the text in the field

	public void clear(String locator) {

		if (locator.endsWith("_CSS")) {
			driver.findElement(By.cssSelector(OR.getProperty(locator))).clear();
			ngDriver.waitForAngularRequestsToFinish();
		} else if (locator.endsWith("_XPATH")) {
			driver.findElement(By.xpath(OR.getProperty(locator))).clear();
			ngDriver.waitForAngularRequestsToFinish();
		} else if (locator.endsWith("_ID")) {
			driver.findElement(By.id(OR.getProperty(locator))).clear();
			ngDriver.waitForAngularRequestsToFinish();
		} else if (locator.endsWith("_MODEL")) {
			driver.findElement(ByAngular.model(OR.getProperty(locator))).clear();
			ngDriver.waitForAngularRequestsToFinish();
		} else if (locator.endsWith("_BTNTEXT")) {
			driver.findElement(ByAngular.buttonText(OR.getProperty(locator))).clear();
			ngDriver.waitForAngularRequestsToFinish();
		}

		Reporter.log("Clear the data of : " + locator);
		log.info("Clear the data of : " + locator);
		test.log(LogStatus.INFO, "Clear the data of : " + locator);
		System.out.println("Clear the data of : " + locator);
	}

	// For Explicit Wait - visibilityOfElementLocated

	public void explicitWait(String locator) {

		WebDriverWait wait = new WebDriverWait(driver, 45);
		if (locator.endsWith("_CSS")) {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(OR.getProperty(locator))));
		} else if (locator.endsWith("_XPATH")) {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty(locator))));
		} else if (locator.endsWith("_ID")) {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(OR.getProperty(locator))));
		} else if (locator.endsWith("_BTNTEXT")) {
			wait.until(ExpectedConditions.visibilityOfElementLocated(ByAngular.buttonText(OR.getProperty(locator))));
		}
		test.log(LogStatus.INFO, "Waiting for : " + locator);
		System.out.println("Waiting for : " + locator);
	}

	// For Explicit Wait - elementToBeClickable

	public void explicitWaitClickable(String locator) throws InterruptedException {

		WebDriverWait wait1 = new WebDriverWait(driver, 45);
		if (locator.endsWith("_CSS")) {
			wait1.until(ExpectedConditions.elementToBeClickable(By.cssSelector(OR.getProperty(locator))));
		} else if (locator.endsWith("_XPATH")) {
			wait1.until(ExpectedConditions.elementToBeClickable(By.xpath(OR.getProperty(locator))));
		} else if (locator.endsWith("_ID")) {
			wait1.until(ExpectedConditions.elementToBeClickable(By.id(OR.getProperty(locator))));
		} else if (locator.endsWith("_BTNTEXT")) {
			wait1.until(ExpectedConditions.elementToBeClickable(ByAngular.buttonText(OR.getProperty(locator))));
		}

		test.log(LogStatus.INFO, "Waiting for : " + locator);
		System.out.println("Waiting for : " + locator);

	}

	// Verify the element is present or not on the screen

	public static boolean isElementPresent(By by) {

		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}

	}

	// Verify the actual result with expected result. -- Soft Assertion

	public static void verifyEquals(String locator, String expected) throws IOException {

		try {
			if (locator.endsWith("_CSS")) {
				String msg = (driver.findElement(By.cssSelector(OR.getProperty(locator))).getText()).trim();
				Assert.assertEquals(msg, expected);
			} else if (locator.endsWith("_XPATH")) {
				String msg = (driver.findElement(By.xpath(OR.getProperty(locator))).getText()).trim();
				Assert.assertEquals(msg, expected);
			} else if (locator.endsWith("_ID")) {
				String msg = (driver.findElement(By.id(OR.getProperty(locator))).getText()).trim();
				Assert.assertEquals(msg, expected);
			} else if (locator.endsWith("_MODEL")) {
				String msg = (driver.findElement(ByAngular.model(OR.getProperty(locator))).getText()).trim();
				Assert.assertEquals(msg, expected);
			} else if (locator.endsWith("_BTNTEXT")) {
				String msg = (driver.findElement(ByAngular.buttonText(OR.getProperty(locator))).getText()).trim();
				Assert.assertEquals(msg, expected);
			}
			test.log(LogStatus.INFO, "Verifing : " + locator + " value as " + "\"" + expected + "\"");
			System.out.println("Verifing : " + locator + " value as " + "\"" + expected + "\"");

		} catch (Throwable t) {

			TestUtil.captureScreenshot();

			// ReportNG
			Reporter.log("<br>" + "Verification failure : " + t.getMessage() + "<br>");
			Reporter.log("<a target=\"_blank\" href=" + TestUtil.screenshotName + "><img src=" + TestUtil.screenshotName
					+ " height=200 width=200></img></a>");
			Reporter.log("<br>");
			Reporter.log("<br>");

			// Extent Report
			test.log(LogStatus.FAIL, " Verification failed with exception : " + t.getMessage());
			test.log(LogStatus.FAIL, test.addScreenCapture(TestUtil.screenshotName));
		}

	}

	// Verify the test is pass or not using AssertTrue

	public static void verifyTrue(String locator, String description) throws IOException {

		try {
			if (locator.endsWith("_CSS")) {
				Assert.assertTrue(isElementPresent(By.cssSelector(OR.getProperty(locator))), description);
			} else if (locator.endsWith("_XPATH")) {
				Assert.assertTrue(isElementPresent(By.xpath(OR.getProperty(locator))), description);
			} else if (locator.endsWith("_ID")) {
				Assert.assertTrue(isElementPresent(By.id(OR.getProperty(locator))), description);
			} else if (locator.endsWith("_MODEL")) {
				Assert.assertTrue(isElementPresent(ByAngular.model(OR.getProperty(locator))), description);
			} else if (locator.endsWith("_BTNTEXT")) {
				Assert.assertTrue(isElementPresent(ByAngular.buttonText(OR.getProperty(locator))), description);
			}
			System.out.println("The verified of the " + locator + " is done successfully.");
			test.log(LogStatus.INFO, "The verified of the " + locator + " is done successfully.");
			Reporter.log("The verified of the " + locator + " is done successfully.");
			log.info("The verified of the " + locator + " is done successfully.");

		} catch (Throwable t) {

			TestUtil.captureScreenshot();

			// ReportNG
			Reporter.log("<br>" + "Verification failure : " + t.getMessage() + "<br>");
			Reporter.log("<a target=\"_blank\" href=" + TestUtil.screenshotName + "><img src=" + TestUtil.screenshotName
					+ " height=200 width=200></img></a>");
			Reporter.log("<br>");
			Reporter.log("<br>");

			// Extent Report
			test.log(LogStatus.FAIL, " Verification failed with exception : " + t.getMessage());
			test.log(LogStatus.FAIL, test.addScreenCapture(TestUtil.screenshotName));

		}

	}

	// Verification of the data using AssertTrue

	public void switchVerification(String locator, String name, String description)
			throws IOException, InterruptedException {

		try {
			if (locator.endsWith("_CSS")) {
				String str1 = (driver.findElement(By.cssSelector(OR.getProperty(locator))).getText()).trim();

				ngDriver.waitForAngularRequestsToFinish();
				if (str1.equals(name)) {
					Assert.assertTrue(isElementPresent(By.cssSelector(OR.getProperty(locator))), description);

					System.out.println("The " + name + " is verified successfully.");
					test.log(LogStatus.INFO, "The " + name + " is verified successfully.");
					Reporter.log("The " + name + " is verified successfully.");
					log.info("The " + name + " is verified successfully.");
				} else {
					verificationFailed();
				}

			} else if (locator.endsWith("_XPATH")) {
				String str2 = (driver.findElement(By.xpath(OR.getProperty(locator))).getText()).trim();
				ngDriver.waitForAngularRequestsToFinish();
				if (str2.equals(name)) {
					Assert.assertTrue(isElementPresent(By.xpath(OR.getProperty(locator))), description);

					System.out.println("The " + name + " is verified successfully.");
					test.log(LogStatus.INFO, "The " + name + " is verified successfully.");
					Reporter.log("The " + name + " is verified successfully.");
					log.info("The " + name + " is verified successfully.");
				} else {
					verificationFailed();
				}
			}

		} catch (Throwable t) {

			verificationFailed();
		}
	}

	public void verificationFailed() throws IOException, InterruptedException {

		TestUtil.captureScreenshot();

		// ReportNG
		Reporter.log("<br>" + " The verification is failed : " + "<br>");
		Reporter.log("<a target=\"_blank\" href=" + TestUtil.screenshotName + "><img src=" + TestUtil.screenshotName
				+ " height=200 width=200></img></a>");
		Reporter.log("<br>");
		Reporter.log("<br>");

		// Extent Report
		test.log(LogStatus.FAIL, " The verification is failed : ");
		test.log(LogStatus.FAIL, test.addScreenCapture(TestUtil.screenshotName));

		System.out.println("The verification is failed.");
		log.info("The verification is failed.");

	}

	public void verificationFailedMessage(String message) throws IOException, InterruptedException {

		TestUtil.captureScreenshot();

		// ReportNG
		Reporter.log("<br>" + message + "<br>");
		Reporter.log("<a target=\"_blank\" href=" + TestUtil.screenshotName + "><img src=" + TestUtil.screenshotName
				+ " height=200 width=200></img></a>");
		Reporter.log("<br>");
		Reporter.log("<br>");

		// Extent Report
		test.log(LogStatus.FAIL, message);
		test.log(LogStatus.FAIL, test.addScreenCapture(TestUtil.screenshotName));

		System.out.println(message);
		log.info(message);

	}



	// This method will set any parameter string to the system's clipboard.

	public static void setClipboardData(String string) {
		// StringSelection is a class that can be used for copy and paste operations.
		StringSelection stringSelection = new StringSelection(string);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
	}

	public static void uploadFile(String fileLocation) {
		try {
			// Setting clip board with file location
			setClipboardData(fileLocation);
			// native key strokes for CTRL, V and ENTER keys
			Robot robot = new Robot();

			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
		} catch (Exception exp) {
			exp.printStackTrace();
		}
	}


	public void successMessage(String message) {
		System.out.println(message);
		test.log(LogStatus.INFO, message);
		Reporter.log(message);
		log.info(message);
	}

	// Console Message
	public void consoleMessage(String message) {

		ngDriver.waitForAngularRequestsToFinish();

		System.out.println("Console ---> " + message);
		test.log(LogStatus.INFO, "Console ---> " + message);
		Reporter.log("Console ---> " + message);
		log.info("Console ---> " + message);
	}

	// Title of the scenario
	public void title(String description) {

		test.log(LogStatus.INFO, "********** " + description + " **********");
		Reporter.log("********** " + description + " **********");
		log.info("********** " + description + " **********");

	}

	// selection of the test case for the execution
	public void execution(Hashtable<String, String> data, String locator) {

		if (TestUtil.isTestRunnable(locator, excel)) {

			if (data.get("runmode").equals("Y")) {

				consoleMessage("Executing the test " + locator.toUpperCase() + " as the Run mode is YES");

			} else {
				throw new SkipException("Skipping the test case as the Run Mode for data set is NO");
			}

		} else {
			throw new SkipException("Skipping the test " + locator.toUpperCase() + " as the Run mode is NO");
		}
	}

	// enter today's date
	public String today() {

		LocalDate today = LocalDate.now();
		String today_string = today.toString();

		return today_string;
	}

	// Verification for the data is not available
	public void switchVerificationFailed(String locator, String name, String description)
			throws IOException, InterruptedException {

		try {
			if (locator.endsWith("_CSS")) {
				String str1 = (driver.findElement(By.cssSelector(OR.getProperty(locator))).getText()).trim();

				if (str1.equals(name)) {

					verificationFailedMessage("The " + name + " is displayed.");

				} else {
					successMessage("The " + name + " is not displayed as expected.");
				}

			} else if (locator.endsWith("_XPATH")) {
				String str2 = (driver.findElement(By.xpath(OR.getProperty(locator))).getText()).trim();

				if (str2.equals(name)) {

					verificationFailedMessage("The " + name + " is displayed.");

				} else {
					successMessage("The " + name + " is not displayed as expected.");
				}
			}

		} catch (Throwable t) {
			successMessage("The " + name + " is not displayed as expected.");
		}
	}

	// Scroll down to pixel
	public void scrollByPixel(int pixel) {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0," + pixel + ")");
		ngDriver.waitForAngularRequestsToFinish();
		consoleMessage("Scroll Down the screen By " + pixel + " px.");
	}

	// Scroll up to top
	public void scrollTop() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, 0)");
		ngDriver.waitForAngularRequestsToFinish();
		consoleMessage("Scroll at the top of the screen.");
	}

	// Scroll up to bottom
	public void scrollBottom() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0, document.body.scrollHeight)");
		ngDriver.waitForAngularRequestsToFinish();
		consoleMessage("Scroll at the bottom of the screen.");
	}

	// scroll to respective element
	public void scrollTillElement(String locator) {
		WebElement ele = null;

		if (locator.endsWith("_CSS")) {
			ele = driver.findElement(By.cssSelector(OR.getProperty(locator)));
		} else if (locator.endsWith("_XPATH")) {
			ele = driver.findElement(By.xpath(OR.getProperty(locator)));
		} else if (locator.endsWith("_ID")) {
			ele = driver.findElement(By.id(OR.getProperty(locator)));
		} else if (locator.endsWith("_MODEL")) {
			ele = driver.findElement(ByAngular.model(OR.getProperty(locator)));
		} else if (locator.endsWith("_BTNTEXT")) {
			ele = driver.findElement(ByAngular.buttonText(OR.getProperty(locator)));

		}

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", ele);
		ngDriver.waitForAngularRequestsToFinish();
		consoleMessage("Scroll till the respective element.");
	}

	@AfterSuite
	public void tearDown() {

		if (driver != null) {
			driver.quit();
		}

		log.info("Test execution completed!!!");
		System.out.println("Test execution completed!!!");
	}

}
