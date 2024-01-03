package laptop.database;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import laptop.model.User;
import laptop.utilities.ConnToDb;

import java.io.*;

import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.nio.file.StandardCopyOption.*;
public class CsvDao implements DaoInterface {
    private static final String CSVFILENAME="localDBFile.csv";

    private static final int getIndexId=0;
    private static final int getIndexRuolo=1;
    private static final int getIndexNome=2;
    private static final int getIndexCognome=3;
    private static final int getIndexEmail=4;
    private static final int getIndexDescrizione=5;
    private static final int getIndexData=6;





    private final File fd;



    public CsvDao()  {
        this.fd = new File(CSVFILENAME);



    }



    @Override
    public void generaReport() throws IOException {

        try {
            if (!fd.exists()) {
                throw new IOException();
            }
        }catch (IOException e)
        {
            Logger.getLogger("genera report").log(Level.SEVERE, "\n file not ecists");



                if (fd.createNewFile())
                {


                    Logger.getLogger("report users").log(Level.SEVERE, "\n making file");


                    String query=   "SELECT * from USERS";


                    try (Connection conn= ConnToDb.connectionToDB();
                         PreparedStatement prepQ=conn.prepareStatement(query)){

                        ResultSet rs = prepQ.executeQuery(query);
                        CSVWriter writer = new CSVWriter(new FileWriter(CSVFILENAME));
                        rs.getMetaData();


                        String[] data =new String[7];
                        while (rs.next())
                        {
                            data[0]= String.valueOf((rs.getInt(1)));
                            data[1]=rs.getString(2);
                            data[2]=rs.getString(3);
                            data[3]=rs.getString(4);
                            data[4]=rs.getString(5);
                            data[5]=rs.getString(7);
                            data[6]= String.valueOf(rs.getDate(8));
                            writer.writeNext(data);
                        }
                        writer.flush();
                        writer.close();


                    } catch (SQLException ex) {
                        Logger.getLogger("report libro").log(Level.SEVERE, "\n eccezione ottenuta .", ex);

                    }


                }

        }

    }
    public static synchronized void saveUser(File fd, User instance) throws Exception {
        CSVWriter csvWriter = new CSVWriter(new BufferedWriter(new FileWriter(fd, true)));
        String[] userVector = new String[8];

        userVector[getIndexId] = String.valueOf(instance.getId());
        userVector[getIndexRuolo] = instance.getIdRuolo().substring(0,1);
        userVector[getIndexNome] = instance.getNome();
        userVector[getIndexCognome] = String.valueOf(instance.getCognome());
        userVector[getIndexEmail] = String.valueOf(instance.getEmail());
        userVector[getIndexDescrizione] = String.valueOf(instance.getDescrizione());
        userVector[getIndexData] = String.valueOf(instance.getDataDiNascita());

        csvWriter.writeNext(userVector);
        csvWriter.flush();
        csvWriter.close();


    }
    public static synchronized List<User> retreiveByNomeEmail(File fd, String nome,String email) throws Exception {
        // create csvReader object passing file reader as a parameter
        CSVReader csvReader = new CSVReader(new BufferedReader(new FileReader(fd)));
        String[] userVector;

        List<User> userList = new ArrayList<>();

        while ((userVector = csvReader.readNext()) != null) {

            boolean userVectorFound = (userVector[getIndexNome].equals(nome))||(userVector[getIndexEmail].equals(email));
            if (userVectorFound) {
                int id = Integer.parseInt(userVector[getIndexId]);
                String nomeA = userVector[getIndexNome];
                String emailA = userVector[getIndexEmail];


                User.getInstance().setId(id);
                User.getInstance().setNome(nomeA);
                User.getInstance().setEmail(emailA);
                userList.add(User.getInstance());
            }
        }

        csvReader.close();

        if (userList.isEmpty()) {
            throw new NullPointerException(" user not found");
        }

        return userList;
    }
    private static synchronized void removeUserById(File fd, User instance) throws Exception {
        File tmpFD = File.createTempFile("dao", "tmp");
        boolean found = false;

        // create csvReader object passing file reader as a parameter
        CSVReader csvReader = new CSVReader(new BufferedReader(new FileReader(fd)));
        String[] userVector;

        CSVWriter csvWriter = new CSVWriter(new BufferedWriter(new FileWriter(tmpFD, true)));

        while ((userVector = csvReader.readNext()) != null) {

            boolean userVectorFound = (userVector[getIndexId].equals(String.valueOf(instance.getId()))||(userVector[getIndexEmail].equals(String.valueOf(instance.getEmail()))));
            if (!userVectorFound) {
                csvWriter.writeNext(userVector);
            } else {
                found = true;
            }
        }
        csvWriter.flush();

        csvReader.close();
        csvWriter.close();

        if (found) {
            Files.move(tmpFD.toPath(), fd.toPath(), REPLACE_EXISTING);
        } else {
            cleanUp(Path.of(tmpFD.toURI()));
        }
    }
    public static synchronized void modifPassUser(File fd, User instance,User instanceA) throws Exception {
        //modified only email because pass not showed

        //instance for delete
        // instance agg fro insert

        removeUserById(fd,instance);
        saveUser(fd,instanceA);



    }
    public static synchronized List<User> retreiveAllDataUser(File fd,String email) throws Exception {
        // create csvReader object passing file reader as a parameter
        CSVReader csvReader = new CSVReader(new BufferedReader(new FileReader(fd)));
        String[] userVector;

        List<User> userList = new ArrayList<>();

        while ((userVector = csvReader.readNext()) != null) {

            boolean userVectorFound = userVector[getIndexEmail].equals(email);
            if (userVectorFound) {
                int id = Integer.parseInt(userVector[getIndexId]);
                String ruolo=userVector[getIndexRuolo];
                String nome = userVector[getIndexNome];
                String cognome=userVector[getIndexCognome];
                String emailA = userVector[getIndexEmail];
                String desc=userVector[getIndexDescrizione];
                String data=userVector[getIndexData];


                User.getInstance().setId(id);
                User.getInstance().setIdRuolo(ruolo);
                User.getInstance().setNome(nome);
                User.getInstance().setCognome(cognome);
                User.getInstance().setEmail(emailA);
                User.getInstance().setDescrizione(desc);
                User.getInstance().setDataDiNascita(LocalDate.parse(data));
                userList.add(User.getInstance());
            }
        }

        csvReader.close();

        if (userList.isEmpty()) {
            throw new Exception(" user not found");
        }

        return userList;
    }




    public static void cleanUp(Path path) throws NoSuchFileException, DirectoryNotEmptyException, IOException {
        Files.delete(path);
    }


}
