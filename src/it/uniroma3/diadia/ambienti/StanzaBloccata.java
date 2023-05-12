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
	private boolean bloccata;
	static final private int NUMERO_MASSIMO_DIREZIONI_BLOCCATE = 3;
	final static private String OGGETTI_DEFAULT = "piedediporco passepartout cacciavite";

	public StanzaBloccata(String nome) {
		this(nome,null, OGGETTI_DEFAULT);
	}
	public StanzaBloccata(String nome,String oggetti) {
		this(nome,null, oggetti);
	}
	public StanzaBloccata(String nome,String direzioni, String oggetti) {
		super(nome);
		this.oggettiSbloccanti= new LinkedList<String>(Arrays.asList(oggetti.split(" ")));
		this.direzioniBloccate= new LinkedList<String>();
		this.bloccata=true;
		if(direzioni!=null)
		this.direzioniBloccate.addAll(Arrays.asList(direzioni.split(" ", NUMERO_MASSIMO_DIREZIONI_BLOCCATE)));
	}

	/**
	 * Permette di impostare la direzione bloccata nella stanza
	 * @param Stringa con la direzione da bloccare
	 */
	/*public void impostaDirezioneBloccata(String[] direzione){
		for(String i :direzione){
			impostaDirezioneBloccata(i);
		}
	}*/
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
		//this.sblocca_stanza();
		List<String> Direzioni=super.getDirezioni();
		if(this.bloccata)
		Direzioni.removeAll(direzioniBloccate);
		return Direzioni;
	}
	@Override
	public boolean addAttrezzo(Attrezzo attrezzo) {
		if(attrezzo!=null && this.oggettiSbloccanti.contains(attrezzo.getNome())) 
			this.bloccata=false;
		return super.addAttrezzo(attrezzo);
	}


	/**
	 * Controlla se l'attrezzo sbloccante è presente nella stanza
	 * in caso ci sia sblocca tutte le direzioni e la stanza si comporta normalmente
	 */
	/*public boolean sblocca_stanza(){
		ListIterator<String> i = this.oggettiSbloccanti.listIterator();
		while(i.hasNext()) {
			String attuale=i.next();
				if(this.hasAttrezzo(attuale)) {
					return this.direzioniBloccate.removeAll(direzioniBloccate);
				}
			}
		return true;
		}*/
	
	public List<String> getDirezioniBloccate() {
		return this.direzioniBloccate;
	}
}
