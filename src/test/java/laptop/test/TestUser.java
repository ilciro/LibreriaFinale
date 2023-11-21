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
import java.sql.Date;
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

    LocalDate dataN=LocalDate.of(1984, 2,5);
    String data="1984/05/05";

    @Test
    void testInsertUser() throws SQLException, ParseException {
        assertTrue(cAU.checkData(RBSERDAINSERIRE.getString("nome"),RBSERDAINSERIRE.getString("cognome"),RBSERDAINSERIRE.getString("email"), RBSERDAINSERIRE.getString("pass"),data));
    }

    @Test
    void testLogin() throws SQLException {
        assertFalse(cL.controlla(RBSERDAINSERIRE.getString("email"), RBSERDAINSERIRE.getString("pass")));
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


