package laptop.database;
/*
 this class was created for
 reduce complexity and duplication for sonarcloud
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
            case "utente", "utenti" -> {

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
                    java.util.logging.Logger.getLogger("Test Eccezione genera report").log(Level.INFO, "Error in SQL", e);
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
                java.util.logging.Logger.getLogger("Test Eccezione genera report").log(Level.INFO, "creating file {0}.", fd1.getPath());
            }
        }

    }

    private static void cleanUp(Path path) throws  IOException {
        Files.delete(path);
    }



}
