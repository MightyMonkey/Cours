package sgbd.test;

import sgbd.stockage.*;
import sgbd.impl.*;
import java.util.*;
import java.util.regex.Matcher;


public class Main {
	
	public static final int datasetSize = 100;
	public static final int nupletSize = 10;
	private static Table t;
	
	public static void main(String[] args){
		
		// G�n�ration des donn�es
		Nuplet[] tab = new NupletInt[datasetSize];
		for(int i=0;i<datasetSize;i++){
			tab[i] = new NupletInt(nupletSize);
			for(int j=0;j<nupletSize;j++){
				tab[i].putAtt(j, (byte)(j+i));
			}
		}

		// Impl�mentation avec Table
		
		System.out.println("------------------------------------------------");	
		System.out.println("Cr�ation d'une table");
		t = new TableInt("/tmp/table2", nupletSize);
		for(int i=0;i<datasetSize;i++){
			t.put(tab[i]);
			}
		System.out.println("Lecture d'une table");
		for(int i=0;i<datasetSize;i++){
			System.out.println(t.get(i).toString());
			}
		
		
		// Utilisation de getByAtt
		System.out.println("Test GetByAtt");
		Nuplet[] res = t.getByAtt(4, (byte)50);
		for(Nuplet n : res){
			System.out.println(n.toString());
		}
		
		
		//testRestrictionInt(4,(byte)50);
		//testProjectionImpl();
		testJointureBl();
		testParseur();
		
		
	}
	/*
	 * Fonction de gestion du parseur:
	 * la commande entrée est divisée en expression régulières par la classe Parser
	 * On teste ensuite le type de commande sql entré et on effectue les traitements
	 */
	public static void testParseur(){
		Nuplet[] n;
		n=t.fullScan();
		RestrictionInt restrictInt = new RestrictionInt();
		Parser parser = new Parser();
		System.out.println("entrez une commande SQL");
		Scanner s = new Scanner(System.in);
		String commande = s.nextLine().toLowerCase();
		s.close();
		Matcher result = parser.Parse(commande);
		
		//Si la commande est un select
		if(result.toString().toLowerCase().contains("select")){
			//On vérifie si le select contient une restriction where
			if(result.group(3)!= null){
				Nuplet[] where=null;
				//Si c'est le cas, on restreint la table selon les paramètres du where avant de faire le select
				if(result.group(4).matches("=")){
					where = restrictInt.egalite(n, Integer.parseInt(result.group(3)), (byte)Integer.parseInt(result.group(5)));
				}
				if(result.group(4).matches(">")){
					where = restrictInt.superieur(n, Integer.parseInt(result.group(3)), (byte)Integer.parseInt(result.group(5)));
				}
				if(result.group(4).matches("<")){
					where = restrictInt.inferieur(n, Integer.parseInt(result.group(3)), (byte)Integer.parseInt(result.group(5)));
				}
				
				//Bloc de traitement du select
				if(result.group(1).equals("*")){
					System.out.println("résultat de la commande :");
					for(Nuplet r : where)
						System.out.println(r);
				}
				else{
					String[] argchar= result.group(1).replaceAll(" ","").split(",");
					int[] argint=new int[argchar.length];
					for(int i=0;i<argchar.length;i++){
						argint[i]= Integer.parseInt(argchar[i]);
					}
					ProjectionImpl proj = new ProjectionImpl();
					Nuplet[] res = proj.project(where, argint);
					for(Nuplet r : res)
						System.out.println(r);
				}				
			}else{
				//Si on n'a pas de where, on passe directement au bloc select
				if(result.group(1).equals("*")){
					System.out.println("résultat de la commande :");
					for(Nuplet r : n)
						System.out.println(r);
				}
				else{
					String[] argchar= result.group(1).replaceAll(" ","").split(",");
					int[] argint=new int[argchar.length];
					for(int i=0;i<argchar.length;i++){
						argint[i]= Integer.parseInt(argchar[i]);
					}
					ProjectionImpl proj = new ProjectionImpl();
					Nuplet[] res = proj.project(n, argint);
					for(Nuplet r : res)
						System.out.println(r);
				}
			}
			
		}
		
		//Si la commande est un update
		if(result.toString().toLowerCase().contains("update")){
			//On vérifie que la colonne à update et la colonne qui sert dans le where sont les mêmes, sinon la fonction update ne sait pas gérer
			if(result.group(2).equals(result.group(4))){
				Nuplet[] res=null, m;
				res = t.update(Integer.parseInt(result.group(2)), Byte.parseByte(result.group(6)), Byte.parseByte(result.group(3)));
				System.out.println("résultat de la commande");
				for(Nuplet b : res){
					System.out.println(b.toString());
				}
			}else{
				System.out.println("Merci de restreindre la même colonne à mettre à jour");
			}
		}
		
		//Si la commande est un insert
		if(result.toString().toLowerCase().contains("insert")){
			//On crée le tableau d'entiers à partir de la chaîne isolée par le parseur
			String[] argchar= result.group(2).replaceAll(" ","").split(",");
			byte argbyte[]=new byte[argchar.length];
			//Avant d'insérer le Nuplet on vérifie qu'il soit de la bonne taille par rapport à la table
			if(argchar.length == t.fullScan()[1].size()){
				for(int i=0;i<argchar.length;i++){
					argbyte[i]= Byte.parseByte(argchar[i]);
				}
				Nuplet r = new NupletInt(argbyte);
				System.out.println("résultat de la commande: ");
				Nuplet[] res= t.insert(r);
				for(Nuplet b : res){
					System.out.println(b.toString());
				}
			}else{
				System.out.println("Erreur: les nuplets de sont pas de la même taille!");
			}
			
		}
		
		//Si la commande est un delete
		if(result.toString().toLowerCase().contains("delete")){
			Nuplet[] res=null;
			//On détermine les conditions du delete pour adapter le traitement
			if(result.group(3).equals("=")){
				res=t.delete(Integer.parseInt(result.group(2)),Byte.parseByte(result.group(4)));
			}
			if(result.group(3).matches(">")){
				for(int i=t.size();i>Integer.parseInt(result.group(4));i--){
					res=t.delete(Integer.parseInt(result.group(2)),(byte)i);
				}
				res = restrictInt.inferieur(n, Integer.parseInt(result.group(2)), (byte)(Integer.parseInt(result.group(4))+1) );
			}
			if(result.group(3).matches("<")){
				for(int i=0;i<Integer.parseInt(result.group(4));i++){
					res=t.delete(Integer.parseInt(result.group(2)),(byte)i);
				}
				res = restrictInt.superieur(n, Integer.parseInt(result.group(2)), (byte)Integer.parseInt(result.group(4)));
			}
			System.out.println("résultat de la commande : ");
			for(Nuplet b : res){
				System.out.println(b.toString());
			}
		}
	}
	
	public static void testJointureBl()
	{
		System.out.println("\n===TESTS JOINTURES===");
		Nuplet[] n,m;
		n=t.fullScan();
		ProjectionImpl projImpl = new ProjectionImpl();
		m = projImpl.project(n, new int[]{1,2,3});
		
		System.out.println("test jointure BL");
		JointureBl jointBl = new JointureBl();
		
		for (Nuplet o : jointBl.joinBl(n, 9, m, 0)){
			System.out.println(o.toString());
		}
	}
	
	public static void testProjectionImpl(int[] atts)
	{
		System.out.println("\n===TESTS PROJECTION===");
		Nuplet[] n;
		n=t.fullScan();
		ProjectionImpl projImpl = new ProjectionImpl();
		
		System.out.println("projection sur les attributs donnés");
		for (Nuplet m : projImpl.project(n, atts)){
			System.out.println(m.toString());
		}
	}
	
	public static void testRestrictionInt(int att, Object object)
	{
		System.out.println("\n====TESTS RESTRICTION====");
		RestrictionInt restrictInt = new RestrictionInt();
		Nuplet[] m;
		m=t.fullScan();
		for(Nuplet l : m)
			System.out.println(l.toString());
		
		System.out.println("test egalite");
		for(Nuplet n : restrictInt.egalite(m, att, object)){
			System.out.println(n.toString());
		}
		System.out.println("test superieur");
		for(Nuplet n : restrictInt.superieur(m, att, object)){
			System.out.println(n.toString());
		}
		System.out.println("test inferieur");
		for(Nuplet n : restrictInt.inferieur(m, att, object)){
			System.out.println(n.toString());
		}
	}
}

