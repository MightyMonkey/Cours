import java.io.*;

public class FileCreator {
	
	private static DataOutputStream dataFlow;
	
	public static void main(String[] args){
		String nomRep = "/eleves/cchastai/Documents/testJava";
		String maChaine = new String ("J'me présente, je m'appelle Henri. J'voudrais bien réussir ma vie. Etre aiméééééééééééééééééé!");
		try {
			File rep = new File (nomRep); 
			if (!rep.exists())  // si le repertoire n’existe pas
				rep.mkdir(); // le créer
			else if (!rep.isDirectory()) {
				System.err.println (nomRep +"n’est pas un repertoire");
				return;
			}
			File unFichier = new File(rep,"donnees.txt");
			unFichier.createNewFile();
			dataFlow = new DataOutputStream (new FileOutputStream(unFichier));
			dataFlow.writeBytes(maChaine);
		}
		catch (IOException e) {
			System.out.println("IOException levee:"+ e);
		}
	}
		
}

