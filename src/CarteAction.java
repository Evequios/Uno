
public class CarteAction extends Carte {
	protected String r�le;
	public CarteAction(String couleur, String r�le) {
		this.couleur = couleur;
		this.valeur = 20;
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

	//pas d'utilit� dans cette classe
	public String getNvCouleur() {
		return null;
	}

	//pas d'utilit� dans cette classe
	protected void setNvCouleur(String nvCouleur) {}

}
