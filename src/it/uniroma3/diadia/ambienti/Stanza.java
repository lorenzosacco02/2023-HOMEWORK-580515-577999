package it.uniroma3.diadia.ambienti;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import it.uniroma3.diadia.attrezzi.*;

/**
 * Una stanza in un gioco di ruolo. Una stanza e' un luogo fisico nel gioco. E'
 * collegata ad altre stanze attraverso delle uscite. Ogni uscita e' associata
 * ad una direzione. La stanza può contenere un numero massimo di Attrezzi.
 * 
 * @author 580515, 577999
 * @see Attrezzo
 * @version 2.0
 */

public class Stanza {

	private String nome;
	private List<Attrezzo> attrezzi;
	private List<Stanza> stanzeAdiacenti;
	private List<String> direzioni;

	/**
	 * Crea una stanza. Non ci sono stanze adiacenti, non ci sono attrezzi.
	 * 
	 * @param nome il nome della stanza
	 */
	public Stanza(String nome) {
		this.nome = nome;
		this.direzioni = new LinkedList<String>();
		this.stanzeAdiacenti = new LinkedList<Stanza>();
		this.attrezzi = new LinkedList<Attrezzo>();
	}

	/**
	 * Imposta una stanza adiacente.
	 * 
	 * @param direzione direzione in cui sara' posta la stanza adiacente.
	 * @param stanza    stanza adiacente nella direzione indicata dal primo
	 *                  parametro.
	 */
	public void impostaStanzaAdiacente(String direzione, Stanza stanza) {
		String temp;
		if(direzioni.size()<4) {
			this.direzioni.add(direzione);
			ListIterator<String> i = direzioni.listIterator();
			while(i.hasNext()) {
				temp = i.next();
				if (direzione.equals(temp)) {
					this.stanzeAdiacenti.add(stanza);
				}
			}
		}
	}

	/**
	 * Restituisce la stanza adiacente nella direzione specificata
	 * 
	 * @param direzione
	 * @return Stanza nella direzione desiderata
	 */
	public Stanza getStanzaAdiacente(String direzione) {
		if(direzioni.indexOf(direzione)==-1) {
			return null;
		}
		return stanzeAdiacenti.get(direzioni.indexOf(direzione));
	}

	/**
	 * Restituisce il nome della stanza.
	 * 
	 * @return stringa con il nome della stanza
	 */
	public String getNome() {
		return this.nome;
	}

	/**
	 * Restituisce la descrizione della stanza.
	 * 
	 * @return la stringa con la descrizione della stanza
	 */
	public String getDescrizione() {
		return this.toString();
	}

	/**
	 * Restituisce il numero di attrezzi nella stanza
	 * 
	 * @return numero di attrezzi nella stanza
	 */
	public int getNumeroAttrezzi() {
		return this.attrezzi.size();
	}

	/**
	 * Restituisce il numero di direzioni disponibili dalla stanza
	 * 
	 * @return numero di direzioni valide nella stanza
	 */
	public int getNumeroStanzeAdiacenti() {
		return this.stanzeAdiacenti.size();
	}

	/**
	 * Restituisce la collezione di attrezzi presenti nella stanza.
	 * 
	 * @return Array con tutti gli attrezzi nella stanza.
	 */
	public List<Attrezzo> getListaDiAttrezzi() {
		return this.attrezzi;
	}

	/**
	 * Mette un attrezzo nella stanza.
	 * 
	 * @param attrezzo l'attrezzo da mettere nella stanza.
	 * @return true se riesce ad aggiungere l'attrezzo, false atrimenti.
	 */
	public boolean addAttrezzo(Attrezzo attrezzo) {
		if (attrezzo == null)
			return false;
		else {
			return attrezzi.add(attrezzo);
		}
	}

	/**
	 * Restituisce una rappresentazione stringa di questa stanza, stampadone la
	 * descrizione, le uscite e gli eventuali attrezzi contenuti
	 * 
	 * @return la rappresentazione stringa
	 */
	public String toString() {
		StringBuilder risultato = new StringBuilder();
		risultato.append(this.nome);
		risultato.append("\nUscite: ");
		ListIterator<String> i = direzioni.listIterator();
		ListIterator<Attrezzo> j = attrezzi.listIterator();
		while(i.hasNext()) {
			risultato.append(" " + i.next());
		}
		risultato.append("\nAttrezzi nella stanza: ");
		while(j.hasNext()){
			risultato.append(j.next().toString() + " ");
		}
		return risultato.toString();
	}

	/**
	 * Controlla se un attrezzo esiste nella stanza (uguaglianza sul nome).
	 * 
	 * @param nome dell'attrezzo cercato
	 * @return true se l'attrezzo esiste nella stanza, false altrimenti.
	 */
	public boolean hasAttrezzo(String nomeAttrezzo) {
		return attrezzi.contains(this.getAttrezzo(nomeAttrezzo));
	}

	/**
	 * Restituisce l'attrezzo con il nome desiderato se presente nella stanza.
	 * 
	 * @param nome dell'attrezzo desiderato
	 * @return l'attrezzo con il nome desiderato, null se l'attrezzo non e'
	 *         presente.
	 */
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		ListIterator<Attrezzo> i = attrezzi.listIterator();
		Attrezzo attrezzoCercato;
		while(i.hasNext()){
			attrezzoCercato=i.next();
			if(attrezzoCercato.getNome().equals(nomeAttrezzo)){
				return attrezzoCercato;
			}
		}
			return null;
	}

	/**
	 * Rimuove un attrezzo dalla stanza.
	 * 
	 * @param Attrezzo da rimuovere
	 * @return l'oggetto attrezzo che andava rimosso se l'attrezzo e' stato rimosso,
	 *         null altrimenti
	 */
	public Attrezzo removeAttrezzo(Attrezzo attrezzo) {
		if(attrezzo==null || attrezzi.indexOf(attrezzo)==-1)
			return null;
		return attrezzi.remove(attrezzi.indexOf(attrezzo));
	}

	/**
	 * Restituisce le direzioni che portano in un'altra stanza valida.
	 * 
	 * @return direzioni della stanza valide
	 */
	public List<String> getDirezioni() {
		return direzioni;
	}

}