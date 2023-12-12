package laptop.view;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.time.Duration;
import java.util.Objects;
import java.util.logging.Level;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import laptop.database.GiornaleDao;
import laptop.model.raccolta.Giornale;
import laptop.utilities.ConnToDb;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


public class Main  extends Application {
    public static final String ANSI_GREEN = "\u001B[32m";

    	// insert a comment
	
	@Override
	public void start(Stage primaryStage) {
		Scene scene;

		try {
			Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("homePage.fxml")));
			scene = new Scene(root);
			primaryStage.setTitle("Benvenuto nella homePage");
			primaryStage.setScene(scene);
			primaryStage.show();
		} 
		catch (Exception e)
		{
			java.util.logging.Logger.getLogger("main page").log(Level.SEVERE,"\n eccezione ottenuta .",e);

			
		}

	}

	public static void main(String[] args)  {
		//uso status per vedere se trigger creati


		try {

			ConnToDb.creaPopolaDb();


		} catch (FileNotFoundException eFile) {
			java.util.logging.Logger.getLogger("crwa db").log(Level.SEVERE, "\n eccezione ottenuta .", eFile);

		}


		launch(args);



	}




		
	
}
