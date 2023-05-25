package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Cane extends Personaggio{
	private static final Attrezzo CIBO_PREFERITO_DEFAULT = new Attrezzo("Osso",1);
	private static final Attrezzo ATTREZZO_DA_DONARE_DEFAULT = new Attrezzo("passepartout",0);
	private static final String PRESENTAZIONE_CANE_DEFAULT = "Wof Wof Wof Wof...WofWof";
	private static final String NOME_CANE_DEFAULT = "Wof Wof Wof Wof...WofWof";
	private Attrezzo ciboPreferito;
	private Attrezzo AttrezzoDaDonare;
	
	public Cane(){
		this(NOME_CANE_DEFAULT, CIBO_PREFERITO_DEFAULT, PRESENTAZIONE_CANE_DEFAULT, ATTREZZO_DA_DONARE_DEFAULT);
	}
	public Cane(String nome) {
		this(nome, CIBO_PREFERITO_DEFAULT, PRESENTAZIONE_CANE_DEFAULT, ATTREZZO_DA_DONARE_DEFAULT);
	}

	public Cane(String nome, Attrezzo cibo) {
        this(nome, cibo,PRESENTAZIONE_CANE_DEFAULT, ATTREZZO_DA_DONARE_DEFAULT);
	}

	public Cane(String nome, Attrezzo cibo, String presentaz) {
		this(nome, cibo, presentaz, ATTREZZO_DA_DONARE_DEFAULT);
	}
    public Cane(String nome, Attrezzo cibo, String presentaz,Attrezzo attrezzo) {
		super(nome, presentaz);
		this.ciboPreferito=cibo;
        this.AttrezzoDaDonare=attrezzo;
	}
	public void setRegalo(Attrezzo Regalo){
		if(Regalo!=null)
		this.AttrezzoDaDonare=Regalo;
	}
	public void setCiboPreferito(Attrezzo Cibo){
		if(Cibo!=null)
		this.ciboPreferito=Cibo;
	}
	@Override
	public String agisci(Partita partita) {
		if(partita!=null && partita.getPlayer()!=null) {
			partita.getPlayer().setCfu(partita.getPlayer().getCfu()-1);
			return "Il cane ti ha morso";
		}
		else return "Qualcosa è andato storto in cane";
	}

	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		if(attrezzo.equals(this.ciboPreferito)){
            if(partita!=null && partita.getStanzaCorrente()!=null) {
            	partita.getStanzaCorrente().addAttrezzo(AttrezzoDaDonare);
            	return "WOOOOOOF";
            }
		}else{
          return this.agisci(partita);
        }
		return "Qualcosa è andato storto in cane";
	}
	@Override
	public void setter(String nome, String presentazione, Attrezzo attrezzo, Attrezzo preferito) {
		this.setNome(nome);
		this.setPresentazione(presentazione);
		this.setRegalo(attrezzo);
		this.setCiboPreferito(preferito);
	}
}
