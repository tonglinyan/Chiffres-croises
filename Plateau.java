public class Plateau{

//LES ATTRIBUTS
        private int[][] tab;
        private int hauteur;
	private int largeur;
        private int[] cibles;

//LES CONSTRUCTEURS
        public Plateau(int h, int l){

                largeur = l;
		hauteur = h;
                tab = new int[largeur][hauteur];
                for (int i = 0; i < hauteur; i++)
                        for (int j = 0; j < largeur; j++)
                                tab[i][j] = 0;
		cibles = new int[3];
                for (int i = 0; i < 3; i++)
                        cibles[i] = 0;
        }

//LES METHODES
        public int getCase(int x, int y){

			return tab[x][y];
	}

	public void setCase(int x, int y, int nb){
		tab[x][y] = nb;
	}
	
	public void setCibles(){
		boolean dif = true;
		for (int i = 0; i < 3; i++){
			do {
				final double d = Math.random();
				this.cibles[i] = (int)(d*36+15);
				for (int j = 0; j < i; j++){
					dif = dif && (this.cibles[i] != this.cibles[i-j-1]);					
				}
			}
			while (!dif);
		}
	}
	
	public int getCibles(int i){
		return this.cibles[i];
	}

	public boolean positionValide(int x, int y, char direc, int nb){
		boolean valide = (tab[x][y] == 0);
		switch (direc){
			case 'h': 
				{valide = (tab[x][y+1] != 0) || (tab[x+1][y] != 0) || (tab[x-1][y] != 0);
				for (int i = 1; i < nb; i++)
					valide = valide && (tab[x][y-i] == 0);}
			break; 
			case 'b':
				{valide = (tab[x][y-1] != 0) || (tab[x+1][y] != 0) || (tab[x-1][y] != 0);
				for (int i = 1; i < nb; i++)
					valide = valide && (tab[x][y+i] == 0);}
			break;
			case 'g': 
				{valide = (tab[x][y+1] != 0) || (tab[x+1][y] != 0) || (tab[x][y-1] != 0);
				for (int i = 1; i < nb; i++)
					valide = valide && (tab[x-i][y] == 0);}
			break;
			case 'd':
				{valide = (tab[x][y+1] != 0) || (tab[x][y-1] != 0) || (tab[x-1][y] != 0);
				for (int i = 1; i < nb; i++)
					valide = valide && (tab[x+1][y] == 0);}
			break; 
		}
		return valide;
	}

	public boolean termine(){
		
	}
	
	public void affichage(){
		
	} 
}
