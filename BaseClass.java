package pages.base;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

	public static WebDriver d;
	static JavascriptExecutor jk;
	static Actions a;
	static Robot robo;
	static Alert aa;
	static Select s;
	int failedcount = 0;
	int limit = 5;

	// Select Browser to launch
	public static void LaunchBrowser(String Browser, String Url) {
		if (Browser.equals("Chrome")) {
			WebDriverManager.chromedriver().setup();
			d = new ChromeDriver();

		}
		if (Browser.equals("Edge")) {
			WebDriverManager.edgedriver().setup();
			d = new EdgeDriver();

		}
		if (Browser.equals("Firefox")) {
			WebDriverManager.firefoxdriver().setup();
			d = new FirefoxDriver();
		}

		d.get(Url);

	}

	// locators
	public static WebElement locators(String locators, String value) {

		@SuppressWarnings("unused")
		WebElement element;

		if (locators.equals("id")) {
			return element = d.findElement(By.id(value));
		} else if (locators.equals("name")) {
			return element = d.findElement(By.name(value));
		} else if (locators.equals("partialLinkText")) {
			return element = d.findElement(By.partialLinkText(value));
		} else if (locators.equals("className")) {
			return element = d.findElement(By.className(value));
		} else if (locators.equals("linkText")) {
			return element = d.findElement(By.linkText(value));
		} else {
			return element = d.findElement(By.xpath(value));

		}
	}

	// enter text using send Keys
	public static void enterText(WebElement element, String text) {

		element.sendKeys(text);

	}

	// left click
	public static void Click(WebElement element) {

		element.click();

	}

	// maximize
	public static void maximize() {
		d.manage().window().maximize();
	}

	// Refresh the Page
	public static void refresh() {
		d.navigate().refresh();
	}

	// Navigate Back Page
	public static void backPage() {
		d.navigate().back();
	}

	// Navigate Forward Page
	public static void forwardPage() {
		d.navigate().forward();
	}

	// timeout for he page to load using implicit wait
	public static void timeimply() {
		d.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	}

	// timeout for he page to load using WebDriver wait
	public static void timeexply(WebElement element) {
		WebDriverWait w = new WebDriverWait(d, Duration.ofSeconds(30));
		w.until(ExpectedConditions.visibilityOf(element));

	}

	// timeout for he page to load using Fluent wait
	public static void timefluent() {
		FluentWait<WebDriver> w = new FluentWait<WebDriver>(d);
		w.withTimeout(Duration.ofSeconds(30));
	}

	// get attribute
	public static String getatt(WebElement element, String value) {
		String attribute = element.getAttribute(value);
		return attribute;

	}

	// drag and drop
	public static void dragNdrop(WebElement sourceelemnt, WebElement destinationelement) {

		a = new Actions(d);
		a.dragAndDrop(sourceelemnt, destinationelement).perform();

	}

	// double click
	public static void DoubleClick(WebElement element) {
		a = new Actions(d);
		a.doubleClick(element).perform();

	}

	// Right Click
	public static void RightClick(WebElement element) {
		a = new Actions(d);
		a.contextClick(element).perform();
	}

	// select by index
	public static void selectByIndex(WebElement element, int a) {
		s = new Select(element);
		s.selectByIndex(a);

	}

	// Select from Visible Text
	public static void selectOption(String name, String text) {
		s = new Select(d.findElement(By.name(name)));
		s.selectByVisibleText(text);
	}

	// select all text
	public static void Selectall() throws AWTException {
		robo = new Robot();
		robo.keyPress(KeyEvent.VK_CONTROL);
		robo.keyPress(KeyEvent.VK_A);
		robo.keyRelease(KeyEvent.VK_CONTROL);
		robo.keyRelease(KeyEvent.VK_A);

	}

	// copy text
	public static void copy() throws AWTException {
		robo = new Robot();
		robo.keyPress(KeyEvent.VK_CONTROL);
		robo.keyPress(KeyEvent.VK_C);
		robo.keyRelease(KeyEvent.VK_CONTROL);
		robo.keyRelease(KeyEvent.VK_C);

	}

	// cut text
	public static void cut() throws AWTException {
		robo = new Robot();
		robo.keyPress(KeyEvent.VK_CONTROL);
		robo.keyPress(KeyEvent.VK_X);
		robo.keyRelease(KeyEvent.VK_CONTROL);
		robo.keyRelease(KeyEvent.VK_X);

	}

	// paste text
	public static void paste() {

		robo.keyPress(KeyEvent.VK_CONTROL);
		robo.keyPress(KeyEvent.VK_V);
		robo.keyRelease(KeyEvent.VK_CONTROL);
		robo.keyRelease(KeyEvent.VK_V);

	}

	// WebTable
	public static void table(String text) {
		WebElement Table = d.findElement(By.tagName("table"));
		Table.findElement(By.xpath(text));
	}

	// Alert OKay
	public static void alertConfirm() {

		aa = d.switchTo().alert();
		aa.accept();

	}

	// Alert Deny
	public static void alertDismiss() {
		aa = d.switchTo().alert();
		aa.dismiss();

	}

	// Quit
	public static void quit() {
		d.quit();
	}

	// Switch to Frames with index
	public static void frames(int a) {
		d.switchTo().frame(a);
	}

	// Switch to Frames with Frames Element
	public static void frames(WebElement element) {
		d.switchTo().frame(element);
	}

	// JavaScript Executor
	public static void Javascript(String Sendkeyvalue, String inspected_element) {
		jk = (JavascriptExecutor) d;
		jk.executeScript("arguments[0].setAttribute('value','" + Sendkeyvalue + "')",
				d.findElement(By.xpath(inspected_element)));
	}

	// ScreenShot
	public static void screenShot(String Path) throws IOException {

		TakesScreenshot take = (TakesScreenshot) d;
		Date d = new Date();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss"); 
		File Sourcefile = take.getScreenshotAs(OutputType.FILE);
		File Destination = new File(Path + " " + sdf.format(d) + ".png");
		
		FileUtils.copyFile(Sourcefile, Destination);
		}
		


	// Read excel
	public static String excelRead(String path, String sheetname, int rowindex, int cellindex) throws IOException {

		File f = new File(path);
		FileInputStream fi = new FileInputStream(f);
		Workbook w = new XSSFWorkbook(fi);
		Sheet s = w.getSheet(sheetname);
		Row r = s.getRow(rowindex);
		Cell c = r.getCell(cellindex);
		int i = c.getCellType();
		if (i == 1) {
			String text = c.getStringCellValue();
			return text;
		} else if (DateUtil.isCellDateFormatted(c)) {
			Date date = c.getDateCellValue();
			SimpleDateFormat fo = new SimpleDateFormat("dd/MM/yyyy");
			String text = fo.format(date);
			return text;
		} else {
			double e = c.getNumericCellValue();
			long l = (long) e;
			String text = String.valueOf(l);
			return text;

		}

		// Write Excel

	}

	public boolean retry(ITestResult result) {
		if (failedcount < limit) {
			failedcount++;
			return true;
		}
		return false;		
	}

}
