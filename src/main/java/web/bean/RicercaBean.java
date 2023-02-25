package web.bean;


import javafx.collections.ObservableList;
import laptop.model.raccolta.Raccolta;

public class RicercaBean {

	private ObservableList<Raccolta>lista;

	public ObservableList<Raccolta> getLista() {
		return lista;
	}

	public void setLista(ObservableList<Raccolta> lista) {
		this.lista = lista;
	}

	
}
