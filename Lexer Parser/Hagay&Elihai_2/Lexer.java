package Hagay_Elihai_Exe2;
import java.util.ArrayList;

public class Lexer {
	 
	public enum Type {IDENTIFIER, OPERATOR, TERM}; //to say us how token is.
	//identifier to name of variable. operator to "+,-,\,*,=,;"
	// term to number.
	
	private String code;
    private final Type t; //save us this type of token.
	private int value;
    
	
	public Lexer()
    {
    	this.code =";";
    	this.t=Type.IDENTIFIER;
    }
        public Lexer(String code, int begin, int end, Type t) {
        	this.code = code.substring(begin, end);
        	this.t = t; //save us this type of token.
        }//close c'tor Token.
        
        public Lexer(String ch, Type t) {
        	code = ch;
        	this.t = t;
        	
        } //close c'tor for operator token
        
       public String getName(){
        	return this.code;
        }
       public Type getType() {
    	   return this.t;
       }
       public void setValue(int value) { //to updet the value of this identifier.
    	   this.value= value;
       }
       public int getValue() {
    	  return this.value;
       }
        
        public ArrayList<Lexer> splitToken(String input) {
        	int length = input.length(); // length of string for loop.
        	int start = 0; //counter to end token.
        	ArrayList<Lexer> tokens = new ArrayList<Lexer>();
        	
        	for (int i=0; i< length; i++) {
        		if (input.charAt(i)=='='||input.charAt(i)=='+'||input.charAt(i)=='-'||
        				input.charAt(i)=='/'||input.charAt(i)=='*'||input.charAt(i)==';'
        				||input.charAt(i)=='('||input.charAt(i)==')') {
        			
        			//token Before the operator:
        			if(input.charAt(start)=='a'||input.charAt(start)=='b'||input.charAt(start)=='c'){
        				Type r1 = Type.IDENTIFIER;
        				Lexer t1 = new Lexer(input, start, i, r1); //this and next substring cat token untill 'i' because substring not chose the last char.
        				tokens.add(t1); //save the identifier token in array.
        			} else if(input.charAt(start)>='0'||input.charAt(start)<='9') {
        				Type r1 = Type.TERM;
        				Lexer t1 = new Lexer(input, start, i, r1);
        				tokens.add(t1);  //save the term token in array
        			}
        			// token of operator:
        			
        			Type r = Type.OPERATOR;
        			String operator = input.substring(i,i+1);
        			Lexer t2 = new Lexer(operator , r);
        			tokens.add(t2); //save the operator token in array.
        			start=i+1; //to up "start" to next start of new token.        			
        		}//close external if.
        			
        	}//close for
        	
        	//printToken(tokens);
        	return tokens;
        } //close splitToken.
        public void printToken(ArrayList <Lexer> tokens) {
        	System.out.println( "the all token is: ");
        	for(Lexer x: tokens) {
        		System.out.println(x.code +" is:"+ x.t);
        	} //close for.
        } //close printToken.

        
//        public ArrayList<Lexer> sendArray (ArrayList <Lexer> tokens){
//        	return tokens;
//        }
//	
	
        
}//close class Lexer.

//
