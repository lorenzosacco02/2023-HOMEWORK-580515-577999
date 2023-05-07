package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.*;

/**
 * Una semplice classe che modella un Labirinto. Imposta la sua entrata, la sua
 * uscita i collegamenti fra le stanze e gli attrezzi al loro interno.
 *
 * @author 580515, 577999
 * @see Stanza,StanzaBuia,StanzaBloccata,Attrezzo
 * @version 2.0
 */

public class Labirinto {
	
	/**
	 * crea entrata e uscita del labirinto 
	 */
	public Stanza entrata;
	public Stanza uscita;

	/** 
	 * crea gli attrezzi 
	 */
	public Attrezzo lanterna = new Attrezzo("lanterna", 3);
	public Attrezzo osso = new Attrezzo("osso", 1);
	public Attrezzo spada = new Attrezzo("spada", 5);
	public Attrezzo cassa = new Attrezzo("cassa", 11);

	/**
	 *  crea stanze del labirinto 
	 */
	public Stanza atrio = new Stanza("Atrio");
	public Stanza aulaN11 = new Stanza("Aula N11");
	public Stanza aulaN10 = new Stanza("Aula N10");
	public Stanza laboratorio = new Stanza("Laboratorio Campus");
	public Stanza biblioteca = new Stanza("Biblioteca");
	public Stanza alle_scale_piace_cambiare = new StanzaMagica("2 oggetti per la magia",2);
	
	/**
	 * crea un labirinto con un'entrata un'uscita e un percorso
	 */
	public Labirinto() {
		this.init(atrio, biblioteca);
		this.genera_percorso();
	}
	
	/**
	 * imposta l'entrata e l'uscita
	 * @param entrata desiderata
	 * @param uscita desiderata
	 */
	private void init(Stanza entrata, Stanza uscita) {
		/* imposta l'uscita e l'entrata del labirinto */
		this.entrata = entrata;
		this.uscita = uscita;
	}
	
	/**
	 * genera collegamento fra le stanze
	 */
	private void genera_percorso() {
		/* crea collegamenti fra stanza del labirinto */
		atrio.impostaStanzaAdiacente("nord", biblioteca);
		atrio.impostaStanzaAdiacente("est", aulaN11);
		atrio.impostaStanzaAdiacente("sud", aulaN10);
		atrio.impostaStanzaAdiacente("ovest", laboratorio);
		aulaN11.impostaStanzaAdiacente("est", laboratorio);
		aulaN11.impostaStanzaAdiacente("ovest", atrio);
		aulaN10.impostaStanzaAdiacente("nord", atrio);
		aulaN10.impostaStanzaAdiacente("est", aulaN11);
		aulaN10.impostaStanzaAdiacente("ovest", laboratorio);
		laboratorio.impostaStanzaAdiacente("est", atrio);
		laboratorio.impostaStanzaAdiacente("sud", alle_scale_piace_cambiare);
		alle_scale_piace_cambiare.impostaStanzaAdiacente("nord", laboratorio);
		laboratorio.impostaStanzaAdiacente("ovest", aulaN11);
		biblioteca.impostaStanzaAdiacente("sud", atrio);
		this.inserisci_attrezzi(atrio, lanterna);
		this.inserisci_attrezzi(aulaN10, osso);
		this.inserisci_attrezzi(aulaN10, spada);
		this.inserisci_attrezzi(aulaN10, cassa);

	}
	
	/**
	 * Mette gli attrezzi nelle stanze
	 * @param stanza dove si vuole inserire attrezzo
	 * @param attrezzo che voglio inserire
	 */
	private void inserisci_attrezzi(Stanza stanza, Attrezzo attrezzo) {
		stanza.addAttrezzo(attrezzo);
	}

	/**
	 * Restituisce l'entrata del labirinto
	 * @return entrata del labirinto
	 */
	public Stanza getEntrata() {
		return this.entrata;
	}

	/**
	 * Restituisce stanza vincente del labirinto
	 * @return uscita del labirinto
	 */
	public Stanza getUscita() {
		return this.uscita;
	}
}
