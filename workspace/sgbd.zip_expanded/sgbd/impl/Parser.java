package sgbd.impl;


import sgbd.stockage.*;
import sgbd.impl.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
	
	
	public String[] Parser(String commande)
	{
		Pattern selectfromwhere = Pattern.compile("(?i)\\s*select\\s*((?:(?:\\w+\\s*,\\s*)*)(?:\\w+)|[*])\\s*from\\s*((?:(?:\\w+\\s*,\\s*)*)(?:\\w+))\\s*(?:;|where\\s*(\\w+\\s*)(=|<|>)(\\s*\\w+)\\s*;)");
				
		Matcher simple =selectfromwhere.matcher(commande);
		System.out.println(simple.groupCount());
		if(simple.matches()){
			for(int i = simple.start();i <= simple.groupCount();i++){
				System.out.println(simple.group(i));
			}
		}
		
		return null;
	}
}
