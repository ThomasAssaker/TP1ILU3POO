package jeu;


import cartes.Carte;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.ConcurrentModificationException;

public class Sabot implements Iterable<Carte> {
    private Carte[] cartes; // Tableau pour stocker les cartes
    private int nbCartes; // Nombre actuel de cartes dans le sabot

    // Constructeur
    public Sabot(Carte[] cartes) {
        this.cartes = cartes;
        this.nbCartes = cartes.length; // Initialement, toutes les cartes du jeu sont dans le sabot
    }

    // a. M�thode estVide() pour v�rifier si le sabot est vide
    public boolean estVide() {
        return nbCartes == 0;
    }

    // b. M�thode ajouterCarte() pour ajouter une carte au sabot
    public void ajouterCarte(Carte carte) throws IllegalStateException {
        if (nbCartes >= cartes.length) {
            throw new IllegalStateException("Capacit� maximale du sabot atteinte");
        }
        cartes[nbCartes] = carte;
        nbCartes++;
    }

    // c. Rendre la classe it�rable avec la gestion des exceptions IllegalState et ConcurrentModification
    @Override
    public Iterator<Carte> iterator() {
        return new Iterator<Carte>() {
            private int index = 0;
            private boolean modifiable = false; // Pour g�rer l'appel � remove()

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
                // Supprimer l'�l�ment en d�calant les autres
                System.arraycopy(cartes, index, cartes, index - 1, nbCartes - index);
                nbCartes--;
                index--; // Ajuste l'index apr�s la suppression
                modifiable = false;
            }
        };
    }

    // d. M�thode piocher() pour piocher et supprimer la premi�re carte du sabot
    public Carte piocher() {
        Iterator<Carte> it = this.iterator();
        if (!it.hasNext()) {
            throw new NoSuchElementException("Le sabot est vide");
        }
        Carte carte = it.next();
        it.remove(); // Supprime la carte apr�s l'avoir pioch�e
        return carte;
    }
}