package jeu;

import cartes.Attaque;
import cartes.Bataille;
import cartes.Borne;
import cartes.Carte;
import cartes.Cartes;
import cartes.Limite;
import cartes.Parade;
import cartes.Type;


import java.util.ArrayList;
import java.util.List;

public class ZoneDeJeu {
    private List<Carte> pileLimites;  // Pile pour les limites/fin de limites de vitesse
    private List<Carte> pileBataille; // Pile pour les attaques et parades
    private List<Carte> bornes;        // Collection de bornes
    private int totalKmParcourus;      // Total des km parcourus

    // Constructeur initialisant des listes vides pour chaque zone
    public ZoneDeJeu() {
        this.pileLimites = new ArrayList<>();
        this.pileBataille = new ArrayList<>();
        this.bornes = new ArrayList<>();
        this.totalKmParcourus = 0;
    }

    // Méthode pour ajouter une carte dans la pile des limites/fin de limites de vitesse
    public void ajouterLimite(Carte carte) {
        pileLimites.add(carte);
    }

    // Méthode pour ajouter une carte dans la pile de bataille (attaques ou parades)
    public void ajouterBataille(Carte carte) {
        pileBataille.add(carte);
    }

    // Méthode pour ajouter une borne
    public void ajouterBorne(Carte carte) {
        bornes.add(carte);
        // Extraire les kilomètres de la carte et les ajouter au total
        int km = Integer.parseInt(carte.getNom().replaceAll("[^0-9]", ""));
        totalKmParcourus += km; // Ajouter les km de la borne au total
    }

    // Méthode pour obtenir la dernière carte ajoutée dans la pile des limites/fin de limites
    public Carte getDerniereLimite() {
        if (pileLimites.isEmpty()) {
            return null; // Pas de limite si la liste est vide
        }
        return pileLimites.get(pileLimites.size() - 1);
    }

    // Méthode pour obtenir la dernière carte dans la pile de bataille
    public Carte getDerniereBataille() {
        if (pileBataille.isEmpty()) {
            return null; // Pas d'attaque ou parade si la liste est vide
        }
        return pileBataille.get(pileBataille.size() - 1);
    }

    // Méthode pour obtenir le nombre total de bornes
    public int getTotalBornes() {
        return bornes.size();
    }

    // Méthode pour renvoyer la limite de vitesse en cours
    public int donnerLimitationVitesse() {
        if (pileLimites.isEmpty() || getDerniereLimite().getType() == Type.BOTTE) {
            return 200; // Limite par défaut
        }
        return 50; // Limite normale
    }

    // Méthode pour renvoyer le total de km parcourus
    public int donnerKmParcourus() {
        return totalKmParcourus;
    }

    // Méthode pour déposer une carte dans la zone
    public void deposer(Carte carte) {
        if (carte.getType() == Type.BORNE) {
            ajouterBorne(carte);
        } else if (carte.getType() == Type.ATTAQUE || carte.getType() == Type.PARADE) {
            ajouterBataille(carte);
        } else if (carte.getType() == Type.BOTTE) {
            ajouterLimite(carte);
        }
    }

    // Méthode pour vérifier si une borne peut être déposée
    public boolean peutAvancer() {
        // Vérifie si la pile de bataille est non vide et si le sommet est un feu vert
        if (!pileBataille.isEmpty() && getDerniereBataille().equals(Cartes.FEU_VERT)) {
            return true; // Une borne peut être déposée
        }
        return false; // Une borne ne peut pas être déposée
    }
    
    public boolean estDepotFeuVertAutorise() {
        // Vérifie si la pile de batailles est vide
        if (pileBataille.isEmpty()) {
            return true; // Le dépôt est autorisé si la pile est vide
        }

        // Vérifie la dernière carte de la pile de batailles
        Carte derniereCarte = getDerniereBataille();
        
        // Vérifie si la dernière carte est un feu rouge ou une parade qui n'est pas un feu vert
        if (derniereCarte.equals(Cartes.FEU_ROUGE) || 
            (derniereCarte instanceof Parade && !derniereCarte.equals(Cartes.FEU_VERT))) {
            return true; // Le dépôt est autorisé
        }

        return false; // Le dépôt n'est pas autorisé
    }
    
    public boolean estDepotBorneAutorise(Borne borne) {
        // Vérifie si le joueur n'est pas bloqué
        if (!peutAvancer()) {
            return false; // Le dépôt n'est pas autorisé si le joueur est bloqué
        }

        // Vérifie si la borne ne dépasse pas la limite de vitesse
        int kmBorne = Integer.parseInt(borne.getNom().replaceAll("[^0-9]", ""));
        if (kmBorne > donnerLimitationVitesse()) {
            return false; // Le dépôt n'est pas autorisé si la borne dépasse la limite
        }

        // Vérifie si la somme des bornes ne dépasse pas 1000
        if (donnerKmParcourus() + kmBorne > 1000) {
            return false; // Le dépôt n'est pas autorisé si le total dépasse 1000 km
        }

        return true; // Le dépôt est autorisé
    }
    
    public boolean estDepotLimiteAutorise(Limite limite) {
        if (limite.getType() == Type.BOTTE) { // Vérifie si c'est un début de limite
            // Vérifie si la pile est vide ou si le sommet est une fin de limite
            return pileLimites.isEmpty() || getDerniereLimite().getType() == Type.BOTTE;
        } else { // C'est une fin de limite
            // Vérifie si le sommet de la pile est un début de limite
            return !pileLimites.isEmpty() && getDerniereLimite().getType() == Type.BOTTE;
        }
    }
    
    public boolean estDepotBatailleAutorise(Carte bataille) {
        // Vérifier si la carte est une attaque
        if (bataille instanceof Attaque) {
            return !estBloque(); // On peut déposer une attaque si le joueur n'est pas bloqué
        }

        // Vérifier si la carte est une parade
        if (bataille instanceof Parade) {
            Parade parade = (Parade) bataille; // Faire le cast ici

            // Cas pour le feu vert
            if (parade.equals(Cartes.FEU_VERT)) {
                return pileBataille.isEmpty() || 
                       pileBataille.get(pileBataille.size() - 1).equals(Cartes.FEU_ROUGE) || 
                       (pileBataille.get(pileBataille.size() - 1) instanceof Parade && 
                        !((Parade) pileBataille.get(pileBataille.size() - 1)).equals(Cartes.FEU_VERT));
            } else {
                // Autres types de parade
                return !pileBataille.isEmpty() &&
                       pileBataille.get(pileBataille.size() - 1) instanceof Attaque &&
                       ((Attaque) pileBataille.get(pileBataille.size() - 1)).getType() == parade.getType();
            }
        }

        return false; // Si ce n'est ni une attaque ni une parade, ne pas permettre le dépôt
    }
 
    public boolean estBloque() {
        // Exemple simple de condition qui pourrait bloquer un joueur
        return false; // Change cela selon ta logique de jeu
    }
    
    public boolean estDepotAutorise(Carte carte) {
        if (carte instanceof Borne) {
            return estDepotBorneAutorise((Borne) carte);
        } else if (carte instanceof Limite) {
            return estDepotLimiteAutorise((Limite) carte);
        } else if (carte instanceof Bataille) {
            return estDepotBatailleAutorise((Bataille) carte); // On peut faire le cast ici
        }
        return false; // Si le type de carte n'est pas reconnu
    }





}


