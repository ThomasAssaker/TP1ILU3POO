package testsFonctionnels;

import cartes.*;
import java.util.HashMap;
import java.util.Map;

public class TestJeuDeCartes {

    public static void main(String[] args) {
        // === Tests de la méthode equals ===
        System.out.println("=== Comparaison d'égalité des cartes ===");

        // Test 1 : Comparer deux cartes Borne "25KM" identiques
        Carte carteBorneA = new Borne("25KM");
        Carte carteBorneB = new Borne("25KM");
        boolean comparBorne = carteBorneA.equals(carteBorneB);
        System.out.println("Les cartes Borne 25KM A et B sont-elles égales ? " + (comparBorne ? "Oui" : "Non"));

        // Test 2 : Comparer deux cartes Attaque "Feu Rouge" identiques
        Carte attaqueFeuRougeA = new Attaque("Feu Rouge");
        Carte attaqueFeuRougeB = new Attaque("Feu Rouge");
        boolean comparFeuRouge = attaqueFeuRougeA.equals(attaqueFeuRougeB);
        System.out.println("Les cartes Attaque Feu Rouge A et B sont-elles égales ? " + (comparFeuRouge ? "Oui" : "Non"));

        // Test 3 : Comparer une carte Attaque "Feu Rouge" et une carte Parade "Feu Vert"
        Carte attaqueFeuRouge = new Attaque("Feu Rouge");
        Carte paradeFeuVert = new Parade("Feu Vert");
        boolean comparFeuRougeVert = attaqueFeuRouge.equals(paradeFeuVert);
        System.out.println("La carte Feu Rouge et la carte Feu Vert sont-elles égales ? " + (comparFeuRougeVert ? "Non" : "Non"));

        // === Tests de la méthode checkCount ===
        System.out.println("\n=== Vérification du compte des cartes dans le jeu ===");

        // Configuration et vérification des occurrences correctes
        Carte[] deckTest1 = {
            new Borne("25KM"), new Borne("50KM"), new Borne("25KM"),
            new Parade("Feu Vert"), new Attaque("Feu Rouge")
        };
        Map<String, Integer> configValide = new HashMap<>();
        configValide.put("25KM", 2);
        configValide.put("50KM", 1);
        configValide.put("Feu Vert", 1);
        configValide.put("Feu Rouge", 1);

        JeuDeCartes jeu1 = new JeuDeCartes(deckTest1, configValide);
        boolean resultTest1 = jeu1.checkCount();
        System.out.println("Test 1 : Le deck a-t-il le bon nombre de cartes ? " + (resultTest1 ? "Oui" : "Non"));

        // Test avec une configuration incorrecte (mauvais nombre pour "25KM")
        Carte[] deckTest2 = {
            new Borne("25KM"), new Borne("50KM"),
            new Parade("Feu Vert"), new Attaque("Feu Rouge")
        };
        Map<String, Integer> configInvalide = new HashMap<>();
        configInvalide.put("25KM", 2); // Il y a une seule carte "25KM" dans le deck
        configInvalide.put("50KM", 1);
        configInvalide.put("Feu Vert", 1);
        configInvalide.put("Feu Rouge", 1);

        JeuDeCartes jeu2 = new JeuDeCartes(deckTest2, configInvalide);
        boolean resultTest2 = jeu2.checkCount();
        System.out.println("Test 2 : Le deck a-t-il le bon nombre de cartes ? " + (resultTest2 ? "Oui" : "Non"));

        // Test avec une carte absente de la configuration ("Feu Vert" non inclus)
        Map<String, Integer> configManque = new HashMap<>();
        configManque.put("25KM", 2);
        configManque.put("50KM", 1);
        // "Feu Vert" est manquant dans cette configuration
        configManque.put("Feu Rouge", 1);

        JeuDeCartes jeu3 = new JeuDeCartes(deckTest1, configManque);
        boolean resultTest3 = jeu3.checkCount();
        System.out.println("Test 3 : Le deck a-t-il le bon nombre de cartes ? " + (resultTest3 ? "Oui" : "Non"));
    }
}

