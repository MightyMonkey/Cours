package sgbd.impl;

import sgbd.stockage.*;

public class JointureBl {

	/*
	 * att1 et att2 sont les numéros de colonne.
	 */
	public Nuplet[] joinBl(Nuplet[] t1, int att1, Nuplet[] t2, int att2){
		int count = 0;
		
		for(int i=0;i<t1.length;i++){
			for(int j=0;j<t2.length;j++){
				if(t1[i].getAtt(att1)==t2[j].getAtt(att2))
					count++;
			}
		}
		Nuplet[] result = new NupletInt[count];
		count=0;
		for(int i=0;i<t1.length;i++){
			for(int j=0;j<t2.length;j++){
				if(t1[i].getAtt(att1)==t2[j].getAtt(att2)){
					result[count] = new NupletInt(t1[i].size()+t2[j].size());
					for(int k=0;k<t1[i].size();k++){
						result[count].putAtt(k, t1[i].getAtt(k));
					}
					for(int k=0;k<t2[j].size();k++){
						result[count].putAtt(k+t1[i].size(), t2[j].getAtt(k));
					}
					count++;
				}
			}
		}
		
		return result;
	}
}/*
if(t1[i].getAtt(att1) == t2[j].getAtt(att2)){
	result[count] = new NupletInt(t1[i].size()+t2[j].size());
	result[count] = t1[i];
	System.out.println(result[count].toString());
	//for(int k=0;k<t2[j].size();k++){
		//result[count].putAtt(k+t1[i].size(), t2[j].getAtt(k));
	//}
	count++;*/