package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Una Variante della Stanza classica, dopo un certo numero di volte che poso un attrezzo al suo interno attvo la magia.
 * Una volta diventata magica la stanza inverte il nome dell'attrezzo posato al suo interno e ne raddopia il peso
 * 
 * @author 580515, 577999
 * @see Stanza
 * @version 1.0
 */

public class StanzaMagica extends Stanza {
	final static private int SOGLIA_MAGICA_DEFAULT = 3;
	private int contatoreAttrezziPosati;
	private int sogliaMagica;

	public StanzaMagica(String nome) {
		this(nome, SOGLIA_MAGICA_DEFAULT);
	}

	/**
	 * Crea una stanza magica con uno nome e una soglia che indica il numero
	 * di attrezzi da posare prima che diventi magica
	 * @param nome della stanza
	 * @param soglia magica
	 */
	public StanzaMagica(String nome, int soglia) {
		super(nome);
		this.contatoreAttrezziPosati = 0;
		this.sogliaMagica = soglia;
	}

	@Override
	public boolean addAttrezzo(Attrezzo attrezzo) {
		if(super.addAttrezzo(attrezzo)==true){
			if(contatoreAttrezziPosati>=sogliaMagica) {
				this.modificaAttrezzo(attrezzo);
			}
			this.contatoreAttrezziPosati++;
			return true;
		}else
			return false;
	}

	private void modificaAttrezzo(Attrezzo attrezzo) {
		StringBuilder reverse = new StringBuilder(attrezzo.getNome());
		attrezzo.setNome(reverse.reverse().toString());
		attrezzo.setPeso(attrezzo.getPeso()*2);
	}

	public void setContatoreAttrezziPosati(int contatore){
		this.contatoreAttrezziPosati = contatore;
	}
}
