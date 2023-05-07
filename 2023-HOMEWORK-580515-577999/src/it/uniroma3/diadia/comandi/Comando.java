package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.*;

public interface Comando {
	public StringBuilder esegui(Partita partita);
    public void setParametro(String parametro);
    public String getNome();
    public String getParametro();
}
