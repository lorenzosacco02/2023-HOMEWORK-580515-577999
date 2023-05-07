package it.uniroma3.diadia.ambienti;

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
	private String[] direzioniBloccate;
	private String[] oggettiSbloccanti;
	private int numeroDirezioniBloccate;
	static final private int NUMERO_MASSIMO_DIREZIONI_BLOCCATE = 3;
	final static private String[] OGGETTI_DEFAULT={"piedediporco","passepartout",""};

	public StanzaBloccata(String nome) {
		this(nome, OGGETTI_DEFAULT);
	}

	public StanzaBloccata(String nome, String... oggetti) {
		super(nome);
		this.oggettiSbloccanti=oggetti;
		this.numeroDirezioniBloccate=0;
		this.oggettiSbloccanti[oggettiSbloccanti.length-1]="chiave della stanza " + this.getNome();
		this.direzioniBloccate= new String[NUMERO_MASSIMO_DIREZIONI_BLOCCATE];
	}

	/**
	 * Permette di impostare la direzione bloccata nella stanza
	 * @param Stringa con la direzione da bloccare
	 */
	public void impostaDirezioneBloccata(String direzione) {
			if (this.numeroDirezioniBloccate<this.direzioniBloccate.length) {
				this.direzioniBloccate[this.numeroDirezioniBloccate] = direzione;
				this.numeroDirezioniBloccate++;
		}
	}

	/**
	 * Restituisce tutte le direzioni non bloccate della stanza
	 * @return Array di Stringhe con direzioni accessibili
	 */

	@Override
	public String[] getDirezioni() {
		sblocca_stanza();
		if(numeroDirezioniBloccate!=0) {
			String[] direzioniNuove = super.getDirezioni();
			int i = 0;
			for(int j=0; j<direzioniNuove.length-1; j++) {
				if(direzioniNuove[i].equals(direzioniBloccate[j])==true){
					direzioniNuove[i]=null;
					i++;
				}
			}
			return direzioniNuove;
		}
		else
			return super.getDirezioni();
	}



	/**
	 * Controlla se l'attrezzo sbloccante è presente nella stanza
	 * in caso ci sia sblocca tutte le direzioni e la stanza si comporta normalmente
	 */
	private void sblocca_stanza(){
		for(int i=0; i<oggettiSbloccanti.length; i++) {
			if(super.hasAttrezzo(oggettiSbloccanti[i])==true)
				this.numeroDirezioniBloccate=0;
		}
	}

	public String[] getDirezioniBloccate() {
		return this.direzioniBloccate;
	}
}
