/*package laptop.test;

import laptop.controller.ControllerModifPage;
import laptop.controller.ControllerSystemState;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TestControllerModifPage {


        private final ResourceBundle RBSETTAOGGETTO=ResourceBundle.getBundle("configurations/settaOggetto");
        private final ControllerSystemState vis=ControllerSystemState.getInstance();
        private ControllerModifPage cMP=new ControllerModifPage();
        @Test
        void getLibriById() throws SQLException {
            vis.setId(Integer.parseInt(RBSETTAOGGETTO.getString("idLibroM")));
            assertNotNull(cMP.getLibriById(vis.getId()));
        }

        @Test
        void getGiornaliById() throws SQLException {
            vis.setId(Integer.parseInt(RBSETTAOGGETTO.getString("idGiornaleM")));
            assertNotNull(cMP.getGiornaliById(vis.getId()));
        }
        @Test
        void getRivistaById() throws SQLException {
            vis.setId(Integer.parseInt(RBSETTAOGGETTO.getString("idRivistaM")));
            assertNotNull(cMP.getRivistaById(vis.getId()));
        }


        @Test
        void checkDataG() throws SQLException {
            String info[]=new String[4];
            info[0]=RBSETTAOGGETTO.getString("infoGM[0]");
            info[1]=RBSETTAOGGETTO.getString("infoGM[1]");
            info[2]=RBSETTAOGGETTO.getString("infoGM[2]");
            info[3]=RBSETTAOGGETTO.getString("infoGM[3]");
            LocalDate data=LocalDate.of(2024,2,1);
            vis.setId(Integer.parseInt(RBSETTAOGGETTO.getString("idGiornaleM")));
            cMP.checkDataG(info,data,Integer.parseInt(RBSETTAOGGETTO.getString("disponibilitaGM")),Float.parseFloat(RBSETTAOGGETTO.getString("prezzoGM")),Integer.parseInt(RBSETTAOGGETTO.getString("copieGM")));
            assertNotNull(data);
        }


        @Test
        void checkDataR() throws SQLException {
            String info[]=new String[5];
            info[0]=RBSETTAOGGETTO.getString("infoRM[0]");
            info[1]=RBSETTAOGGETTO.getString("infoRM[1]");
            info[2]=RBSETTAOGGETTO.getString("infoRM[2]");
            info[3]=RBSETTAOGGETTO.getString("infoRM[3]");
            info[4]=RBSETTAOGGETTO.getString("infoRM[4]");
            LocalDate data=LocalDate.of(2024,5,5);
            vis.setId(Integer.parseInt(RBSETTAOGGETTO.getString("idRivistaM")));
            cMP.checkDataR(info,data,Integer.parseInt(RBSETTAOGGETTO.getString("dispRM")),Float.parseFloat(RBSETTAOGGETTO.getString("prezzoRM")),Integer.parseInt(RBSETTAOGGETTO.getString("copieRimRM")),vis.getId(),RBSETTAOGGETTO.getString("descrizioneRM"));
            assertNotNull(data);
        }

        @Test
        void checkDataL() throws SQLException {
            String info[]=new String[7];
            String infoCosti[]=new String[6];
            vis.setId(Integer.parseInt(RBSETTAOGGETTO.getString("idLibroM")));

            info[0]=RBSETTAOGGETTO.getString("infoLM[0]");
            info[1]=RBSETTAOGGETTO.getString("infoLM[1]");
            info[2]=RBSETTAOGGETTO.getString("infoLM[2]");
            info[3]=RBSETTAOGGETTO.getString("infoLM[3]");
            info[4]=RBSETTAOGGETTO.getString("infoLM[4]");
            info[5]=RBSETTAOGGETTO.getString("infoLM[5]");
            info[6]=RBSETTAOGGETTO.getString("infoLM[6]");

            infoCosti[0]=RBSETTAOGGETTO.getString("infoCostiLM[0]");
            infoCosti[1]=RBSETTAOGGETTO.getString("infoCostiLM[1]");
            infoCosti[2]=RBSETTAOGGETTO.getString("infoCostiLM[2]");
            infoCosti[3]=RBSETTAOGGETTO.getString("infoCostiLM[3]");
            infoCosti[4]=RBSETTAOGGETTO.getString("infoCostiLM[4]");
            infoCosti[5]=RBSETTAOGGETTO.getString("infoCostiLM[5]");

            LocalDate data=LocalDate.of(2024,1,8);

            cMP.checkDataL(info,RBSETTAOGGETTO.getString("recensioneLM"),RBSETTAOGGETTO.getString("descrizioneLM"),data,infoCosti);
            assertNotNull(data);

        }
    }



 */