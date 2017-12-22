import org.chocosolver.solver.Model;
import org.chocosolver.solver.Solution;
import org.chocosolver.solver.Solver;
import org.chocosolver.solver.variables.IntVar;
import org.chocosolver.solver.search.strategy.*;
import java.lang.Math;


public class Sudoku {
public static int n = 9;

public static void main(String[] args) {
	long time = System.currentTimeMillis();
	System.out.println("Sudoku");
	assert Math.rint(Math.sqrt(n))==Math.sqrt(n);
	Model m = new Model();
	IntVar[][] vars = createVariables(m);
	postConstraints(m, vars);
	Solver s = m.getSolver();
	s.read(m);
	setHeuristic(s);
	s.solve();
	displayResult(s, vars);
	System.out.println("Time ellapsed: " + (System.currentTimeMillis() - time) + "ms.");
}
// 1. Création des variables
private static IntVar[][] createVariables(Model m) {
	return m.intVarMatrix("Case", n, n, 1, n);
}
// 2. Création des contraintes sur les lignes, colonnes, carrés
private static void postConstraints(Model m, IntVar[][] vars) {
	for(int i = 0; i < n; i++) {
		IntVar[] lignes = new IntVar[n];
		IntVar[] colonnes = new IntVar[n];
		IntVar[] carres = new IntVar[n];
		for(int j = 0; j < n; j++) {
			lignes[j] = vars[i][j];
			colonnes[j] = vars[j][i];
			int sqrtn=(int) Math.sqrt(n);
			carres[j] = vars[j%sqrtn + (i % sqrtn) * sqrtn][j/sqrtn + (i / sqrtn) * sqrtn];
		}
		postAlldiff(m, lignes);
		postAlldiff(m, colonnes);
		postAlldiff(m, carres);
	}
}
// 3. Création d'une contrainte Alldiff
private static void postAlldiff(Model m, IntVar[] vars) {
	m.allDifferent(vars).post();
}
// 4. Réglage des heuristiques de choix de variables et de valeurs
private static void setHeuristic(Solver s) {
	s.setVarIntSelector(new RandomIntVarSelector(s));
	s.setValIntIterator(new IncreasingDomain());
	 }
// 5. Affichages des résultats
private static void displayResult(Solver s, IntVar[][] vars) {
	int sqrtn=(int) Math.sqrt(n);
	for(int i = 0; i < n; i++) {
		if (i%sqrtn == 0) {
			String str="";
			for(int k=0;k<n+(2*(sqrtn+1));k++) str=str.concat("-");
			System.out.println(str);
		}
		for(int j = 0; j < n; j++) {
			if (j%sqrtn == 0) System.out.print("||");
			int val=s.getIntVal(vars[i][j]);
			if(val>=10){
				char c= (char) ('A'+val-10);
				System.out.print(c);
			}
			else System.out.print(val);
		}
		System.out.println("||");
	}
	String str="";
	for(int k=0;k<n+(2*(sqrtn+1));k++) str=str.concat("-");
	System.out.println(str);
}
}