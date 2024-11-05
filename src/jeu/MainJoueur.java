package jeu;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import cartes.Carte;

public class MainJoueur implements Iterable<Carte>{
	private List<Carte> main = new ArrayList<>();
	
	public void prendre(Carte c) {
		main.add(c);
	}
	
	public void jouer(Carte c) {
		if(main.contains(c)) {
			main.remove(c);
		}
	}
	
	@Override
	public String toString() {
		return main.toString();
	}

	@Override
	public Iterator<Carte> iterator() {
		return main.iterator();	
	}
}
