package laptop.controller;

import java.util.logging.Level;




public class ControllerRicercaPerTipo {


	private boolean state=false;
	
	public ControllerRicercaPerTipo() 
	{
		java.util.logging.Logger.getLogger("Test Costruttore").log(Level.INFO,"Costruttore ControllerRicercaPerTipo");

	}
	

	public boolean setRicerca(String type)
	{
		switch (type){
			case "libro", "giornale", "rivista":
				state=true;
				break;
            default:return state;

		}
		return true;
	}
}
