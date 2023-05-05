package it.uniroma3.diadia.ambienti;

/**
 * Una stanza in un gioco di ruolo.
 * Questa classe estende Stanza ed eredita i sui metodi
 * se in questra stanza non è presente un attrezzo che la illumini il giocatore non vede nulla al suo interno
 * la descrizione della stanza sarà quindi "qui c'è un buio pesto"
 * si comporta come una stanza normale se al suo interno si trova un attrezzo illuminante
 * 
 * @author 580515, 577999
 * @see Stanza
 * @version 1.0
 */

public class StanzaBuia extends Stanza{
	static final private String ATTREZZO_MAGICO = "lanterna";
	String AttrezzoBuio;
	
	public StanzaBuia(String nome) {
		this(nome, ATTREZZO_MAGICO);
	}
	
	public StanzaBuia(String nome, String attrezzo) {
		super(nome);
		AttrezzoBuio = attrezzo;
	}
	/**
	 * Restituisce la descrizione della stanza
	 * Se la stanza non è illuminata restituisce "qui c'è un buio pesto"
	 * @return String con la descrizione della stanza
	 */
	@Override
	public String getDescrizione(){
		if(super.hasAttrezzo(AttrezzoBuio)==true) {
			return super.getDescrizione();
		}
		else {
			return "qui c'è un buio pesto";
		}
	}
}
