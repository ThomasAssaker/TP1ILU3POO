package jeu;

import java.util.ArrayList;
import java.util.List;
import cartes.*;

public class ZoneDeJeu {

    private List<Limite> pileLimite = new ArrayList<>();
    private List<Bataille> pileBataille = new ArrayList<>();
    private List<Borne> bornes = new ArrayList<>();

    public int donnerLimitationVitesse() {
        if (pileLimite.isEmpty() || pileLimite.get(0) instanceof FinLimite) {
            return 200;
        } else {
            return 50;
        }
    }

    public int donnerKmParcourus() {
        return bornes.stream().mapToInt(Borne::getKm).sum();
    }

    public void deposer(Carte carte) {
        if (carte instanceof Bataille bataille) {
            pileBataille.add(0, bataille);
        } else if (carte instanceof Borne borne) {
            bornes.add(0, borne);
        } else if (carte instanceof Limite limite) {
            pileLimite.add(0, limite);
        }
    }

    public boolean peutAvancer() {
        return !pileBataille.isEmpty() && pileBataille.get(0).equals(Cartes.FEU_VERT);
    }

    private boolean estDepotFeuVertAutorise() {
        return pileBataille.isEmpty() || pileBataille.get(0).equals(Cartes.FEU_ROUGE) || pileBataille.get(0) instanceof Parade;
    }

    private boolean estDepotBorneAutorise(Borne borne) {
        return peutAvancer() &&
               borne.getKm() <= donnerLimitationVitesse() &&
               (donnerKmParcourus() + borne.getKm() <= 1000);
    }

    private boolean estDepotLimiteAutorise(Limite limite) {
        if (limite instanceof DebutLimite) {
            return pileLimite.isEmpty() || !pileLimite.get(0).equals(limite);
        } else {
            return !pileLimite.isEmpty() && !pileLimite.get(0).equals(limite);
        }
    }

    private boolean estDepotBatailleAutorise(Bataille bataille) {
        if (bataille instanceof Attaque) {
            return peutAvancer();
        } else if (bataille.equals(Cartes.FEU_VERT)) {
            return estDepotFeuVertAutorise();
        } else {
            return !pileBataille.isEmpty() && bataille.getType() == pileBataille.get(0).getType();
        }
    }

    public boolean estDepotAutorise(Carte carte) {
        if (carte instanceof Borne borne) {
            return estDepotBorneAutorise(borne);
        } else if (carte instanceof Bataille bataille) {
            return estDepotBatailleAutorise(bataille);
        } else if (carte instanceof Parade) {
            return estDepotFeuVertAutorise();
        } else if (carte instanceof Limite limite) {
            return estDepotLimiteAutorise(limite);
        }
        return false;
    }
}


