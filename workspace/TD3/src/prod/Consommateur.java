package prod;

public class Consommateur extends Thread{

	private Buffer buf;
	private int identite;
	
	public Consommateur(Buffer c,int n){
		this.buf=c;
		this.identite=n;
	}
	
	public void run(){
		int val=0;
		for(int i=0;i<10;i++){
			val=buf.prendre();
			System.out.println("Consommateur numero "+this.identite+" prend: "+val);
		}
	}
}
