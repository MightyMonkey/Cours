import java.io.IOException;

public class In {

	private static int c;
	
	
	private static boolean isWhite(){
		return Character.isWhitespace((char)c);
	}
	
	private static void readC(){
		try {
			System.in.read();
		} catch (IOException e) {
			c=-1;
		}
	}
	
	public static void init(){
		readC();
	}
	
	public static boolean isEmpty(){
		return c == -1;
	}
	
	public static String getString(){
		String s = "";
		while (!(isEmpty()) && (isWhite())){
			readC(); // retire les espaces
		}
		while (!(isEmpty() || isWhite())){
			s+= (char) c; readC();
		}
		return s;
	}
	
	public static int getInt(){
		return Integer.parseInt(getString());
	}
	
	public static double getDouble(){
		return Double.parseDouble(getString());
	}
}