
public abstract class Carte {
	protected String couleur;
	protected int valeur;

	protected abstract int getNum�ro();

	protected abstract String getCouleur();
	
	protected abstract String getR�le();
	
	protected abstract String getNvCouleur();
	
	protected abstract void setNvCouleur(String nvCouleur);
}
