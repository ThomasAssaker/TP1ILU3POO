package jeu;

import cartes.Carte;

public class Joueur {
    private String nom;
    private ZoneDeJeu zoneDeJeu;

    // Constructeur
    public Joueur(String nom) {
        this.nom = nom;
        this.zoneDeJeu = new ZoneDeJeu();
    }

    public String getNom() {
        return nom;
    }

    public ZoneDeJeu getZoneDeJeu() {
        return zoneDeJeu;
    }

    // Méthode pour déposer une carte dans la zone de jeu
    public void deposerCarte(Carte carte) {
        zoneDeJeu.deposer(carte);
    }

    // Méthode pour obtenir le total de km parcourus
    public int donnerKmParcourus() {
        return zoneDeJeu.donnerKmParcourus();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Joueur autreJoueur = (Joueur) obj;
        return nom.equals(autreJoueur.nom);
    }

    @Override
    public String toString() {
        return nom;
    }
    
    public boolean estDepotAutorise(Carte carte) {
        return zoneDeJeu.estDepotAutorise(carte); // Appelle la méthode de ZoneDeJeu
    }

}

