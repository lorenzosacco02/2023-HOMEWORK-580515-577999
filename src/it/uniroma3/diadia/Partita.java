package it.uniroma3.diadia;

import it.uniroma3.diadia.giocatore.*;
import it.uniroma3.diadia.ambienti.*;

/**
 * Questa classe modella una partita del gioco
 * ha il compito di gestire i movimenti che il
 * giocatore effettua fra le varie stanze;
 * controlla inoltre se la partita deve finire per svariate ragioni.
 *
 * @author  580515, 577999
 * @see Stanza,Giocatore 
 * @version 2.0
 */

public class Partita {
	
	private Stanza stanzaCorrente;
	private boolean finita;
	private Labirinto Lab;
	private Giocatore Player;
	/**
	 * Crea una partita a cui associa un labirinto e un giocatore
	 */
	public Partita(){
		Lab= new Labirinto();
		stanzaCorrente = this.Lab.getEntrata();
		this.finita = false;
		Player=new Giocatore();
	}

	/**
	 * Restituisce la stanza vincente
	 * @return stanza di uscita dal labirinto
	 */
	public Stanza getUscita() {
		return this.Lab.getUscita();
	}

	/**
	 * imposta la stanza in cui ci troviamo dopo ogni spostamento
	 * @param stanzaCorrente
	 */
	public void setStanzaCorrente(Stanza stanzaCorrente) {
		this.stanzaCorrente = stanzaCorrente;
	}

	/**
	 * mi restiruisce la stanza in cui si trova il giocatore
	 * @return stanza in cui si trova il giocatore
	 */
	public Stanza getStanzaCorrente() {
		return this.stanzaCorrente;
	}

	/**
	 * Restituisce vero se e solo se la partita e' stata vinta
	 * @return vero se partita vinta
	 */
	public boolean vinta() {
		return this.getStanzaCorrente()== this.Lab.getUscita();
	}

	/**
	 * Restituisce vero se e solo se la partita e' finita
	 * @return vero se partita finita
	 */
	public boolean isFinita() {
		return finita || vinta() || (Player.getCfu() == 0);
	}

	/**
	 * Imposta la partita come finita
	 */
	public void setFinita() {
		this.finita = true;
	}
	public Labirinto getLabirinto(){
		return this.Lab;
	}

	/**
	 * Restituisce il giocatore
	 * @return Giocatore della partita
	 */
	public Giocatore getPlayer(){
		return this.Player;
	}
	
	public void setPlayer(Giocatore Player) {
		this.Player=Player;
	}
}
