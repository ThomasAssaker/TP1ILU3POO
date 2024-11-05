package jeu;

import java.util.Arrays;
import java.util.List;

import cartes.Carte;
import cartes.JeuDeCartes;
import utils.GestionCartes;

public class Jeu {
	
	private Sabot<Carte> sabot;
	
	public Jeu() {
		JeuDeCartes jeu = new JeuDeCartes();
		List<Carte> liste = Arrays.asList(jeu.donnerCartes());
		liste = GestionCartes.melanger(liste);
		
		this.sabot = new Sabot<>(liste.toArray(jeu.donnerCartes()));
	}
}
