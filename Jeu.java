import java.util.Scanner;
public class Jeu{

	int nbJoueur;
	ArrayList<Joueur> joueurs;
	int nbJetons;
	int dim = 20;
	int hauteur = 17;
	int largeur = 17;

	
// Initialisation du jeu
	Plateau plateau = new Plateau(hauteur, largeur);
	Jetons sacJetons = new Jetons(dim);
	Joueur joueur = new Joueur();

	public static void main( String[] args ){
		Scanner sc = new Scanner(System.in);
		System.out.println("Combien de joueurs?");
		nbJoueur = sc.nextInt;
		for (int i = 0; i < nbJoueur; i++) {
			joueurs.add(joueur);
		}
	
// Distribution des jetons

	plateau.setCibles();
	for(int i = 0; i < joueurs.size(); i ++){
		joueur = joueurs.get(i);		
		for (int j = 0; j < 6; i++) {
			jeton = sacJetons.jetonRandom(); 
			sacJetons.tirerJeton(jeton);
			joueur.ajouterJeton(jeton);
		}
		joueurs.set(i, joueur);
	}

	
// Plan du jeu
	tmp = 0;
	joueur = joueurs.get(tmp);
	while (non termine) {
		while (joueur.changerJetons()) {
			System.out.println("Combien de jetons vous voulez changer?");
			lire(nbJetonsChange);
			for (int i = 0; i < nbJetonsChange; i++){
				do{
					System.out.println("jeton" + i + " : ");
					lire(jeton);
				} 
				while (joueur.getJeton(jeton) < 1);
				joueur.tirerJeton(jeton);
				sacJetons.ajouterJeton(jeton);
			}
			for (int i = 0; i < 2; i++){
				jeton = sacJetons.jetonRandom(); 
				sacJetons.tirerJeton(jeton);
				joueur.ajouterJeton(jeton);
			}
		}


//le joueur propose son coup puis, après validation, le score est calculé
		do {		
			System.out.println("nombre de jetons à poser :");
			lire(nbJetons);
			System.out.println("Choisissez une position de départ");
			lire(x,y);
			System.out.println("en haut(h), en bas(b), à gauche(g), à droite(d)");
			lire(direc);
		}
		while (!(plateau.positionValide(x, y, direc, nbJetons)));
		
		joueur.poserJetons(nbJetons, x, y, direc, plateau);

		joueur.CoupValide(nbJetons, x, y, direc, plateau); 
		joueur.calculScore();

//on pose les jetons sur le plateau
		for (int i = 0; i < nbJetons; i++)
			plateau.setCase(joueur.getCoup(i,1), joueur.getCoup(i,2), joueur.getCoup(i,0));

//on passe au joueur suivant
		joueurs.set(1,joueur);
		if (tmp < joueur.size()-1)
			tmp ++;
		else tmp = 0;
		joueur = joueurs.get(tmp);
	}
	}
}



