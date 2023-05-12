package it.uniroma3.diadia.ambienti;
import it.uniroma3.diadia.attrezzi.*;

import java.util.Arrays;
import java.util.ListIterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Una stanza in un gioco di ruolo.
 * Questa classe estende Stanza ed eredita i sui metodi
 * si comporta come una stanza normale ma ha una o più direzioni bloccate
 * per sbloccare le direzioni c'è un attrezzo corrispondente che deve trovarsi dentro la stanza 
 * 
 * @author 580515, 577999
 * @see Stanza
 * @version 1.0
 */

public class StanzaBloccata extends Stanza{
	private List<String> direzioniBloccate;
	private List<String> oggettiSbloccanti;
	static final private int NUMERO_MASSIMO_DIREZIONI_BLOCCATE = 3;
	final static private String[] OGGETTI_DEFAULT = {"piedediporco","passepartout","cacciavite"};

	public StanzaBloccata(String nome) {
		this(nome, OGGETTI_DEFAULT);
	}

	public StanzaBloccata(String nome, String... oggetti) {
		super(nome);
		this.oggettiSbloccanti= new LinkedList<String>(Arrays.asList(oggetti));
		this.direzioniBloccate= new LinkedList<String>();
	}

	/**
	 * Permette di impostare la direzione bloccata nella stanza
	 * @param Stringa con la direzione da bloccare
	 */
	public void impostaDirezioneBloccata(String[] direzione){
		for(String i :direzione){
			impostaDirezioneBloccata(i);
		}
	}
	public void impostaDirezioneBloccata(String direzione) {
		if (this.direzioniBloccate.size()<NUMERO_MASSIMO_DIREZIONI_BLOCCATE) {
			this.direzioniBloccate.add(direzione);
		}
	}

	/**
	 * Restituisce tutte le direzioni non bloccate della stanza
	 * @return Array di Stringhe con direzioni accessibili
	 */

	@Override
	public List<String> getDirezioni() {
		sblocca_stanza();
		List<String> Direzioni=super.getDirezioni();
		Direzioni.removeAll(direzioniBloccate);
		return Direzioni;
	}



	/**
	 * Controlla se l'attrezzo sbloccante è presente nella stanza
	 * in caso ci sia sblocca tutte le direzioni e la stanza si comporta normalmente
	 */
	private void sblocca_stanza(){
		ListIterator<Attrezzo> i = super.getListaDiAttrezzi().listIterator();
		ListIterator<String> j = this.oggettiSbloccanti.listIterator();
		while(i.hasNext()) {
			while(j.hasNext()) {
				if(i.next().toString().equals(j.next())) {
					this.direzioniBloccate.removeAll(direzioniBloccate);
				}
			}	
		}
	}

	public List<String> getDirezioniBloccate() {
		return this.direzioniBloccate;
	}
}
