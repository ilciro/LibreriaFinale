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
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import laptop.database.LibroDao;
import laptop.utilities.ConnToDb;
import web.bean.LibroBean;

class TestLibri {
	
	private ChromeDriver driver;
	private LibroBean lB;
	private LibroDao lD;
	
	@BeforeEach
	void tearUp()
	{
		driver=new ChromeDriver();
		lB=new LibroBean();
		lD=new LibroDao();
	}

	@Test
	void testGeneraListaLibri() throws SQLException {
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.get("http://localhost:8087/LibreriaMaven/libri.jsp");
		WebElement buttonL=driver.findElement(By.xpath("//input[@id='genera lista']"));
		buttonL.click();
		lB.setListaLibriB(lD.getLibri());
		assertNotNull(lB.getListaLibriB());
		
	}
	@ParameterizedTest
	@ValueSource(strings={"1","2","3","4","5","6"})
	void testScegliLibro(String strings)
	{
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.get("http://localhost:8087/LibreriaMaven/libri.jsp");
		WebElement idL=driver.findElement(By.xpath("//input[@id='idOgg']"));
		idL.sendKeys(strings);
		lB.setIdB(Integer.parseInt(strings));
		driver.findElement(By.xpath("//input[@id='procedi']")).click();
		assertEquals(lB.getIdB(),Integer.parseInt(strings));
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
