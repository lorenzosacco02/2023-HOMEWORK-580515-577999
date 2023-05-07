package it.uniroma3.diadia.giocatore;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.*;

/**
 * Una classe che modella un Giocatore.
 * Ogni giocatore possiede una borsa e ha un numero di CFU preimpostato.
 * I Giocatori si spostano fra le stanze del gioco.
 * Il giocatore ha il compito di prendere e posare gli attrezzi.
 *
 * @author  580515, 577999
 * @see Stanza
 * @version 1.0
 */

public class Giocatore {
	static final private int CFU_INIZIALI = 20;
	private int CFU;
	private Borsa BorsaDelGiocatore;

	public Giocatore() {
		CFU=CFU_INIZIALI;
		this.BorsaDelGiocatore = new Borsa();
	}

	public Giocatore(Borsa borsa) {
		CFU=20;
		this.BorsaDelGiocatore = borsa;
	}

	public String PrendiAttrezzo(String attrezzo, Stanza StanzaAttuale) {
		StringBuilder messaggioDaStampare = new StringBuilder("");
		Attrezzo prendere;
		if(attrezzo==null){
			messaggioDaStampare.append("Che attrezzo vuoi prendere?");
		}else{
			if(StanzaAttuale!=null) {
				prendere = StanzaAttuale.getAttrezzo(attrezzo);
				if(prendere!=null) {
					if((BorsaDelGiocatore.getPeso()+prendere.getPeso())<=10) {
						BorsaDelGiocatore.addAttrezzo(prendere);
						StanzaAttuale.removeAttrezzo(prendere);
						messaggioDaStampare.append("Ho preso " + prendere.toString()+"! Lo troverai nella tua Borsa: "+(BorsaDelGiocatore.getPeso())+"kg/"+BorsaDelGiocatore.getPesoMax()+"kg\n");
					}
					else
						messaggioDaStampare.append("La tua borsa è troppo pesante prova a lasciare qualche attrezzo a terra");
				}else{
					messaggioDaStampare.append("Il tuo attrezzo non esiste");
				}
			}
			else
				messaggioDaStampare.append("La stanza non esiste");
		}
		return messaggioDaStampare.toString();
	}


	public String RemoveAttrezzo(String attrezzo, Stanza StanzaAttuale) {
		if(attrezzo == null) 
			return "Che attrezzo vuoi posare?";
		if(StanzaAttuale== null)
			return "Stanza innesistente";
		if(StanzaAttuale.getNumeroAttrezzi()==10)
			return "C'è già troppo disordine in questa stanza!\nNon vorrei crearne altro";
		if(this.BorsaDelGiocatore.getAttrezzo(attrezzo)==null){
			return "Ho toccato il fondo, ma "+attrezzo+" non l'ho trovato";
		}
		StanzaAttuale.addAttrezzo(this.BorsaDelGiocatore.removeAttrezzo(attrezzo));
		return "Non mi è mai servito a nulla "+attrezzo+" quindi me ne sono sbarazzato, ora la mia borsa è più leggera! "+(BorsaDelGiocatore.getPeso())+"kg/"+BorsaDelGiocatore.getPesoMax()+"kg\n";
	}

	public int getCfu() {
		return this.CFU;
	}

	public void setCfu(int cfu) {
		this.CFU = cfu;		
	}	

	public Borsa getBorsa () {
		return this.BorsaDelGiocatore;
	}
}
