import java.util.ArrayList;

public class Manche {
	private Joueur gagnant;
	
	public boolean checkFinManche(ArrayList<Joueur> joueurs) {
		//indique au programme principal qu'un des joueurs n'a plus de carte
		for(Joueur j : joueurs) {
			if (j.getMain().size() == 0) {
				j.setManchesGagnées(j.getManchesGagnées() + 1);
				return true;
			}
		}
		return false;
	}
	
	public void calculPoints(ArrayList<Joueur> joueurs) {
		// additionne les valeurs de chaque cartes restantes dans la main des joueurs
		for(Joueur j : joueurs) {
			for(Carte c : j.getMain()) {
				j.setScoreManche(j.getScoreManche() + c.valeur);
			}
			j.setScorePartie(j.getScorePartie() + j.getScoreManche());
		}
	}
	
	public void afficherPoints(ArrayList<Joueur> joueurs) {
		System.out.println("Scores de la manche");
		for(Joueur j : joueurs) {
			System.out.println(j.getNom() + " : " + j.getScoreManche());
		}
		
		System.out.println("Scores de la partie");
		for(Joueur j : joueurs) {
			System.out.println(j.getNom() + " : " + j.getScorePartie());
		}
	}
	
	public void resetScores(ArrayList<Joueur> joueurs) {
		// reset des scores au début de chaque manche
		for(Joueur j : joueurs) {
			j.setScoreManche(0);
		}
	}
	
	public void resetMains(ArrayList<Joueur> joueurs) {
		//reset des mains au début de chaque manche
		for(Joueur j : joueurs) {
			j.resetMain();
		}
	}
	
	
}
