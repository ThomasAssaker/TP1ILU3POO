package cartes;

import jeu.ZoneDeJeu;

public abstract class Bataille extends Carte { // Héritage de Carte
    protected String nom; 
    protected Type type;  

    public Bataille(String nom, Type type) {
        super(type, nom); // Appel du constructeur de Carte
        this.nom = nom;
        this.type = type;
    }

    public String getNom() {
        return nom;
    }

    public Type getType() {
        return type;
    }

    public abstract boolean estDepotAutorise(ZoneDeJeu zoneDeJeu); // Méthode abstraite

    @Override
    public String toString() {
        return type + ": " + nom; // Représentation en chaîne de la bataille
    }
}
