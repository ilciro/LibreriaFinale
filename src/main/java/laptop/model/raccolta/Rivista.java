package laptop.model.raccolta;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;



public class Rivista implements Raccolta  {
	
	private String titolo;
	private String tipologia;
	private String autore;
	private String lingua;
	private String editore;
	private String descrizione;
	private LocalDate dataPubb;
	private int disp;
	private float prezzo;
	private int copieRim;
	private int id;

	private final ResourceBundle rbTitoli=ResourceBundle.getBundle("configurations/titles");
	private String [] infoGenerali=new String[5];

	private final String DSTPATH="/home/daniele/Scaricati/libriPerSito/";


    public Rivista(String [] info,String descrizione, LocalDate dataPubb2, int disp, float prezzo, int copieRim,int id) {
		this.setInfoGenerali(info);
		this.descrizione = descrizione;
		this.dataPubb = dataPubb2;
		this.disp = disp;
		this.prezzo = prezzo;
		this.copieRim = copieRim;
		this.id = id;
		this.titolo=info[0];
		this.tipologia=info[5];
		this.editore=info[2];
		this.lingua=info[4];
		this.autore=info[3];
	}
	public Rivista() {
		super();
	}
	
	public String getTitolo() {
		return this.titolo;
	}
	public String getTipologia() {
		return this.tipologia;
	}
	public String getAutore() {
		return this.autore;
	}
	public String getLingua() {
		return this.lingua;
	}
	public String getEditore() {
		return this.editore;
	}
	public String getDescrizione() {
		return this.descrizione;
	}
	public LocalDate getDataPubb() {
		return this.dataPubb;
	}
	public int getDisp() {
		return this.disp;
	}
	public float getPrezzo() {
		return this.prezzo;
	}
	public int getCopieRim() {
		return this.copieRim;
	}
	public int getId() {
		return this.id;
	}
	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}
	public void setTipologia(String tipologia) {
		switch (tipologia){
		
		case "SETTIMANALE": 
			this.tipologia = CategorieRivista.SETTIMANALE.toString();  
			break;

		case "BISETTIMANALE": 
			this.tipologia = CategorieRivista.BISETTIMANALE.toString();  
			break;

		case "MENSILE" : 
			this.tipologia = CategorieRivista.MENSILE.toString();  
			break;

		case "BIMESTRALE" : 
			this.tipologia = CategorieRivista.BIMESTRALE.toString();  
			break;

		case "TRIMESTRALE" : 
			this.tipologia = CategorieRivista.TRIMESTRALE.toString();  
			break;

		case "ANNUALE" : 
			this.tipologia = CategorieRivista.ANNUALE.toString();  
			break;

		case "ESTIVO" : 
			this.tipologia = CategorieRivista.ESTIVO.toString();  
			break;

		case "INVERNALE" : 
			this.tipologia = CategorieRivista.INVERNALE.toString();  
			break;

		case "SPORTIVO" : 
			this.tipologia = CategorieRivista.SPORTIVO.toString();  
			break;

		case "CINEMATOGRAFICA" : 
			this.tipologia = CategorieRivista.CINEMATOGRAFICA.toString();  
			break;

		case "GOSSIP" : 
			this.tipologia = CategorieRivista.GOSSIP.toString();  
			break;

		case "TELEVISIVO" : 
			this.tipologia = CategorieRivista.TELEVISIVO.toString();  
			break;

		case "MILITARE" : 
			this.tipologia = CategorieRivista.MILITARE.toString();  
			break;

		case "INFORMATICA" : 
			this.tipologia = CategorieRivista.INFORMATICA.toString();  
			break;

		default :
			this.tipologia = null;
			break;
		}
	}
	public void setAutore(String autore) {
		this.autore = autore;
	}
	public void setLingua(String lingua) {
		this.lingua = lingua;
	}
	public void setEditore(String editore) {
		this.editore = editore;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public void setDataPubb(LocalDate dataPubb) {
		this.dataPubb = dataPubb;
	}
	public void setDisp(int disp) {
		this.disp = disp;
	}
	public void setPrezzo(float prezzo) {
		this.prezzo = prezzo;
	}
	public void setCopieRim(int copieRim) {
		this.copieRim = copieRim;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Override
	public void scarica(int i) throws IOException {
		Document document=new Document();
		try{
			PdfWriter writer=PdfWriter.getInstance(document,new FileOutputStream(DSTPATH+ rbTitoli.getString("titolo15")));
			document.open();
			document.addTitle("Rivista ");
			document.add(new Paragraph("that is a copy of magazine"));

			readPdf();
			document.close();
			writer.close();




		}catch (DocumentException | IOException e)
		{
			java.util.logging.Logger.getLogger("create pdf").log(Level.SEVERE,"pdf not created");
		}
	}
	@Override
	public void leggi(int i) throws IOException, DocumentException {
		Desktop desktop = Desktop.getDesktop();
		desktop.open(new File(DSTPATH+rbTitoli.getString("titolo15")));
		
	}
	public String [] getInfoGenerali() {
		return infoGenerali;
	}
	public void setInfoGenerali(String [] infoGenerali) {
		this.infoGenerali = infoGenerali;
	}

	private void readPdf() throws IOException, DocumentException {

		Document document = new Document();
        String SRCPATH = "/home/daniele/IdeaProjects/LibreriaFinale/libriPerSito/";
        PdfReader reader = new PdfReader(SRCPATH +rbTitoli.getString("titolo15"));
		PdfCopy copy=new PdfCopy(document,new FileOutputStream(DSTPATH+rbTitoli.getString("titolo15")));
		document.open();

		int pages = reader.getNumberOfPages();
		for (int i = 1; i <= pages; i++) {
			copy.addPage(copy.getImportedPage(reader,i));

		}


		reader.close();
		document.close();



	}
	
}
