package compteur;


public class compteur {

	private static int position = 1;
	
	public static void compteur(int n,String s){
		int count;
		String nom=s;
		for(count=1;count<=n;count++){
			System.out.println(nom+" "+count);
			try {
				Thread.sleep((long)(Math.random() * 1000));
			} catch (InterruptedException e) {
				System.out.println(nom+" a foiré en itération "+count);
			}
		}
		System.out.println(nom+" a fini de compter jusqu'à "+n+" en position "+position);
		position++;
	}
	
	
}
