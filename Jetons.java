public class Jetons{

//LES ATTRIBUTS
        private int[] jeton;
        private int dim;

//LES CONSTRUCTEURS
	
	public Jetons(){
		
		this.dim = 20;
		this.jeton = new int[this.dim];
		for (int i = 0; i < dim; i++)
			this.jeton[i] = 0;

	}

	public Jetons(int dim){
		
		this.dim = dim;
		this.jeton = new int[this.dim];
		for (int i = 0; i < 10; i++)
			this.jeton[i] = 5;
		for (int i = 11; i < 20; i++)
			this.jeton[i] = 4;

	}

//LES METHODES

//renvoyer un nombre aléatoire entre 1 et 20 et vérifier s'il est valide
	public int jetonRandom(){
		int j;
		do {
			final double d = Math.random();
			j = (int)(d*20+1);
		}
		while (this.jeton[j]<1);
		return j;
	}

//tirer un certain jeton
        public void tirerJetons(int j){
		this.jeton[j]--;
	}

//ajouter un certain jeton dans le sac de jetons
	public void ajouterJetons(int j){
		this.jeton[j]++;
	}

	public int getJeton(int i){
		return this.jeton[i];
	}

}
