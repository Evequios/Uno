
public class CarteNoire extends Carte{
	public String r�le;
	public String nvCouleur = "";
	
	public CarteNoire(String r�le) {
		this.couleur = "noire";
		this.valeur = 50;
		this.r�le = r�le;
	}
	
	//pas d'utilit� dans cette classe
	public int getNum�ro() {
		return -1;
	}
	
	public String getCouleur() {
		return couleur;
	}
	
	public String getR�le() {
		return r�le;
	}
	
	public String getNvCouleur() {
		return nvCouleur;
	}
	
	public void setNvCouleur(String nvCouleur) {
		this.nvCouleur = nvCouleur;
	}

	
}
