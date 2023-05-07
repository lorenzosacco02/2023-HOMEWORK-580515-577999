package it.uniroma3.diadia;

import java.util.Scanner;

/**
 * Questa classe serve a gestire la stampa e la lettura.
 * Passare sempre per la console se si vuole stampare o leggere un messaggio.
 * Questa classe implementa l'interface IO
 *
 * @author  580515, 577999
 * @version 2.0
 */

public class IOConsole implements IO {
	
	/**
	 * mostra il messaggio che riceve come parametro
	 * @param Stringa che bisogna mostrare
	 */
	@Override
	public void mostraMessaggio(String msg) {
		System.out.println(msg);
	}
	
	/**
	 * restituisce una stringa con il messaggio scritto dall'utente
	 * @return Stringa con messaggio letto
	 */
	@Override
	public String leggiRiga() {
		Scanner scannerDiLinee = new Scanner(System.in);
		String riga = scannerDiLinee.nextLine();
		return riga;
	}
}