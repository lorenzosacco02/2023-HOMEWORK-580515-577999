package it.uniroma3.diadia;
import it.uniroma3.diadia.comandi.AbstractComando;
import it.uniroma3.diadia.comandi.FabbricaDiComandi;
import it.uniroma3.diadia.comandi.FabbricaDiComandiRiflessiva;
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
	public final static String LABIRINTO_DEF=Configuratore.getLabirinto();
	public static void main(String[] argc) {
		IO io = new IOConsole();
		DiaDia gioco = new DiaDia(io);
		gioco.gioca();
	}
	
	static final private String MESSAGGIO_BENVENUTO = Configuratore.getMessaggioDiBenvenuto();
			

	private Partita partita;
	private IOConsole console;
	private FabbricaDiComandi Fabbrica;

	/**
	 * Inizializza una nuova Partita e una Console
	 */
	public DiaDia(IO io) {
		this.partita = new Partita(LABIRINTO_DEF);
		this.console = (IOConsole) io;
		this.Fabbrica= new FabbricaDiComandiRiflessiva();
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
		AbstractComando comandoCostruito;
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
	private boolean processaIstruzione (AbstractComando comandoCostruito, IOConsole console) {
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