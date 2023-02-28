import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
public class Partie {
	private ArrayList<Manche> manches = new ArrayList<Manche>();
	private ArrayList<Joueur> joueurs = new ArrayList<Joueur>();
	private ArrayList<Carte> paquet = new ArrayList<Carte>();
	private ArrayList<Carte> talon = new ArrayList<Carte>();
	private Joueur gagnant = new Joueur("");
	private int nbJoueurs;
	private int modeJeu;
	
	
	
	public boolean vérifCarte(ArrayList<Carte> talon, Carte c, Joueur j) {
		if(c instanceof CarteNumérotée) {
			if(talon.get(0) instanceof CarteNumérotée) {
				if(talon.get(0).getCouleur().equals(c.getCouleur()) || talon.get(0).getNuméro() == c.getNuméro()) {
					return true;
				}
			}
			
			if(talon.get(0) instanceof CarteAction) {
				if(talon.get(0).getCouleur().equals(c.getCouleur())) {
					return true;
				}
			}
			
			if(talon.get(0) instanceof CarteNoire) {
				if(talon.get(0).getNvCouleur().equals(c.getCouleur())) {
					return true;
				}
			}
		}
		
		if(c instanceof CarteAction) {
			if(talon.get(0) instanceof CarteNumérotée) {
				if(talon.get(0).getCouleur().equals(c.getCouleur())) {
					return true;
				}
			}
			
			if(talon.get(0) instanceof CarteAction) {
				if(talon.get(0).getRôle().equals(c.getRôle()) || talon.get(0).getCouleur().equals(c.getCouleur())) {
					return true;
				}
			}
			
			if(talon.get(0) instanceof CarteNoire) {
				if(talon.get(0).getNvCouleur().equals(c.getCouleur())) {
					return true;
				}
			}
		}
		
		if(c instanceof CarteNoire) {
			if(c.getRôle().equals("+4")) {
				String coulDemandée = "";
				if(talon.get(0) instanceof CarteNoire) {
					coulDemandée = talon.get(0).getNvCouleur();
				}
				else {
					coulDemandée = talon.get(0).getCouleur();
				}
				
				for(Carte c2 : j.getMain()) {
					if(c2.getCouleur().equals(coulDemandée)) {
						return false;
					}
				}
			}
			return true;
		}
		
		return false;
	}
	
	public Boolean vérifBluff(Joueur jPrécédent) {
		for(Carte c : jPrécédent.getMain()) {
			// vérifie si le joueur précédent ne pouvait pas jouer une carte de même symbole ou de même numéro
			if(c instanceof CarteAction) {
				if(c.getRôle().equals(talon.get(1).getRôle())){
					return true;
				}
			}
			if(c instanceof CarteNumérotée) {
				if(c.getNuméro() == talon.get(1).getNuméro()) {
					return true;
				}
			}
		}
		return false;
	}
	public void créationPaquet() {
		for(int i = 0; i < 4; i++) {
				String couleur;
				switch(i) {
				case 0:
					couleur = "rouge";
					break;
				case 1:
					couleur = "jaune";
					break;
				case 2:
					couleur = "bleu";
					break;
				case 3:
					couleur = "vert";
					break;
				default:
					couleur = "";
					break;
				}
			CarteNumérotée c0 = new CarteNumérotée(couleur, 0);
			paquet.add(c0);
		
			for(int j = 1; j < 10; j++) {
				CarteNumérotée cNum1 = new CarteNumérotée(couleur, j);
				paquet.add(cNum1);
				CarteNumérotée cNum2 = new CarteNumérotée(couleur, j);
				paquet.add(cNum2);
			}
			
			for(int k = 0; k < 2; k++) {
				CarteAction cA1 = new CarteAction(couleur, "+2");
				paquet.add(cA1);
				CarteAction cA2 = new CarteAction(couleur, "CHANGEMENT");
				paquet.add(cA2);
				CarteAction cA3 = new CarteAction(couleur, "PASSE");
				paquet.add(cA3);
			}
			
			CarteNoire cNoire1 = new CarteNoire("+4");
			paquet.add(cNoire1);
			CarteNoire cNoire2 = new CarteNoire("JOKER");
			paquet.add(cNoire2);
		}
	}
	
	public void mélanger() {
		Collections.shuffle(paquet);
	}
	
	public void distribuer() {
		// distribution des cartes
		for(int i = 0; i < 6; i++) {
			for(Joueur j : joueurs) {
				j.piocher(paquet);
			}
		}
		
		// première carte du jeu
		boolean valide = false;
		while(valide == false) {
			if(paquet.get(0) instanceof CarteNumérotée) {
				System.out.println("La carte du talon est un " + paquet.get(0).getNuméro() + " " + paquet.get(0).getCouleur());
				valide = true;
			}
			
			if(paquet.get(0) instanceof CarteAction) {
				if(paquet.get(0).getRôle().equals("+2")) {
					System.out.println("La carte du talon est un +2 " + paquet.get(0).getCouleur());
					valide = true;
				}
				if(paquet.get(0).getRôle().equals("CHANGEMENT")) {
					System.out.println("La carte du talon est un changement de sens " + paquet.get(0).getCouleur());
					valide = true;
				}
				if(paquet.get(0).getRôle().equals("PASSE")) {
					System.out.println("La carte du talon est une carte passer le tour " + paquet.get(0).getCouleur());
					valide = true;
				}
			}
			
			if(paquet.get(0) instanceof CarteNoire) {
				if(paquet.get(0).getRôle().equals("JOKER")) {
					System.out.println("La carte du talon est un changement de couleur");
					Scanner lectureCouleur = new Scanner(System.in);
					String couleur = "";
					System.out.println("Quelle couleur choisissez-vous ?");
					while(couleur.equals("")) {
						try {
							String c = lectureCouleur.next().toLowerCase();
							if(c.equals("rouge") || c.equals("vert") || c.equals("jaune") || c.equals("bleu")) {
								couleur = c;
							}
							
							else {
								System.out.println("Veuillez entrer une couleur valide");
							}
						}
						catch(Exception e) {
							System.out.println("Veuillez entrer une couleur valide");
						}
					}
					
					paquet.get(0).setNvCouleur(couleur);
					System.out.println("La couleur actuelle est le " + paquet.get(0).getNvCouleur());
					valide = true;
				}
				if(paquet.get(0).getRôle().equals("+4")) {
					System.out.println("La carte du talon est un +4, on la remet dans le paquet");
					mélanger();
				}
			}
		}
		talon.add(0,paquet.get(0));
		paquet.remove(0);
	}
	
	public void checkPaquetVide() {
		if(paquet.size() == 0) {
			// création d'une copie du talon
			ArrayList<Carte> temp = new ArrayList<Carte>(talon);
			// on vide le vrai talon puis y ajoute la dernière carte jouée
			talon.clear();
			talon.add(temp.get(0));
			// on retire la dernière carte jouée de la copie du talon, on le mélange et on remplace le paquet
			temp.remove(0);
			mélanger();
			setPaquet(temp);
		}
	}
	
	
	public boolean checkFinPartie() {
		// vérifie si un des joueurs a atteint le score de 500 points
		boolean fin = false;
		for(Joueur j : joueurs){
			if (j.getScorePartie() >= 500) {
				fin = true;
				break;
			}
			
		}
		
		// si un des joueurs a 500 points, on cherche le joueur avec le moins de points et si un ou plusieurs joueurs sont à égalité
		if(fin == true) {
			ArrayList<Joueur> gagnants = new ArrayList();
			gagnant = joueurs.get(0);
			for(Joueur j : joueurs) {
				if(j.getScorePartie() < gagnant.getScorePartie() ) {
					gagnants.clear();
					gagnant = j;
					gagnants.add(gagnant);
				}
				
				if(j.getScorePartie() == gagnant.getScorePartie() && j.getNom() != gagnant.getNom()) {
					gagnants.add(j);
				}
			}
			
			// s'il y a égalité, on compare le nombre de manches gagnées
			if(gagnants.size() > 1) {
				for(Joueur j : gagnants) {
					if(j.getManchesGagnées() > gagnant.getManchesGagnées() && j.getNom() != gagnant.getNom()) {
						gagnant = j;
					}
					
					// s'il y a une égalité au niveau des manches gagnées, match nul
					if(j.getManchesGagnées() == gagnant.getManchesGagnées()) {
						System.out.println("Il y a une égalité entre au moins deux joueurs ! Veuillez recommencer une partie");
						return fin;
					}
				}
			}
				
				System.out.println("FIN DE LA PARTIE ! Le gagnant est " + gagnant.getNom());
		}
		
		return fin;
	}
	
	public void changementSens(String nom) {
		// remplace l'arrayList des joueurs par une commençant par le joueur précédent
		// ex : si 8 joueurs et que le joueur 3 joue le changement de sens
		// arraylist finale : 2/1/8/7/6/5/4/3
		// lorsque le sens est changé, il l'est jusqu'à la fin de la partie ou jusqu'à qu'un autre joueur joue un changement de sens
		ArrayList<Joueur> temp = new ArrayList<Joueur>();
		int indexJ = 0;
		for(Joueur j : joueurs) {
			if(j.getNom().equals(nom)) {
				indexJ = joueurs.indexOf(j);
				break;
			}
		}
		
		int j = 0;
		for(int i = 0; i < indexJ ; i++) {
			temp.add(0, joueurs.get(i));
		}
		for(int i = joueurs.size()-1; i > indexJ ; i --) {
			temp.add(joueurs.get(i));
		}
		temp.add(joueurs.get(indexJ));
		setJoueurs(temp);
	}
	
	public int getNbJoueurs() {
		return nbJoueurs;
	}
	
	public void setNbJoueurs(int nbJoueurs) {
		this.nbJoueurs = nbJoueurs;
	}
	
	public int getModeJeu() {
		return modeJeu;
	}
	
	public void setModeJeu(int modeJeu) {
		this.modeJeu = modeJeu;
	}
	
	public ArrayList<Carte> getPaquet(){
		return paquet;
	}
	
	public void setPaquet(ArrayList<Carte> paquet) {
		this.paquet = paquet;
	}
	
	public ArrayList<Joueur> getJoueurs(){
		return joueurs;
	}
	
	public void setJoueurs(ArrayList<Joueur> joueurs) {
		this.joueurs = joueurs;
	}
	
	public ArrayList<Manche> getManches(){
		return manches;
	}
	
	public ArrayList<Carte> getTalon(){
		return talon;
	}
	
	public void setTalon(ArrayList<Carte> talon){
		this.talon = talon;
	}
}
