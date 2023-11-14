package laptop.test;

import laptop.controller.ControllerAggiungiUtente;
import laptop.controller.ControllerLogin;
import laptop.controller.ControllerModificaUtente;
import laptop.model.User;

import org.junit.jupiter.api.Test;


import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.*;

class TestUser {
    private final ResourceBundle rBUserDaInserire=ResourceBundle.getBundle("configurations/userDaInserire");

    private ControllerAggiungiUtente cAU=new ControllerAggiungiUtente();
    private ControllerLogin cL=new ControllerLogin();
    private ControllerModificaUtente cMU=new ControllerModificaUtente();

    @Test
    void testInsertUser() throws SQLException, ParseException {
        assertTrue(cAU.checkData(rBUserDaInserire.getString("nome"),rBUserDaInserire.getString("cognome"),rBUserDaInserire.getString("email"), rBUserDaInserire.getString("pass"),rBUserDaInserire.getString("dataN") ));
    }

    @Test
    void testLogin() throws SQLException {
        assertFalse(cL.controlla(rBUserDaInserire.getString("email"), rBUserDaInserire.getString("pass")));
    }
    /*
    @Test
    void testAggiornaUtente() throws SQLException {
        User.getInstance().setEmail(rBUserDaInserire.getString("email"));
        assertTrue(cMU.aggiorna(rBUserDaInserire.getString("nomeM"), rBUserDaInserire.getString("cognomeM"), rBUserDaInserire.getString("emailM"), rBUserDaInserire.getString("passM"), rBUserDaInserire.getString("desc"), LocalDate.of(1985,4,4) ,rBUserDaInserire.getString("email")));
    }
    */
    @Test
    void testAggiornaUtenteTot() throws SQLException {

        User.getInstance().setEmail(rBUserDaInserire.getString("email"));
        User.getInstance().setId(11);

        assertTrue(cMU.aggiornaTot(rBUserDaInserire.getString("nomeM"), rBUserDaInserire.getString("cognomeM"), rBUserDaInserire.getString("emailM"), rBUserDaInserire.getString("passM"), rBUserDaInserire.getString("desc"), LocalDate.of(1985,4,4) ,rBUserDaInserire.getString("ruolo")));
    }

/*
    @AfterAll
    static void ripristinaDB() throws FileNotFoundException {

        Connection conn;
        ScriptRunner sr;

        java.util.logging.Logger.getLogger("Test ripristina db").log(Level.INFO,"---------Chiamo stored truncate---------\n\n");

        conn= ConnToDb.generalConnection();
        sr = new ScriptRunner(conn);
        sr.setSendFullScript(true);
        Reader reader = new BufferedReader(new FileReader("FileSql/dropSchema.sql"));
        //Running the script
        sr.runScript(reader);

    }




 */

}
