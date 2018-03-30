package dom1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;

public class Livre {

	Document document = null;
	DocumentBuilderFactory fabrique = null;
	
	
	public Livre(Document document){
		this.document = document;
	}
	
	public void ajouterParagraphe(String titreChapitre, String texte){
		Element paragraphe = (Element) document.createElement("paragraphe");
		paragraphe.appendChild(document.createTextNode(texte));
		
		NodeList nodes = document.getElementsByTagName("chapitre");
		for (int i = 0; i < nodes.getLength(); i++) {
			Element node = (Element) nodes.item(i);
			if(node.getAttribute("titre") == titreChapitre){
				node.appendChild(paragraphe);
			}
		}
	}
	//Ajouter un paragraphe au chapitre. 
	public void ajouterChapitre(String titreSection, String titreChapitre) {
		Element chapitre = (Element) document.createElement("chapitre");
		chapitre.setAttribute("titre", titreChapitre);
		
		NodeList nodes = document.getElementsByTagName("section");
		for (int i = 0; i < nodes.getLength(); i++) {
			Element node = (Element) nodes.item(i);
			if(node.getAttribute("titre") == titreSection){
				node.appendChild(chapitre);
			}
		}
	}
	//Ajouter un chapitre à la section. 
	public void ajouterSection(String titreSection) {
		try{
			Element section = (Element) document.createElement("section");
			section.setAttribute("titre", titreSection);
			document.getElementsByTagName("sections").item(0).appendChild(section);
		} 
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	//Ajouter une section. 
	public static void afficherLivre() throws IOException{
		try {
			BufferedReader r = new BufferedReader( new FileReader( "/eleves/cchastai/Documents/workspace/DOM/livre.xml" ) );
			System.out.println(r.readLine());
			r.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	//Afficher dans la console le document XML final. 
}
