
public class CarteAction extends Carte {
	protected String rôle;
	public CarteAction(String couleur, String rôle) {
		this.couleur = couleur;
		this.valeur = 20;
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

	//pas d'utilité dans cette classe
	public String getNvCouleur() {
		return null;
	}

	//pas d'utilité dans cette classe
	protected void setNvCouleur(String nvCouleur) {}

}
