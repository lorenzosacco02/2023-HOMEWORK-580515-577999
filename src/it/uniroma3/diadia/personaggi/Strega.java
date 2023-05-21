package it.uniroma3.diadia.personaggi;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.ComparatoreStanzePerNumeroAttrezzi;
import it.uniroma3.diadia.ambienti.Stanza;


public class Strega extends Personaggio{
	private static final String PresentazioneStrega = "Ciao sono la strega, hai fatto bene a salutarmi non te ne pentirai";

	public Strega(String nome) {
		super(nome, PresentazioneStrega);
	}

	public Strega(String nome, String presentaz) {
		super(nome, presentaz);
	}

	@Override
	public String agisci(Partita partita) {
		ComparatoreStanzePerNumeroAttrezzi cmp=new ComparatoreStanzePerNumeroAttrezzi();
		Collection<Stanza> collectionStanze = partita.getStanzaCorrente().getMapStanzeAdiacenti().values();
		LinkedList<Stanza> listaStanze=new LinkedList<>(collectionStanze);
		if(listaStanze.size()!=0) {
			Collections.sort(listaStanze,cmp);
			if(this.haSalutato()){
				partita.setStanzaCorrente(listaStanze.getFirst());	
				return "Sei stato bravo ora divertiti ti ho trasportato in un posto pieno di attrezzi";
			}
			else {
				partita.setStanzaCorrente(listaStanze.getLast());
				return "Impara l'educazione e saluta la prossima volta! ora ti spedisco in una stanza desolata";
			}
		}
		else
			return "Non ci sono uscite da questa Stanza non posso trasportarti da nessuna parte";
	}
}
