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



public class Giornale implements Raccolta{

	private String  titolo;
	private String tipologia;
	private String lingua;
	private String editore;
	private LocalDate dataPubb;
	private int copieRimanenti;
	private int disponibilita;
	private float prezzo;
	private int id;
	private static final String URLL="/home/daniele/Scaricati/libriPerSito/";

	private String[] infoGenerali=new String[5];

	
	public Giornale()
	{
		super();
	}


	public Giornale(String []info,LocalDate dataPubb,int nrCopie, int disponibilita, float prezzo, int id)
	{
		this.infoGenerali=info;
		this.dataPubb=dataPubb;
		this.copieRimanenti=nrCopie;
		this.disponibilita=disponibilita;
		this.prezzo=prezzo;
		this.id=id;
		this.titolo=info[0];
		this.tipologia=info[5];
		this.editore=info[2];
		this.lingua=info[4];
		



	}

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public String getTipologia() {
		return tipologia;
	}

	public void setTipologia(String tipologia) {
		this.tipologia = tipologia;
	}

	public String getLingua() {
		return lingua;
	}

	public void setLingua(String lingua) {
		this.lingua = lingua;
	}

	public String getEditore() {
		return editore;
	}

	public void setEditore(String editore) {
		this.editore = editore;
	}

	public LocalDate getDataPubb() {
		return dataPubb;
	}

	public void setDataPubb(LocalDate dataPubb) {
		this.dataPubb = dataPubb;
	}

	public int getCopieRimanenti() {
		return copieRimanenti;
	}

	public void setCopieRimanenti(int copieRimanenti) {
		this.copieRimanenti = copieRimanenti;
	}

	public int getDisponibilita() {
		return this.disponibilita;
	}

	public void setDisponibilita(int disponibilita) {
		this.disponibilita = disponibilita;
	}

	public float getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(float prezzo) {
		this.prezzo = prezzo;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	private void readPdf() throws IOException, DocumentException {

		Document document = new Document();
		PdfReader reader = new PdfReader("/home/daniele/IdeaProjects/LibreriaFinale/libriPerSito/giornale.pdf");
		PdfCopy copy=new PdfCopy(document,new FileOutputStream("/home/daniele/Scaricati/libriPerSito/giornale.pdf"));
		document.open();

		int pages = reader.getNumberOfPages();
		for (int i = 1; i <= pages; i++) {
			copy.addPage(copy.getImportedPage(reader,i));

		}


		reader.close();
		document.close();



	}

	@Override
	public void scarica(int i) throws IOException {

		Document document=new Document();
		try{
			PdfWriter writer=PdfWriter.getInstance(document,new FileOutputStream("/home/daniele/Scaricati/libriPerSito/giornale.pdf"));
			document.open();
			document.addTitle("Giornale ");
			document.add(new Paragraph("that is a copy of daily"));

			readPdf();
			document.close();
			writer.close();




		}catch (DocumentException | IOException e)
		{
			java.util.logging.Logger.getLogger("create pdf").log(Level.SEVERE,"pdf not created");
		}

	}

	@Override
	public void leggi(int i) throws DocumentException, IOException {
		File file=new File("/home/daniele/Scaricati/libriPerSito/giornale.pdf");
		Desktop.getDesktop().open(file);


	}

	public String[] getInfoGenerali() {
		return infoGenerali;
	}

	public void setInfoGenerali(String[] infoGenerali) {
		this.infoGenerali = infoGenerali;
	}


	
	
}
