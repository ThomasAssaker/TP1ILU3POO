package cartes;

public enum Type {
	FEU("Feu Rouge","Feu Vert","Vehicule Prioritaire"),
	ESSENCE("Panne d'essence","Citerne d'essence","Increvable"),
	CREVAISON("Crevaison","Roue de secours","Increvable"),
	ACCIDENT("Accident","Reparaiton","As du volant");
	
	private String attaque;
	private String parade;
	private String botte;
	
	Type(String attaque,String parade,String botte){
		this.attaque=attaque;
		this.botte=botte;
		this.parade=parade;
	}
	
	public String getAttaque() {
		return attaque;
	}
	public String getParade() {
		return parade;
	}
	public String getBotte() {
		return botte;
	}
}
