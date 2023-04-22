package it.uniroma3.diadia;

import java.util.Scanner;

/**
 * Questa classe serve a gestire la stampa e la lettura.
 * Passare sempre per la console se si vuole stampare o leggere un messaggio.
 *
 * @author  580515, 577999
 * @see Stanza
 * @version 1.0
 */

public class IOConsole implements IO {
	
	@Override
	public void mostraMessaggio(String msg) {
		System.out.println(msg);
	}
	
	@Override
	public String leggiRiga() {
		Scanner scannerDiLinee = new Scanner(System.in);
		String riga = scannerDiLinee.nextLine();
		return riga;
	}
}