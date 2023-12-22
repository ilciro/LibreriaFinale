package laptop.database;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.logging.Level;

import laptop.controller.ControllerSystemState;
import laptop.model.raccolta.Factory;
import laptop.model.raccolta.Giornale;
import laptop.model.raccolta.Raccolta;
import laptop.utilities.ConnToDb;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class GiornaleDao {
	
	private Factory f;
	

	private  String query  ;
	private  int q ; 
	private String categoria;
	private int disp=0;
	private int id=0;
	

	private boolean state=false;
	private ControllerSystemState vis=ControllerSystemState.getInstance();
	private static final String GIORNALE = "giornale";
	private static String eccezione="eccezione generata:";







	public float getCosto(Giornale g) throws SQLException  
	{		

		float prezzo=(float) 0.0;
		query="select * from GIORNALE where id=?";
		
		try(Connection conn=ConnToDb.connectionToDB();
				PreparedStatement prepQ=conn.prepareStatement(query))
		{
			prepQ.setInt(1, g.getId());
			ResultSet rs=prepQ.executeQuery();
		
			while ( rs.next() ) {
				prezzo=rs.getFloat("prezzo");
			}
		}catch(SQLException e)
		{
			java.util.logging.Logger.getLogger("GetCosto g").log(Level.INFO, eccezione, e);
		}
		
		return prezzo;


		
	}

	public  void aggiornaDisponibilita(Giornale g) throws SQLException 
	{

		
		int d=vis.getQuantita();
		int i=g.getCopieRimanenti();
		
		int rim=i-d;
		
				
		query="update GIORNALE set copiRim= ? where id=?";
		
		try(Connection conn=ConnToDb.connectionToDB();
				PreparedStatement prepQ=conn.prepareStatement(query))
		{
			prepQ.setInt(1, rim);
			prepQ.setInt(2, g.getId());
			prepQ.executeUpdate();
		}catch(SQLException e)
		{
			java.util.logging.Logger.getLogger("aggiornaDisp g").log(Level.INFO, eccezione, e);
		}
		
		
	}
	
	public  int retId(Giornale g) throws SQLException  {
		query="select id from GIORNALE where titolo=?";
		
		try(Connection conn=ConnToDb.connectionToDB();
				PreparedStatement prepQ=conn.prepareStatement(query))
		{
			prepQ.setString(1, g.getTitolo());
			ResultSet rs=prepQ.executeQuery();
			while ( rs.next() ) {
				id=rs.getInt("id");

			}
		}catch(SQLException e)
		{
			java.util.logging.Logger.getLogger("id girnoale").log(Level.INFO, eccezione, e);
		}

		return id;



	}
	

	

	public  ObservableList<Raccolta> getGiornali() throws SQLException   {

			ObservableList<Raccolta> catalogo=FXCollections.observableArrayList();

		
		
			query="select * from GIORNALE";
			try(Connection conn=ConnToDb.connectionToDB();
					PreparedStatement prepQ=conn.prepareStatement(query);
					ResultSet rs=prepQ.executeQuery())
			{
			while(rs.next())        

			{
				
				f.createRaccoltaFinale1(GIORNALE, rs.getString(1),rs.getString(2), null,rs.getString(3),rs.getString(4),null);
				f.createRaccoltaFinale2(GIORNALE,0,null,0,rs.getInt(7),rs.getFloat(8),rs.getInt(6));
				catalogo.add(f.createRaccoltaFinaleCompleta(GIORNALE, rs.getDate(5).toLocalDate(), null, null,rs.getInt(9)));
			
				
			}
			}catch(SQLException e)
			{
				java.util.logging.Logger.getLogger("catalogo giornali").log(Level.INFO, eccezione, e);
			}

		return catalogo;
	}
	
	public  Giornale getGiornale(Giornale g) throws SQLException  
	{

		query="select * from GIORNALE where id=?";
		
		try(Connection conn=ConnToDb.connectionToDB();
				PreparedStatement prepQ=conn.prepareStatement(query))
		{
			prepQ.setInt(1, g.getId());
			ResultSet rs=prepQ.executeQuery();
			while (rs.next())
			{

				f.createRaccoltaFinale1(GIORNALE, rs.getString(1),rs.getString(2), null,rs.getString(3),rs.getString(4),null);
				f.createRaccoltaFinale2(GIORNALE,0,null,0,rs.getInt(7),rs.getFloat(8),rs.getInt(6));
				g=(Giornale) (f.createRaccoltaFinaleCompleta(GIORNALE, rs.getDate(5).toLocalDate(), null, null,rs.getInt(9)));
	
			
			}
			
		}catch(SQLException e)
		{
			java.util.logging.Logger.getLogger("get giornale").log(Level.INFO, eccezione, e);
		}
		return g;

	}
	public GiornaleDao()
	{
		f=new Factory();
	}



	public  String retTip(Giornale g) throws SQLException  {
		
		query="select tipologia from GIORNALE where titolo=? or id=?";
		
		try(Connection conn=ConnToDb.connectionToDB();
				PreparedStatement prepQ=conn.prepareStatement(query))
		{
			prepQ.setString(1, g.getTitolo());
			prepQ.setInt(2, g.getId());
			ResultSet rs=prepQ.executeQuery();
			while ( rs.next() ) {
				categoria=rs.getString("tipologia");

			}
		}catch(SQLException e)
		{
			java.util.logging.Logger.getLogger("ret tipo").log(Level.INFO, eccezione, e);
		}
		
		return categoria;


	}

	public  String getNome(Giornale g) throws SQLException  
	{
		String name = "";
		query="select titolo from GIORNALE where id=?";
		try(Connection conn=ConnToDb.connectionToDB();
				PreparedStatement prepQ=conn.prepareStatement(query))
		{
			prepQ.setInt(1,g.getId());
			ResultSet rs=prepQ.executeQuery();
			while (rs.next())
			{
				name = rs.getString(1);
			}
			
			
		}catch(SQLException e)
		{
			java.util.logging.Logger.getLogger("nome giornale").log(Level.INFO, eccezione, e);
		}
			
		return name;
	}

	public  int getDisp(Giornale g) throws SQLException 
	{

		query="select disp from GIORNALE where id=?";
		try(Connection conn=ConnToDb.connectionToDB();
				PreparedStatement prepQ=conn.prepareStatement(query))
		{
			prepQ.setInt(1, g.getId());
			ResultSet rs=prepQ.executeQuery();
			while (rs.next())
				{
				
				disp = rs.getInt(1);
				
				
			}
		}catch(SQLException e) {
			java.util.logging.Logger.getLogger("get disp g").log(Level.INFO, eccezione, e);
		}
	
	
		return disp;
	}

	public  int getQuantita(Giornale g) throws SQLException  
	{
		

		query="select copiRim from GIORNALE where id=?";
		try(Connection conn=ConnToDb.connectionToDB();
				PreparedStatement prepQ=conn.prepareStatement(query);
				)
		{
			
			prepQ.setInt(1, g.getId());
			ResultSet rs=prepQ.executeQuery();
			
				while (rs.next()) {
					q = rs.getInt("copiRim");
				}			
		}catch(SQLException e)
		{
			java.util.logging.Logger.getLogger("get quantita g").log(Level.INFO, eccezione, e);
		}

		return q;
	}

	public  boolean checkDisp(Giornale g) throws SQLException  
	{
		query="select disp from GIORNALE where id=?";
			try(Connection conn=ConnToDb.connectionToDB();
					PreparedStatement prepQ=conn.prepareStatement(query);
					)
		
			{
			prepQ.setInt(1, g.getId());
			ResultSet rs=prepQ.executeQuery();

			if(rs.next())
			{
				disp = rs.getInt(1);
				if (disp >= 1)
					state=true;
				java.util.logging.Logger.getLogger("Controlla Disponibilita").log(Level.INFO, "giornale Trovato");
			}
			}catch(SQLException e)
			{
				java.util.logging.Logger.getLogger("Controlla disp").log(Level.INFO, eccezione, e);
			}
			
	 	return state;
	}

	public  ObservableList<Raccolta> getGiornaleSingolo() throws SQLException   {

		ObservableList<Raccolta> catalogo=FXCollections.observableArrayList();
		
		query="select * from GIORNALE";
		
		try(Connection conn=ConnToDb.connectionToDB();
				PreparedStatement prepQ=conn.prepareStatement(query))
		{
			
			ResultSet rs=prepQ.executeQuery();
			while (rs.next())
			{

				f.createRaccoltaFinale1(GIORNALE, rs.getString(1),rs.getString(2), null,rs.getString(3),rs.getString(4),null);
				f.createRaccoltaFinale2(GIORNALE,0,null,0,rs.getInt(7),rs.getFloat(8),rs.getInt(6));
				catalogo.add((f.createRaccoltaFinaleCompleta(GIORNALE, rs.getDate(5).toLocalDate(), null, null,rs.getInt(9))));
	
			
			}
			
		}catch(SQLException e)
		{
			java.util.logging.Logger.getLogger("grionale singolo ").log(Level.INFO, eccezione, e);
		}
		return catalogo;


	}


	public  boolean creaGiornale(Giornale g) throws SQLException  {
		 int row;

			
			query= "INSERT INTO `GIORNALE`"
					+ "(`titolo`,"
					+ "`tipologia`,"
					+ "`lingua`,"
					+ "`editore`,"
					+ "`dataPubblicazione`,"
					+ "`copiRim`,"
					+ "`disp`,"
					+ "`prezzo`)"
					+ "VALUES"
					+ "(?,?,?,?,?,?,?,?)";
			try(Connection conn=ConnToDb.connectionToDB();
					PreparedStatement prepQ=conn.prepareStatement(query))
			{
			
			prepQ.setString(1,g.getTitolo()); 
			prepQ.setString(2,g.getTipologia());
			prepQ.setString(3,g.getLingua());
			prepQ.setString(4,g.getEditore());
			prepQ.setDate(5, java.sql.Date.valueOf(g.getDataPubb().toString())); 
			prepQ.setInt(6,g.getCopieRimanenti());
			prepQ.setInt(7, g.getDisponibilita());
			prepQ.setFloat(8, g.getPrezzo());


			row=prepQ.executeUpdate();
                state= row == 1; // true
		
			}catch(SQLException e)
			{
				java.util.logging.Logger.getLogger("creazione giornale").log(Level.INFO, eccezione, e);
			}


		return state;


	}


	public  int cancella(Giornale g) throws SQLException  {
		int row=0;
		query="delete from GIORNALE where id=?";
		try(Connection conn=ConnToDb.connectionToDB();
				PreparedStatement prepQ=conn.prepareStatement(query))
		{
			prepQ.setInt(1, g.getId());
			row=prepQ.executeUpdate();
			
		}catch(SQLException e)
		{
						java.util.logging.Logger.getLogger("cancella").log(Level.INFO, eccezione, e);
		}
		java.util.logging.Logger.getLogger("cancella g").log(Level.INFO,"\n rows affcted {0}",row);

	return row;


	}

	public ObservableList<Giornale> getGiornaliSingoloById(Giornale g) throws SQLException    {

		ObservableList<Giornale> catalogo=FXCollections.observableArrayList();
		query="SELECT * FROM GIORNALE where id=?";
		try(Connection conn=ConnToDb.connectionToDB();
				PreparedStatement prepQ=conn.prepareStatement(query))
		{
			prepQ.setInt(1, g.getId());
			ResultSet rs=prepQ.executeQuery();
			while(rs.next())
			{
				

					f.createRaccoltaFinale1(GIORNALE, rs.getString(1),rs.getString(2), null,rs.getString(3),rs.getString(4),null);
					f.createRaccoltaFinale2(GIORNALE,0,null,0,rs.getInt(7),rs.getFloat(8),rs.getInt(6));
					catalogo.add((Giornale) f.createRaccoltaFinaleCompleta(GIORNALE, rs.getDate(5).toLocalDate(), null, null, rs.getInt(9)));


		}
		}catch(SQLException e)
		{
						java.util.logging.Logger.getLogger("giornale by id").log(Level.INFO, eccezione, e);
		}
		
		java.util.logging.Logger.getLogger("elenco giornali by id").log(Level.INFO,"catalogo {0}",catalogo);

			
		return catalogo;

	}

	public  ObservableList<Raccolta> getGiornaliByName(Giornale g) throws SQLException {

		ObservableList<Raccolta> catalogo=FXCollections.observableArrayList();
		
		query="select * from GIORNALE where titolo=? or editore=?";
		
		try(Connection conn=ConnToDb.connectionToDB();
				PreparedStatement prepQ=conn.prepareStatement(query))
		{
			prepQ.setString(1,g.getTitolo());
			prepQ.setString(2, g.getEditore());
			ResultSet rs=prepQ.executeQuery();
			while (rs.next())
			{

				f.createRaccoltaFinale1(GIORNALE, rs.getString(1),rs.getString(2), null,rs.getString(3),rs.getString(4),null);
				f.createRaccoltaFinale2(GIORNALE,0,null,0,rs.getInt(7),rs.getFloat(8),rs.getInt(6));
				catalogo.add (f.createRaccoltaFinaleCompleta(GIORNALE, rs.getDate(5).toLocalDate(), null, null,rs.getInt(9)));
	
			
			}
			
		}catch(SQLException e)
		{
			java.util.logging.Logger.getLogger("gionali by name").log(Level.INFO, eccezione, e);
		}

		
		

			

		return catalogo;


	}

	public  int aggiornaGiornale(Giornale g) throws SQLException  {
		 int row=0;


			query=" UPDATE `GIORNALE`"
					+ "SET"
					+ "`titolo` =?,"
					+ "`tipologia` = ?,"
					+ "`lingua` = ?,"
					+ "`editore` = ?,"
					+ "`dataPubblicazione` = ?,"
					+ "`copiRim` = ?,"
					+ "`disp` = ?,"
					+ "`prezzo` = ?"
					+ "WHERE `id` = ? or id=?";
			try(Connection conn=ConnToDb.connectionToDB();
					PreparedStatement prepQ=conn.prepareStatement(query))
			{
			prepQ.setString(1,g.getTitolo());
			prepQ.setString(2,g.getTipologia());
			prepQ.setString(3,g.getLingua());
			prepQ.setString(4, g.getEditore());
			prepQ.setString(5,g.getDataPubb().toString());
			prepQ.setInt(6,g.getCopieRimanenti());
			prepQ.setInt(7,g.getDisponibilita());
			prepQ.setFloat(8,g.getPrezzo());
			prepQ.setInt(9, g.getId());
			prepQ.setInt(10, vis.getId());



			row=prepQ.executeUpdate();
			}catch(SQLException e)
			{
							java.util.logging.Logger.getLogger("update g").log(Level.INFO, eccezione, e);
			}

			java.util.logging.Logger.getLogger("aggiorna g").log(Level.INFO," rows aggiornalte {0}",row);
			return row;

	}	

	public   void generaReport() throws IOException, SQLException
	{
		FileWriter w;
		query="select titolo,editore,copiRim,prezzo as totale  from GIORNALE";
		w=new FileWriter("ReportFinale/riepilogoGiornali.txt");
		   try (BufferedWriter b=new BufferedWriter (w)){
			   try(Connection conn=ConnToDb.connectionToDB();
					   PreparedStatement prepQ=conn.prepareStatement(query))
			   {
				  
				   ResultSet rs=prepQ.executeQuery();

			while(rs.next())
			{



				b.write("Titolo :"+rs.getString(1)+"\t"+"Editore :"+rs.getString(2)+"\t"+"Ricavo totale :" +rs.getInt(3)*rs.getFloat(4)+"\n");

				b.flush();


			}



			}catch(SQLException e)
			   {
							java.util.logging.Logger.getLogger("Test Eccezione").log(Level.INFO, eccezione, e);
			   }




		}
		


	}


	public void incrementaDisponibilita(Giornale g) throws SQLException {
		int d=vis.getQuantita();
		int i=g.getCopieRimanenti();
		
		int rim=i+d;
		
		query="update GIORNALE set copiRim= ? where titolo= ? or id=?";
		
			try(Connection conn=ConnToDb.connectionToDB();
					PreparedStatement prepQ=conn.prepareStatement(query))
			{
				prepQ.setInt(1, rim);
				prepQ.setString(2,g.getTitolo());
				prepQ.setInt(3,g.getId());
				prepQ.executeUpdate();
				
			}catch(SQLException e)
			{
				java.util.logging.Logger.getLogger("Test Eccezione").log(Level.INFO, eccezione, e);
			}

		
	}

	
	public String getTitolo(Giornale g) 
	{
		String t="";
		 query="select titolo from GIORNALE where id=? or id=?";
		 try(Connection conn=ConnToDb.connectionToDB();
				 PreparedStatement prepQ=conn.prepareStatement(query))
		 {
			 prepQ.setInt(1, g.getId());
			 prepQ.setInt(2, vis.getId());
			 ResultSet rs=prepQ.executeQuery();
			 if(rs.next())
			 {
				 t=rs.getString("titolo");
			 }
		 }catch(SQLException e)
		 {
				java.util.logging.Logger.getLogger("titolo giornale").log(Level.INFO, "titolo aggiornati {0}.",e.toString());
		 }
		 
		
		 return t;
		
	}

	public void aggiornaData(Giornale g, Date sqlDate) throws SQLException {
		int row=0;
		query="update GIORNALE set dataPubblicazione=? where id=? or id=?  ";
		try(Connection conn=ConnToDb.connectionToDB();
				PreparedStatement prepQ=conn.prepareStatement(query))
		{
			prepQ.setDate(1, sqlDate);
			prepQ.setInt(2, g.getId());
			prepQ.setInt(3, vis.getId());
			row=prepQ.executeUpdate();
		}
		java.util.logging.Logger.getLogger("aggiorna data giornale").log(Level.INFO, "giornali aggiornati {0}.",row);

	}
}

