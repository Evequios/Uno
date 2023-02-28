
public class CarteNumérotée extends Carte{
	private int numéro;
	
	public CarteNumérotée(String couleur, int numéro) {
		this.couleur = couleur;
		this.numéro = numéro;
		valeur = numéro;
	}
	
	public int getNuméro() {
		return numéro;
	}
	
	public String getCouleur() {
		return couleur;
	}
	
	//pas d'utilité dans cette classe
	public String getRôle() {
		return "";
	}
	
	//pas d'utilité dans cette classe
	public String getNvCouleur() {
		return null;
	}

	//pas d'utilité dans cette classe
	protected void setNvCouleur(String nvCouleur) {}
}
