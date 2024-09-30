package testsFonctionnels;

import cartes.*;
import jeu.Sabot;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class TestSabot {

    public static void main(String[] args) {
        // Cr�e un tableau de cartes pour le sabot
        Carte[] cartes = {
            new Borne("25KM"),
            new Borne("50KM"),
            new Borne("75KM"),
            new Parade("Feu Vert"),
            new Attaque("Feu Rouge")
        };

        // Cr�e le sabot avec les cartes
        Sabot sabot = new Sabot(cartes);

        try {
            // a. Pioche une carte avant d'entrer dans la boucle
            Carte carte = sabot.piocher();
            System.out.println("Je pioche " + carte);

            // b. Utilisation de l'it�rateur et remove pour piocher et supprimer les cartes
            Iterator<Carte> it = sabot.iterator();
            while (it.hasNext()) {
                carte = it.next();
                System.out.println("Je pioche " + carte);
                it.remove(); // Supprime la carte apr�s l'avoir pioch�e
            }

            // c. Tentative d'ajouter une carte apr�s la boucle pour provoquer une exception
            sabot.ajouterCarte(new Botte("AsDuVolant")); // Ajout apr�s avoir vid� le sabot
            System.out.println("Ajout de la carte AsDuVolant apr�s la boucle.");
        } catch (IllegalStateException e) {
            System.out.println("Erreur : Capacit� maximale du sabot atteinte.");
        } catch (NoSuchElementException e) {
            System.out.println("Le sabot est vide !");
        }

        // Tentative de piocher apr�s que le sabot soit vide, ce qui devrait lever une exception
        try {
            Carte carte = sabot.piocher(); // Ce piocheur doit lever une exception
            System.out.println("Je pioche " + carte);
        } catch (NoSuchElementException e) {
            System.out.println("Erreur : Le sabot est vide !");
        }
    }
}