package testsFonctionnels;

import cartes.*;

public class TestMethodeEquals {

    public static void main(String[] args) {
        // a. Tester deux objets Borne avec la même valeur ("25KM")
        Carte borne1 = new Borne("25KM");
        Carte borne2 = new Borne("25KM");
        System.out.println("Les deux cartes Borne 25KM sont-elles égales ? " + borne1.equals(borne2));
        System.out.println("Détails: borne1 = " + borne1 + ", borne2 = " + borne2);

        // b. Tester deux objets Attaque avec la même valeur ("Feu Rouge")
        Carte attaque1 = new Attaque("Feu Rouge");
        Carte attaque2 = new Attaque("Feu Rouge");
        System.out.println("\nLes deux cartes Attaque Feu Rouge sont-elles égales ? " + attaque1.equals(attaque2));
        System.out.println("Détails: attaque1 = " + attaque1 + ", attaque2 = " + attaque2);

        // c. Tester un objet Attaque "Feu Rouge" avec un objet Parade "Feu Vert"
        Carte carteAttaque = new Attaque("Feu Rouge");
        Carte carteParade = new Parade("Feu Vert");
        System.out.println("\nLa carte Attaque Feu Rouge et la carte Parade Feu Vert sont-elles égales ? " + carteAttaque.equals(carteParade));
        System.out.println("Détails: carteAttaque = " + carteAttaque + ", carteParade = " + carteParade);
    }
}
