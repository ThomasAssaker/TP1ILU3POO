package cartes;

public enum Type {
    ATTAQUE("Attaque"),
    PARADE("Parade"),
    BOTTE("Botte"),
    BORNE("Borne"),
    FIN_DE_LIMITE("Fin de Limite"),
    ACCIDENT("Accident"), // Ajouté
    ESSENCE("Essence"),   // Ajouté
    CREVAISON("Crevaison"); // Ajouté

    private String description;

    // Constructeur pour l'énumération Type
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
