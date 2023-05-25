package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoBorsa extends AbstractComando{
	
	@Override
	public String esegui(Partita partita){
		StringBuilder String = new StringBuilder(partita.getPlayer().getBorsa().toString());
		return String.toString();
	}

	@Override
	public String getNome() {
		return "ComandoBorsa";
	}
}
