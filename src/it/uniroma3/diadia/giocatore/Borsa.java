package it.uniroma3.diadia.giocatore;

import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Una semplice classe che modella una Borsa.
 * Le Borse sono un oggetto posseduto dal giocatore
 * in cui vengono depositati gli attrezzi che il giocatore raccoglie.
 * Ogni borsa ha un peso massimo trasportabile.
 *
 * @author  580515, 577999
 * @see Attrezzo
 * @version 1.0
 */

public class Borsa {

	public final static int DEFAULT_PESO_MAX_BORSA = 10;
	private Attrezzo[] attrezzi;
	private int numeroAttrezzi;
	private int pesoMax;

	/**
	 * Crea un oggetto borsa
	 * Associa un peso max alla borsa
	 */ 
	public Borsa() {
		this(DEFAULT_PESO_MAX_BORSA);
	}

	/**
	 * crea una borsa con un peso max a scelta
	 * Associa un'array di attrezzi  alla borsa lungo 10
	 * @param pesoMax
	 */
	public Borsa(int pesoMax) {
		this.pesoMax = pesoMax;
		this.attrezzi = new Attrezzo[10]; 
		this.numeroAttrezzi = 0;
	}

	/**
	 * Aggiunge un attrezzo alla borsa solo se si rientra nei limiti di peso.
	 * Controlla anche di non superare i 10 attrezzi nella borsa.
	 * ritorna un valore booleano vero se l'aggiunta va a buon fine.
	 * @param attrezzo da aggiungere
	 * @return true se attrezzo viene aggiunto, false altrimenti
	 */
	public boolean addAttrezzo(Attrezzo attrezzo) {
		if(attrezzo==null)
			return false;
		if (this.getPeso() + attrezzo.getPeso() > this.getPesoMax())
			return false;
		if (this.numeroAttrezzi == 10)
			return false;
		this.attrezzi[this.numeroAttrezzi] = attrezzo;
		this.numeroAttrezzi++;
		return true;
	}

	/**
	 * Restituisce il peso massimo della borsa.
	 * @return peso massimo della borsa
	 */
	public int getPesoMax() {
		return pesoMax;
	}

	/**
	 * Restituisce il numero di attrezzi presenti in quel momento nella borsa.
	 * @return attrezzi nella borsa
	 */
	public int getNumeroAttrezzi() {
		return this.numeroAttrezzi;
	}

	/**
	 * Resttituisce, se esiste nella borsa, l'attrezzo con il nome desiderato.
	 * @param nome dell'attrezzo cercato
	 * @return attrezzo con il nome desiderato
	 */
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		Attrezzo a = null;
		for (int i = 0; i < this.numeroAttrezzi; i++)
			if (this.attrezzi[i].getNome().equals(nomeAttrezzo))
				a = attrezzi[i];
		return a;
	}

	/**
	 * Restituisce il peso attuale della borsa dato dalla somma dei pesi degli attrezzi contenuti.
	 * @return peso della borsa
	 */
	public int getPeso() {
		int peso = 0;
		for (int i = 0; i < this.numeroAttrezzi; i++)
			peso += this.attrezzi[i].getPeso();

		return peso;
	}

	/**
	 * Restituisce true se la borsa non ha al suo interno attrezzi
	 * @return true se borsa è vuota, false altrimenti
	 */
	public boolean isEmpty() {
		return this.numeroAttrezzi == 0;
	}

	/**
	 * Funzione che restituisce true se all'interno della borse c'è attrezzo con nome desiderato
	 * @param nome dell' attrezzo cercato
	 * @return true se attrezzo con quel nome è nella borsa, false altrimenti
	 */
	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.getAttrezzo(nomeAttrezzo) != null;
	}

	/**
	 * Rimuove un attrezzo dalla borsa solo se esiste al suo interno
	 * Scala l'array di attrezzi di una posizione indietro a partire dall'attrezzo da rimuovere
	 * @param nome dell'attrezzo da rimuovere
	 * @return l'attrezzo rimosso se attrezzo viene rimosso, null altrimenti
	 */
	public Attrezzo removeAttrezzo(String nomeAttrezzo) {
		Attrezzo a = null;
		for (int i = 0; i < this.numeroAttrezzi; i++)
			if (this.attrezzi[i].getNome().equals(nomeAttrezzo)) {
				a = attrezzi[i];
				for(int j=i+1;j< this.numeroAttrezzi;j++) {
					this.attrezzi[j-1]=this.attrezzi[j];
				}
				this.numeroAttrezzi--;
			}
		return a;
	}

	/**
	 * Trasforma la borsa in una stringa con scritti gli attrezzi e i loro pesi
	 * @return stringa con nomi degli attrezzi, stringa "Borsa vuota" se la borsa è vuota
	 */
	public String toString() {
		StringBuilder s = new StringBuilder();
		if (!this.isEmpty()) {
			s.append("Contenuto borsa (" + this.getPeso() + "kg/" + this.getPesoMax() + "kg): ");
			for (int i = 0; i < this.numeroAttrezzi; i++)
				s.append(attrezzi[i].toString() + " ");
		} else
			s.append("Borsa vuota");
		return s.toString();
	}
}