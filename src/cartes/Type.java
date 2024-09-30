package cartes;


public enum Type {
    ATTAQUE("Attaque"),
    PARADE("Parade"),
    BOTTE("Botte"),
    BORNE("Borne");

    private String description;

    // Constructeur pour l'�num�ration Type
    Type(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return description;
    }
}