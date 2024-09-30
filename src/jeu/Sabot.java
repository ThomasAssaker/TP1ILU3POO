package jeu;


import cartes.Carte;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.ConcurrentModificationException;

public class Sabot implements Iterable<Carte> {
    private final Carte[] cartes; // Tableau pour stocker les cartes
    private int nbCartes; // Nombre actuel de cartes dans le sabot

    // Constructeur
    public Sabot(Carte[] cartes) {
        this.cartes = cartes;
        this.nbCartes = cartes.length; // Initialement, toutes les cartes du jeu sont dans le sabot
    }

    // M�thode estVide() pour v�rifier si le sabot est vide
    public boolean estVide() {
        return nbCartes == 0;
    }

    // M�thode ajouterCarte() pour ajouter une carte au sabot
    public void ajouterCarte(Carte carte) {
        if (nbCartes >= cartes.length) {
            throw new IllegalStateException("Capacit� maximale du sabot atteinte");
        }
        cartes[nbCartes++] = carte;
    }

    // Impl�mentation de l'it�rable avec gestion des exceptions
    @Override
    public Iterator<Carte> iterator() {
        return new SabotIterator();
    }

    // Classe interne pour l'it�ration sur les cartes
    private class SabotIterator implements Iterator<Carte> {
        private int index = 0;
        private boolean modifiable = false;

        @Override
        public boolean hasNext() {
            return index < nbCartes;
        }

        @Override
        public Carte next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            modifiable = true;
            return cartes[index++];
        }

        @Override
        public void remove() {
            if (!modifiable) {
                throw new IllegalStateException("Impossible de supprimer avant next()");
            }
            if (index <= 0 || index > nbCartes) {
                throw new ConcurrentModificationException();
            }
            // D�caler les cartes apr�s suppression
            System.arraycopy(cartes, index, cartes, index - 1, nbCartes - index);
            nbCartes--;
            index--; // Ajuster l'index apr�s la suppression
            modifiable = false;
        }
    }

    // M�thode piocher() pour piocher et supprimer la premi�re carte du sabot
    public Carte piocher() {
        if (estVide()) {
            throw new NoSuchElementException("Le sabot est vide");
        }
        Carte carte = cartes[0]; // R�cup�rer la premi�re carte
        System.arraycopy(cartes, 1, cartes, 0, --nbCartes); // Supprimer la premi�re carte et d�caler les autres
        return carte;
    }
}