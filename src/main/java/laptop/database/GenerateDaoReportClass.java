package laptop.database;
/*
 * this class was created for
 * reduce complexity and duplication for sonarcloud
 */

import laptop.model.TempUser;
import laptop.utilities.ConnToDb;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GenerateDaoReportClass {

    private  File fd;
    private  File fd1;


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



    private static final String RIEPILOGOGIORNALI = "riepilogoGiornali.txt";
    private static final String RIEPILOGOGIORNALIWEB = "src/main/webapp/riepilogoGiornali.txt";
    private static final String REPORTLIBRI="riepilogoLibri.txt";

    private static final String REPORTLIBRIWEB="src/main/webapp/riepilogoLibri.txt";
    private static final String RIEPILOGORIVISTE="riepilogoRiviste.txt";
    private static final String RIEPILOGORIVISTEWEB="src/main/webapp/riepilogoRiviste.txt";
    private static final String TXT_FILE_NAME="riepilogoUtenti.txt";
    private static final String TXT_FILE_NAME_WEB="src/main/webapp/riepilogoUtenti.txt";


    public GenerateDaoReportClass(String type)
    {
        	switch (type)
            {
                case GIORNALE -> {
                    this.fd = new File(RIEPILOGOGIORNALI);
                    this.fd1 = new File(RIEPILOGOGIORNALIWEB);
                }
                case LIBRO -> {
                    this.fd=new File(REPORTLIBRI);
                    this.fd1=new File(REPORTLIBRIWEB);
                }
                case RIVISTA -> {
                    this.fd=new File(RIEPILOGORIVISTE);
                    this.fd1=new File(RIEPILOGORIVISTEWEB);
                }
                case "utenti"->{
                    this.fd=new File(TXT_FILE_NAME);
                    this.fd1=new File(TXT_FILE_NAME_WEB);
                }
                default -> {}

            }

    }

    private String path;

    private  static final  String RIVISTA="rivista";
    private  static final String LIBRO="libro";
    private  static final String GIORNALE="giornale";
    private static final String UTENTI="utenti";



    public boolean generateReport(String type) throws IOException {
        boolean status=false;

        switch (type) {
            case LIBRO -> {

                setQuery("select  idLibro ,titolo,copieRimanenti,prezzo  from LIBRO");
                setPath(REPORTLIBRI);
                status = writetoFileLGR(LIBRO, getPath());
            }
            case GIORNALE -> {
                setQuery("select  idGiornale ,titolo,copieRimanenti,prezzo  from GIORNALE");
                setPath(RIEPILOGOGIORNALI);
                status = writetoFileLGR(GIORNALE, getPath());
            }
            case RIVISTA -> {

                setQuery("select  idRivista ,titolo,copieRimanenti,prezzo  from RIVISTA");
                setPath(RIEPILOGORIVISTE);
                status = writetoFileLGR(RIVISTA, getPath());
            }
            case UTENTI-> {

            setQuery("select  * from USERS");
            path = TXT_FILE_NAME;
            status = writeToFileU(path);
         }
            default -> {

            }
        }
       return !status;

    }

    private boolean writetoFileLGR(String type,String path) throws IOException {
        boolean status=false;
        if(type.equals(LIBRO)|| type.equals(GIORNALE)||type.equals(RIVISTA)) {

            try (BufferedWriter b = new BufferedWriter(new FileWriter(path))) {
                try (Connection conn = ConnToDb.connectionToDB();
                     PreparedStatement prepQ = conn.prepareStatement(getQuery())) {
                    ResultSet rs = prepQ.executeQuery();
                    while (rs.next()) {
                        b.write("Id :\t" + rs.getInt(1) + "titolo :\t" + rs.getString(2) + "ricavo totale :\t" + rs.getInt(3) * rs.getFloat(4) + "\n");

                    }

                } catch (SQLException e) {
                    Logger.getLogger("Test Eccezione genera report").log(Level.INFO, "Error in SQL", e);
                }
                b.flush();
            }

            if (!path.isEmpty())
                status = true;
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

    public void checkFilePath(Path path) throws IOException {

        try {
            cleanUp(path);

            if (!fd.exists())
                throw new IOException("file " + fd.getPath() + " not exists -> creating");
            if (fd.exists()) {
                cleanUp(path);
                throw new IOException("file " + fd.getPath() + " -> deleted not exists -> creating");
            }

        } catch (IOException e) {
            if (fd.createNewFile()) {
                Logger.getLogger("Test Eccezione genera report").log(Level.INFO, "creating file {0}.", fd1.getPath());
            }
        }

    }

    public String getReportView(String type) throws SQLException {
        String reportFinale = null;

        switch (type) {
            case LIBRO -> {
                setQuery("create or replace view reportLibri (id,titolo,ricavoMassimo) as select idLibro,titolo,copieRimanenti*prezzo from LIBRO");
                try(Connection conn=ConnToDb.connectionToDB();
                    PreparedStatement prepQ=conn.prepareStatement(getQuery())) {
                    prepQ.executeQuery();
                }catch (SQLException e)
                {
                    Logger.getLogger("Test report book view ").log(Level.SEVERE, "view book not created!");

                }
                reportFinale=leggiReport(LIBRO);
            }
            case GIORNALE -> {
                setQuery("create or replace view reportGiornali (id,titolo,ricavoMassimo) as select idLibro,titolo,copieRimanenti*prezzo from GIORNALE");
                try(Connection conn=ConnToDb.connectionToDB();
                    PreparedStatement prepQ=conn.prepareStatement(getQuery())) {
                    prepQ.executeQuery();
                }catch (SQLException e)
                {
                    Logger.getLogger("Test report daily view ").log(Level.SEVERE, "view giornali not created!");

                }
                reportFinale=leggiReport(GIORNALE);
            }
            case RIVISTA -> {
                setQuery("create or replace view reportRiviste (id,titolo,ricavoMassimo) as select idLibro,titolo,copieRimanenti*prezzo from RIVISTA");
                try(Connection conn=ConnToDb.connectionToDB();
                    PreparedStatement prepQ=conn.prepareStatement(getQuery())) {
                    prepQ.executeQuery();
                }catch (SQLException e)
                {
                    Logger.getLogger("Test report magazine view ").log(Level.SEVERE, "view magazine not created!");

                }
                reportFinale=leggiReport(RIVISTA);
            }
            case UTENTI -> {

                    setQuery("create or replace view reportUtenti (id,ruolo,nome,cognome) as select idUser,idRuolo,nome,cognome from USERS");
                    try(Connection conn=ConnToDb.connectionToDB();
                        PreparedStatement prepQ=conn.prepareStatement(getQuery())) {
                        prepQ.executeQuery();
                    }catch (SQLException e)
                    {
                        Logger.getLogger("Test report users view ").log(Level.SEVERE, "view users not created!");

                    }
                    reportFinale=leggiReport(UTENTI);

            }
        }
        return reportFinale;
    }

    private static void cleanUp(Path path) throws  IOException {
        Files.delete(path);
    }
    private String leggiReport(String type) throws SQLException {
        StringBuilder builder = new StringBuilder();
        switch (type) {
            case LIBRO -> setQuery("select * from reportLibri");
            case GIORNALE -> setQuery("select * from reportGiornali");
            case RIVISTA -> setQuery("select * from reportRiviste");
            case UTENTI -> setQuery("select * from reportUtenti");
        }


        switch (type){
            case LIBRO ,RIVISTA,GIORNALE->{
            try(Connection conn=ConnToDb.connectionToDB();
                PreparedStatement prepQ=conn.prepareStatement(getQuery())) {
                ResultSet rs = prepQ.executeQuery();
                while (rs.next()) {
                    builder.append("id :");
                    builder.append(rs.getInt(1));
                    builder.append("\t");
                    builder.append("titolo :");
                    builder.append(rs.getString(2));
                    builder.append("\t");
                    builder.append("ricavoMassimo :");
                    builder.append(rs.getInt(1));
                    builder.append("\n");

                }
            }
        }
            case UTENTI -> {
                try(Connection conn=ConnToDb.connectionToDB();
                    PreparedStatement prepQ=conn.prepareStatement(getQuery()))
                {
                    ResultSet rs= prepQ.executeQuery();
                    while(rs.next())
                    {
                        builder.append("nome :");
                        builder.append(rs.getString(3));
                        builder.append("\t");
                        builder.append("cognome :");
                        builder.append(rs.getString(4));
                        builder.append("\t");
                        builder.append("ruolo :");
                        builder.append(rs.getString(2));
                        builder.append("id :");
                        builder.append(rs.getInt(1));
                        builder.append("\n");

                    }
                }
            }
        }
        return builder.toString();
    }



}
