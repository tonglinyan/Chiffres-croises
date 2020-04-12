
import java.util.*;

public class Personne {

	//LES ATTRIBUTS
		private String prenom;
		private String nom;
		private int score;
		
	//LES CONSTRUCTEURS
		
		public Personne(String p, String n, int score){
			this.prenom = p;
			this.nom = n;
			this.score = score;	
		}

		public String getPrénom(){
				return this.prenom;		
			}

		public String getNom(){
				return this.nom;		
			}
		
		public int getScore(){
			return this.score;		
		}
		
		public void setScore(int s) {
			this.score = s;
		}

		public void AjouterPersonne(ArrayList<Personne> personnes) {
			int i = 0;
			boolean existe = false;
			while (i < personnes.size() && !existe) {
				if (this.prenom == personnes.get(i).getPrénom() && this.nom == personnes.get(i).getNom()) {
					existe = true;
					if (this.score > personnes.get(i).getScore()) {
						personnes.get(i).setScore(this.score);
					}	
				}
				i++;
			}
			if (!existe) personnes.add(this);
		}
		
		public String toString() {
			return (this.prenom + "\t" + this.nom +"\t" +  this.score);
		} 
}

