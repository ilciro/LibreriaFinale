package laptop.test;

import laptop.controller.ControllerCheckPagamentoData;
import laptop.controller.ControllerSystemState;
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
import java.util.ResourceBundle;
import java.util.logging.Level;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class TestControllerPagamentoData {

     private final ControllerCheckPagamentoData cCPD=new ControllerCheckPagamentoData();
     private final ResourceBundle RBSETTAOGGETTO=ResourceBundle.getBundle("configurations/settaOggetto");
    private final ControllerSystemState vis=ControllerSystemState.getInstance();
    private final User u= User.getInstance();

     @Test
    void testCheckPagamentoData()
     {
         u.setEmail(RBSETTAOGGETTO.getString("email"));
         vis.setId(Integer.parseInt(RBSETTAOGGETTO.getString("idL")));
         assertDoesNotThrow(()->cCPD.checkPagamentoData(RBSETTAOGGETTO.getString("nomeUser")));
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
