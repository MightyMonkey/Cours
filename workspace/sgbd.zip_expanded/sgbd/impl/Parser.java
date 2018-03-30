package sgbd.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
	
	
	public Matcher Parse(String commande)
	{
		Pattern selectfromwhere = Pattern.compile("(?i)\\s*select\\s*((?:(?:\\w+\\s*,\\s*)*)(?:\\w+)|[*])\\s*from\\s*((?:(?:\\w+\\s*,\\s*)*)(?:\\w+))\\s*(?:;|where\\s*(\\w+\\s*)(=|<|>)(\\s*\\w+)\\s*;)");
		Pattern update = Pattern.compile("(?i)\\s*update\\s*((?:(?:\\w+\\s*,\\s*)*)(?:\\w+)|[*])\\s*set\\s*(\\w+\\s*)=(\\s*\\w+)\\s*where\\s*(\\w+\\s*)(=)(\\s*\\w+)\\s*;");
		Pattern insert = Pattern.compile("(?i)\\s*insert into\\s*(\\w+)\\s*values\\s*((?:(?:\\w+\\s*,\\s*)*)(?:\\w+))\\s*;");
		Pattern delete = Pattern.compile("(?i)^\\s*delete\\s*from\\s*(\\w+)\\s*where\\s*(\\w+\\s*)(=|<|>)(\\s*\\w+)\\s*;");
		
		Matcher simple =selectfromwhere.matcher(commande);
		Matcher updat =update.matcher(commande);
		Matcher inser =insert.matcher(commande);
		Matcher delet = delete.matcher(commande);
		
		
		if(simple.matches()){
			return simple;
		}
		
		if(updat.matches()){
			return updat;
		}
		
		if(inser.matches()){
			return inser;
		}
		
		if(delet.matches()){
			return delet;
		}
		
		System.out.println("Erreur: expression non reconnue! \n Rappel:");
		System.out.println("select att,att3 from t (where att1 <|>|= 2);");
		System.out.println("update t set att=3 where att = 9");
		System.out.println("insert into t values 1,2 ,3,5;");
		System.out.println("delete from t where att <|>|= 3;");
		return null;
	}
}
