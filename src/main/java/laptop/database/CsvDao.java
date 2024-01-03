package laptop.database;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import laptop.model.User;
import laptop.model.raccolta.Libro;
import laptop.utilities.ConnToDb;

import java.io.*;

import java.nio.file.Files;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.nio.file.StandardCopyOption.*;
public class CsvDao implements DaoInterface {
    private static final String CSVFILENAME="localDBFile.csv";

    private final File fd;



    public CsvDao() throws IOException {
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
                        ResultSetMetaData Mdata = rs.getMetaData();


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

        userVector[UserAttributes.getIndexId()] = String.valueOf(instance.getId());
        userVector[UserAttributes.getIndexRuolo()] = instance.getIdRuolo().substring(0,1);
        userVector[UserAttributes.getIndexNome()] = instance.getNome();
        userVector[UserAttributes.getIndexCognome()] = String.valueOf(instance.getCognome());
        userVector[UserAttributes.getIndexEmail()] = String.valueOf(instance.getEmail());
        userVector[UserAttributes.getIndexDescrizione()] = String.valueOf(instance.getDescrizione());
        userVector[UserAttributes.getIndexData()] = String.valueOf(instance.getDataDiNascita());

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
            int posNome = UserAttributes.getIndexNome();
            int posEmail=UserAttributes.getIndexEmail();

            boolean userVectorFound = (userVector[posNome].equals(nome))||(userVector[posEmail].equals(email));
            if (userVectorFound) {
                int id = Integer.parseInt(userVector[UserAttributes.getIndexId()]);
                String nomeA = userVector[UserAttributes.getIndexNome()];
                String emailA = userVector[UserAttributes.getIndexEmail()];


                User.getInstance().setId(id);
                User.getInstance().setNome(nomeA);
                User.getInstance().setEmail(emailA);
                userList.add(User.getInstance());
            }
        }

        csvReader.close();

        if (userList.isEmpty()) {
            throw new Exception(" user not found");
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
            int posId = UserAttributes.getIndexId();
            int posMail=UserAttributes.getIndexEmail();

            boolean userVectorFound = (userVector[posId].equals(String.valueOf(instance.getId()))||(userVector[posMail].equals(String.valueOf(instance.getEmail()))));
            if (!userVectorFound) {
                csvWriter.writeNext(userVector);
            } else {
                found = userVectorFound;
            }
        }
        csvWriter.flush();

        csvReader.close();
        csvWriter.close();

        if (found) {
            Files.move(tmpFD.toPath(), fd.toPath(), REPLACE_EXISTING);
        } else {
            tmpFD.delete();
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

            int posEmail=UserAttributes.getIndexEmail();

            boolean userVectorFound = userVector[posEmail].equals(email);
            if (userVectorFound) {
                int id = Integer.parseInt(userVector[UserAttributes.getIndexId()]);
                String ruolo=userVector[UserAttributes.getIndexRuolo()];
                String nome = userVector[UserAttributes.getIndexNome()];
                String cognome=userVector[UserAttributes.getIndexCognome()];
                String emailA = userVector[UserAttributes.getIndexEmail()];
                String desc=userVector[UserAttributes.getIndexDescrizione()];
                String data=userVector[UserAttributes.getIndexData()];


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



    private static class UserAttributes{
        public static int getIndexId() {
            return 0;
        }

        public static int getIndexRuolo() {
            return 1;
        }

        public static int getIndexNome() {
            return 2;
        }

        public static int getIndexCognome() {
            return 4;
        }
        public static int getIndexEmail() {
            return 5;
        }
        public static int getIndexDescrizione() {
            return 6;
        }
        public static int getIndexData() {
            return 7;
        }
    }




}
