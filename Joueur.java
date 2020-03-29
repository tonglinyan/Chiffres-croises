import java.util.Scanner;
public class Joueur extends Jetons{

//LES ATTRIBUTS
	private int nb_jetons;
	private int dimCoup;
	private int score;
	private int[][] coup;

//LES CONSTRUCTEURS

	public Joueur(){

		super();
		this.nb_jetons = 6;
		this.dimCoup = 3;
		this.coup = new int[this.nb_jetons][this.dimCoup];
		for (int i = 0; i < this.nb_jetons; i++)
			for (int j = 0; j < this.dimCoup; j++)
				this.coup[i][j] = 0;
		this.score = 0;

	}

//LES METHODES

	public void setCoup(int x, int y, int n){
		this.coup[x][y] = n;
	}
	
	public int getCoup(int x, int y){
		return this.coup[x][y];
	}

	
	public void poserJetons(int nbJetons, int x, int y, char direc){
		this.coup[0][1] = x;
		this.coup[0][2]	= y;	
		for (int i = 1; i < nbJetons; i++){
			Scanner sc = new Scanner(System.in);
			System.out.println("choisissez un jeton");
			this.coup[i][0] = sc.nextInt();
		}
		switch (direc){
			case 'h': 
				for (int i = 1; i < nbJetons; i++){
					this.coup[i][1] = x;
					this.coup[i][2] = y-i;
				}
			break; 
			case 'b':
				for (int i = 1; i < nbJetons; i++){
					this.coup[i][1] = x;
					this.coup[i][2] = y+i;
				}
			break;
			case 'g': 
				
				for (int i = 1; i < nbJetons; i++){
					this.coup[i][2] = y;
					this.coup[i][1] = x-i;
				}
			break;
			case 'd':
				
				for (int i = 1; i < nbJetons; i++){
					this.coup[i][2] = y;
					this.coup[i][1] = x+i;
				}
			break; 
		}
	}

	/*public boolean changerJetons(){
		
	}*/


	public boolean coupValide(int nbJetons, int x, int y, char direc, Plateau plateau){
		int somme = 0;
		for (int i = 0; i < nbJetons; i++)
			somme += this.coup[i][0];
		switch (direc){
			case 'h': 
				somme += plateau.getCase(x, y+1);
			break; 
			case 'b':
				somme += plateau.getCase(x, y-1);
			break;
			case 'g': 
				somme += plateau.getCase(x+1, y);
			break;
			case 'd':
				somme += plateau.getCase(x-1, y);
			break; 
		}
		return (somme == plateau.getCibles(1)) || (somme == plateau.getCibles(1)) || (somme == plateau.getCibles(2));
	}


	public void calculScore(){
	
	}

}
