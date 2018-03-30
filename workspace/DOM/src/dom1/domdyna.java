package dom1;

import java.io.FileWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class domdyna {

	public static void main(String[] args) {
		Document document = null;
		DocumentBuilderFactory fabrique = null;
		
		try{
			fabrique = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = fabrique.newDocumentBuilder();
			document = builder.newDocument();
			Livre Livre = new Livre(document);
			Element racine = (Element) document.createElement("livre");
			racine.setAttribute("titre", "salut");
			document.appendChild(racine);

				Element auteurs = (Element) document.createElement("auteurs");
				Element auteur = (Element) document.createElement("auteur");
				auteur.setAttribute("nom", "");
				auteur.setAttribute("prenom", "");
				racine.appendChild(auteurs);
				auteurs.appendChild(auteur);
				
				Element sections = (Element) document.createElement("sections");
				racine.appendChild(sections);
				Livre.ajouterSection("partie1");
				Livre.ajouterChapitre("partie1", "chap1.1");
				Livre.ajouterChapitre("partie1", "chap1.2");
				Livre.ajouterParagraphe("chap1.1", "texte");
				Livre.ajouterParagraphe("chap1.1", "texte2");
				Livre.ajouterParagraphe("chap1.2", "texte");
				Livre.ajouterParagraphe("chap1.2", "texte2");
				Livre.ajouterSection("partie2");
				Livre.ajouterChapitre("partie2", "chap2.1");
				Livre.ajouterChapitre("partie2", "chap2.2");
				Livre.ajouterParagraphe("chap2.1", "texte");
				Livre.ajouterParagraphe("chap2.1", "texte2");
				Livre.ajouterParagraphe("chap2.2", "texte");
				Livre.ajouterParagraphe("chap2.2", "texte2");
				
				
//sérialisation de l’arbre DOM
			TransformerFactory tfact = TransformerFactory.newInstance();
			Transformer transformer = tfact.newTransformer();
			transformer.setOutputProperty("encoding", "UTF-8");
			DOMSource source = new DOMSource(document);
			FileWriter fw = new FileWriter("livre.xml");
			StreamResult result = new StreamResult(fw);
			transformer.transform(source, result);
			
			Livre.afficherLivre();
		} 
		catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}	
}
