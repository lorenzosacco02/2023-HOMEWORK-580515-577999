package it.uniroma3.diadia;

/**
 * Classe principale di diadia, un semplice gioco di ruolo ambientato al dia.
 * Per giocare crea un'istanza di questa classe e invoca il metodo gioca
 *
 * Questa e' la classe principale crea e istanzia tutte le altre
 *
 * @author 580515, 577999 (da un'idea di Michael Kolling and David J. Barnes)
 * @version 1.0
 */

public class DiaDia {

	public static void main(String[] argc) {
		DiaDia gioco = new DiaDia();
		gioco.gioca(gioco.console);
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

	/**
	 * Inizializza una nuova Partita e una Console
	 */
	public DiaDia() {
		this.partita = new Partita();
		this.console = new IOConsole();
	}

	/**
	 * Permette di interagire con l'utente prendendo messagggi in input.
	 * riceve come parametro la console per interagire con l'utente e invoca il metodo processaIstruzione
	 * Finisce la partita dal momento in cui si vince o si desidera smettere di giocare.
	 * @param console
	 */
	public void gioca(IOConsole console) {
		String istruzione;
		String[] istruzioni;
		console.mostraMessaggio(MESSAGGIO_BENVENUTO);
		do {
			istruzione = console.leggiRiga();
			istruzione = istruzione.toLowerCase();
			istruzioni = istruzione.split("\\W");
		} while (!processaIstruzione(istruzioni) && !this.partita.isFinita());
		Comando comandoFine = new Comando();
		this.console.mostraMessaggio(comandoFine.fine());
	}

	/**
	 * Metodo che gestisce lo svolgimento del gioco 
	 * processa le istruzioni che riceve in imput invocando i metodo necessari
	 * gestisce anche interazione con utente stampando messaggi.
	 * @param istruzione da eseguire
	 * @return true solo se partita è vinta o finita, false altrimenti
	 */
	private boolean processaIstruzione(String[] istruzione) {
		Comando comandoDaEseguire = new Comando();
		switch (istruzione[0]) {
		case "fine":
			return true;
		case "vai":
			if (istruzione.length > 1) {
				this.console.mostraMessaggio(comandoDaEseguire.vai(istruzione[1], this.partita));
			} else {
				this.console.mostraMessaggio("Dove vuoi andare?");
				this.console.mostraMessaggio(comandoDaEseguire.vai(this.console.leggiRiga(), this.partita));
			}
			break;
		case "aiuto":
			if (istruzione.length > 1)
				this.console.mostraMessaggio(comandoDaEseguire.aiuto(istruzione[1]));
			else
				this.console.mostraMessaggio(comandoDaEseguire.aiuto(""));
			break;
		case "prendi":
			if (istruzione.length > 1)
				this.console.mostraMessaggio(comandoDaEseguire.prendi(istruzione[1], this.partita));
			else {
				this.console.mostraMessaggio("Cosa vuoi prendere?");
				this.console.mostraMessaggio(comandoDaEseguire.prendi(this.console.leggiRiga(), this.partita));
			}
			break;
		case "posa":
			if (istruzione.length > 1)
				this.console.mostraMessaggio(comandoDaEseguire.rimuovi(istruzione[1], this.partita));
			else {
				this.console.mostraMessaggio("Cosa vuoi lasciare?");
				this.console.mostraMessaggio(comandoDaEseguire.rimuovi(this.console.leggiRiga(), this.partita));
			}
			break;
		case "borsa":
			this.console.mostraMessaggio(this.partita.getPlayer().getBorsa().toString());
			break;
		default:
			this.console.mostraMessaggio("Comando sconosciuto");
			break;
		}
		if (this.partita.vinta()) {
			this.console.mostraMessaggio("WOW HAI VINTO!");
			return true;
		} else
			return false;
	}
}