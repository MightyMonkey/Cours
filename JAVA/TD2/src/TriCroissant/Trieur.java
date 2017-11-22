package TriCroissant;

public class Trieur 
{

	private int[] t;
	
	private Trieur(int[] t)
	{
		this.t = t;
	}
	public static void trier(int[] t)
	{
		Trieur tableau = new Trieur(t);
		tableau.trier(0,t.length -1);
	}
	
	private void trier(int debut, int fin)
	{
		if(fin-debut<2)
		{
			if( t[debut]>t [fin])
			{
				echanger(debut,fin);
			}
		}
		else
		{
			int milieu = debut + (fin-debut)/2;
			trier(debut,milieu);
			trier(milieu +1, fin);
			triFusion(debut,fin);
		}
	}
	
	private void echanger(int i, int j)
	{
		int valeur = t[i];
		t[i]= t[j];
		t[j] = valeur;
	}

	
	private void triFusion(int debut, int fin)
	{
		int[] tFusion = new int[fin-debut+1];
		int milieu = (debut+fin)/2;
		int i1=debut;
		int i2=milieu +1;
		int iFusion=0;
		while (i1<=milieu && i2<=fin)
		{
			if(t[i1]<t[i2])
			{
				tFusion[iFusion++]=t[i1++];
			}
			else
			{
				tFusion[iFusion++]=t[i2++];
			}			
		}
		if(i1>milieu)
		{
			for(int i=i2;i<=fin;)
			{
				tFusion[iFusion++]=t[i++];
			}
		}
		else
		{
			for(int i=i1;i<=milieu;)
			{
				tFusion[iFusion++]=t[i++];
			}
		}
		for (int i=0,j=debut;i<=fin-debut;)
		{
			t[j++]=tFusion[i++];
		}
	}
	
	public static void main(String[] args)
	{
		int[] t = {5,8,3,2,7,10,1};
		Trieur.trier(t);
		for(int i = 0; i<t.length; i++)
		{
			System.out.println(t[i]+ ";");
		}
		System.out.println();
	}
}

