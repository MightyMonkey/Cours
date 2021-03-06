package sgbd.impl;

import sgbd.stockage.Nuplet;

public class RestrictionInt implements sgbd.operateurs.Restriction{

	@Override
	public Nuplet[] egalite(Nuplet[] t, int att, Object v){
		// Retourne les Nuplets de la table t dont l'attribut att est �gal � la valeur v
		int count=0;
		
		for(int i=0;i<t.length;i++){
			//System.out.println(t[i].getAtt(att).getClass()+" "+v.getClass());
			if(t[i].getAtt(att) == v){
				count++;
			}
		}
		Nuplet[] result = new NupletInt[count];
		count=0;
		
		for(int i=0;i<t.length;i++){
			if(t[i].getAtt(att) == v){
				result[count]=t[i];
				count++;
			}
		}
		return result;
		
	}
	
	@Override
	public Nuplet[] superieur(Nuplet[] t, int att, Object v){
		// Retourne les Nuplets de la table t dont l'attribut att est sup�rieur ou �gal � la valeur v
		int count=0;
		
		for(int i=0;i<t.length;i++){
			//System.out.println(t[i].getAtt(att).getClass()+" "+v.getClass());
			if((byte)t[i].getAtt(att) >= (byte)v){
				count++;
			}
		}
		Nuplet[] result = new NupletInt[count];
		count=0;
		
		for(int i=0;i<t.length;i++){
			if((byte)t[i].getAtt(att) >= (byte)v){
				result[count]=t[i];
				count++;
			}
		}
		return result;
	}
	
	@Override
	public Nuplet[] inferieur(Nuplet[] t, int att, Object v){
		// Retourne les Nuplets de la table t dont l'attribut att est inferieur ou �gal � la valeur v
		int count=0;
		
		for(int i=0;i<t.length;i++){
			//System.out.println(t[i].getAtt(att).getClass()+" "+v.getClass());
			if((byte)t[i].getAtt(att) < (byte)v){
				count++;
			}
		}
		Nuplet[] result = new NupletInt[count];
		count=0;
		
		for(int i=0;i<t.length;i++){
			if((byte)t[i].getAtt(att) < (byte)v){
				result[count]=t[i];
				count++;
			}
		}
		return result;
	}
	
}
