public class Plateau {
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
		return this.tab[x][y];
    }

	public void setCase(int x, int y, int nb){
		this.tab[x][y] = nb;
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
	
	public boolean coupValide(int x, int y, char direc, int nb){
		int fin = 0;
		switch(direc) {
			case 'h' :
				fin = y;	
				for (int i = 0; i < nb; i++){
					while (fin>0 && this.getCase(x, fin-i) != 0) {
						fin--;		
					}
					fin--;
				}
				break; 
			case 'b':
				fin = y;
				for (int i = 0; i < nb; i++){
					while (fin<16 && !(this.getCase(x, fin+i)==0)) {
						fin++;		
					}
					fin++;
				}
			break;
			case 'g': 
				fin = x;
				for (int i = 0; i < nb; i++){
					while ( fin>0 && !(this.getCase(fin-i, y)==0)) {
						fin--;		
					}
					fin--;
				}
			break;
			case 'd':
				fin = x;
				for (int i = 0; i < nb; i++){
					while (fin<16 && !(this.getCase(fin+i, y)==0)) {
						fin++;		
					}
					fin++;
				}
			break;	
		}
		return (fin>=0 && fin<17);
	}		
	
	public void affichage(){
		int c;
		for(int i = 0;i<this.hauteur;i++) {
			System.out.println("");
			for(int j = 0;j<this.largeur;j++) {
				c = this.getCase(j,i);
				if (c<10) {
					System.out.print("  " + c);
				}
				else {
					System.out.print(" " + c);
				}
			}
		}
	} 
	
	public void affichageCibles() {
		System.out.println("\nnombres cibles : " + this.getCibles(0) + " " + this.getCibles(1) + " " + this.getCibles(2));	
	}
}
