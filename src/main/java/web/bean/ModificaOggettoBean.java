package web.bean;



import javafx.collections.ObservableList;
import laptop.model.raccolta.Raccolta;

public class ModificaOggettoBean {
	private ObservableList<Raccolta> miaListaB;
	private String tipologiaLB;
	private String tipologiaGB;
	private String tipologiaRB;
	public ObservableList<Raccolta> getMiaListaB() {
		return miaListaB;
	}
	public void setMiaListaB(ObservableList<Raccolta> miaListaB) {
		this.miaListaB = miaListaB;
	}
	public String getTipologiaLB() {
		return tipologiaLB;
	}
	public void setTipologiaLB(String tipologiaLB) {
		this.tipologiaLB = tipologiaLB;
	}
	public String getTipologiaGB() {
		return tipologiaGB;
	}
	public void setTipologiaGB(String tipologiaGB) {
		this.tipologiaGB = tipologiaGB;
	}
	public String getTipologiaRB() {
		return tipologiaRB;
	}
	public void setTipologiaRB(String tipologiaRB) {
		this.tipologiaRB = tipologiaRB;
	}
	

	

	

}
