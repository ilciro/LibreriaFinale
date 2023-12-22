package laptop.test;

import laptop.controller.ControllerBookData;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class TestControllerBookData {

     private final ResourceBundle RBLIBRO=ResourceBundle.getBundle("configurations/libroDaInserire");

     private final ControllerBookData cBD=new ControllerBookData();

     @Test
    void testCheckBookData()
     {
        String []info=new String[6];
        String []infoCosti=new String[6];
         info[0]=RBLIBRO.getString("info[0]");
         info[1]=RBLIBRO.getString("info[1]");
         info[2]=RBLIBRO.getString("info[2]");
         info[3]=RBLIBRO.getString("info[3]");
         info[4]=RBLIBRO.getString("info[4]");
         info[5]=RBLIBRO.getString("info[5]");
         infoCosti[0]=RBLIBRO.getString("infoGen[0]");
         infoCosti[1]=RBLIBRO.getString("infoGen[1]");
         infoCosti[2]=RBLIBRO.getString("infoGen[2]");
         infoCosti[3]=RBLIBRO.getString("infoGen[3]");
         infoCosti[4]=RBLIBRO.getString("infoGen[4]");
         infoCosti[5]=RBLIBRO.getString("infoGen[5]");
         LocalDate date=LocalDate.of(2024,9,9);
         String recensione=RBLIBRO.getString("recensione");
         String descrizione=RBLIBRO.getString("descrizione");
         assertNotNull(cBD.checkBookData(info,recensione,descrizione,date,infoCosti));

     }



}
