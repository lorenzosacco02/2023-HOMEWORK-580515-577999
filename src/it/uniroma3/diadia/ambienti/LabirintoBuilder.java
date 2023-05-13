package it.uniroma3.diadia.ambienti;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class LabirintoBuilder {
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
		this.MappaStanze.get(NomeStanza1).impostaStanzaAdiacente(Direzione, this.MappaStanze.get(NomeStanza2));
		this.MappaStanze.get(NomeStanza2).impostaStanzaAdiacente(this.getDirezioneOpposta(Direzione), this.MappaStanze.get(NomeStanza1));
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

	private String getDirezioneOpposta(String stringa) {
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
	}

	/*public List<String> getMappaStanze() {
		return this.MappaStanze.keySet().stream().toList();
	}*/

	public Map<String, Stanza> getMappaStanze() {
		return MappaStanze;
	}

	
}
