package it.uniroma3.diadia.ambienti;

import java.util.Comparator;

public class ComparatoreStanzePerNumeroAttrezzi implements Comparator<Stanza>{

	@Override
	public int compare(Stanza s1, Stanza s2) {
		if(s1==null&&s2==null) return 0;
		else{
		if(s1==null) return -1;
		if(s2==null) return  1;
		return s1.getListaDiAttrezzi().size()-s2.getListaDiAttrezzi().size();
		}
	}
}
