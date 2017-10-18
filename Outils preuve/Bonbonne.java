public class Bonbonne {

    public static String B(int n) {
	StringBuffer res = new StringBuffer();

	/* declaration des propriétés */
	res.append("B" + n + "=[c0");
	for (int i=1;i<=n;i++)
	    res.append(",c"+i);
	res.append("]{\n");

	/* etat et init */
	res.append("\tetat = " + (n+1) + ";\n");
	res.append("\tinit = 0\n;");

	/* proprietes */
	for (int i=0;i<=n;i++)
	res.append("\t" + i + " = c" + i +";\n");

	/* action vider */
	res.append("\n");
	for (int i=1;i<=n;i++)
	    res.append("\t" + i + " -> 0 [vider];\n");

	/* action remplir */
	res.append("\n");
	for (int i=0;i<n;i++)
	    res.append("\t" + i + " -> " + n + " [remplir];\n");

	/* action vider en precision de combien*/
	res.append("\n");
	for (int i=1;i<=n;i++)
	    res.append("\t" + i + " -> 0 [vider" + i + "];\n");

	/* action remplir en precisant la quantite ajoutée */
	res.append("\n");
	for (int i=0;i<n;i++)
	    res.append("\t" + i + " -> " + n + " [remplir" + (n-i) + "];\n");

	/* action ajouter en precisant la quantite ajoutée */
	res.append("\n");
	for (int i=0;i<n;i++)
	    for (int j=1; j<=n-i;j++)
		     res.append("\t" + i + " -> " + (i + j) + " [ajouter" + j + "];\n");

	/* action supprimer en precisant la quantite supprimé */
	res.append("\n");
	for (int i=1;i<=n;i++)
	    for (int j=1; j<=i;j++)
		     res.append("\t" + i + " -> " + (i - j) + " [supprimer" + j + "];\n");

	res.append("};;\n");
	return res.toString();
    }

    public static String systeme(int n1, int n2) {
	assert (n1 <= n2);

	StringBuffer res = new StringBuffer();
	res.append("systeme = < B" + n1 + " b" + n1 + " , B" + n2 + " b" + n2 + ">{\n");

	/* operations simples */
	res.append("<vider,_>;\n");
	res.append("<remplir,_>;\n");
	res.append("<_,vider>;\n");
	res.append("<_,remplir>;\n");

	/* operation de b1 vers b2 */
	for (int i =1; i<=n1; i++) {
	    res.append("<vider"+ i +", ajouter" + i + ">;\n");
	    res.append("<ajouter"+ i +", vider" + i + ">;\n");
	    res.append("<remplir"+ i +", supprimer" + i + ">;\n");
	    res.append("<supprimer"+ i +", remplir" + i + ">;\n");
	}
	res.append("};;\n");

	return res.toString();
    }

    public static void main(String[] arg) {
	int n1 = Integer.parseInt(arg[0]);
	int n2 = Integer.parseInt(arg[1]);
	System.out.println(B(n1));
	System.out.println(B(n2));
	System.out.println(systeme(n1,n2));
    }


}