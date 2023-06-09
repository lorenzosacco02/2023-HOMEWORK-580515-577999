package it.uniroma3.diadia.ambienti;

import java.util.HashMap;
import java.util.Map;

import it.uniroma3.diadia.attrezzi.*;

/**
 * Una semplice classe che modella un Labirinto. Imposta la sua entrata, la sua
 * uscita i collegamenti fra le stanze e gli attrezzi al loro interno.
 *
 * @author 580515, 577999
 * @see Stanza,StanzaBuia,StanzaBloccata,Attrezzo
 * @version 2.0
 */

public class Labirinto {
	
	/**
	 * crea entrata e uscita del labirinto 
	 */
	private Stanza entrata;
	private Stanza uscita;

	/** 
	 * crea gli attrezzi 
	 */
	public Attrezzo lanterna = new Attrezzo("lanterna", 3);
	public Attrezzo osso = new Attrezzo("osso", 1);
	public Attrezzo spada = new Attrezzo("spada", 5);
	public Attrezzo cassa = new Attrezzo("cassa", 11);

	/**
	 *  crea stanze del labirinto 
	 */
	public Stanza atrio = new Stanza("Atrio");
	public Stanza aulaN11 = new Stanza("Aula N11");
	public Stanza aulaN10 = new Stanza("Aula N10");
	public Stanza laboratorio = new Stanza("Laboratorio Campus");
	public Stanza biblioteca = new Stanza("Biblioteca");
	public Stanza alle_scale_piace_cambiare = new StanzaMagica("2 oggetti per la magia",2);
	
	/**
	 * crea un labirinto con un'entrata un'uscita e un percorso
	 */
	public Labirinto() {
		this.init(atrio, biblioteca);
		this.genera_percorso();
	}
	
	public Labirinto(Stanza entrata, Stanza uscita){
		this.init(entrata, uscita);
	}
	
	/**
	 * imposta l'entrata e l'uscita
	 * @param entrata desiderata
	 * @param uscita desiderata
	 */
	private void init(Stanza entrata, Stanza uscita) {
		this.entrata = entrata;
		this.uscita = uscita;
	}
	
	/**
	 * genera collegamento fra le stanze
	 */
	private void genera_percorso() {
		/* crea collegamenti fra stanza del labirinto */
		atrio.impostaStanzaAdiacente("nord", biblioteca);
		atrio.impostaStanzaAdiacente("est", aulaN11);
		atrio.impostaStanzaAdiacente("sud", aulaN10);
		atrio.impostaStanzaAdiacente("ovest", laboratorio);
		aulaN11.impostaStanzaAdiacente("est", laboratorio);
		aulaN11.impostaStanzaAdiacente("ovest", atrio);
		aulaN10.impostaStanzaAdiacente("nord", atrio);
		aulaN10.impostaStanzaAdiacente("est", aulaN11);
		aulaN10.impostaStanzaAdiacente("ovest", laboratorio);
		laboratorio.impostaStanzaAdiacente("est", atrio);
		laboratorio.impostaStanzaAdiacente("sud", alle_scale_piace_cambiare);
		alle_scale_piace_cambiare.impostaStanzaAdiacente("nord", laboratorio);
		laboratorio.impostaStanzaAdiacente("ovest", aulaN11);
		biblioteca.impostaStanzaAdiacente("sud", atrio);
		this.inserisci_attrezzi(atrio, lanterna);
		this.inserisci_attrezzi(aulaN10, osso);
		this.inserisci_attrezzi(aulaN10, spada);
		this.inserisci_attrezzi(aulaN10, cassa);
	}
	
	/**
	 * Mette gli attrezzi nelle stanze
	 * @param stanza dove si vuole inserire attrezzo
	 * @param attrezzo che voglio inserire
	 */
	private void inserisci_attrezzi(Stanza stanza, Attrezzo attrezzo) {
		stanza.addAttrezzo(attrezzo);
	}

	/**
	 * Restituisce l'entrata del labirinto
	 * @return entrata del labirinto
	 */
	public Stanza getEntrata() {
		return this.entrata;
	}

	/**
	 * Restituisce stanza vincente del labirinto
	 * @return uscita del labirinto
	 */
	public Stanza getUscita() {
		return this.uscita;
	}
	
	public static class LabirintoBuilder {
		private String ingresso;
		private String uscita;
		private String ultimaAggiunta;
		private Map<String,Stanza> MappaStanze;

		LabirintoBuilder(){
			this.ingresso=null;
			this.uscita=null;
			this.MappaStanze = new HashMap<>();
		}

		public LabirintoBuilder addAttrezzo(String NomeAttrezzo,int peso){
			this.MappaStanze.get(this.ultimaAggiunta).addAttrezzo(new Attrezzo(NomeAttrezzo,peso));
			return this;
		}
		
		public LabirintoBuilder addPersonaggio(String tipo, String nome, String presentazione, String attr, String regalo,int peso1,int peso2){
			this.MappaStanze.get(this.ultimaAggiunta).addPersonaggio(tipo);
			this.MappaStanze.get(this.ultimaAggiunta).getPersonaggio().setter(nome, presentazione, new Attrezzo(attr, peso1), new Attrezzo(regalo, peso2));
			return this;
		}
		

		public LabirintoBuilder addStanzaIniziale(String NomeIngresso){
			this.ingresso=NomeIngresso;
			this.addStanza(NomeIngresso);
			return this;
		}

		public LabirintoBuilder addStanzaVincente(String NomeUscita){
			this.uscita=NomeUscita;
			this.addStanza(NomeUscita);
			return this;
		}
		
		public LabirintoBuilder addAdiacenza(String NomeStanza1,String NomeStanza2, String Direzione){
			if(!this.MappaStanze.containsKey(NomeStanza1)){
				this.addStanza(NomeStanza1);
			}
			if(!this.MappaStanze.containsKey(NomeStanza2)){
				this.addStanza(NomeStanza2);
			}
			this.MappaStanze.get(NomeStanza1).impostaStanzaAdiacente(Direzioni.valueOf(Direzione).toString(), this.MappaStanze.get(NomeStanza2));
			this.MappaStanze.get(NomeStanza2).impostaStanzaAdiacente(Direzioni.getOpposta(Direzioni.valueOf(Direzione)).toString(), this.MappaStanze.get(NomeStanza1));
			return this;
		}

		public LabirintoBuilder addStanza(String NomeStanza){
			this.addStanzaStandard(new Stanza(NomeStanza));
			return this;
		}
		
		public LabirintoBuilder addStanzaMagica(String NomeStanza, int sogliaMagica){
			this.addStanzaStandard(new StanzaMagica(NomeStanza,sogliaMagica));
			return this;
		}
		
		public LabirintoBuilder addStanzaBuia(String NomeStanza, String attrezzo){
			this.addStanzaStandard(new StanzaBuia(NomeStanza,attrezzo));
			return this;
		}
		
		public LabirintoBuilder addStanzaBloccata(String NomeStanza,String direzioni, String oggetti){
			StanzaBloccata attuale=new StanzaBloccata(NomeStanza,direzioni,oggetti);
			attuale.impostaDirezioneBloccata(direzioni);
			this.addStanzaStandard(attuale);
			return this;
		}
		public LabirintoBuilder addStanzaMagica(String NomeStanza){
			this.addStanzaStandard(new StanzaMagica(NomeStanza));
			return this;
		}
		
		public LabirintoBuilder addStanzaBuia(String NomeStanza){
			this.addStanzaStandard(new StanzaBuia(NomeStanza));
			return this;
		}
		
		public LabirintoBuilder addStanzaBloccata(String NomeStanza){
			this.addStanzaStandard(new StanzaBloccata(NomeStanza));
			return this;
		}
		private void addStanzaStandard(Stanza NomeStanza){
			if(this.ingresso==null) {
				this.addStanzaIniziale(NomeStanza.getNome());
			}
			else{
				if(!this.MappaStanze.containsKey(NomeStanza.getNome())){
					this.ultimaAggiunta=NomeStanza.getNome();
					this.MappaStanze.put(NomeStanza.getNome(),NomeStanza);
				}
			}
		}

		public Labirinto getLabirinto(){
			Labirinto LabirintoCostruito = new Labirinto(this.MappaStanze.get(this.ingresso),this.MappaStanze.get(this.uscita));
			return LabirintoCostruito;
		}

		/*private String getDirezioneOpposta(String stringa) {
			switch(stringa){
			case "nord":
				return "sud";
			case "sud":
				return "nord";
			case "ovest":
				return "est";
			case "est":
				return "ovest";
			default:
				return null;
			}
		}*/

		public Map<String, Stanza> getMappaStanze() {
			return MappaStanze;
		}
	}
}
