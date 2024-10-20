package testsFonctionnels;

import cartes.*;
import utils.GestionCartes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestGestionCartes {

    public static void main(String[] args) {
        // Initialisation d'une liste de cartes pour les tests
        List<Carte> cartesDeTest = Arrays.asList(
            new Borne("25KM"),
            new Borne("50KM"),
            new Borne("25KM"),
            new Parade("Feu Vert"),
            new Attaque("Feu Rouge")
        );

        // Copie de la liste pour certains tests
        List<Carte> cartesPourExtraction = new ArrayList<>(cartesDeTest);

        // a. Test de la méthode extraire
        System.out.println("=== Test de la méthode extraire ===");
        Carte carteExtraite = GestionCartes.extraire(cartesPourExtraction);
        System.out.println("Carte extraite : " + carteExtraite);
        System.out.println("Liste après extraction : " + cartesPourExtraction);

        // Réinitialisation de la liste
        cartesPourExtraction = new ArrayList<>(cartesDeTest);

        // a. Test de la méthode extraireAvecIterator
        System.out.println("\n=== Test de la méthode extraireAvecIterator ===");
        carteExtraite = GestionCartes.extraireAvecIterator(cartesPourExtraction);
        System.out.println("Carte extraite avec iterator : " + carteExtraite);
        System.out.println("Liste après extraction avec iterator : " + cartesPourExtraction);

        // b. Test de la méthode melanger
        List<Carte> cartesMelangees = GestionCartes.melanger(new ArrayList<>(cartesDeTest));
        System.out.println("\n=== Test de la méthode melanger ===");
        System.out.println("Liste mélangée : " + cartesMelangees);

        // c. Test de la méthode verifierMelange
        boolean melangeCorrect = GestionCartes.verifierMelange(cartesDeTest, cartesMelangees);
        System.out.println("\n=== Test de la méthode verifierMelange ===");
        System.out.println("La liste est-elle correctement mélangée ? " + melangeCorrect);

        // d. Test de la méthode rassembler
        List<Carte> cartesRassemblees = GestionCartes.rassembler(cartesDeTest);
        System.out.println("\n=== Test de la méthode rassembler ===");
        System.out.println("Liste rassemblée : " + cartesRassemblees);

        // e. Test de la méthode verifierRassemblement
        boolean rassemblementCorrect = GestionCartes.verifierRassemblement(cartesRassemblees);
        System.out.println("\n=== Test de la méthode verifierRassemblement ===");
        System.out.println("La liste est-elle correctement rassemblée ? " + rassemblementCorrect);
    }
}
