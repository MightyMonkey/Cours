package compteur;

public class Main {

	public static void main(String args[]){
		int n=5;
		new Thread(() -> compteur.compteur(n,"titi")).start();
	    new Thread(() -> compteur.compteur(n,"tata")).start();
	    new Thread(() -> compteur.compteur(n,"toto")).start();
	    
	    /*compteur2[] Compteurs= { new compteur2("claude"),new compteur2("titi"),new compteur2("toto"),new compteur2("tata")};
	    for(int i=0;i<Compteurs.length;i++){
	    	Compteurs[i].start();
	    }*/
	}
}
