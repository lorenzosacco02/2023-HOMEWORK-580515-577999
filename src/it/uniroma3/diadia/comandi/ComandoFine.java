package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoFine implements Comando{

	@Override
	public void setParametro(String Parametro) {

	}

	@Override
	public String esegui(Partita partita){
		StringBuilder string = new StringBuilder("Grazie di aver giocato!");
		return string.toString();
	}
	
	@Override
	public String getNome() {
		return "ComandoFine";
	}
	
	@Override
	public String getParametro() {
		return null;
	}
}
