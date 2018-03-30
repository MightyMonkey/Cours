public class TestDom1 {
	
	public static void printDomTree(Node node, String indent)
	// ume methode pour afficher le sous-arbre DOM
	// à partir du noeud node en paramètre
	// avec une indentation conformement à l'arbre d'élément
	{
		String type;
		switch (node.getNodeType()) {
		// type d'élément XML
		case Node.ATTRIBUTE_NODE:
			type = "attribut";
			break;
		case Node.CDATA_SECTION_NODE:
			type = "CDATA";
			break;
		case Node.COMMENT_NODE:
			type = "comment";
			break;
		case Node.DOCUMENT_FRAGMENT_NODE:
			type = "document fragment";
			break;
		case Node.DOCUMENT_NODE:
			type = "document";
			break;
		case Node.DOCUMENT_TYPE_NODE:
			type = "document type";
			break;
		case Node.ELEMENT_NODE:
			type = "node";
			break;
		case Node.ENTITY_NODE:
			type = "entity";
			break;
		case Node.ENTITY_REFERENCE_NODE:
			type = "entity reference";
			break;
		case Node.NOTATION_NODE:
			type = "notation";
			break;
		case Node.PROCESSING_INSTRUCTION_NODE:
			type = "processing instruction";
			break;
		case Node.TEXT_NODE:
			type = "text";
			break;
		default:
			type = "none";
		}
		System.out.println(indent + "type : " + type);
		System.out.println(indent + "noeud name : " + node.getNodeName());
		// nom de l'élément ou de l'attribut ou ..
		System.out.println(indent + "value : " + node.getNodeValue());
		// valeur de l'attribut ou texte d'un élément texte ou ..
		if (node.hasChildNodes()) {
			// comme les Tree-s JAVA .....
			Node nextFils = node.getFirstChild();
			while (nextFils != null) {
				printDomTree(nextFils, indent + " ");
				nextFils = nextFils.getNextSibling();
			}
		}
	}
	
	public static void main(String argv[]) throws IOException {
		if (argv.length != 1) {
			System.err.println("Usage: cmd filename");
			System.exit(1);
		}
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		// recuperer une fabrique de constructeur de Dom
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			// recuperer un constructeur de Dom
			Document document = builder.parse(new File(argv[0]));
			// parser pour construire le Dom
			Element rootElement = document.getDocumentElement();
			// recuperer le premier element du Dom (cad root)
			printDomTree(rootElement, "");
		} catch (Throwable t) {
			t.printStackTrace();
			System.exit(1);
		}
		System.exit(0);
	}
	
}
