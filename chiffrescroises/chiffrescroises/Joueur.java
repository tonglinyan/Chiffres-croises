import java.util.Scanner;

public class Joueur extends Jetons{

	//LES ATTRIBUTS
		private int nb_jetons;
		private int score;
		private int[] coup;
		private int somme;

	//LES CONSTRUCTEURS

		public Joueur(){

			super();
			this.nb_jetons = 6;
			this.coup = new int[this.nb_jetons];
			for (int i = 0; i < this.nb_jetons; i++)
					this.coup[i] = 0;
			this.score = 0;
			this.somme = 0;

		}

	//LES METHODES

		public void setCoup(int x, int n){
			this.coup[x] = n;
		}
		
		public int getCoup(int x){
			return this.coup[x];
		}

		public int getScore(){
			return this.score;	
		}

		public void proposeCoup(int nbJetons, Scanner sc) {
			System.out.println("choisissez les valeurs des jetons");
			for (int i = 0; i < nbJetons; i++){
				this.setCoup(i, sc.nextInt());
			}
		}	
		
		public void poserJetons(int nbJetons, int x, int y, char direc, Plateau p){
			switch (direc){
				case 'h': 
					for (int i = 0; i < nbJetons; i++){
						while (p.getCase(x, y-i)!=0) {
							y--;		
						}
						p.setCase(x,y-i,this.coup[i]);
					}
				break; 
				case 'b':
					for (int i = 0; i < nbJetons; i++){
						while (p.getCase(x, y+i)!=0) {
							y++;		
						}
						p.setCase(x,y+i,this.coup[i]);
					}
				break;
				case 'g': 
					
					for (int i = 0; i < nbJetons; i++){
						while (p.getCase(x-i, y)!=0) {
							x--;		
						}
						p.setCase(x-i,y,this.coup[i]);
					}
				break;
				case 'd':
					
					for (int i = 0; i < nbJetons; i++){
						while (p.getCase(x+i, y)!=0) {
							x++;		
						}
						p.setCase(x+i,y,this.coup[i]);
					}
				break; 
			}
		}
		
		public void mainApresPose(int nbJetons) {
			for(int i = 0;i<nbJetons;i++) {
				this.enleverJetons(this.coup[i]-1);
				this.nb_jetons--;
			}
		}


		public boolean coupValide(int nbJetons, int x, int y, char direc, Plateau plateau, int largeur, int hauteur){
			this.somme = 0;
			for (int i = 0; i < nbJetons; i++) {
				this.somme += this.coup[i];
			}	
			int i = 1;
			switch (direc){
				case 'b': 
					while(y-i >= 0 && !(plateau.getCase(x,y-i)==0)) {
						this.somme += plateau.getCase(x,y-i);
						i++;	
					}
				break; 
				case 'h':
					while(y+i < largeur && !(plateau.getCase(x,y+i)==0)) {
						this.somme += plateau.getCase(x,y+i);
						i++;	
					}
				break;
				case 'd': 
					while( x-i >= 0 && !(plateau.getCase(x-i,y)==0)) {
						this.somme += plateau.getCase(x-i,y);
						i++;	
					}
				break;
				case 'g':
					while( x+i < hauteur && !(plateau.getCase(x+i,y)==0)) {
						this.somme += plateau.getCase(x+i,y);
						i++;	
					}
				break; 
			}
			boolean b = (this.somme == plateau.getCibles(0) || this.somme == plateau.getCibles(1) || this.somme == plateau.getCibles(2));
			if (b != true) System.out.println("Coup invalide!");
			return b;
		}


		public void calculScore(int nbJetons){
			this.score += this.somme + nbJetons;
			this.somme = 0;
		}
		
		public void affichage() {
			System.out.println("\nChevalet : ");
			for(int i = 0; i < 20; i ++) {
				if (!(this.jeton[i] == 0)) {
					for(int j = 0; j < this.jeton[i]; j ++) {
						System.out.print(i+1 + " ");
					}
				}	
			}
		}
		
		public void affichageScore() {
			System.out.println(this.score);	
		}

		public boolean termine(){
			 return(this.nb_jetons == 0);
		}

}
