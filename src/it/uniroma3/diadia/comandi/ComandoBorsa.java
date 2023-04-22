package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoBorsa implements Comando{

	@Override
	public void setParametro(String parametro) {

	}
	
	@Override
	public StringBuilder esegui(Partita partita){
		StringBuilder String = new StringBuilder(partita.getPlayer().getBorsa().toString());
		return String;
	}

	@Override
	public String getNome() {
		return "ComandoBorsa";
	}

	@Override
	public String getParametro() {
		return null;
	}
}
