package laptop.database;
/*
 this class was created for
 reduce complexity and duplication for sonarcloud
 */

import laptop.model.TempUser;
import laptop.utilities.ConnToDb;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GenerateDaoReportClass {
    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    private String query;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    private String path;

    private final static String RIVISTA="rivista";
    private final static String LIBRO="libro";
    private final static String GIORNALE="giornale";



    public boolean generateReport(String type) throws IOException {
        boolean status=false;

        switch (type) {
            case LIBRO -> {

                setQuery("select  idLibro ,titolo,copieRimanenti,prezzo  from LIBRO");
                setPath("riepilogoLibri.txt");
                status = writetoFileLGR("libro", getPath());
            }
            case GIORNALE -> {
                setQuery("select  idGiornale ,titolo,copieRimanenti,prezzo  from GIORNALE");
                setPath("riepilogoGiornali.txt");
                status = writetoFileLGR("giornale", getPath());
            }
            case RIVISTA -> {

                setQuery("select  idRivista ,titolo,copieRimanenti,prezzo  from RIVISTA");
                setPath("riepilogoRiviste.txt");
                status = writetoFileLGR("rivista", getPath());
            }
            case "utente", "utenti" -> {

            setQuery("select  * from USERS");
            path = "riepilogoUtenti.txt";
            status = writeToFileU(path);
         }
        }
       return !status;

    }

    private boolean writetoFileLGR(String type,String path) throws IOException {
        boolean status=false;
        switch (type)
        {
            case LIBRO ,GIORNALE,RIVISTA:
                try (BufferedWriter b = new BufferedWriter(new FileWriter(path))) {
                    try (Connection conn = ConnToDb.connectionToDB();
                         PreparedStatement prepQ = conn.prepareStatement(getQuery())) {
                        ResultSet rs= prepQ.executeQuery();
                        while(rs.next())
                        {
                            b.write("Id :\t"+rs.getInt(1) + "titolo :\t" + rs.getString(2)+"ricavo totale :\t"+ rs.getInt(3)*rs.getFloat(4)  +"\n");

                        }

                    } catch (SQLException e) {
                        java.util.logging.Logger.getLogger("Test Eccezione genera report").log(Level.INFO, "Error in SQL", e);
                    }
                    b.flush();
                }
                if (!path.isEmpty())
                    status=true;
        }
        return status;
    }
    private boolean writeToFileU(String path) throws IOException
    {
        boolean status=false;
        try (BufferedWriter b = new BufferedWriter(new FileWriter(path))) {
            query = "select * from USERS";


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
            } catch (SQLException e1) {
                Logger.getLogger("lista utenti").log(Level.SEVERE, "\n eccezione ottenuta .", e1);

            }
            b.flush();

        }
        if(!path.isEmpty())
            status=true;
        return status;
    }




}
