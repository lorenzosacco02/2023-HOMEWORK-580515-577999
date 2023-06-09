package it.uniroma3.diadia.personaggi;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.ComparatoreStanzePerNumeroAttrezzi;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;


public class Strega extends Personaggio{
	private static final String PRESENTAZIONE_STREGA_DEFAULT = "Hai fatto bene a salutarmi non te ne pentirai";
	private static final String NOME_STREGA_DEFAULT = "Wendy";
	
	public Strega() {
		this(NOME_STREGA_DEFAULT);
	}
	
	public Strega(String nome) {
		super(nome, PRESENTAZIONE_STREGA_DEFAULT);
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
				partita.setStanzaCorrente(listaStanze.getLast());	
				return "Sei stato bravo ora divertiti ti ho trasportato in un posto pieno di attrezzi";
			}
			else {
				partita.setStanzaCorrente(listaStanze.getFirst());
				return "Impara l'educazione e saluta la prossima volta! ora ti spedisco in una stanza desolata";
			}
		}
		else
			return "Non ci sono uscite da questa Stanza non posso trasportarti da nessuna parte";
	}

	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		Random random=new Random();
		int rand=random.nextInt(4);
		StringBuilder output=new StringBuilder("");
		switch(rand){
            case 0 :
				output.append("ah ah ah ah ah");
			break;
            case 1 : 
				output.append("hi hi hi hi");
			break;
			case 2 : 
				output.append("ma fammi il piacere a cosa mi serve "+attrezzo.getNome());
			break;
			case 3 : 
				output.append("non sono tanto idiota come il mago");
			break;
			case 4 : 
				output.append("AHAHAHAAHAHHAHAHAHAHAHAHAHAHAAHAHAHAHAHAHAH!!!!!!!!!");
			break;
			default:
				output.append("Eeeeeeeh Volevi!\nguarda che faccia non se l'aspettava");
			break;
        }
		return output.toString();
	}

	@Override
	public void setter(String nome, String presentazione, Attrezzo attrezzo, Attrezzo preferito) {
		this.setNome(nome);
		this.setPresentazione(presentazione);
	}
}
