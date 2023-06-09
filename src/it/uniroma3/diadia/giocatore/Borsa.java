package it.uniroma3.diadia.giocatore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.attrezzi.ComparatorPerPeso;

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
		this.attrezzi = new ArrayList<Attrezzo>(); 
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
	 * restituisce l'insieme degli attrezzi nella borsa ordinati per nome
	 * @return Set di attrezzi ordinati per nome
	 */
	public SortedSet<Attrezzo> getContenutoOrdinatoPerNome(){
		SortedSet<Attrezzo> attrezziPerNome = new TreeSet<Attrezzo>(attrezzi);
		return attrezziPerNome;
	}
	
	/**
	 * restituisce l'insieme gli attrezzi nella borsa
	 * ordinati per peso e quindi, a parità di peso, per nome
	 * @return Set di attrezzi ordinarti per peso
	 */
	public SortedSet<Attrezzo> getSortedSetOrdinatoPerPeso(){
		Comparator<Attrezzo> comp = new ComparatorPerPeso();
		SortedSet<Attrezzo> attrezziSetPeso = new TreeSet<Attrezzo>(comp);
		attrezziSetPeso.addAll(attrezzi);
		return attrezziSetPeso;
	}
	
	/**
	 * restituisce una mappa che associa un intero (rappresentante un
	 * peso) con l’insieme degli attrezzi di tale peso
	 * @return Map con interi per chiavi e set di attrezzi come valori associati
	 */
	public Map<Integer,Set<Attrezzo>> getContenutoRaggruppatoPerPeso(){
		Map<Integer,Set<Attrezzo>> MapDiAttrezzi = new HashMap<>();
		ListIterator<Attrezzo> i = attrezzi.listIterator();
		while(i.hasNext()) {
			Attrezzo temp = i.next();
			if(!MapDiAttrezzi.containsKey(temp.getPeso())) {
				Set<Attrezzo> attrezziSet = new TreeSet<Attrezzo>();
				attrezziSet.add(temp);
				MapDiAttrezzi.put(temp.getPeso(), attrezziSet);
			}else{
				MapDiAttrezzi.get(temp.getPeso()).add(temp);
			}
		}
		return MapDiAttrezzi;
	}

	/**
	 * restituisce la lista degli attrezzi nella borsa ordinati per peso e
	 * quindi, a parità di peso, per nome
	 * @return List di attrezzi presenti nella borsa ordinati per peso
	 */
	public List<Attrezzo> getContenutoOrdinatoPerPeso(){
		List<Attrezzo> attrezziOrdinati = new ArrayList<Attrezzo>(attrezzi);
		Collections.sort(attrezziOrdinati, new ComparatorPerPeso());
		return attrezziOrdinati;
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
		Attrezzo attrezzoCercato = new Attrezzo(attrezzo,0);
		if(attrezzo!=null && attrezzi.indexOf(attrezzoCercato)!=-1) {
			return attrezzi.get(attrezzi.indexOf(attrezzoCercato));
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