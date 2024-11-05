package jeu;

import cartes.Carte;

public class Joueur {
	private String name;
	private ZoneDeJeu zone;
	private MainJoueur main;
	
	public Joueur(String name, ZoneDeJeu zone) {
		this.name = name;
		this.zone = zone;
		this.main = new MainJoueur();
	}
	
	public String toString() {
		return name;
	}
	
	public String getName() {
		return name;
	}

	public void donner(Carte c) {
		main.prendre(c);
	}
	
	public Carte prendreCarte(Sabot<Carte> sabot) {
		if (sabot.estVide()) {
			return null;
		} else {
			Carte c = sabot.piocher();
			donner(c);
	
			return c;
		}
	}	
	
	public int donnerKmParcourus() {
		return zone.donnerKmParcourus();
	}
	
	@Override
	public boolean equals(Object joueur) {
		if(joueur instanceof Joueur j) {
			return name.equals(j.getName());
		}
		return false;
	}
}
