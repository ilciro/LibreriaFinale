package web.bean;


import java.time.LocalDate;


public class UserBean {
	
	enum Ruoli {
		ADMIN,
		UTENTE,
		SCRITTORE,
		EDITORE;
 }
	
	
		


	private int idB;
	private String nomeB;
	private String cognomeB;
	private String emailB;
	private String passwordB;
	private String descrizioneB;
	private LocalDate dataDiNascitaB;
	private String rB;
	private String mexB;
	private String listaUtentiB;
	
	//istanza per il patter singleton
	private static UserBean userInstanceB ;

	private UserBean() {

	}

	public static UserBean getInstanceB() {
		if (userInstanceB == null)
		{
			userInstanceB = new UserBean();
			return userInstanceB; 
		}
		return userInstanceB;
	} 

	

	public int getIdB() {
		return idB;
	}

	public void setIdB(int idB) {
		this.idB = idB;
	}

	public String getNomeB() {
		return nomeB;
	}

	public void setNomeB(String nomeB) {
		this.nomeB = nomeB;
	}

	public String getCognomeB() {
		return cognomeB;
	}

	public void setCognomeB(String cognomeB) {
		this.cognomeB = cognomeB;
	}

	public String getEmailB() {
		return emailB;
	}

	public void setEmailB(String emailB) {
		this.emailB = emailB;
	}

	public String getPasswordB() {
		return passwordB;
	}

	public void setPasswordB(String passwordB) {
		this.passwordB = passwordB;
	}

	public String getDescrizioneB() {
		return descrizioneB;
	}

	public void setDescrizioneB(String descrizioneB) {
		this.descrizioneB = descrizioneB;
	}

	public LocalDate getDataDiNascitaB() {
		return dataDiNascitaB;
	}

	public void setDataDiNascitaB(LocalDate dataDiNascitaB) {
		this.dataDiNascitaB = dataDiNascitaB;
	}

	public String getrB() {
		return rB;
	}

	

	public String getMexB() {
		return mexB;
	}

	public void setMexB(String mexB) {
		this.mexB = mexB;
	}

	public String getListaUtentiB() {
		return listaUtentiB;
	}

	public void setListaUtentiB(String listaUtentiB) {
		this.listaUtentiB = listaUtentiB;
	}

	public void setIdRuolo(String ruolo) {

		 switch (ruolo){
			case "ADMIN":
				rB= Ruoli.ADMIN.toString();
				break;				
			case "EDITORE":
				rB= Ruoli.EDITORE.toString();
				break;
			case "SCRITTORE":
				rB= Ruoli.SCRITTORE.toString();
				break;
			case "UTENTE":
				rB= Ruoli.UTENTE.toString();
				break;	
			case "W":
				rB= Ruoli.SCRITTORE.toString();
				break;
			case "E":
				rB= Ruoli.EDITORE.toString();
				break;	
			case "A":
				rB= Ruoli.ADMIN.toString();
				break;
				
			default:
				rB= Ruoli.UTENTE.toString();
				break;
			}
		

	}

	

	


}
