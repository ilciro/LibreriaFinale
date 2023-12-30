package laptop.database;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;

import java.util.logging.Level;
import java.util.logging.Logger;


import laptop.model.raccolta.Factory;
import laptop.utilities.ConnToDb;
import laptop.model.TempUser;
import laptop.model.User;


public class UsersDao {


	private static String query;
	private static int max;
	private static boolean state = false;
	private static final String ECCEZIONE = "errore in mysql :";
	private static int row = 0;


	// use this function on controller after you had check the email
	// add an user on db after registration
	// prende i dati dall'oggetto che gli abbiamo passato 
	public static boolean createUser(User u) throws SQLException {


		LocalDate d = u.getDataDiNascita();

		query = "INSERT INTO `USERS`"
				+ "(`Nome`,"
				+ "`Cognome`,"
				+ "`Email`,"
				+ "`pwd`,"
				+ " `descrizione`,"
				+ "`DataDiNascita`)"
				+ "VALUES"
				+ "(?,?,?,?,?,?)";

		try (Connection conn = ConnToDb.connectionToDB();
			 PreparedStatement prepQ = conn.prepareStatement(query)) {


			prepQ.setString(1, User.getInstance().getNome());
			prepQ.setString(2, User.getInstance().getCognome());
			prepQ.setString(3, User.getInstance().getEmail());
			prepQ.setString(4, User.getInstance().getPassword());
			prepQ.setString(5, "utente aggiunto");
			prepQ.setDate(6, Date.valueOf(d));
			prepQ.executeUpdate();

			state = true;
		} catch (SQLException e) {
			Logger.getLogger("createUser").log(Level.INFO, ECCEZIONE, e);

		}


		return state;

	}

	//Uso questa funzione quando un admin deve creare un utente 
	//tramite il terzo caso d'uso per la gestione del sito  
	public static boolean createUser2(TempUser uT) throws SQLException {

		LocalDate d = uT.getDataDiNascitaT();
		query = "INSERT INTO `USERS`"
				+ "(`idRuolo`,"
				+ "`Nome`,"
				+ "`Cognome`,"
				+ "`Email`,"
				+ "`pwd`,"
				+ "`descrizione`,"
				+ "`DataDiNascita`)"
				+ "VALUES (?,?,?,?,?,?,?)";

		try (Connection conn = ConnToDb.connectionToDB();
			 PreparedStatement prepQ = conn.prepareStatement(query)) {


			prepQ.setString(1, uT.getIdRuolo().substring(0, 1));
			prepQ.setString(2, uT.getNomeT());
			prepQ.setString(3, uT.getCognomeT());
			prepQ.setString(4, uT.getEmailT());
			prepQ.setString(5, uT.getPasswordT());
			prepQ.setString(6, uT.getDescrizioneT());
			// alternativa NO se rompe tutto se passi un oggetto di tipo data java lui
			// vuole un oggetto di tipo data sql 
			prepQ.setDate(7, Date.valueOf(d));
			//prepQ.setString(7,U.getInstance())
			prepQ.executeUpdate();
			state = true;

		} catch (SQLException e) {
			Logger.getLogger("create temp User").log(Level.INFO, ECCEZIONE, e);

		}


		return state;

	}

	//check User email if we found that we return true else we return false
	//Qui viene passato dal controller un oggetto di tipo user
	public static int checkUser(User u) throws SQLException {
		int status = 0;
		// ritorna int per motivi legati al controller
		// anche se lo tratto come boolean
		//levato pwd se no non aggiorna


		query = "SELECT idUser FROM USERS where Email =? and pwd=?";
		try (Connection conn = ConnToDb.connectionToDB();
			 PreparedStatement prepQ = conn.prepareStatement(query)) {

			prepQ.setString(1, u.getEmail());
			prepQ.setString(2, u.getPassword());
			ResultSet rs = prepQ.executeQuery();
			if (rs.next()) {
				int id = rs.getInt("idUser");
				if (id > 0)
					status = 1;

			}
		} catch (SQLException e) {
			Logger.getLogger("check user").log(Level.INFO, ECCEZIONE, e);

		}


		return status;
	}

	//Questo check
	public static int checkTempUser(TempUser uT) throws SQLException {
		// ritorna int per motivi legati al controller
		// anche se lo tratto come boolean
		String email = uT.getEmailT();


		int idUser = 0;

		query = "SELECT idUser FROM USERS where Email = ?";
		try (Connection conn = ConnToDb.connectionToDB();
			 PreparedStatement prepQ = conn.prepareStatement(query)) {
			prepQ.setString(1, email);

			ResultSet rs = prepQ.executeQuery();
			while (rs.next()) {
				idUser = rs.getInt("idUser");

			}
		} catch (SQLException e) {
			Logger.getLogger("check temp user").log(Level.INFO, ECCEZIONE, e);

		}

		return idUser;
	}

	public static String getRuolo(User u) throws SQLException {

		String r = "";
		query = "SELECT idRuolo FROM USERS where Email = ?";
		try (Connection conn = ConnToDb.connectionToDB();
			 PreparedStatement prepQ = conn.prepareStatement(query)) {
			prepQ.setString(1, u.getEmail());

			ResultSet rs = prepQ.executeQuery();
			while (rs.next()) {
				r = rs.getString("idRuolo");

			}

		} catch (SQLException e) {
			Logger.getLogger("get ruolo user").log(Level.INFO, ECCEZIONE, e);

		}
		u.setIdRuolo(r);


		return r;

	}

	// this function check if you have changed password successfully 
	public static boolean checkResetpass(User u, String pwd, String email) throws SQLException {

		query = "Update USERS SET pwd = ?  where Email = ?";
		try (Connection conn = ConnToDb.connectionToDB();
			 PreparedStatement prepQ = conn.prepareStatement(query)) {


			prepQ.setString(1, pwd);
			prepQ.setString(2, email);
			row = prepQ.executeUpdate();
			if (row == 1)
				state = true;

		} catch (SQLException e) {
			Logger.getLogger("check reset pwd").log(Level.INFO, ECCEZIONE, e);

		}


		Logger.getLogger("Reset pwd").log(Level.INFO, "row affected{0}", u.getEmail());
		return state;
	}


	public static TempUser findUser(TempUser u) {
		String r = TempUser.getInstance().getIdRuolo();
		u.setIdRuolo(r);
		return u;

	}

	// delete a user from db  terzo caso d'uso

	public static boolean deleteUser(User user) throws SQLException {
		String email = user.getEmail();
		int id= user.getId();

		query = "DELETE FROM USERS WHERE Email = ? or idUser=?";
		try (Connection conn = ConnToDb.connectionToDB();
			 PreparedStatement prepQ = conn.prepareStatement(query)) {


			prepQ.setString(1, email);
			prepQ.setInt(2, id);
			row = prepQ.executeUpdate();
			if (row == 1)
				state = true;

		} catch (SQLException e) {
			Logger.getLogger("delete user").log(Level.INFO, ECCEZIONE, e);

		}


		Logger.getLogger("delete user ruolo").log(Level.INFO, "cancello user id{0}", id);


		return state;

	}

	public static boolean deleteTempUser(TempUser uT) throws SQLException {
		String email = uT.getEmailT();
		int id=uT.getId();


		query = "DELETE FROM USERS WHERE Email = ? or idUser=?";
		try (Connection conn = ConnToDb.connectionToDB();
			 PreparedStatement prepQ = conn.prepareStatement(query)) {

			prepQ.setString(1, email);
			prepQ.setInt(2,id);
			row = prepQ.executeUpdate();
			if (row == 1)
				state = true;

		} catch (SQLException e) {
			Logger.getLogger("delete user").log(Level.INFO, ECCEZIONE, e);

		}
		Logger.getLogger("delete user okr").log(Level.INFO, "user deleted ");

		return state;
	}

	// Con pickData prendo i dati dall'utente creato per il login
	// per poi restituirlo in un nuovo oggetto di tipo User
	// e poi il controller lo specializza nelle 4 classi 
	public static User pickData(User u) throws SQLException {


		query = "SELECT idRuolo,Nome,Cognome,Email,descrizione,dataDiNascita from USERS where Email=? or idUser=?";
		try (Connection conn = ConnToDb.connectionToDB();
			 PreparedStatement prepQ = conn.prepareStatement(query)) {
			prepQ.setString(1, u.getEmail());
			prepQ.setInt(2,u.getId());

			ResultSet rs = prepQ.executeQuery();
			while (rs.next()) {


				// setto i vari dati

				u.setIdRuolo(rs.getString("idRuolo"));
				u.setNome(rs.getString("Nome"));
				u.setCognome(rs.getString("Cognome"));
				u.setDescrizione(rs.getString("descrizione"));
				u.setDataDiNascita(rs.getDate("DataDiNascita").toLocalDate());


			}
		} catch (SQLException e) {
			Logger.getLogger("pick data exception ").log(Level.SEVERE, "errore nel pick data {0}", e.toString());


		}


		// errore
		return u;
	}

	public static User aggiornaNome(User u) throws SQLException {


		query = "UPDATE USERS set Nome=? where Email=?";

		try (Connection conn = ConnToDb.connectionToDB();
			 PreparedStatement prepQ = conn.prepareStatement(query)) {
			prepQ.setString(1, u.getNome());
			prepQ.setString(2, u.getEmail());
			prepQ.executeUpdate();
		} catch (SQLException e) {
			Logger.getLogger("aggiorna nome user").log(Level.INFO, ECCEZIONE, e);

		}


		return u;
	}

	public static User aggiornaCognome(User u) throws SQLException {


		query = "UPDATE USERS set Cognome=? where Email=?";
		try (Connection conn = ConnToDb.connectionToDB();
			 PreparedStatement prepQ = conn.prepareStatement(query)) {


			prepQ.setString(1, u.getNome());
			prepQ.setString(2, u.getEmail());
			prepQ.executeUpdate();

		} catch (SQLException e) {
			Logger.getLogger("aggiorna cognome").log(Level.INFO, ECCEZIONE, e);

		}


		// errore
		return u;
	}

	public static User aggiornaEmail(User u, String m) throws SQLException {


		query = "UPDATE USERS set Email=? where Email=?";

		u.setEmail(m);

		try (Connection conn = ConnToDb.connectionToDB();
			 PreparedStatement prepQ = conn.prepareStatement(query)) {


			prepQ.setString(1, u.getEmail());
			prepQ.setString(2, u.getEmail());
			prepQ.executeUpdate();
		} catch (SQLException e) {
			Logger.getLogger("aggiorna email user").log(Level.INFO, ECCEZIONE, e);

		}

		return u;
	}

	public static User aggiornaPass(User u) throws SQLException {


		query = "UPDATE USERS set pwd=? where Email=?";
		try (Connection conn = ConnToDb.connectionToDB();
			 PreparedStatement prepQ = conn.prepareStatement(query)) {


			prepQ.setString(1, u.getPassword());
			prepQ.setString(2, u.getEmail());
			prepQ.executeUpdate();
		} catch (SQLException e) {
			Logger.getLogger("aggiornaPass").log(Level.INFO, ECCEZIONE, e);

		}


		return u;
	}

	public static User aggiornaDesc(User u) throws SQLException {

		query = "UPDATE USERS set descrizione=? where Email=?";
		try (Connection conn = ConnToDb.connectionToDB();
			 PreparedStatement prepQ = conn.prepareStatement(query)) {


			prepQ.setString(1, u.getDescrizione());
			prepQ.setString(2, u.getEmail());
			prepQ.executeUpdate();


		} catch (SQLException e) {
			Logger.getLogger("aggiorna desc").log(Level.INFO, ECCEZIONE, e);

		}
		return u;
	}



	public static void getListaUtenti() throws IOException, SQLException {

		  final String TXT_FILE_NAME="ReportFinale/riepilogoLibro.txt";
		 File fd;


			fd=new File(TXT_FILE_NAME);
			if (!fd.exists()) {
				fd.createNewFile();
			}

		query = "select * from USERS";
		FileWriter w;
		w = new FileWriter("ReportFinale/riepilogoUtenti.txt");


		try (BufferedWriter b = new BufferedWriter(w)) {

			try (Connection conn = ConnToDb.connectionToDB();
				 PreparedStatement prepQ = conn.prepareStatement(query)) {

				ResultSet rs = prepQ.executeQuery();


				while (rs.next()) {

					TempUser.getInstance().setId(rs.getInt(1));
					TempUser.getInstance().setIdRuolo(rs.getString(2));
					TempUser.getInstance().setNomeT(rs.getString(3));
					TempUser.getInstance().setCognomeT(rs.getString(4));
					TempUser.getInstance().setEmailT(rs.getString(5));
					TempUser.getInstance().setDescrizioneT(rs.getString(7));
					TempUser.getInstance().setDataDiNascitaT(rs.getDate(8).toLocalDate());
					b.write(TempUser.getInstance().getId() + "\t" + TempUser.getInstance().getIdRuolo() + "\t" + TempUser.getInstance().getNomeT() + "\t" + TempUser.getInstance().getCognomeT() +
							"\t" + TempUser.getInstance().getEmailT() + "\t" + TempUser.getInstance().getDescrizioneT() + "\t" + TempUser.getInstance().getDataDiNascitaT().toString() + "\n");

				}
			} catch (SQLException e) {
				Logger.getLogger("lista utenti").log(Level.SEVERE, "\n eccezione ottenuta .", e);

			}
			b.flush();

		}

	}

	public static TempUser getTempUserSingolo(TempUser uT) throws SQLException {


		query = "SELECT * FROM USERS where idUser = ?";
		try (Connection conn = ConnToDb.connectionToDB();
			 PreparedStatement prepQ = conn.prepareStatement(query)) {

			prepQ.setInt(1, uT.getId());

			ResultSet rs = prepQ.executeQuery();
			while (rs.next()) {

				uT.setIdRuolo(rs.getString(2));
				uT.setNomeT(rs.getString(3));
				uT.setCognomeT(rs.getString(4));
				uT.setEmailT(rs.getString(5));
				uT.setPasswordT(rs.getString(6));
				uT.setDescrizioneT(rs.getString(7));
				uT.setDataDiNascitaT(rs.getDate(8).toLocalDate());


			}
		} catch (SQLException e) {
			Logger.getLogger("get single temp user").log(Level.INFO, ECCEZIONE, e);

		}

		return uT;
	}

	public static User aggiornaUtente(User u) throws SQLException {


		query = "UPDATE USERS set idRuolo=? , Nome=? , Cognome=? , Email=? , pwd=? , descrizione=? , DataDiNascita=? where idUser=?";


		try (Connection conn = ConnToDb.connectionToDB();
			 PreparedStatement prepQ = conn.prepareStatement(query)) {


			// setto i vari dati


			prepQ.setString(1, u.getIdRuolo().substring(0, 1));
			prepQ.setString(2, u.getNome());
			prepQ.setString(3, u.getCognome());
			prepQ.setString(4, u.getEmail());
			prepQ.setString(5, u.getPassword());
			prepQ.setString(6, u.getDescrizione());
			prepQ.setString(7, u.getDataDiNascita().toString());
			prepQ.setInt(8, u.getId());


			prepQ.executeUpdate();

		} catch (SQLException e) {
			Logger.getLogger("aggiorna utente").log(Level.INFO, ECCEZIONE, e);

		}

		return u;
	}

	public static int maxIdUSer() throws SQLException {
		query = "select max(idUser) as idMax from USERS";

		try (Connection conn = ConnToDb.connectionToDB();
			 PreparedStatement prepQ = conn.prepareStatement(query)) {

			ResultSet rs = prepQ.executeQuery();
			if (rs.next()) {
				max = rs.getInt("idMax");
			}
		} catch (SQLException e) {
			Logger.getLogger("get max id user").log(Level.INFO, ECCEZIONE, e);

		}
		return max;
	}


	private UsersDao() {
	}

	public static String getUserList() throws SQLException {
		query = "select * from USERS";
		StringBuilder s = new StringBuilder();
		try (Connection conn = ConnToDb.connectionToDB();
			 PreparedStatement prepQ = conn.prepareStatement(query)) {
			ResultSet rs = prepQ.executeQuery();
			while (rs.next()) {
				s.append("\n Id User \t");
				s.append("Id Ruolo \t");
				s.append("Nome \t");
				s.append("Cognome \t");
				s.append("Email \t");
				s.append("Data di nascita \n");

				s.append(rs.getInt(1));
				s.append("\t");
				s.append(rs.getInt(1));
				s.append("\t");
				s.append(rs.getString(2));
				s.append("\t");
				s.append(rs.getString(3));
				s.append("\t");
				s.append(rs.getString(4));
				s.append("\t");
				s.append(rs.getString(5));
				s.append("\t");
				s.append(rs.getDate(8).toLocalDate());
				s.append("\n");
			}
		} catch (SQLException e) {
			Logger.getLogger("user list").log(Level.INFO, "user list {0}.", e.toString());

		}
		return s.toString();
	}

	public static int aggiornaTempUser(TempUser uT) throws SQLException {

		int row = 0;

		query = "UPDATE USERS set idRuolo=? , Nome=? , Cognome=? , Email=? , pwd=? , descrizione=? , DataDiNascita=? where idUser=?";


		try (Connection conn = ConnToDb.connectionToDB();
			 PreparedStatement prepQ = conn.prepareStatement(query)) {


			// setto i vari dati


			prepQ.setString(1, uT.getIdRuolo().substring(0, 1));
			prepQ.setString(2, uT.getNomeT());
			prepQ.setString(3, uT.getCognomeT());
			prepQ.setString(4, uT.getEmailT());
			prepQ.setString(5, uT.getPasswordT());
			prepQ.setString(6, uT.getDescrizioneT());
			prepQ.setString(7, uT.getDataDiNascitaT().toString());
			prepQ.setInt(8, uT.getId());


			row=prepQ.executeUpdate();

		} catch (SQLException e) {
			Logger.getLogger("aggiorna utente").log(Level.INFO, ECCEZIONE, e);

		}

		return row;
	}
}

