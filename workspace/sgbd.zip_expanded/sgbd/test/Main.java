package sgbd.test;

import sgbd.stockage.*;
import sgbd.impl.*;

import java.util.*;

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
		//testJointureBl();
		parse();
	}
	
	public static void parse()
	{
		System.out.println("\n donnez la commande select from  à effectuer");
		Scanner s = new Scanner(System.in);
		s.useDelimiter(",|\\s");
		s.findInLine("select");
		List<Integer> atts= new ArrayList<Integer>() ;
		do{
			atts.add(Integer.parseInt(s.next()));
		}while(s.hasNextInt());
		
		s.findInLine("from");
	    do{
	    	
	    }while(s.hasNext() && s.next() !="where");
	    
	    s.findInLine("where");
	    s.close();
	    
	    int[] arr = atts.stream().mapToInt(Integer::intValue).toArray();
	    testProjectionImpl(arr);
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
		
		for (Nuplet o : jointBl.joinBl(n, 0, m, 2)){
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
