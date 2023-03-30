package it.uniroma3.diadia;

import it.uniroma3.diadia.ambienti.Stanza;

/**
 * Questa classe modella un comando.
 * Un comando consiste al piu' di due parole:
 * il nome del comando ed un parametro
 * su cui si applica il comando.
 * (Ad es. alla riga digitata dall'utente "vai nord"
 *  corrisponde un comando di nome "vai" e parametro "nord").
 *
 * @author  580515, 577999
 * @see Stanza
 * @version 1.0
 */

public class Comando {

	static final private String[] elencoComandi = {"vai", "aiuto", "fine", "prendi", "posa"};

	/**
	 * Permette di spostarsi fra le varie stanze.
	 * riceve come parametro la direzione in cui si vuole andare e la partita a cui si sta giocando
	 * Restituisce "Dove vuoi andare" se non si inserisce una direzione 
	 * Restituisce "Direzione inesistente" se la direzione porta ad una stanza inesistente
	 * Restituisce i CFU rimasti (cioè uno in meno di di quelli ricevuti) se ci si sposta effettivamente in un altra stanza
	 * @param direzione desiderata
	 * @param partita
	 * @return Stringa che spiega cosa sta succedendo
	 */
	public String vai(String direzione, Partita partita) {
		String string="";
		if(direzione==null)
			string= "Dove vuoi andare ?\n"; 
		else {
			Stanza prossimaStanza = null;
			prossimaStanza = partita.getStanzaCorrente().getStanzaAdiacente(direzione);
			if (prossimaStanza == null)
				string= "Direzione inesistente\n";
			else {
				partita.setStanzaCorrente(prossimaStanza);
				partita.getPlayer().setCfu(partita.getPlayer().getCfu()-1);
				string =("CFU rimasti: "+partita.getPlayer().getCfu()+"\n");
			}
		}
		return string+partita.getStanzaCorrente().getDescrizione();
	}

	/**
	 * Riceve una stringa che indica il comando di cui si vuole una descrizione
	 * Restituisce una descrizione del comando desiderato
	 * @param Stringa con nome del comando
	 * @return Stringa con descrizione del comando
	 */
	public String aiuto(String aiuto) {
		String string = "";
		switch (aiuto) {
		case "aiuto":
			string = "Il comando aiuto ti da informazioni su tutti i comandi del gioco\nsi scriva aiuto e una di queste opzioni ";
		case "":
			string = string + "[";
			for (int i = 0; i < elencoComandi.length; i++) {
				if(i==0)
					string = (string +elencoComandi[i]);
				else
					string = (string +" "+ elencoComandi[i]);
			}
			string = string + "]";
			break;
		case "vai":
			string = "Il comando vai ti fa andare nella stanza adiacente a quella in cui ti trovi nella direzione da te indicata\nsi scriva vai e una di queste opzioni [nord sud est ovest]";
			break;
		case "fine":
			string = "Il comando fine ti permette di finire il gioco in qualsiasi momento\nsi scriva fine";
			break;
		case "prendi":
			string = "Il comando prendi ti fa prendere un attrezzo che si trova nella stanza in cui ti trovi\nsi scriva prendi e il nome del attrezzo che vuoi prendere";
			break;
		case "posa":
			string = "Il comando posa ti fa lasciare un attrezzo nella stanza in cui ti trovi\nsi scriva posa e il nome del attrezzo che vuoi prendere";
			break;
		default:
			string = "non esiste il comando:\n" + aiuto;
			break;
		}
		return string;
	}

	/**
	 * Restituisce una Stringa che mostra il messaggio di fine partita
	 * @return Stringa di fine partita
	 */
	public String fine() {
		return ("Grazie di aver giocato!"); 
	}

	/**
	 * Riceve come parametro il nome dell'attrezzo cercato e la partita a cui si sta giocando.
	 * Prende un attrezzo dalla stanza e lo sposta nella borsa.
	 * Restituisce un messaggio che indica degli errori se non va a buon fine la raccolta
	 * Restituisce i CFU rimasti se effettivamente sposta l'attrezzo da stanza a borsa
	 * @param Stringa con nome dell'attrezzo da prendere
	 * @param partita 
	 * @return Stringa con descrizione di cosa è successo
	 */
	public String prendi(String attrezzo, Partita partita){
		String output;
		output=partita.getPlayer().PrendiAttrezzo(attrezzo, partita.getStanzaCorrente());
		Boolean Bool =output.equals("Il tuo attrezzo non esiste");
		Boolean Bool2 =output.equals("La tua borsa è troppo pesante prova a lasciare qualche attrezzo a terra");
		if((Bool||Bool2)==true) {
			return output;
		}
		else {
			partita.getPlayer().setCfu(partita.getPlayer().getCfu()-1);
			return (output+"\nCFU rimasti: "+ partita.getPlayer().getCfu());
		}		
	} 

	/**
	 * Riceve come parametro il nome dell'attrezzo cercato e la partita a cui si sta giocando.
	 * Prende un attrezzo dalla borsa e lo sposta nella stanza.
	 * Restituisce un messaggio che indica degli errori se non va a buon fine l'azione
	 * Restituisce i CFU rimasti e un messaggio che indica la buona riuscita se effettivamente sposta l'attrezzo da borsa a stanza 
	 * @param Stringa con nome dell'attrezzo da posare
	 * @param partita 
	 * @return Stringa con descrizione di cosa è successo
	 */
	public String rimuovi(String attrezzo, Partita partita){ 
		String output;
		output=partita.getPlayer().RemoveAttrezzo(attrezzo, partita.getStanzaCorrente());
		if((!output.equals("Oggetto o Stanza innesistente")&&(!output.equals("C'è già troppo disordine in questa stanza!\nNon vorrei crearne altro")))&&(!output.equals("Ho toccato il fondo, ma "+attrezzo+" non l'ho trovato")))  {
			partita.getPlayer().setCfu(partita.getPlayer().getCfu()-1);
			return (output+"\nCFU rimasti: "+ partita.getPlayer().getCfu());
		}
		else 
			return output;
	}
}
