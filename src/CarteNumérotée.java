
public class CarteNum�rot�e extends Carte{
	private int num�ro;
	
	public CarteNum�rot�e(String couleur, int num�ro) {
		this.couleur = couleur;
		this.num�ro = num�ro;
		valeur = num�ro;
	}
	
	public int getNum�ro() {
		return num�ro;
	}
	
	public String getCouleur() {
		return couleur;
	}
	
	//pas d'utilit� dans cette classe
	public String getR�le() {
		return "";
	}
	
	//pas d'utilit� dans cette classe
	public String getNvCouleur() {
		return null;
	}

	//pas d'utilit� dans cette classe
	protected void setNvCouleur(String nvCouleur) {}
}
