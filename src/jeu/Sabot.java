package jeu;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import cartes.Carte;

public class Sabot<E extends Carte> implements Iterable<E> {
    private static final int NB_CARTES = 106;

    private final E[] sabot;
    private int nbCartes;
    private int nombreOperations = 0;

    public Sabot(E[] cartes) {
        this.sabot = cartes;
        this.nbCartes = sabot.length;
    }

    public boolean estVide() {
        return nbCartes == 0;
    }

    public void ajouterCarte(E carte) {
        if (nbCartes < NB_CARTES) {
            sabot[nbCartes++] = carte;
            nombreOperations++; // les itérateurs existants perdent leur droit d’accès
        } else {
            throw new IndexOutOfBoundsException("Le Sabot est déjà plein");
        }
    }

    public E piocher() {
        Iterator<E> it = iterator();
        if (it.hasNext()) {
            E cartePioche = it.next();
            it.remove();
            nbCartes--;
            nombreOperations++;
            return cartePioche;
        } else {
            throw new NoSuchElementException("le sabot est vide");
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterateur();
    }

   
//classe interne
    private class Iterateur implements Iterator<E> {
        private int indiceIterateur = 0;
        private final int nombreOperationsReference = nombreOperations;
        private boolean nextEffectue = false;

        @Override
        public boolean hasNext() {
            return indiceIterateur < nbCartes;
        }

        @Override
        public E next() {
            verificationConcurrence();
            if (!hasNext()) {
                throw new NoSuchElementException("Sortie du sabot");
            }

            E element = sabot[indiceIterateur++];
            nextEffectue = true;
            return element;
        }

        @Override
        public void remove() {
            verificationConcurrence();
            if (!nextEffectue) {
                throw new IllegalStateException();
            }

            System.arraycopy(sabot, indiceIterateur, sabot, indiceIterateur - 1, nbCartes - indiceIterateur);
            sabot[--nbCartes] = null;
            nextEffectue = false;
            nombreOperations++;
        }

        private void verificationConcurrence() {
            if (nombreOperations != nombreOperationsReference) {
                throw new ConcurrentModificationException();
            }
        }
    }
}

