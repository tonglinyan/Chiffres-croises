import java.util.Scanner;
import java.util.*;
import java.io.*;

public class Jeu {

	static int nbJetons;
	static int dim = 20;
	static int hauteur = 17;
	static int largeur = 17;
	static int tmpJoueur = 0;
	static int tmpJoueurbis = 0;
	static int nbJoueur;
	static int jeton;
	static int nbJetonsChanges;
	static int x;
	static int y;
	static char direc;
	static String titre = "CLASSEMENT";
	static boolean termine = false;
	static Scanner sc = new Scanner(System.in);
	
// Initialisation du jeu

	static Plateau plateau = new Plateau(hauteur, largeur);
	//static Plateau plateautest = new Plateau(hauteur, largeur);
	static Jetons sacJetons = new Jetons(dim);
	static ArrayList<Joueur> joueurs = new ArrayList<Joueur>();	
	static ArrayList<Personne> personnes = new ArrayList<Personne>();
	static Joueur joueur;
	
	public static boolean changerJetons(){
		char reponse;
		do {
			System.out.println("\nVoulez-vous changer des jetons ? (o/n)");
			reponse = sc.next().charAt(0);
		} while (reponse != 'o' && reponse != 'n'); 
		return (reponse == 'o');
	}
	
	public static void main( String[] args ){
		
		System.out.println("Combien de joueurs?");
		int nbJoueur = sc.nextInt();
		for (int i = 0; i < nbJoueur; i++) {	
			Joueur joueur = new Joueur();
			joueurs.add(joueur);
		}
		
// Distribution des jetons

		plateau.setCibles();
		for(int i = 0; i < joueurs.size(); i ++){
			joueur = joueurs.get(i);		
			for (int j = 0; j < 6; j++) {
				jeton = sacJetons.jetonRandom(); 
				sacJetons.enleverJetons(jeton);
				joueur.ajouterJetons(jeton);
				
			}
			joueurs.set(i, joueur);
		}
		
// Plan du jeu
		
		joueur = joueurs.get(tmpJoueur);
		
		do {
			
			plateau.affichage();
			System.out.println("\n\n-------------------- JOUEUR " + (tmpJoueur+1) + " ----------------------");
			plateau.affichageCibles();
			joueur.affichage();
			
			if (changerJetons()) {
				do {
					System.out.println("\nCombien de jetons voulez-vous changer? (3 max)");
					nbJetonsChanges = sc.nextInt();
				}while (nbJetonsChanges>3 || nbJetonsChanges<1);
				for (int i = 0; i < nbJetonsChanges; i++){
					do{
						System.out.println("\njeton" + (i+1) + " : ");
						jeton = sc.nextInt();
					} 
					while (joueur.getJeton(jeton-1) < 1);
					joueur.enleverJetons(jeton-1);
					sacJetons.ajouterJetons(jeton-1);
				}
				
				for (int i = 0; i < 2; i++){
					jeton = sacJetons.jetonRandom(); 
					sacJetons.enleverJetons(jeton);
					joueur.ajouterJetons(jeton);
				}
				joueur.affichage();
			}
			
			else{	
		
//le joueur propose son coup puis, après validation, le score est calculé
			
			//plateautest = plateau;
			do {		
				do {
					System.out.println("\nNombre de jetons à poser :");
					nbJetons = sc.nextInt();
					System.out.println("\nChoisissez une position de départ (x puis y) :");
					x = sc.nextInt();
					y = sc.nextInt();
					do{
						System.out.println("\nen haut(h), en bas(b), à gauche(g), à droite(d)");
						direc = sc.next().charAt(0);
					} while (direc!='h' && direc!='b' && direc!='g' && direc!='d');
				} while(!(plateau.coupValide(x-1,y-1,direc,nbJetons) && x>=1 && x<=20 && y>=1 && y<=20));
				joueur.proposeCoup(nbJetons, sc);
			}
			while (!joueur.coupValide(nbJetons, x-1, y-1, direc, plateau, largeur, hauteur)); 
			joueur.poserJetons(nbJetons, x-1, y-1, direc, plateau);
			joueur.mainApresPose(nbJetons);
			joueur.calculScore(nbJetons);
			}
			termine = joueur.termine();
			tmpJoueurbis = tmpJoueur;
//on passe au joueur suivant

			if (tmpJoueur < joueurs.size()-1)
				tmpJoueur ++;
			else tmpJoueur = 0;
			System.out.println("\nJOUEUR " + tmpJoueur + " : ");
			joueur = joueurs.get(tmpJoueur);	
		} while (!termine);
		System.out.println("Joueur " + tmpJoueurbis + " a gagné !");
		
//affiche les scores
		for(int i = 0; i < joueurs.size(); i ++){
			joueur = joueurs.get(i);		
			System.out.println("Joueur " + (i+1) + " , saisissez votre nom et prénom : ");
			Personne personne = new Personne(sc.next(),sc.next(), joueur.getScore());
			//Personne personne = new Personne(sc.next(),sc.next(), 2);
			personnes.add(personne);
		}
		charger("score.txt", personnes, titre);
		enregistrer("score.txt", personnes, titre);

	}
	public static void charger(String file, ArrayList<Personne> personnes, String titre) {
		  String line;
		  String[] s;
		  
		  try {
			  FileReader fileReader = new FileReader(file);
			  BufferedReader bufferedReader = new BufferedReader(fileReader);
			  
			  //titre
			  line = bufferedReader.readLine();
			  titre = line;
			  
			  // Nombre comptes courant
			  line = bufferedReader.readLine();
			  while (line != ".") {
				  s = line.split("\t");
				  Personne personne = new Personne(s[0], s[1], Integer.parseInt(s[2]));
				  personne.AjouterPersonne(personnes);
				  line = bufferedReader.readLine();
			  }
			  
			  bufferedReader.close();
			  
		  } catch (Exception e) {
			  System.out.println("Erreur de chargement : " + e);
		  }
	}
	
	public static void enregistrer(String file, ArrayList<Personne> personnes, String titre) {
		 
		try {
			  Writer fWriter = new FileWriter(file);
			  BufferedWriter bWriter = new BufferedWriter(fWriter);
			  bWriter.write(titre);
			  bWriter.newLine();
			  
			  for (Personne p: personnes) {
				  bWriter.write(p.toString());
				  bWriter.newLine();
			  }
			  bWriter.write(".");
			  bWriter.close();
			  fWriter.close();
		  } catch (Exception e) {
			  System.out.println("Erreur d'enregistrement" + e);
		  }
	  }
	  
}


