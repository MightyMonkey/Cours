package prod;

public class Producteur extends Thread {

	private Buffer buf;
	private int identite;
	
	public Producteur(Buffer c, int n){
		buf=c;
		this.identite=n;
	}
	
	public void run(){
		for(int i=0;i<100;i++){
			buf.mettre(i);
			System.out.println("Producteur numero"+this.identite+" met: "+i);
			try{
				sleep((int)Math.random()*100);
			}catch(InterruptedException e){}
		}
	}
}
