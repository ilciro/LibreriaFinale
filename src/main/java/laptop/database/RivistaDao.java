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
import laptop.exception.IdException;
import laptop.model.raccolta.Factory;
import laptop.model.raccolta.Raccolta;
import laptop.model.raccolta.Rivista;
import laptop.utilities.ConnToDb;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class 	RivistaDao {
	
	private  Factory f;
	
	private  String query ;
	
	private  int q;
	private int id = 0;
	private boolean state=false;
	private String riv="SELECT * from RIVISTA;";

	private ControllerSystemState vis=ControllerSystemState.getInstance();
	private static final String RIVISTA="rivista";

	private static String eccezione="eccezione generata:";
	private int disp;
	private static String rivistaS="rivista singola";

	
	
	
	public void getDesc(Rivista r) throws SQLException
	{
		query="select * from RIVISTA where titolo=?";
		try(Connection conn=ConnToDb.connectionToDB();
				PreparedStatement prepQ=conn.prepareStatement(query);
				)
		{
			prepQ.setString(1,r.getTitolo());
			ResultSet rs=prepQ.executeQuery();
			while ( rs.next() ) {
	                rs.getString("titolo");
	               rs.getString("tipologia");
	               rs.getString("autore");
	               rs.getString("lingua");	   
	               rs.getString("editore");
	               rs.getString("Descrizione");

	               rs.getDate("dataPubblicazione");
	               
	               rs.getInt("disp");
	               rs.getFloat("prezzo");
	               rs.getInt("copieRimanenti");


	                
	                
	    	        
	            }
		}catch(SQLException e)
		{
			java.util.logging.Logger.getLogger("Test GestDesc").log(Level.INFO, eccezione, e);
		}
	            
	        
				
		 
	    }

	public float getCosto(Rivista r) throws SQLException
	{
		float prezzo=(float) 0.0;
		query="select * from RIVISTA where id=?";
		try(Connection conn=ConnToDb.connectionToDB();
				PreparedStatement prepQ=conn.prepareCall(query))
		{
			prepQ.setInt(1, r.getId());
			ResultSet rs=prepQ.executeQuery();
			 while ( rs.next() ) {
              prezzo=rs.getFloat("prezzo");

         }
		}catch(SQLException e)
		{
			java.util.logging.Logger.getLogger("get costo").log(Level.INFO, eccezione, e);
		}
		
		
		return prezzo;
		
	}

	public void aggiornaDisponibilita(Rivista r) throws SQLException
	{
		
		int d=vis.getQuantita();
		int i=r.getCopieRim();
		
		int rim=i-d;
		
		
		query="update RIVISTA set copieRimanenti= ? where  id=?";
		
		try(Connection conn=ConnToDb.connectionToDB();
				PreparedStatement prepQ=conn.prepareStatement(query))
		{
			prepQ.setInt(1, rim);
			prepQ.setInt(2, r.getId());
			prepQ.executeUpdate();
		}catch(SQLException e)
		{
			java.util.logging.Logger.getLogger("aggiorna disp").log(Level.INFO, eccezione, e);
		}
		
		

		}

	
	
	public ObservableList<Raccolta> getRiviste() throws SQLException
	{
		

		
		ObservableList<Raccolta> catalogo=FXCollections.observableArrayList();
		 
		query=riv;
		try(Connection conn=ConnToDb.connectionToDB();
				PreparedStatement prepQ=conn.prepareStatement(query);
				ResultSet rs=prepQ.executeQuery())
		{
            while(rs.next())
            {

        		
        			f.createRaccoltaFinale1(RIVISTA, rs.getString(1),rs.getString(2), rs.getString(3),rs.getString(4),rs.getString(5),null);
					f.createRaccoltaFinale2(RIVISTA,0,null,0,rs.getInt(8),rs.getFloat(9),rs.getInt(10));
					catalogo.add(f.createRaccoltaFinaleCompleta(RIVISTA, rs.getDate(7).toLocalDate(), null, null,rs.getInt(11)));
		
					
        		
            }
		}catch(SQLException e)
		{
			java.util.logging.Logger.getLogger("elenco riviste").log(Level.INFO, eccezione, e);
		}
		return catalogo;
		
	}
	
	
	public ObservableList<Raccolta> getRivisteByName(String s) throws SQLException
	{
		 

		
		ObservableList<Raccolta> catalogo=FXCollections.observableArrayList();
		
		query="select * from RIVISTA where titolo=? or autore=?";
		try(Connection conn=ConnToDb.connectionToDB();
				PreparedStatement prepQ=conn.prepareStatement(query);
				)
		{
			prepQ.setString(1, s);
			prepQ.setString(2, s);
			ResultSet rs=prepQ.executeQuery();
            while(rs.next())
            {

        		
        			f.createRaccoltaFinale1(RIVISTA, rs.getString(1),rs.getString(2), rs.getString(3),rs.getString(4),rs.getString(5),null);
					f.createRaccoltaFinale2(RIVISTA,0,null,0,rs.getInt(8),rs.getFloat(9),rs.getInt(10));
					catalogo.add(f.createRaccoltaFinaleCompleta(RIVISTA, rs.getDate(7).toLocalDate(), null, null,rs.getInt(11)));
		
					
        		
            }
		}catch(SQLException e)
		{
			java.util.logging.Logger.getLogger("rivista by name").log(Level.INFO, eccezione, e);
		}
		return catalogo;
		 
		
	}


	public Rivista getRivista(Rivista r) throws SQLException
	{
		query="select * from RIVISTA where id=?";

		try(Connection conn=ConnToDb.connectionToDB();
				PreparedStatement prepQ=conn.prepareStatement(query))
		{
			
		prepQ.setInt(1, r.getId());
		ResultSet rs=prepQ.executeQuery();
        while (rs.next())
        {
        	f.createRaccoltaFinale1(RIVISTA, rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6));
			f.createRaccoltaFinale2(RIVISTA,0,null,0,rs.getInt(8),rs.getFloat(9),rs.getInt(10));
			r=(Rivista) (f.createRaccoltaFinaleCompleta(RIVISTA, rs.getDate(7).toLocalDate(), null, rs.getString(6),rs.getInt(11)));
        }
		}catch(SQLException e)
		{
			java.util.logging.Logger.getLogger("rivista by id").log(Level.INFO, eccezione, e);
		}
             return r;
	}

	public RivistaDao()
	{
		f=new Factory();
	}
	
	public int retId(Rivista r) throws SQLException {
		query="select id from RIVISTA where titolo=?";
		
		try(Connection conn=ConnToDb.connectionToDB();
				PreparedStatement prepQ=conn.prepareStatement(query))
		{
			prepQ.setString(1, r.getTitolo());
			ResultSet rs=prepQ.executeQuery();
         while ( rs.next() ) {
              id=rs.getInt("id");

         }
		}catch(SQLException e)
		{
			java.util.logging.Logger.getLogger("rivista by titolo").log(Level.INFO, eccezione, e);
		}
		return id;

		
		
	}

	public String retTip(Rivista r) throws SQLException {
		
		String categoria=null;
		query="select tipologia from RIVISTA where titolo=? or id=?";
		try(Connection conn=ConnToDb.connectionToDB();
				PreparedStatement prepQ=conn.prepareStatement(query))
		{
			prepQ.setString(1, r.getTitolo());
			prepQ.setInt(2, r.getId());
			ResultSet rs=prepQ.executeQuery();
	         while ( rs.next() ) {
	              categoria=rs.getString("tipologia");
	
	         }
		}catch(SQLException e)
		{
			java.util.logging.Logger.getLogger("rivista titolo/id").log(Level.INFO, eccezione, e);
		}
		return categoria;

		
	}
	
	public String getNome(Rivista r) throws SQLException
	{
		String name=null;
		query="select titolo from RIVISTA where id=?";
		try(Connection conn=ConnToDb.connectionToDB();
				PreparedStatement prepQ=conn.prepareStatement(query))
		{
			prepQ.setInt(1,r.getId());
			ResultSet rs=prepQ.executeQuery();
			while (rs.next())
	        {
	        	name = rs.getString(1);
	        }
		}catch(SQLException e)
		{
			java.util.logging.Logger.getLogger("nome rivista").log(Level.INFO, eccezione, e);
		}
        	
        return name;
   }

	public int getDisp(Rivista r) throws SQLException
	{
		 disp = 0;
		query="select disp from RIVISTA where id=?";
		
		try(Connection conn=ConnToDb.connectionToDB();
				PreparedStatement prepQ=conn.prepareStatement(query))
		{
			prepQ.setInt(1, r.getId());
			ResultSet rs=prepQ.executeQuery();
			if(rs.next())
				{
					disp = rs.getInt("disp");

				
					if(disp==1)
						 disp=1;
					if (disp == 0)
						disp=0;
				}
		}catch(SQLException e)
		{
			java.util.logging.Logger.getLogger("disp r").log(Level.INFO, eccezione, e);
		}
			
		
		
		return disp;
	}
	
	public int getQuantita(Rivista r) throws SQLException
	{
        
		query="select copieRimanenti from RIVISTA where id=?";
		try(Connection conn=ConnToDb.connectionToDB();
				PreparedStatement prepQ=conn.prepareStatement(query))
		{
			
			prepQ.setInt(1, r.getId());
			ResultSet rs=prepQ.executeQuery();
			
				while (rs.next()) {
					q = rs.getInt(1);
				}			
			
		}catch(SQLException e)
		{
			java.util.logging.Logger.getLogger("quantita r").log(Level.INFO, eccezione, e);
		}

		return q;
	}

	public boolean checkDisp(Rivista r) throws SQLException
	{
		
			query="select disp from RIVISTA where id=?";
			
			try(Connection conn=ConnToDb.connectionToDB();
					PreparedStatement prepQ=conn.prepareStatement(query);
					)

			{
				
				prepQ.setInt(1, r.getId());
			
				ResultSet rs=prepQ.executeQuery();
			if(rs.next())
			{
				disp = rs.getInt(1);
				if (disp == 1)
					state=true;
				else
				{
					java.util.logging.Logger.getLogger("check disp").log(Level.INFO, eccezione, new IdException("id non trovato"));
					
				
				}
			}
			}catch(SQLException e)
			{
				java.util.logging.Logger.getLogger("disp ecc").log(Level.INFO, eccezione, e);
			}
			
	 	return state;
	
	}

	public ObservableList<Raccolta> getRivistaSingolo() throws SQLException {
		ObservableList<Raccolta> catalogo=FXCollections.observableArrayList();

		query=riv;
		try(Connection conn=ConnToDb.connectionToDB();
				PreparedStatement prepQ=conn.prepareStatement(query))
		{
			
		
		ResultSet rs=prepQ.executeQuery();
        while (rs.next())
        {
        	f.createRaccoltaFinale1(RIVISTA, rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6));
			f.createRaccoltaFinale2(RIVISTA,0,null,0,rs.getInt(8),rs.getFloat(9),rs.getInt(10));
			catalogo.add(f.createRaccoltaFinaleCompleta(RIVISTA, rs.getDate(7).toLocalDate(), null, rs.getString(6),rs.getInt(11)));
        }
		}catch(SQLException e)
		{
			java.util.logging.Logger.getLogger(rivistaS).log(Level.INFO, eccezione, e);
		}
             return catalogo;
             
		
		
	}

	public Boolean creaRivista(Rivista r) throws SQLException {

		int row;
    		
				
				query= "INSERT INTO `RIVISTA`"
			 			+ "(`titolo`,"
			 			+ "`tipologia`,"
			 			+ "`autore`,"
			 			+ "`lingua`,"
			 			+ "`editore`,"
			 			+ "`Descrizione`,"
			 			+ "`dataPubblicazione`,"
			 			+ "`disp`,"
			 			+ "`prezzo`,"
			 			+ "`copieRimanenti`) VALUES (?,?,?,?,?,?,?,?,?,?)";
				try(Connection conn=ConnToDb.connectionToDB();
						PreparedStatement prepQ=conn.prepareStatement(query))
				{
				prepQ.setString(1,r.getTitolo()); 
				prepQ.setString(2,r.getTipologia());
				prepQ.setString(3,r.getAutore());
				prepQ.setString(4,r.getLingua());
				prepQ.setString(5,r.getEditore());
				prepQ.setString(6,r.getDescrizione());
				prepQ.setDate(7, java.sql.Date.valueOf(r.getDataPubb().toString()));  
				prepQ.setInt(8, r.getDisp());
				prepQ.setFloat(9, r.getPrezzo());
				prepQ.setInt(10,r.getCopieRim());


				
				row=prepQ.executeUpdate();
				if(row==1)
			 		state= true; // true
				}catch(SQLException e)
				{
					state=false;
					java.util.logging.Logger.getLogger("crea rivista").log(Level.INFO, eccezione, e);
				}


			
		
		

		return state;
		
		
	}

	public int cancella(Rivista r) throws SQLException {

		 int row=0;
		 query="delete from RIVISTA where id=?";
		 try(Connection conn=ConnToDb.connectionToDB();
				 PreparedStatement prepQ=conn.prepareStatement(query))
		 {
			 prepQ.setInt(1, r.getId());
			 row=prepQ.executeUpdate();
		 }catch(SQLException e)
		 {
			 java.util.logging.Logger.getLogger("cancella r").log(Level.INFO, eccezione, e);
		 }
		 java.util.logging.Logger.getLogger("rivista cancellata").log(Level.INFO, "row delected{0}",row);
	return row;
	}

	public ObservableList<Rivista> getRivistaSingoloById(Rivista r) throws SQLException {
		ObservableList<Rivista> catalogo=FXCollections.observableArrayList();

		query="SELECT * from RIVISTA where id=?";
		try(Connection conn=ConnToDb.connectionToDB();
				PreparedStatement prepQ=conn.prepareStatement(query))
		{
			prepQ.setInt(1, r.getId());
			ResultSet rs=prepQ.executeQuery();
            while(rs.next())
            {

        		
        			f.createRaccoltaFinale1(RIVISTA, rs.getString(1),rs.getString(2), rs.getString(3),rs.getString(4),rs.getString(5),null);
					f.createRaccoltaFinale2(RIVISTA,0,null,0,rs.getInt(8),rs.getFloat(9),rs.getInt(10));
					catalogo.add((Rivista) f.createRaccoltaFinaleCompleta(RIVISTA, rs.getDate(7).toLocalDate(), null, rs.getString(6),rs.getInt(11)));
            }
		}catch(SQLException e)
		{
			java.util.logging.Logger.getLogger("rivista by id").log(Level.INFO, eccezione, e);
		}
		
		return catalogo;
		
	}
	
	
	public boolean aggiornaRivista(Rivista r) throws SQLException {
		 int rowAffected=0;
		 boolean status=false;


		

			query="UPDATE `RIVISTA`"
		 			+ "SET"
		 			+ "`titolo` = ?,"
		 			+ "`tipologia` =?,"
		 			+ "`autore` = ?,"
		 			+ "`lingua` = ?,"
		 			+ "`editore` = ?,"
		 			+ "`Descrizione` =?,"
		 			+ "`dataPubblicazione` =?,"
		 			+ "`disp` = ?,"
		 			+ "`prezzo` = ?,"
		 			+ "`copieRimanenti` =? WHERE `id` =?";
		 		
		 	try(Connection conn=ConnToDb.connectionToDB();
		 			PreparedStatement prepQ=conn.prepareStatement(query))
		 	{
			
			prepQ.setString(1,r.getTitolo());
			prepQ.setString(2,r.getTipologia());
			prepQ.setString(3,r.getAutore());
			prepQ.setString(4,r.getLingua());
			prepQ.setString(5,r.getEditore());
			prepQ.setString(6,r.getDescrizione());
			prepQ.setString(7,r.getDataPubb().toString());
			prepQ.setInt(8,r.getDisp());
			prepQ.setFloat(9,r.getPrezzo());
			prepQ.setInt(10,r.getCopieRim());
			prepQ.setInt(11, r.getId());
		

			rowAffected = prepQ.executeUpdate();
		 	}catch(SQLException e)
		 	{
		 		java.util.logging.Logger.getLogger("aggiorna rivista").log(Level.INFO, eccezione, e);
		 	}
			 if(rowAffected==1)
				 status=true;
		 	java.util.logging.Logger.getLogger("Aggiorno").log(Level.INFO,"rows afffected{0}",rowAffected);
			return status;
	 }
	
	public void generaReport() throws SQLException, IOException
	{
				FileWriter w;
				query="select titolo,editore,copieRimanenti,prezzo as totale ,dataPubblicazione from RIVISTA";
		        w=new FileWriter("ReportFinale/riepilogoRiviste.txt");
		        
		        
		        try (BufferedWriter b=new BufferedWriter (w)){
		        	try(Connection conn=ConnToDb.connectionToDB();
		        			PreparedStatement prepQ=conn.prepareStatement(query))
		        	{
		        		
		        		ResultSet rs=prepQ.executeQuery();
				
		           
		            while(rs.next())
		            {
		        		
		        	

				
										
				
		        		b.write("Titolo :"+rs.getString(1)+"\t"+"Editore :"+rs.getString(2)+"\t"+"Ricavo totale :" +rs.getInt(3)*rs.getFloat(4)+"\t"+"pubblicato il : "+rs.getDate("dataPubblicazione")+"\n");




		     			b.flush();


		        			
		        		
		            }
		        	}catch(SQLException e)
		        	{
		        		java.util.logging.Logger.getLogger("Test Eccezione").log(Level.INFO, eccezione, e);
		        	}
		     
	}
	
		
	}
			
	
	
	

	public void incrementaDisponibilita(Rivista r) throws SQLException {
		int d=vis.getQuantita();
		int i=r.getCopieRim();
		
		int rim=i+d;
		
		query="update RIVISTA set copieRimanenti= ? where titolo=? or id=?";

		try(Connection conn=ConnToDb.connectionToDB();
				PreparedStatement prepQ=conn.prepareStatement(query);)
		{
			prepQ.setInt(1, rim);
			prepQ.setString(2,r.getTitolo());
			prepQ.setInt(3,r.getId());
			prepQ.executeUpdate();
			
		}catch(SQLException e)
		{
			java.util.logging.Logger.getLogger("Test Eccezione").log(Level.INFO, eccezione, e);
		}


		
		
	}

	
	
	
	
	
	public String getTitolo(Rivista r) 
	{
		String t="";
		query="select titolo from RIVISTA where id=? or id=? ";
		try(Connection conn=ConnToDb.connectionToDB();
				PreparedStatement prepQ=conn.prepareStatement(query);)
		{
			prepQ.setInt(1, r.getId());
			prepQ.setInt(2, vis.getId());
			ResultSet res=prepQ.executeQuery();
			if(res.next())
			{
				t=res.getString("titolo");
			}
		}catch(SQLException e)
		{
			java.util.logging.Logger.getLogger("get titolo").log(Level.INFO, "titolo rivista {0}.",e.toString());
		}
		
		return t;
	}

	public void aggiornaData(Rivista r, Date sqlDate) throws SQLException {
		int row=0;
		query="update RIVISTA set dataPubblicazione=? where id=? or id=?  ";
		try(Connection conn=ConnToDb.connectionToDB();
				PreparedStatement prepQ=conn.prepareStatement(query);)
		{
			prepQ.setDate(1, sqlDate);
			prepQ.setInt(2, r.getId());
			prepQ.setInt(3, vis.getId());
			row=prepQ.executeUpdate();
		}
		java.util.logging.Logger.getLogger("aggiorna data giornale").log(Level.INFO, "rivista aggiornati {0}.",row);

	}
		
	
		
}

		
