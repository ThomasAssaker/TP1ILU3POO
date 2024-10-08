package cartes;

import java.util.ArrayList;
import java.util.List;

public class JeuDeCartes {
    private final List<Configuration> configurations;

    // Constructeur
    public JeuDeCartes() {
        this.configurations = new ArrayList<>();
    }

    // Ajoute une configuration de carte avec le nombre d'exemplaires
    public void ajouterConfiguration(Carte carte, int nombreExemplaires) {
        configurations.add(new Configuration(carte, nombreExemplaires));
    }

    // M�thode d'affichage du jeu de cartes
    public void affichageJeuDeCartes() {
        System.out.println("JEU :");
        configurations.forEach(configuration -> 
            System.out.println(configuration.getNombreExemplaires() + " " + configuration.getCarte())
        );
    }
}

