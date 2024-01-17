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




	}





	//	launch(args);

/*

		csvDao=new CsvDao();

		csvDao.generaReport();
		User u=User.getInstance();
		User u1=User.getInstance();

		// vedere se id presente
		u.setId(10);
		u.setIdRuolo("U");
		u.setNome("daniele");
		u.setCognome("cinus");
		u.setEmail("danielecinus10@gmail.com");
		u.setDescrizione("utente semplice");
		u.setDataDiNascita(LocalDate.of(1995,10,31));
		File fd=new File("localDBFile.csv");
		//funziona
		CsvDao.saveUser(fd,User.getInstance());
		System.out.println("lista by name / email :"+CsvDao.retreiveByNomeEmail(fd,User.getInstance().getNome(), User.getInstance().getEmail()));
		System.out.println("modif users \n");

		//modif data
		u1.setId(u.getId());
		u1.setIdRuolo(u.getIdRuolo());
		u1.setNome(u.getNome());
		u1.setCognome(u.getCognome());
		u1.setEmail("gnappetto1995@gmail.com");
		u1.setDescrizione(u.getDescrizione());
		u1.setDataDiNascita(u.getDataDiNascita());
		CsvDao.modifPassUser(fd,u,u1);




 */



	/*
	todo
	copia file
	public static void copyFile(File sourceFile, File destFile) throws IOException {
    if(!destFile.exists()) {
        destFile.createNewFile();
    }

    FileChannel source = null;
    FileChannel destination = null;

    try {
        source = new FileInputStream(sourceFile).getChannel();
        destination = new FileOutputStream(destFile).getChannel();
        destination.transferFrom(source, 0, source.size());
    }
    finally {
        if(source != null) {
            source.close();
        }
        if(destination != null) {
            destination.close();
        }
    }
}
	 */




		
	
}
