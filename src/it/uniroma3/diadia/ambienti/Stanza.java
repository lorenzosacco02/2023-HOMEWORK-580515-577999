package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.*;

/**
 * Una stanza in un gioco di ruolo. Una stanza e' un luogo fisico nel gioco. E'
 * collegata ad altre stanze attraverso delle uscite. Ogni uscita e' associata
 * ad una direzione. La stanza può contenere un numero massimo di Attrezzi.
 * 
 * @author 580515, 577999
 * @see Attrezzo
 * @version 1.0
 */

public class Stanza {

	static final private int NUMERO_MASSIMO_DIREZIONI = 4;
	static final private int NUMERO_MASSIMO_ATTREZZI = 10;

	private String nome;
	private Attrezzo[] ArrayDiAttrezzi;
	private int numeroAttrezzi;
	private Stanza[] stanzeAdiacenti;
	private int numeroStanzeAdiacenti;
	private String[] direzioni;

	/**
	 * Crea una stanza. Non ci sono stanze adiacenti, non ci sono attrezzi.
	 * 
	 * @param nome il nome della stanza
	 */
	public Stanza(String nome) {
		this.nome = nome;
		this.numeroStanzeAdiacenti = 0;
		this.numeroAttrezzi = 0;
		this.direzioni = new String[NUMERO_MASSIMO_DIREZIONI];
		this.stanzeAdiacenti = new Stanza[NUMERO_MASSIMO_DIREZIONI];
		this.ArrayDiAttrezzi = new Attrezzo[NUMERO_MASSIMO_ATTREZZI];
		for (int i = 0; i < NUMERO_MASSIMO_ATTREZZI; i++) {
			this.ArrayDiAttrezzi[i] = new Attrezzo();
		}
	}

	/**
	 * Imposta una stanza adiacente.
	 * 
	 * @param direzione direzione in cui sara' posta la stanza adiacente.
	 * @param stanza    stanza adiacente nella direzione indicata dal primo
	 *                  parametro.
	 */
	public void impostaStanzaAdiacente(String direzione, Stanza stanza) {
		boolean aggiornato = false;
		for (int i = 0; i < this.direzioni.length; i++)
			if (direzione.equals(this.direzioni[i])) {
				this.stanzeAdiacenti[i] = stanza;
				aggiornato = true;
			}
		if (!aggiornato)
			if (this.numeroStanzeAdiacenti < NUMERO_MASSIMO_DIREZIONI) {
				this.direzioni[numeroStanzeAdiacenti] = direzione;
				this.stanzeAdiacenti[numeroStanzeAdiacenti] = stanza;
				this.numeroStanzeAdiacenti++;
			}
	}

	/**
	 * Restituisce la stanza adiacente nella direzione specificata
	 * 
	 * @param direzione
	 * @return Stanza nella direzione desiderata
	 */
	public Stanza getStanzaAdiacente(String direzione) {
		Stanza stanza = null;
		for (int i = 0; i < this.numeroStanzeAdiacenti; i++)
			if (this.direzioni[i].equals(direzione))
				stanza = this.stanzeAdiacenti[i];
		return stanza;
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
		return this.numeroAttrezzi;
	}

	/**
	 * Restituisce la collezione di attrezzi presenti nella stanza.
	 * 
	 * @return Array con tutti gli attrezzi nella stanza.
	 */
	public Attrezzo[] getArrayDiAttrezzi() {
		return this.ArrayDiAttrezzi;
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
		if (this.numeroAttrezzi < NUMERO_MASSIMO_ATTREZZI) {
			this.ArrayDiAttrezzi[numeroAttrezzi] = attrezzo;
			this.numeroAttrezzi++;
			return true;
		} else {
			return false;
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
		for (String direzione : this.direzioni)
			if (direzione != null)
				risultato.append(" " + direzione);
		risultato.append("\nAttrezzi nella stanza: ");
		for (int i = 0; i < this.numeroAttrezzi; i++) {
			risultato.append(ArrayDiAttrezzi[i].toString() + " ");
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
		boolean trovato;
		trovato = false;
		for (Attrezzo attrezzo : this.ArrayDiAttrezzi) {
			if (attrezzo.getNome().equals(nomeAttrezzo))
				trovato = true;
		}
		return trovato;
	}

	/**
	 * Restituisce l'attrezzo con il nome desiderato se presente nella stanza.
	 * 
	 * @param nome dell'attrezzo desiderato
	 * @return l'attrezzo con il nome desiderato, null se l'attrezzo non e'
	 *         presente.
	 */
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		Attrezzo attrezzoCercato;
		attrezzoCercato = null;
		for (int i = 0; i < this.numeroAttrezzi; i++) {
			if (ArrayDiAttrezzi[i] != null) {
				if (ArrayDiAttrezzi[i].getNome().equals(nomeAttrezzo)) {
					attrezzoCercato = ArrayDiAttrezzi[i];
				}
			}
		}
		return attrezzoCercato;
	}

	/**
	 * Rimuove un attrezzo dalla stanza.
	 * 
	 * @param Attrezzo da rimuovere
	 * @return l'oggetto attrezzo che andava rimosso se l'attrezzo e' stato rimosso,
	 *         null altrimenti
	 */
	public Attrezzo removeAttrezzo(Attrezzo attrezzo) {
		Attrezzo a = null;
		if (attrezzo != null) {
			for (int i = 0; i < this.numeroAttrezzi; i++)
				if (this.ArrayDiAttrezzi[i].equals(attrezzo)) {
					a = this.ArrayDiAttrezzi[i];
					this.ArrayDiAttrezzi[i] = this.ArrayDiAttrezzi[this.numeroAttrezzi - 1];
				}
			this.numeroAttrezzi--;
		}
		return a;
	}

	/**
	 * Restituisce le direzioni che portano in un'altra stanza valida.
	 * 
	 * @return direzioni della stanza valide
	 */
	public String[] getDirezioni() {
		String[] direzioni = new String[this.numeroStanzeAdiacenti];
		for (int i = 0; i < this.numeroStanzeAdiacenti; i++)
			direzioni[i] = this.direzioni[i];
		return direzioni;
	}

}