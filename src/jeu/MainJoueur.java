package jeu;

import cartes.Carte;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MainJoueur implements Iterable<Carte> {
    private List<Carte> cartes; // Liste pour stocker les cartes en main

    // Constructeur
    public MainJoueur() {
        this.cartes = new ArrayList<>(); // Initialisation de la liste de cartes
    }

    // Méthode pour ajouter une carte à la main
    public void prendre(Carte carte) {
        cartes.add(carte); // Ajoute la carte à la liste
    }

    // Méthode pour jouer (supprimer) une carte de la main
    public void jouer(Carte carte) {
        // Assure que la carte est présente dans la main
        assert cartes.contains(carte) : "La carte n'est pas dans la main"; 
        cartes.remove(carte); // Supprime la carte de la liste
    }

    // Méthode toString pour afficher la main du joueur
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Main du joueur :\n");
        for (Carte carte : cartes) {
            sb.append(carte).append("\n"); // Ajoute chaque carte à la chaîne de caractères
        }
        return sb.toString(); // Renvoie la chaîne de caractères formée
    }

    // Méthode pour itérer sur les cartes
    @Override
    public Iterator<Carte> iterator() {
        return cartes.iterator(); // Renvoie un itérateur pour la liste de cartes
    }
}
