package Hagay_Elihai_Exe2;
import java.util.ArrayList;

import Hagay_Elihai_Exe2.Lexer.Type;
public class Parser {
	
	private static ArrayList<Lexer> memory = new ArrayList<Lexer>();
	private static ArrayList<Lexer> allTokens = new ArrayList<Lexer>();
	
	private static int x = 0; // this static for all method in this class. 
	private static String token;
	
	public static String identi = "IDENTIFIER";
	public static String term = "TERM";
	public static String operator = "OPERATOR";
	
	public Parser(ArrayList <Lexer> tokens) {
		allTokens.addAll(tokens); // array for the parser.
		memory.addAll(tokens); // array of identifier.
		for (int i= 0; i< memory.size(); i++) {
			if((memory.get(i).getType()).equals(term)||
					(memory.get(i).getType()).equals(operator)) { //this loop remove all of token that not identifier.  
			memory.remove(i); //remove all of token that not identifier.
			i--; //because after remove all index down one step, we write --.
			} //close if.
		}//close for. now We only have identifier.
		 //printArray(memory); //print array list after removing.
		
		//now we remove the double identifier:
		for (int i= memory.size() - 1; i>=0; i--) {
			for (int j= i-1; j>=0; j--) {
				if(memory.get(i).getName().equals(memory.get(j).getName())) {
					memory.remove(j);
				}
			}
		}
		//printArray(memory); //print array list after removing.
	}
	
	//Auxiliary methods:
	public static void updatValue(String nameIdentifier, int value) {
		int j=SearchIdentifier(nameIdentifier); //find the index of this identifier.
		memory.get(j).setValue(value);
System.out.println("-----------in updat-------------");
	}// close updatValue.
	
	public static int SearchIdentifier(String name) { //find the index of this name and return index.
		int temp = -1;
		for(int j=0 ; j<memory.size(); j++) {
			if ((memory.get(j).getName()).equals(name))
		temp = j;		
		}
		return temp;
	}
	
	private static int getValue() 
	{
		int i=0;
		int value=0;
		for(i=0; i<memory.size() ; i++)
		{
			if(token.equals(memory.get(i).getName())) {
				value = memory.get(i).getValue();}
		}
		return value;
	}
	 public void printArray(ArrayList <Lexer> tokens) {
     	System.out.println( "the all token in parser is: ");
     	for(Lexer y: tokens) {
     		System.out.println(y.getName() +" is:"+ y.getType()+"and the value is:"+ y.getValue());
     	} //close for.
     } //close printToken.
	 
	 public static void play() {
	boolean flag = true;
	token = allTokens.get(0).getName();
	while(x < allTokens.size() && flag){

	if((allTokens.get(x).getType()).equals(identi)) { //identifier that exists 
		System.out.println(allTokens.get(x).getType());
		
		if(allTokens.get(x+1).equals("=")) {
			String ident = token; 
			nextToken();
			nextToken();
			updatValue(ident, exp());	
			}
	}

	// output
	if(allTokens.get(x).getName().equals(identi) && x < allTokens.size()) { //identifier that exists 
		if(!allTokens.get(x+1).getName().equals("=")) {
		System.out.println(allTokens.get(x).getValue());
		nextToken();
		}
	}
	if(token.equals(")")){
		nextToken();
		} //edge case
	
	
	if(allTokens.get(x).getName().equals(";"))
	System.out.println(printIdnt(allTokens.get(x-1).getName()));
	flag = false;
	} //close while.
	} //close play
 
	 
	 public static int printIdnt(String name) {
		 int index = SearchIdentifier(name);
		 return memory.get(index).getValue();
	}

	public static int exp()
		{
			int currentToken = term();
			while(token.equals("+") || token.equals("-"))
			{
				switch((int)token.charAt(0)) {
					case 43 : // +
						nextToken();
						return currentToken + term();
					case 45 : // -
						nextToken();
						return currentToken - term();
				}
			}
			return currentToken;
		}
		
		public static int term()
		{
			int currentToken= factor();
			while(token.equals("*") || token.equals("/"))
			{
				switch((int)token.charAt(0)) {
					case 42 : // *
						nextToken();
						return currentToken * factor();
					case 47 : // /
						nextToken();
						return currentToken / factor();
				}
			}
			return currentToken;
		}
		
		public static int factor()
		{	System.out.println("===="+ x +"====");
			int currentToken; 
			if(allTokens.get(x).getType().equals(identi)) {
				int value = allTokens.get(x).getValue(); 
				nextToken(); 
				return value;
				} // if token == ident
			else {
				if((allTokens.get(x).getType()).equals(term)) { 
					currentToken = Integer.parseInt(allTokens.get(x).getName()); 
					nextToken(); 
					return currentToken;}
				else {
					if(allTokens.get(x).getName().equals("(")){
						nextToken(); 
						return exp();
						}
					if(token.equals(")")){
						nextToken();
						}
					else {System.out.println("Error!");
					}
				}
			}
			System.out.println("ERROR");
			return 1;
		}

		public static String nextToken()
		{
			if(x < allTokens.size()) { //if have more token in array list return this:
			 token = allTokens.get(++x).getName();
				return token;
			}
			else { //if this token is the last token return this:
				token = allTokens.get(x).getName();
				return token;
			}
		}

		
		
} //close class parser.   //a2=2;a1=(1+1);a3=3;a3=(a3+a1+a2);
