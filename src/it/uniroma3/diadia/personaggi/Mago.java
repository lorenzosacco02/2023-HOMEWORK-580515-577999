package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Mago extends Personaggio{
	private Attrezzo attrezzoRegalo;
	private static final Attrezzo ATTREZZO_DEFAULT = new Attrezzo("Bacchetta",1);
	private static final String NOME_MAGO_DEFAULT = "Merlino";
	private static final String PRESENTAZIONE_MAGO = "Mi piace fare magie gioca con me e lo scoprirai";
	
	public Mago() {
		this(NOME_MAGO_DEFAULT,ATTREZZO_DEFAULT);
	}
	public Mago(String nome) {
		this(nome, ATTREZZO_DEFAULT);
	}
	
	public Mago(String nome, Attrezzo attrezzo) {
		super(nome, PRESENTAZIONE_MAGO);
		this.attrezzoRegalo=attrezzo;
	}
	
	public Mago(String nome, Attrezzo attrezzo, String presentaz) {
		super(nome, presentaz);
		this.attrezzoRegalo=attrezzo;
	}
	public void setRegalo(Attrezzo Regalo){
		if(Regalo!=null)
		this.attrezzoRegalo=Regalo;
	}
	
	@Override
	public String agisci(Partita partita) {
		if(partita.getPlayer().getBorsa().addAttrezzo(attrezzoRegalo)==true)
		return "Da un'occhiata alla tua borsa giocatore ho fatto una magia!";
		else return "Torna da me quando sarai più leggero e verrai ricompensato";
	}

	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		if(attrezzo!=null&&partita!=null) {
			if(partita.getPlayer().getBorsa().removeAttrezzo(attrezzo.getNome())!=null) {
				attrezzo.setPeso(attrezzo.getPeso()/2);
				partita.getStanzaCorrente().addAttrezzo(attrezzo);
				return "Cerca nella stanza, fidati ;)";
			}
			else return "Purtroppo non posso aiutarti non ho trovato il tuo attrezzo neanche con l'aiuto della mia magia";
		}
		return "Mi stai prendendo in giro, non è divertente";
	}
	
	@Override
	public void setter(String nome, String presentazione, Attrezzo attrezzo, Attrezzo preferito) {
		this.setNome(nome);
		this.setPresentazione(presentazione);
		this.setRegalo(attrezzo);
	}
}
