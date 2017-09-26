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
	private static final String URL = "http://www.checkbox.io/studies.html";

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
	 * This test asserts that `The participant count of "Frustration of Software
	 * Developers" is 55`
	 * 
	 * @throws Exception
	 */
	@Test
	public void participationCount() throws Exception {
		driver.get(URL);
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
	 * This test asserts that `The total number of studies closed is 5.`
	 * 
	 * @throws Exception
	 */
	@Test
	public void closed() throws Exception {
		driver.get(URL);
		String closedPostPath = "//a[@class='status']/span[.='CLOSED']";
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(closedPostPath)));
		List<WebElement> spans = driver.findElements(By.xpath(closedPostPath));
		assertNotNull(spans);
		assertEquals(5, spans.size());
	}

	/**
	 * This test asserts that `If a status of a study is open, you can click on a
	 * "Participate" button.`
	 * 
	 * @throws Exception
	 */
	@Test
	public void clickableButton() throws Exception {
		driver.get(URL);
		String openPostPath = "//a[@class='status']/span[.='OPEN']";
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(openPostPath)));
		List<WebElement> spans = driver.findElements(By.xpath(openPostPath));
		assertNotNull(spans);
		for (WebElement span : spans) {
			WebElement child = span.findElement(By.xpath("../following-sibling::div//button"));
			// System.out.println(child.getText());
			if ("Participate".equals(child.getText()) && child.isDisplayed() && child.isEnabled()) {
				// case when participate button is there.
				try {
					child.click();
					// case when participate button is click-able
					assertTrue(true);
				} catch (Exception e) {
					// case when participate button is there and enabled and displayed, but it
					// cannot be clicked.
					assertTrue(false);
				}
			} else {
				// case when participate button itself is not there, or is disabled or hidden.
				assertTrue(false);
			}
		}
	}

	/**
	 * This test asserts that `"Software Changes Survey" has a Amazon reward image.`
	 * 
	 * @throws Exception
	 */
	@Test
	public void rewardImage() throws Exception {
		driver.get(URL);
		String titlePath = "//div[@class='span8']//span[.='Software Changes Survey']";
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(titlePath)));
		WebElement title = driver.findElement(By.xpath(titlePath));
		assertNotNull(title);
		WebElement amazonImage = title.findElement(By.xpath("../following-sibling::div[@class='award']/div/span/img"));
		assertNotNull(amazonImage);
		// System.out.println(amazonImage.getAttribute("src"));
		// Check that the reward image is of 'amazon'
		assertTrue(amazonImage.getAttribute("src").contains("amazon"));
	}

}