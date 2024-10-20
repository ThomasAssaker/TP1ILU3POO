package cartes;

public class Limite extends Carte {
    public Limite(String nom) {
        super(Type.BOTTE, nom); // Associe un type à la limite, ici utilisé comme BOTTE
    }

    @Override
    public String toString() {
        return "Limite: " + nom;
    }
}
