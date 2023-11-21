package laptop.test;

import laptop.controller.ControllerAggiungiUtente;
import laptop.controller.ControllerLogin;
import laptop.controller.ControllerModificaUtente;
import laptop.model.User;

import laptop.utilities.ConnToDb;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;

import static org.junit.jupiter.api.Assertions.*;

class TestUser {
    private final ResourceBundle RBSERDAINSERIRE=ResourceBundle.getBundle("configurations/userDaInserire");

    private final ControllerAggiungiUtente cAU=new ControllerAggiungiUtente();
    private final ControllerLogin cL=new ControllerLogin();
    private final ControllerModificaUtente cMU=new ControllerModificaUtente();

    @Test
    void testInsertUser() throws SQLException, ParseException {
        assertTrue(cAU.checkData(RBSERDAINSERIRE.getString("nome"),RBSERDAINSERIRE.getString("cognome"),RBSERDAINSERIRE.getString("email"), RBSERDAINSERIRE.getString("pass"),RBSERDAINSERIRE.getString("dataN") ));
    }

    @Test
    void testLogin() throws SQLException {
        assertFalse(cL.controlla(RBSERDAINSERIRE.getString("email"), RBSERDAINSERIRE.getString("pass")));
    }


    @Test
    void testAggiornaUtenteTot() throws SQLException {

        User.getInstance().setEmail(RBSERDAINSERIRE.getString("email"));
        User.getInstance().setId(11);

        assertTrue(cMU.aggiornaTot(RBSERDAINSERIRE.getString("nomeM"), RBSERDAINSERIRE.getString("cognomeM"), RBSERDAINSERIRE.getString("emailM"), RBSERDAINSERIRE.getString("passM"), RBSERDAINSERIRE.getString("desc"), LocalDate.of(1985,4,4) ,RBSERDAINSERIRE.getString("ruolo")));
    }


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






}


