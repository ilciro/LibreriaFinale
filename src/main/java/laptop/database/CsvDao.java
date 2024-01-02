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
import java.util.HashMap;
import java.util.List;

import static java.nio.file.StandardCopyOption.*;
public class CsvDao implements DaoInterface {
    private static final String CSV_FILE_NAME="localDBFile.csv";

    private final File fd;

    //private HashMap<String, Libro> localCache;


    public CsvDao() throws IOException {
        this.fd = new File(CSV_FILE_NAME);



        //this.localCache = new HashMap<String, Libro>();
    }



    @Override
    public void generaReport() throws IOException {

        try {
            if (!fd.exists()) {
                throw new IOException();
            }
        }catch (IOException e)
        {
            System.out.println("EXCEPTION CsvDao");

            if (!fd.exists()) {
                if (fd.createNewFile())
                {


                    System.out.println("creo file CsvDao");

                    String query=   "SELECT * from USERS";


                    try (Connection conn= ConnToDb.connectionToDB();
                         PreparedStatement prepQ=conn.prepareStatement(query)){

                        ResultSet rs = prepQ.executeQuery(query);
                        CSVWriter writer = new CSVWriter(new FileWriter(CSV_FILE_NAME));
                        ResultSetMetaData Mdata = rs.getMetaData();
                        Mdata.getColumnName(1);
                        String[] line1 = {Mdata.getColumnName(1), Mdata.getColumnName(2), Mdata.getColumnName(3), Mdata.getColumnName(4), Mdata.getColumnName(5),Mdata.getColumnName(7),Mdata.getColumnName(8)};
                        writer.writeNext(line1);

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
                        throw new RuntimeException(ex);
                    }


                }
            }
        }

    }
    public static synchronized void saveUser(File fd, User instance) throws Exception {
        CSVWriter csvWriter = new CSVWriter(new BufferedWriter(new FileWriter(fd, true)));
        String[] record = new String[8];

        record[UserAttributes.getIndex_Id()] = String.valueOf(instance.getId());
        record[UserAttributes.getIndex_Ruolo()] = instance.getIdRuolo().substring(0,1);
        record[UserAttributes.getIndex_Nome()] = instance.getNome();
        record[UserAttributes.getIndex_Cognome()] = String.valueOf(instance.getCognome());
        record[UserAttributes.getIndex_Email()] = String.valueOf(instance.getEmail());
        record[UserAttributes.getIndex_Descrizione()] = String.valueOf(instance.getDescrizione());
        record[UserAttributes.getIndex_Data()] = String.valueOf(instance.getDataDiNascita());

        csvWriter.writeNext(record);
        csvWriter.flush();
        csvWriter.close();


    }
    public static synchronized List<User> retreiveByNomeEmail(File fd, String nome,String email) throws Exception {
        // create csvReader object passing file reader as a parameter
        CSVReader csvReader = new CSVReader(new BufferedReader(new FileReader(fd)));
        String[] record;

        List<User> userList = new ArrayList<User>();

        while ((record = csvReader.readNext()) != null) {
            int posNome = UserAttributes.getIndex_Nome();
            int posEmail=UserAttributes.getIndex_Email();

            boolean recordFound = (record[posNome].equals(nome))||(record[posEmail].equals(email));
            if (recordFound) {
                int id = Integer.parseInt(record[UserAttributes.getIndex_Id()]);
                String nomeA = record[UserAttributes.getIndex_Nome()];
                String emailA = record[UserAttributes.getIndex_Email()];


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
        String[] record;

        CSVWriter csvWriter = new CSVWriter(new BufferedWriter(new FileWriter(tmpFD, true)));

        while ((record = csvReader.readNext()) != null) {
            int posId = UserAttributes.getIndex_Id();
            int posMail=UserAttributes.getIndex_Email();

            boolean recordFound = (record[posId].equals(String.valueOf(instance.getId()))||(record[posMail].equals(String.valueOf(instance.getEmail()))));
            if (!recordFound) {
                csvWriter.writeNext(record);
            } else {
                found = recordFound;
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
        String[] record;

        List<User> userList = new ArrayList<User>();

        while ((record = csvReader.readNext()) != null) {

            int posEmail=UserAttributes.getIndex_Email();

            boolean recordFound = record[posEmail].equals(email);
            if (recordFound) {
                int id = Integer.parseInt(record[UserAttributes.getIndex_Id()]);
                String ruolo=record[UserAttributes.getIndex_Ruolo()];
                String nome = record[UserAttributes.getIndex_Nome()];
                String cognome=record[UserAttributes.getIndex_Cognome()];
                String emailA = record[UserAttributes.getIndex_Email()];
                String desc=record[UserAttributes.getIndex_Descrizione()];
                String data=record[UserAttributes.getIndex_Data()];


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
        public static int getIndex_Id() {
            return 0;
        }

        public static int getIndex_Ruolo() {
            return 1;
        }

        public static int getIndex_Nome() {
            return 2;
        }

        public static int getIndex_Cognome() {
            return 4;
        }
        public static int getIndex_Email() {
            return 5;
        }
        public static int getIndex_Descrizione() {
            return 6;
        }
        public static int getIndex_Data() {
            return 7;
        }
    }



        /*
        todo
            copiare users dao in csv
         */
        /*
        @Override
	public void saveAlbum(Album instance) throws Exception {
		boolean duplicatedRecordId = false;

		synchronized (this.localCache) {
			duplicatedRecordId = (this.localCache.get(String.valueOf(instance.getAlbumId())) != null);
		}

		if (!duplicatedRecordId) {
			try {
				List<Album> albumList = retreiveById(this.fd, instance.getAlbumId());
				duplicatedRecordId = (albumList.size() != 0);
			} catch (Exception e) {
				duplicatedRecordId = false;
			}
		}

		if (duplicatedRecordId) {
			DuplicatedRecordException e = new DuplicatedRecordException(
					"Duplicated Instance ID. Id " + instance.getAlbumId() + " was already assigned");
			throw e;
		}

		saveAlbum(this.fd, instance);
	}

	private static synchronized void saveAlbum(File fd, Album instance) throws Exception {

		// create csvWriter object passing file reader as a parameter
		CSVWriter csvWriter = new CSVWriter(new BufferedWriter(new FileWriter(fd, true)));

		String[] record = new String[4];

		record[AlbumAttributesOrder.getIndex_AlbumID()] = String.valueOf(instance.getAlbumId());
		record[AlbumAttributesOrder.getIndex_Titolo()] = instance.getTitolo();
		record[AlbumAttributesOrder.getIndex_Artista()] = instance.getArtista();
		record[AlbumAttributesOrder.getIndex_Anno()] = String.valueOf(instance.getAnno());

		csvWriter.writeNext(record);
		csvWriter.flush();
		csvWriter.close();
	}

         */
}
