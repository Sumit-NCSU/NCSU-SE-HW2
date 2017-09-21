package com.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.ChromeDriverManager;

public class WebTests {

	private static WebDriver driver;

	@BeforeClass
	public static void setUp() throws Exception {
		// driver = new HtmlUnitDriver();
		ChromeDriverManager.getInstance().setup();
		driver = new ChromeDriver();
	}

	@AfterClass
	public static void tearDown() throws Exception {
		driver.close();
		driver.quit();
	}

	@Test
	public void googleExists() throws Exception {
		driver.get("http://www.google.com");
		assertEquals("Google", driver.getTitle());
	}

	/**
	 * Assert `The participant count of "Frustration of Software Developers" is 55`
	 * 
	 * @throws Exception
	 */
	@Test
	public void participationCount() throws Exception {
		driver.get("http://www.checkbox.io/studies.html");
		String titlePath = "//div[@class='span8']//span[.='Frustration of Software Developers']";
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(titlePath)));
		WebElement title = driver.findElement(By.xpath(titlePath));
		assertNotNull(title);
		WebElement child = title.findElement(By.xpath("../../following-sibling::div//span[@class='backers']"));
		assertNotNull(child);
		assertEquals("55", child.getText());
	}

	/**
	 * Assert `The total number of studies closed is 5.`
	 * 
	 * @throws Exception
	 */
	@Test
	public void closed() throws Exception {
		driver.get("http://www.checkbox.io/studies.html");
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='status']/span[.='CLOSED']")));
		List<WebElement> spans = driver.findElements(By.xpath("//a[@class='status']/span[.='CLOSED']"));
		assertNotNull(spans);
		assertEquals(5, spans.size());
	}

	/**
	 * Assert `If a status of a study is open, you can click on a "Participate"
	 * button.`
	 * 
	 * @throws Exception
	 */
	@Test
	public void clickableButton() throws Exception {
		driver.get("http://www.checkbox.io/studies.html");
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='status']/span[.='OPEN']")));
		List<WebElement> spans = driver.findElements(By.xpath("../a[@class='status']/span[.='OPEN']"));
		assertNotNull(spans);
		for (WebElement span : spans) {
			WebElement child = span.findElement(By.xpath("../following-sibling::div//button"));
			if (child.isDisplayed() && child.isEnabled()) {
				assertTrue(true);
			} else {
				assertTrue(false);
			}
		}
	}

	/**
	 * Assert `Check if the "Software Changes Survey" has a Amazon reward image.`
	 * 
	 * @throws Exception
	 */
	@Test
	public void rewardImage() throws Exception {
		driver.get("http://www.checkbox.io/studies.html");
		String titlePath = "//div[@class='span8']//span[.='Software Changes Survey']";
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(titlePath)));
		WebElement title = driver.findElement(By.xpath(titlePath));
		assertNotNull(title);
		WebElement amazonImage = title.findElement(By.xpath("../following-sibling::div[@class='award']/div/span/img"));
		assertNotNull(amazonImage);
	}

}