package compteur;

public class compteur2  extends Thread{
    protected String nom;
    protected int max;
   
    public compteur2(String nom, int max)
    {
        this.nom = nom;
        this.max = max;
    }
    public compteur2(String nom)
    {
        this(nom,10);
    }
   
    public void run()
    {
        for(int i=1; i<= max; i++)
        {
            try {
                sleep((int)Math.random()*5000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                System.err.println(nom+"interrcompteur");
            }
            System.out.println(nom +" : "+i);
        }
        System.out.println("max"+nom +"a fini de compter jusqu'à"+max);
    }
   

}
