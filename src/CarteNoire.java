
public class CarteNoire extends Carte{
	public String rôle;
	public String nvCouleur = "";
	
	public CarteNoire(String rôle) {
		this.couleur = "noire";
		this.valeur = 50;
		this.rôle = rôle;
	}
	
	//pas d'utilité dans cette classe
	public int getNuméro() {
		return -1;
	}
	
	public String getCouleur() {
		return couleur;
	}
	
	public String getRôle() {
		return rôle;
	}
	
	public String getNvCouleur() {
		return nvCouleur;
	}
	
	public void setNvCouleur(String nvCouleur) {
		this.nvCouleur = nvCouleur;
	}

	
}
