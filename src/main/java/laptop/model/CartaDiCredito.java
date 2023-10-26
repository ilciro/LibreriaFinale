package laptop.model;

import laptop.exception.SetterException;

import java.sql.Date;
import java.util.Objects;

public class CartaDiCredito {

	private int tipo;
	private String numeroCC;
	private double limite;
	private double ammontare;
	private java.util.Date scadenza;
	private String nomeUser; 
	private float prezzoTransazine;
	private String cognomeUser; 
	private String civ;

	private static final String[] types = {"No Card:Cash Only","DINER'S","JCB","MASTER","VISA"};

	public CartaDiCredito() {
	}
	
	public CartaDiCredito(int tipo, String numeroCC, double limite, double ammontare, Date scadenza, String nomeUser,
			float prezzoTransazine) {
		super();
		this.tipo = tipo;
		this.numeroCC = numeroCC;
		this.limite = limite;
		this.ammontare = ammontare;
		this.scadenza = scadenza;
		this.nomeUser = nomeUser;
		this.prezzoTransazine = prezzoTransazine;
	}
	

	public CartaDiCredito(String n,String c,String cod,java.util.Date date,String civ,float prezzo)
	{
		
		this.nomeUser=n;
		this.cognomeUser=c;
		this.numeroCC=cod;
		this.ammontare=1000.0;
		this.scadenza=date;
		this.civ=civ;
		this.prezzoTransazine=prezzo;
	}

	public int getTipo() {
		return tipo;
	}
	public void setTipo(int tipo) throws SetterException {

		if(tipo <=0 )
		{
			throw new SetterException(" type of card invalid");
		}
		this.tipo = tipo;
	}
	public String getNumeroCC() {
		return numeroCC;
	}
	public void setNumeroCC(String numeroCC) throws SetterException {
		if (numeroCC.equals("") )
		{
			throw new SetterException(" type of card invalid");

		}
		this.numeroCC = numeroCC;
	}
	public double getLimite() {
		return limite;
	}
	public void setLimite(double limite) throws SetterException {
		if (limite > 3000f)
		{
			throw new SetterException("limit over 3000.0");
		}
		this.limite = limite;
	}
	public double getAmmontare() {
		return ammontare;
	}
	public void setAmmontare(double ammontare) throws SetterException {
		if (ammontare<1f || ammontare >3000f)
		{
			throw new SetterException(" spending over 3000f");
		}
		this.ammontare = ammontare;
	}
	public java.util.Date getScadenza() {
		return scadenza;
	}
	public void setScadenza(Date scadenza) throws SetterException {
		if (null == scadenza)
		{
			throw new SetterException(" wrong date");

		}
		this.scadenza = scadenza;
	}
	public String getNomeUser() {
		return nomeUser;
	}
	public void setNomeUser(String nomeUser) throws SetterException {
		if (nomeUser.equals(""))
		{
			throw new SetterException("name incorrect");
		}
		this.nomeUser = nomeUser;
	}
	public float getPrezzoTransazine() {
		return prezzoTransazine;
	}
	public void setPrezzoTransazine(float prezzoTransazine) throws SetterException {
		if((prezzoTransazine<0f )|| prezzoTransazine>3000f)
		{
			throw new SetterException("price too high");
		}
		this.prezzoTransazine = prezzoTransazine;
	}

	public String getCognomeUser() {
		return cognomeUser;
	}

	public void setCognomeUser(String cognomeUser) throws SetterException {
		if (cognomeUser.equals(""))
		{
			throw new SetterException("surname incorrect");
		}
		this.cognomeUser = cognomeUser;
	}

	public String getCiv() { 
		return civ;
	}

	public void setCiv(String civ) throws SetterException {
		if((Objects.equals(civ, ""))|| civ.length()!=3)
		{
				throw new SetterException("civ incorrect");

		}
		this.civ = civ;
	}

	public static String[] getTypes() {
		return types;
	}

	

	
	
	
}
