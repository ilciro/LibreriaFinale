package web.test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.Duration;
import java.util.logging.Level;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


import laptop.utilities.ConnToDb;



import org.junit.jupiter.api.TestClassOrder;
import org.junit.jupiter.api.ClassOrderer;

@TestClassOrder(ClassOrderer.ClassName.class)class TestPaymentCashWeb {
	
	private ChromeDriver driver;

	
	@BeforeEach
	void tearUp()
	{
		driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
	}

	@Test
	void testPagamento()  {
		
		int idPreso;
		
		

		driver.get("http://localhost:8087/LibreriaMaven/index.jsp");
		driver.findElement(By.xpath("//input[@id='buttonL']")).click();
		driver.findElement(By.xpath("//input[@id='genera lista']")).click();
		WebElement id=driver.findElement(By.xpath("//input[@id='idOgg']"));

		id.sendKeys("4");
		idPreso=Integer.parseInt(id.getAttribute("value"));
		driver.findElement(By.xpath("//input[@id='procedi']")).click();
		driver.findElement(By.xpath("//input[@id='quantita']")).clear();
		driver.findElement(By.xpath("//input[@id='quantita']")).sendKeys("6");
		driver.findElement(By.xpath("//input[@id='totaleB']")).click();
		WebElement input =

		driver.findElement(By.xpath("//input[@list='metodi']"));

		WebElement option =

		driver.findElement(By.xpath("//*[@id='metodi']/option[1]"));
		String value = option.getAttribute("value");
		input.sendKeys(value);
		driver.findElement(By.xpath("//input[@id='pdfB']")).click();

		driver.findElement(By.xpath("//input[@id='nomeL']")).sendKeys("franco");
		driver.findElement(By.xpath("//input[@id='cognomeL']")).sendKeys("rossi");
		driver.findElement(By.xpath("//input[@id='indirizzoL']")).sendKeys("via gelsomini 13");

		driver.findElement(By.id("com")).sendKeys("la consegna dopo le 	12");

		WebElement 	buttonConferma=driver.findElement(By.xpath("//input[@id='buttonC']"));

		buttonConferma.click();
		//driver.findElement(By.xpath("//input[@id='downloadB']")).click();
		assertEquals(idPreso,4);
	}
	@AfterEach
	void tearDown()
	{
		driver.quit();
	}

	@AfterAll
	static void  testCancellaDB() throws SQLException
	{
		int rows=0;
		try(Connection conn=ConnToDb.generalConnection();
				PreparedStatement prepQ=conn.prepareStatement("drop schema ispw"))
		{
			rows=prepQ.executeUpdate();		}
		
		java.util.logging.Logger.getLogger("cancella db").log(Level.INFO, "database cancellato ");

		assertEquals(rows,11);

	}
}
