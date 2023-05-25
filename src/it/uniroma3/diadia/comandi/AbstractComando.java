package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public abstract class AbstractComando {
	protected String parametro;
	
	public void setParametro(String p) {
	}
	
	public String getParametro() {
		return this.parametro;
	}
	

	abstract public String esegui(Partita partita);
	abstract public String getNome();
}
