import java.util.ArrayList;
public class Joueur {
	private String nom;
	private int scorePartie;
	private int scoreManche;
	private int manchesGagnées;
	private ArrayList<Carte> main = new ArrayList<Carte>();
	
	public Joueur(String nom) {
		this.nom = nom;
		scorePartie = 0;
		scoreManche = 0;
	}
	
	public String getNom() {
		return nom;
	}
	
	public int getScorePartie() {
		return scorePartie;
	}
	
	public void setScorePartie(int scorePartie) {
		this.scorePartie = scorePartie;
	}
	
	public int getScoreManche() {
		return scoreManche;
	}
	
	public void setScoreManche(int scoreManche) {
		this.scoreManche = scoreManche;
	}
	
	public int getManchesGagnées() {
		return manchesGagnées;
	}
	
	public void setManchesGagnées(int manchesGagnées) {
		this.manchesGagnées = manchesGagnées;
	}
	
	public void resetMain() {
		main.clear();
	}
	
	public ArrayList<Carte> piocher(ArrayList<Carte> paquet) {
		//ajout de la première carte du paquet à la fin de la main puis retrait de la carte du paquet
		main.add(paquet.get(0));
		paquet.remove(paquet.get(0));
		return paquet;
	}
	
	public ArrayList<Carte> jouer(int i, ArrayList<Carte> talon) {
		// traitement des différents types de cartes
		if(main.get(i) instanceof CarteNumérotée) {
			System.out.println(nom + " joue un " + main.get(i).getNuméro() + " " + main.get(i).getCouleur());
		}
		
		if(main.get(i) instanceof CarteAction) {
			String r = "";
			if(main.get(i).getRôle().equals("CHANGEMENT")) {
				r = "changement de sens";
			}
			if(main.get(i).getRôle().equals("PASSE")) {
				r = "passer le tour";
			}
			if(main.get(i).getRôle() == "+2") {
				r = "+2";
			}
			System.out.println(nom + " joue une carte " + r + " " + main.get(i).getCouleur());
		}
		
		if(main.get(i) instanceof CarteNoire) {
			String r = "";
			if(main.get(i).getRôle().equals("JOKER")) {
				r = "joker";
			}
			if(main.get(i).getRôle().equals("+4")) {
				r = "+4";
			}
			System.out.println(nom + " joue une carte " + r);
		}
		
		//ajout de la carte jouée au talon puis suppression de la carte dans la main
		talon.add(0, main.get(i));
		main.remove(main.get(i));
		//print la carte jouée
		return talon;
	}
	
	public ArrayList<Carte> getMain() {
		return main;
	}
	
	public void afficherMain() {
		System.out.println(nom + " a " + main.size() + " cartes en main.");
		for(Carte c : main) {
			if(c instanceof CarteNumérotée) {
				System.out.println(c.getNuméro() + " " + c.getCouleur());
			}
			
			if(c instanceof CarteAction) {
				System.out.println(c.getRôle() + " " + c.getCouleur());
			}
			
			if(c instanceof CarteNoire) {
				System.out.println(c.getRôle());
			}
		}
	}
}
