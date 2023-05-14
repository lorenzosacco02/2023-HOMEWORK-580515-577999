package it.uniroma3.diadia;
import it.uniroma3.diadia.comandi.*;
/**
 * Classe principale di diadia, un semplice gioco di ruolo ambientato al dia.
 * Per giocare crea un'istanza di questa classe e invoca il metodo gioca
 *
 * Questa e' la classe principale crea e istanzia tutte le altre
 *
 * @author 580515, 577999 (da un'idea di Michael Kolling and David J. Barnes)
 * @version 2.0
 */

public class DiaDia {

	public static void main(String[] argc) {
		IO io = new IOConsole();
		DiaDia gioco = new DiaDia(io);
		gioco.gioca();
	}
	
	static final private String MESSAGGIO_BENVENUTO = ""
			+ "Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n"
			+ "Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"
			+ "I locali sono popolati da strani personaggi, " + "alcuni amici, altri... chissa!\n"
			+ "Ci sono attrezzi che potrebbero servirti nell'impresa:\n"
			+ "puoi raccoglierli, usarli, posarli quando ti sembrano inutili... ma fai attenzione al peso!\n"
			+ "puoi anche regalarli se pensi che possano ingraziarti qualcuno\n"
			+ "Ti trovi nell'atrio e la tua borsa è vuota, puoi andare nella direzione che vuoi\n"
			+ "in fondo alla stanza vedi una lanterna il suo peso è 3kg.\n\n"
			+ "Per conoscere le istruzioni usa il comando 'aiuto'.";

	private Partita partita;
	private IOConsole console;
	private FabbricaDiComandi Fabbrica;

	/**
	 * Inizializza una nuova Partita e una Console
	 */
	public DiaDia(IO io) {
		this.partita = new Partita();
		this.console = (IOConsole) io;
		this.Fabbrica= new FabbricaDiComandiMap();
	}

	/**
	 * Permette di interagire con l'utente prendendo messagggi in input.
	 * inizializza una Fabbrica di comandi grazie alla quale costruisce il comando
	 * invoca il metodo processaIstruzione
	 * Finisce la partita dal momento in cui si vince o si desidera smettere di giocare.
	 */
	public void gioca() {
		String istruzione;
		this.console.mostraMessaggio(MESSAGGIO_BENVENUTO);
		Comando comandoCostruito;
		do {
			istruzione = this.console.leggiRiga();
			comandoCostruito=Fabbrica.costruisciComando(istruzione);
		} 
		while (!processaIstruzione(comandoCostruito, this.console) && !this.partita.isFinita());
		if(this.partita.getPlayer().getCfu()==0) {
			this.console.mostraMessaggio("\nOh no hai perso mi dispiace,");
		}
		this.console.mostraMessaggio("Grazie di aver giocato!");
	}

	/**
	 * Metodo che gestisce lo svolgimento del gioco 
	 * processa le istruzioni che riceve in input invocando i metodo necessari
	 * gestisce anche interazione con utente stampando messaggi.
	 * @param istruzione da eseguire, console di gioco
	 * @return true solo se partita è vinta o finita, false altrimenti
	 */
	private boolean processaIstruzione (Comando comandoCostruito, IOConsole console) {
		String stampa = comandoCostruito.esegui(this.partita);
		String Parametro;

		switch(stampa){
		case "Che attrezzo vuoi posare?":
			do{
				console.mostraMessaggio("Che oggetto vuoi posare?");
				Parametro = console.leggiRiga();
			}
			while(Parametro.equals(""));
			comandoCostruito.setParametro(Parametro);
			stampa = comandoCostruito.esegui(this.partita);
			break;

		case "Che attrezzo vuoi prendere?":
			do{
				console.mostraMessaggio("Che oggetto vuoi prendere?");
				Parametro = console.leggiRiga();
			}
			while(Parametro.equals(""));
			comandoCostruito.setParametro(Parametro);
			stampa = comandoCostruito.esegui(this.partita);
			break;

		case "Dove vuoi andare?\n":
			do{
				console.mostraMessaggio("Dove vuoi andare?");
				Parametro = console.leggiRiga();
			}
			while(Parametro.equals(""));
			comandoCostruito.setParametro(Parametro);
			stampa = comandoCostruito.esegui(this.partita);
			break;
		case "Su cosa vuoi avere le informazioni?":
			do{
				console.mostraMessaggio("Su cosa vuoi avere le informazioni?");
				Parametro = console.leggiRiga();
			}
			while(Parametro.equals(""));
			comandoCostruito.setParametro(Parametro);
			stampa = comandoCostruito.esegui(this.partita);
			break;

		case "Grazie di aver giocato!":
			return true;
		}
		this.console.mostraMessaggio(stampa);
		if (this.partita.vinta()) {
			this.console.mostraMessaggio("WOW HAI VINTO!");
			return true;
		}
		return false;
	}
}