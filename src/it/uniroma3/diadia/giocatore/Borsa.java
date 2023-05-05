package it.uniroma3.diadia.giocatore;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

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
 * @param <E>
 */

public class Borsa{

	public final static int DEFAULT_PESO_MAX_BORSA = 10;
	private List<Attrezzo> attrezzi;
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
		this.attrezzi = new LinkedList<Attrezzo>(); 
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
		return attrezzi.add(attrezzo);
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
		return attrezzi.size();
	}

	/**
	 * Resttituisce, se esiste nella borsa, l'attrezzo con il nome desiderato.
	 * @param nome dell'attrezzo cercato
	 * @return attrezzo con il nome desiderato
	 */
	public Attrezzo getAttrezzo(String attrezzo) {
		ListIterator<Attrezzo> i = attrezzi.listIterator();
		Attrezzo temp=null;
		while(i.hasNext()){
			temp = i.next();
			if(temp.getNome().equals(attrezzo)){
				return temp;
			}
		}
		return null;
	}

	/**
	 * Restituisce il peso attuale della borsa dato dalla somma dei pesi degli attrezzi contenuti.
	 * @return peso della borsa
	 */
	public int getPeso() {
		ListIterator<Attrezzo> i = attrezzi.listIterator();
		int peso = 0;
		while(i.hasNext()) {
			peso+=i.next().getPeso();
		}
		return peso;
	}

	/**
	 * Restituisce true se la borsa non ha al suo interno attrezzi
	 * @return true se borsa è vuota, false altrimenti
	 */
	public boolean isEmpty() {
		return attrezzi.size() == 0;
	}

	/**
	 * Funzione che restituisce true se all'interno della borse c'è attrezzo con nome desiderato
	 * @param nome dell' attrezzo cercato
	 * @return true se attrezzo con quel nome è nella borsa, false altrimenti
	 */
	public boolean hasAttrezzo(String attrezzo) {
		return attrezzi.contains(this.getAttrezzo(attrezzo));
	}

	/**
	 * Rimuove un attrezzo dalla borsa solo se esiste al suo interno
	 * Scala l'array di attrezzi di una posizione indietro a partire dall'attrezzo da rimuovere
	 * @param nome dell'attrezzo da rimuovere
	 * @return l'attrezzo rimosso se attrezzo viene rimosso, null altrimenti
	 */
	public Attrezzo removeAttrezzo(String attrezzo) {
		if(this.getAttrezzo(attrezzo)==null)
			return null;
		return attrezzi.remove(attrezzi.indexOf(this.getAttrezzo(attrezzo)));
	}

	/**
	 * Trasforma la borsa in una stringa con scritti gli attrezzi e i loro pesi
	 * @return stringa con nomi degli attrezzi, stringa "Borsa vuota" se la borsa è vuota
	 */
	public String toString() {
		ListIterator<Attrezzo> i = attrezzi.listIterator();
		StringBuilder s = new StringBuilder();
		if (!this.isEmpty()) {
			s.append("Contenuto borsa (" + this.getPeso() + "kg/" + this.getPesoMax() + "kg): ");
			while(i.hasNext()) {
				s.append(i.next().toString()+" ");
				}
		} else
			s.append("Borsa vuota");
		return s.toString();
	}
}