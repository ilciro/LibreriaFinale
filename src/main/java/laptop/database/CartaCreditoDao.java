package laptop.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.sql.Connection;
import java.sql.Date;

import laptop.model.CartaDiCredito;
import laptop.utilities.ConnToDb;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CartaCreditoDao {

	private  String n;
	private  String cog;
	private String query;
	private static final String ECCEZIONE="eccezione ottenuta :";
	


	public ObservableList<CartaDiCredito> getCarteCredito(String nome)
	{
		String cod;
		query="select nomeP,cognomeP,codiceCarta from CARTACREDITO where nomeP=?";
		
		ObservableList<CartaDiCredito> catalogo=FXCollections.observableArrayList();

		try(Connection conn=ConnToDb.ConnectionToDB();
				PreparedStatement prepQ=conn.prepareStatement(query))
		{
			prepQ.setString(1, nome);
			ResultSet rs=prepQ.executeQuery();
			while(rs.next())
			{
				n=rs.getString(1);
				cog=rs.getString(2);
				cod=rs.getString(3);


				catalogo.add(new CartaDiCredito(n,cog,cod, null, cod,0));


			}
		}catch(SQLException e)
		{
						java.util.logging.Logger.getLogger("Test Eccezione").log(Level.INFO, ECCEZIONE, e);
		}
			
	
		return catalogo;


	}	

	
	public void insCC(CartaDiCredito cc)
	{

		query="insert into CARTACREDITO (nomeP,cognomeP,codiceCarta,scad,codicePin,ammontare)  values(?,?,?,?,?,?)";
				 
		
			try(Connection conn=ConnToDb.ConnectionToDB();
					PreparedStatement prepQ=conn.prepareStatement(query))
			{
				prepQ.setString(1,cc.getNomeUser());
				prepQ.setString(2, cc.getCognomeUser());
				prepQ.setString(3, cc.getNumeroCC());
				prepQ.setDate(4, (Date) cc.getScadenza());
				prepQ.setString(5,cc.getCiv());
				//in alternativa vis.getSpesa
				prepQ.setFloat(6, cc.getPrezzoTransazine());
				prepQ.executeLargeUpdate();
			}catch(SQLException e)
			{
				java.util.logging.Logger.getLogger("report libro").log(Level.SEVERE,"\n eccezione ottenuta .",e);

			}


	}

	
	public CartaDiCredito  popolaDati(CartaDiCredito cc)
	{
		String cod;

		n = null;
		cog = null;
		cod = null;
		
		query="select nomeP,cognomeP,codiceCarta,scad from CARTACREDITO where codiceCarta=?";

		
			try(Connection conn=ConnToDb.ConnectionToDB();
					PreparedStatement prepQ=conn.prepareStatement(query)){
				prepQ.setString(1, cc.getNumeroCC());
				ResultSet rs=prepQ.executeQuery();

				while(rs.next())
				{
					n=rs.getString(1);
					cog=rs.getString(2);
					cod=rs.getString(3);
		}

				cc.setNomeUser(n);
				cc.setCognomeUser(cog);
				cc.setNumeroCC(cod);
			} catch (SQLException e) {
				java.util.logging.Logger.getLogger("report libro").log(Level.SEVERE,"\n eccezione ottenuta .",e);

			}
		return cc;

	}


}











