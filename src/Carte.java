
public abstract class Carte {
	protected String couleur;
	protected int valeur;

	protected abstract int getNuméro();

	protected abstract String getCouleur();
	
	protected abstract String getRôle();
	
	protected abstract String getNvCouleur();
	
	protected abstract void setNvCouleur(String nvCouleur);
}
