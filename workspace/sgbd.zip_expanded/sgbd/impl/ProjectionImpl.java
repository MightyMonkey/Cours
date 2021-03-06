package sgbd.impl;

import sgbd.stockage.Nuplet;

public class ProjectionImpl implements sgbd.operateurs.Projection {

	/**
	 * permet de projeter un Nuplet[] t sur un ensemble d'attributs
	 * exemple: project( [4,9,5,3,7,6,2], [1,2,3]) = [9,5,3]
	 */
	@Override
	public Nuplet[] project(Nuplet[] t, int[] atts) {
		NupletInt[] result = new NupletInt[t.length];
		for(int i=0;i<t.length;i++){
			result[i]= new NupletInt(atts.length);
			for(int j=0;j<atts.length;j++){
				result[i].putAtt(j,t[i].getAtt(atts[j]));
			}
		}
		return result;
	}

}
