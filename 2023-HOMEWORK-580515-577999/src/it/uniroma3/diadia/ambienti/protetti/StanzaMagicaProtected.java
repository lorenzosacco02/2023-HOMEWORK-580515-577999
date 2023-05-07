package it.uniroma3.diadia.ambienti.protetti;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaMagicaProtected extends StanzaProtected {
	final static private int SOGLIA_MAGICA_DEFAULT = 3;
	private int contatoreAttrezziPosati;
	private int sogliaMagica;

	public StanzaMagicaProtected(String nome) {
		this(nome, SOGLIA_MAGICA_DEFAULT);
	}

	public StanzaMagicaProtected(String nome, int soglia) {
		super(nome);
		this.contatoreAttrezziPosati = 0;
		this.sogliaMagica = soglia;
	}

	@Override
	public boolean addAttrezzo(Attrezzo attrezzo) {
		if (attrezzo == null)
			return false;
		if (this.numeroAttrezzi < NUMERO_MASSIMO_ATTREZZI) {
			this.ArrayDiAttrezzi[numeroAttrezzi] = attrezzo;
			this.numeroAttrezzi++;
			if(contatoreAttrezziPosati>=sogliaMagica) {
				this.modificaAttrezzo(attrezzo);
			}
			this.contatoreAttrezziPosati++;
			return true;
			}
		else
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
