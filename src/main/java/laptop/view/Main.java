package laptop.view;

import java.io.*;

import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import laptop.controller.ControllerReportPage;
import laptop.database.CsvDao;

import laptop.database.LibroDao;
import laptop.database.UsersDao;
import laptop.model.raccolta.Giornale;
import laptop.model.raccolta.Libro;
import laptop.model.raccolta.Rivista;
import laptop.utilities.ConnToDb;



public class Main  extends Application {
    public static final String ANSI_GREEN = "\u001B[32m";
	private static CsvDao csvDao ;

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
			Logger.getLogger("main page").log(Level.SEVERE,"\n eccezione ottenuta .",e);

			
		}

	}

	public static void main(String[] args) throws Exception {
		//uso status per vedere se trigger creati


		try {

			ConnToDb.creaPopolaDb();


		} catch (FileNotFoundException eFile) {
			Logger.getLogger("crwa db").log(Level.SEVERE, "\n eccezione ottenuta .", eFile);

		}


			launch(args);
/*
		Libro l = new Libro();
		Giornale g=new Giornale();
		Rivista r=new Rivista();
		for (int i = 1; i < 13; i++) {
			l.setId(i);
			l.scarica(l.getId());
			l.leggi(l.getId());
		}
//		g.setId(1);
//		g.scarica(g.getId());
//		g.leggi(g.getId());
//		r.setId(1);
//		r.scarica(r.getId());
//		r.leggi(r.getId());


 */

	}


		
	
}
