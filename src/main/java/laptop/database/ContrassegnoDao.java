package laptop.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;

import laptop.utilities.ConnToDb;
import laptop.model.Fattura;

public class ContrassegnoDao {
	
	private  static  final String ECCEZIONE="eccezione ottenuta:";
	private String query;

	

	public void inserisciFattura(Fattura f) throws SQLException 
	{
		 
		query="insert into FATTURA values (?,?,?,?,?,?)";
		 		
 		try(Connection conn=ConnToDb.connectionToDB();
 			PreparedStatement prepQ=conn.prepareStatement(query)){
 			
 			prepQ.setString(1, f.getNome());
 			prepQ.setString(2, f.getCognome());
 			prepQ.setString(3, f.getVia());
 			prepQ.setString(4,f.getCom());
 			prepQ.setInt(5, 0);
 			prepQ.setFloat(6,f.getAmmontare());
 			prepQ.execute();
 			
 			 
 		}catch(SQLException e)
 		{
 			java.util.logging.Logger.getLogger("insert fattura").log(Level.INFO, ECCEZIONE, e);
 		}
       
		 

         
        	 
	}  
	

	public int retUltimoOrdineF() throws SQLException 
	{
		int id=0;
		 query="select count(*) as massimoF from FATTURA";
		 
		 try(Connection conn=ConnToDb.connectionToDB();
				 PreparedStatement prepQ=conn.prepareStatement(query);
				 )
		 {
			 ResultSet rs=prepQ.executeQuery();
			 while(rs.next())
				{
					id=rs.getInt("massimoF");

				}
			
		 }catch(SQLException e)
		 {
			 java.util.logging.Logger.getLogger("Test Eccezione").log(Level.INFO, ECCEZIONE, e);
		 }

		
			
		return id;
		
		
	}
	
	public boolean annullaOrdineF(int idC) throws SQLException
	{
		boolean state=false;
		int row;
		String query1="delete from FATTURA where id=?";
		try(Connection conn=ConnToDb.connectionToDB();
				PreparedStatement prepQ=conn.prepareStatement(query1))
		{
			prepQ.setInt(1,idC);
			row=prepQ.executeUpdate();
			if(row==1)
				state=true;
		}catch(SQLException e)
		{
			java.util.logging.Logger.getLogger("Test Eccezione").log(Level.INFO, ECCEZIONE, e);
		}
			
			return state;

		}
		
}
	

		

	
	
	
	
	
	

         


