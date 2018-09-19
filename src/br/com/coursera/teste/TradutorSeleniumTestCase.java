package br.com.coursera.teste;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TradutorSeleniumTestCase {
	private WebDriver driver;
	private String baseUrl;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();

	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver", "resources\\chromedriver.exe");
		driver = new ChromeDriver();
		baseUrl = "http://localhost:8080/tradutor/";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void testPalavraExistenteBTestCase() throws Exception {
		driver.get(baseUrl);
		driver.findElement(By.id("btn_idioma")).click();
	    driver.findElement(By.id("tradutor")).click();
	    driver.findElement(By.id("tradutor")).clear();
	    driver.findElement(By.id("tradutor")).sendKeys("run");
	    driver.findElement(By.id("btn_enviar")).click();
	    assertEquals("Corrercarreirasériejornada", driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Resultados'])[1]/following::ul[1]")).getText());
	}
	
	@Test
	public void testPalavraExistenteTestCase() throws Exception {
		driver.get(baseUrl);
		driver.findElement(By.id("btn_idioma")).click();
	    driver.findElement(By.id("tradutor")).click();
	    driver.findElement(By.id("tradutor")).clear();
	    driver.findElement(By.id("tradutor")).sendKeys("world");
	    driver.findElement(By.id("btn_enviar")).click();
	    assertEquals("mundo", driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Resultados'])[1]/following::ul[1]")).getText());
	}
	
	@Test
	public void testPalavraNaoExistenteTestCase() throws Exception {
		driver.get(baseUrl);
		driver.findElement(By.id("btn_idioma")).click();
	    driver.findElement(By.id("tradutor")).click();
	    driver.findElement(By.id("tradutor")).clear();
	    driver.findElement(By.id("tradutor")).sendKeys("place");
	    driver.findElement(By.id("btn_enviar")).click();
	    assertEquals("", driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Resultados'])[1]/following::ul[1]")).getText());
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

	private boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	private boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}

	private String closeAlertAndGetItsText() {
		try {
			Alert alert = driver.switchTo().alert();
			String alertText = alert.getText();
			if (acceptNextAlert) {
				alert.accept();
			} else {
				alert.dismiss();
			}
			return alertText;
		} finally {
			acceptNextAlert = true;
		}
	}
}
