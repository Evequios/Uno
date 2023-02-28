// mode 2 quand la carte est jou� depuis la main
import java.util.Collections;
import java.util.Scanner;
public class Program {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// Parametrage de la partie
		Partie p = new Partie();
		p.setNbJoueurs(0);
		
		
		// Nombre de joueurs
		while(p.getNbJoueurs() < 2 || p.getNbJoueurs() > 10) {
			Scanner lectureNbJoueurs = new Scanner(System.in);
			System.out.println("Veuillez renseigner le nombre de joueurs");
			try {
				p.setNbJoueurs(lectureNbJoueurs.nextInt());
				if (p.getNbJoueurs() < 2 || p.getNbJoueurs() > 10) {
					System.out.println("Le nombre de joueurs doit �tre entre 2 et 10");
				}
				else {
					System.out.println("La partie se jouera avec " + p.getNbJoueurs() + " joueurs.");
					
				}
			}
			catch(Exception e) {
				System.out.println("Veuillez entrer un nombre ! ");
			}
		}
		
		// Noms des joueurs
		for(int i = 1; i <= p.getNbJoueurs(); i++) {
			boolean valide = false;
			boolean existe = false;
			String nom = "";
			while(valide == false) {
				existe = false;
				Scanner lectureNomJoueur = new Scanner(System.in);
				System.out.println("Veuillez entrer le nom du Joueur " + i);
				nom = lectureNomJoueur.nextLine();
				
				if(i == 1) {
					valide = true;
				}
				
				else {
					for(Joueur j : p.getJoueurs()) {
						if(j.getNom().equals(nom)) {
							existe = true;
							break;
						}
					}
				}
				
				if(existe) {
					System.out.println("Ce nom est d�j� utilis�");
				}
				else {
					valide = true;
				}
				
			}
			Joueur j = new Joueur (nom);
			p.getJoueurs().add(j);
		}
		
		// Mode de jeu
		while(p.getModeJeu() != 1 && p.getModeJeu() != 2) {
			Scanner lectureModeJeu = new Scanner(System.in);
			System.out.println("Veuillez choisir le mode de jeu");
			System.out.println("Pour jouer au mode classique (ne permettant pas de cumuler les +2 et les +4) tapez 1");
			System.out.println("Pour jouer au mode expert permettant les cumuls (un +4 pouvant se cumuler � un +2 mais pas l'inverse) tapez 2");
			try {
				p.setModeJeu(lectureModeJeu.nextInt());
				if(p.getModeJeu() != 1 && p.getModeJeu() != 2) {
					System.out.println("Le mode de jeu doit �tre choisit en tapant 1 ou 2");
				}
				else {
					String strMode = "";
					if(p.getModeJeu() == 1)
						strMode = "classique";
					if(p.getModeJeu() == 2)
						strMode = "expert";
					System.out.println("Vous avez choisi le mode " + strMode);
				}
			}
			catch(Exception e) {
				System.out.println("Veuillez entrer l'option 1 ou l'option 2 !");
			}
		}
		
		// PARTIE
		while(!p.checkFinPartie()) {
			
			Joueur jActuel = new Joueur("");
			Joueur jPr�c�dent = new Joueur("");
			Manche m = new Manche();
			p.getManches().add(m);
			m.resetScores(p.getJoueurs());
			m.resetMains(p.getJoueurs());
			System.out.println("Manche num�ro " + p.getManches().size());
			p.cr�ationPaquet();
			p.m�langer();
			p.distribuer();
			
			
			
			int nbAPiocher = 0; // indique le nombre de cartes � piocher (+2 ou +4) --> utilis� dans une boucle pour piocher le nombre de carte ad�quat
			boolean actionAFaire = false; // la carte jou�e pr�c�demment requiert une action par le joueur actuel
			boolean finManche = false;
			boolean changementSens = false; // indique si le sens a �t� chang� pendant le tour du joueur ou non
			
			
			while(!finManche) {
				// a faire : tant que la manche n'est pas finie;
				if(jActuel.getNom() == "") {
					// si la premi�re carte de la manche est une carte action ou joker
					if(p.getTalon().get(0).getR�le().equals("PASSE") || p.getTalon().get(0).getR�le().equals("+2") || p.getTalon().get(0).getR�le().equals("JOKER")){
						actionAFaire = true;
					}
					
					if(p.getTalon().get(0).getR�le().equals("+2")){
						nbAPiocher = 2;
					}
					
					if(p.getTalon().get(0).getR�le().equals("CHANGEMENT")) {
						Collections.reverse(p.getJoueurs());
					}
				}
				for(Joueur j : p.getJoueurs()) {
					changementSens = false;
					jActuel = j;
					boolean aJou� = false;
					if(actionAFaire == true) {
						if(p.getTalon().get(0).getR�le().equals("PASSE")) {
							aJou� = true;
							actionAFaire = false;
						}
						
					}
					while(aJou� == false) {
						System.out.println("Au tour de " + j.getNom() + " : ");
						
						try {
							Scanner lectureCommande = new Scanner(System.in);
							String commande = lectureCommande.nextLine();
							
							
							// affichage de la main
							if(commande.equals("/m")) {
								j.afficherMain();
							}
							
							// le joueur pioche (intentionnellent, +2 ou +4)
							else if(commande.equals("/p")) {
								
								if(actionAFaire == true) {
									for(int i = 0; i < nbAPiocher; i++) {
										p.checkPaquetVide();
										j.piocher(p.getPaquet());
										printPioche(j);
									}
									nbAPiocher = 0;
									aJou� = true;
									actionAFaire = false;
								}
								else {
									p.checkPaquetVide();
									j.piocher(p.getPaquet());
									printPioche(j);
									
									while(aJou� == false) {
										if(p.v�rifCarte(p.getTalon(), j.getMain().get(j.getMain().size()-1), j)) {
											// si la carte pioch�e est valide
											System.out.println("La carte pioch�e peut �tre jou�e, voulez vous la jouer ? Tapez oui pour la jouer, sinon tapez non");
											Scanner lecturePioche = new Scanner(System.in);
											try {
												String choix = lecturePioche.next().toLowerCase();
												if(choix.equals("oui")) {
													p.setTalon(j.jouer((j.getMain().size()-1), p.getTalon()));
													if(p.getTalon().get(0).getR�le().equals("PASSE") || p.getTalon().get(0).getR�le().equals("+2") || p.getTalon().get(0).getR�le().equals("+4")){
														actionAFaire = true;
													}
													
													if(p.getTalon().get(0).getR�le().equals("CHANGEMENT")) {
														changementSens = true;
														p.changementSens(j.getNom());
													}
													
													if(p.getTalon().get(0).getR�le().equals("+2")) {
														nbAPiocher = 2;

													}
													
													if(p.getTalon().get(0).getR�le().equals("+4")) {
														nbAPiocher = 4;

													}
													
													if(p.getTalon().get(0).getR�le().equals("JOKER") || p.getTalon().get(0).getR�le().equals("+4")) {
														if(p.getTalon().get(0).getR�le().equals("+4")) {
															actionAFaire = true;
															nbAPiocher = 4;
														}
														choisirNvCouleur(p);													
													}
													aJou� = true;
												}
												
												else if(choix.equals("non")) {
													aJou� = true;
												}
												
												else {
													System.out.println("Veuillez taper oui ou non pour faire votre choix");
												}
											}
											catch(Exception e) {
												System.out.println("Veuillez taper oui ou non pour faire votre choix");
											}
										}
										else {
											System.out.println("Vous ne pouvez pas jouer la carte pioch�e, au tour du joueur suivant");
											aJou� = true;
										}
									}
								}
							}
							
							// le joueur actuel accuse le joueur pr�c�dent de bluff
							else if(commande.equals("/b")) {
								if(p.getModeJeu() == 1) {
									if(p.getTalon().get(0).getR�le().equals("+4") && actionAFaire == true) {
										jPr�c�dent.afficherMain();
										if(p.v�rifBluff(jPr�c�dent)) {
											System.out.println(jPr�c�dent.getNom() + " a bluff� ! Il va piocher 4 cartes");
											for(int i = 0; i < nbAPiocher; i++) {
												p.checkPaquetVide();
												jPr�c�dent.piocher(p.getPaquet());
												printPioche(jPr�c�dent);
											}
											
										}
										else {
											System.out.println(jPr�c�dent.getNom() + " n'a pas bluff� !" + jActuel.getNom() + " va piocher 6 cartes");
											nbAPiocher = nbAPiocher + 2;
											for(int i = 0; i < nbAPiocher; i++) {
												p.checkPaquetVide();
												jActuel.piocher(p.getPaquet());
												printPioche(jActuel);
											}
										}
										
										nbAPiocher = 0;
										aJou� = true;
										actionAFaire = false;
									}
									else {
										System.out.println("Vous ne pouvez accuser de bluff le joueur pr�c�dent que s'il vient de jouer une carte +4");
									}
								}
								else {
									System.out.println("Vous ne pouvez pas accuser un joueur de bluff en mode expert");
								}
								
							}
							
							
							// le joueur actuel joue une carte
							else if(commande.substring(0, 2).equals("/j")) {
								
								if(p.getTalon().get(0).getR�le().equals("+2") && actionAFaire == true) {
									System.out.println("Vous ne pouvez pas jouer, veuillez piocher deux cartes");
								}
								
								else if(p.getTalon().get(0).getR�le().equals("+4") && actionAFaire == true) {
									System.out.println("Vous ne pouvez pas jouer, veuillez piocher quatre cartes");
								}
								
								else {
									String tabCommande[] = commande.split(" ");
									try {
										int numCarte = Integer.parseInt(tabCommande[1]);
										numCarte = numCarte - 1;
										if(numCarte < j.getMain().size() && numCarte >= 0 ) {
											if(j.getMain().get(numCarte) instanceof CarteNoire) {
												if(tabCommande.length == 3) {
													tabCommande[2] = tabCommande[2].toLowerCase();
													if(tabCommande[2].equals("rouge") || tabCommande[2].equals("vert") || tabCommande[2].equals("jaune") || tabCommande[2].equals("bleu") && (!tabCommande[2].equals(p.getTalon().get(0).getCouleur()))) {
														if(p.v�rifCarte(p.getTalon(), j.getMain().get(numCarte), j)) {
															p.setTalon(j.jouer(numCarte, p.getTalon()));
															if(p.getTalon().get(0).getR�le().equals("+4")) {
																actionAFaire = true;
																nbAPiocher = 4;
															}
															p.getTalon().get(0).setNvCouleur(tabCommande[2]);
															System.out.println("La nouvelle couleur est le " + p.getTalon().get(0).getNvCouleur());
															aJou� = true;
															
														}
														else {
															System.out.println("Cette carte ne peut pas �tre jou�e maintenant");
														}
													}
													
													else{
														System.out.println("Veuillez choisir une couleur valide. Celle-ci doit �tre diff�rente de la couleur actuelle.");
													}
												}
												else {
													if(tabCommande.length < 3) {
														if(p.v�rifCarte(p.getTalon(), j.getMain().get(numCarte),  j)) {
															p.setTalon(j.jouer(numCarte, p.getTalon()));
															choisirNvCouleur(p);
															if(p.getTalon().get(0).getR�le().equals("+4")) {
																actionAFaire = true;
																nbAPiocher = 4;
															}
															aJou� = true;
														}
														else {
															System.out.println("Vous ne pouvez pas jouer cette carte");
														}
													}
													
													else {
														System.out.println("Veuillez r�it�rer votre commande");
													}
												}
											}
											else {
												if(p.v�rifCarte(p.getTalon(), j.getMain().get(numCarte), j)) {
													if(j.getMain().get(numCarte).getR�le().equals("+2") || j.getMain().get(numCarte).getR�le().equals("+4") || j.getMain().get(numCarte).getR�le().equals("PASSE")){
														actionAFaire = true;
													}
													
													if(j.getMain().get(numCarte).getR�le().equals("+2")) {
														nbAPiocher = 2;
													}
													
													if(j.getMain().get(numCarte).getR�le().equals("+4")) {
														nbAPiocher = 4;
													}
													aJou� = true;
													
													if(j.getMain().get(numCarte) instanceof CarteAction) {
														if(j.getMain().get(numCarte).getR�le().equals("CHANGEMENT")){
															changementSens = true;
															p.changementSens(j.getNom());
														}
													}
													
													p.setTalon(j.jouer(numCarte, p.getTalon()));
												}
												
												else {
													System.out.println("Vous ne pouvez pas jouer cette carte maintenant");
												}
											}
										}
										else {
											System.out.println("Le num�ro de carte que vous avez entr� n'est pas valide");
										}
									}
									catch(Exception e) {
										System.out.println("Veuillez entrer le num�ro de la carte � jouer. Celui-ci doit �tre un num�ro valide");
									}
								
								}
							}
							else {
								System.out.println("Cette commande n'existe pas");
							}
						}
						catch(Exception e){
							System.out.println("Veuillez entrer une commande valide");
						}
					}
					
					jPr�c�dent = j;
					finManche = m.checkFinManche(p.getJoueurs());
					if(finManche || changementSens) {
						break;
					}
					
				}
			}
			
		
			if(p.getTalon().get(0).getR�le().equals("+2") || p.getTalon().get(0).getR�le().equals("+4")) {
				// si la derni�re carte pioch�e est un +2 ou +4, le joueur suivant doit piocher avant le calcul du score de la manche
				for(int i = 0; i < nbAPiocher; i++) {
					p.checkPaquetVide();
					if(p.getJoueurs().indexOf(jActuel) == p.getJoueurs().size() -1){
						// si le dernier joueur � avoir jouer est le dernier de la liste
						p.getJoueurs().get(0).piocher(p.getPaquet());
						printPioche(p.getJoueurs().get(0));
					}
					else {
						p.getJoueurs().get(p.getJoueurs().indexOf(jActuel) + 1).piocher(p.getPaquet());
						System.out.println(p.getJoueurs().get(p.getJoueurs().indexOf(jActuel) + 1).getNom() + " pioche ");
						printPioche(jActuel);
					}
					
					
				}
			}
			System.out.println("Fin de la manche");
			m.calculPoints(p.getJoueurs());
			m.afficherPoints(p.getJoueurs());
		}
	}
	
	
	public static void printPioche(Joueur j) {
		// affiche la carte pioch�e par le joueur
		if(j.getMain().get(j.getMain().size()-1) instanceof CarteNum�rot�e) {
			System.out.println("Vous avez pioch� un " + j.getMain().get(j.getMain().size()-1).getNum�ro() + " " + j.getMain().get(j.getMain().size()-1).getCouleur());
		}
		
		if(j.getMain().get(j.getMain().size()-1) instanceof CarteAction) {
			if(j.getMain().get(j.getMain().size()-1).getR�le() == "PASSE") {
				System.out.println("Vous avez pioch� un passer le tour " + j.getMain().get(j.getMain().size()-1).getCouleur());
			}
			if(j.getMain().get(j.getMain().size()-1).getR�le() == "CHANGEMENT") {
				System.out.println("Vous avez pioch� un changement de sens " + j.getMain().get(j.getMain().size()-1).getCouleur());
			}
			
			if(j.getMain().get(j.getMain().size()-1).getR�le() == "+2") {
				System.out.println("Vous avez pioch� un +2 " + j.getMain().get(j.getMain().size()-1).getCouleur());
			}
		}
		
		if(j.getMain().get(j.getMain().size()-1) instanceof CarteNoire) {
			System.out.println("Vous avez pioch� un " + j.getMain().get(j.getMain().size()-1).getR�le());
		}
	}
	
	public static void choisirNvCouleur(Partie p) {
		String couleur = "";
		while(couleur.equals("")) {
			Scanner lectureCouleur = new Scanner(System.in);
			System.out.println("Quelle couleur attendue ?");
			try {
				String c = lectureCouleur.next().toLowerCase();
				String couleurActuelle = p.getTalon().get(1).getCouleur();
				if(c.equals(couleurActuelle)){
					System.out.println("Veuillez choisir une couleur diff�rente de la couleur actuelle");
				}
				else if(c.equals("rouge") ||c.equals("vert") || c.equals("jaune") || c.equals("bleu")) {
					couleur = c;
					p.getTalon().get(0).setNvCouleur(couleur);
					System.out.println("La nouvelle couleur est le " + c);
				}
				else{
					System.out.println("Veuillez entrer une couleur valide");
				}
			}
			catch(Exception e){
				System.out.println("Veuillez entrer une couleur valide");
			}
		}
	}

}
