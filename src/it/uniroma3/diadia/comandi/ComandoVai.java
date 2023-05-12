package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;

public class ComandoVai implements Comando{
	String Parametro;

	public ComandoVai(){
		this.Parametro=null;
	}

	@Override
	public void setParametro(String Parametro) {
		this.Parametro=Parametro;
	}

	@Override
	public String esegui(Partita partita){
		StringBuilder string= new StringBuilder("");
		if(this.Parametro==null)
			return string.append("Dove vuoi andare?\n").toString(); 
		else {
			Stanza prossimaStanza = null;
			prossimaStanza = partita.getStanzaCorrente().getStanzaAdiacente(this.Parametro);
			if (prossimaStanza == null)
				string.append("Direzione inesistente\n");
			else {
				partita.setStanzaCorrente(prossimaStanza);
				partita.getPlayer().setCfu(partita.getPlayer().getCfu()-1);
				string.append("CFU rimasti: "+partita.getPlayer().getCfu()+"\n");
			}
		}
		return string.append(partita.getStanzaCorrente().getDescrizione()).toString();
	}   

	@Override
	public String getNome() {
		return "ComandoVai";
	}

	@Override
	public String getParametro() {
		return this.Parametro;
	}
}

