package Hagay_Elihai_Exe2;
import java.util.ArrayList;
import java.util.Scanner;
import Hagay_Elihai_Exe2.Lexer.Type;

public class main {

	public static void main(String[] args) {
	Scanner sc = new Scanner(System.in); // to input
	int flag = 1;
	do {
	System.out.println( "please enter your code in one line. don't push 'ENTER' until you finish your code: ");
	String input = sc.nextLine(); // save the string from user.
	Lexer l1 = new Lexer();
	Parser p1= new Parser(l1.splitToken(input)); //for parser.
	p1.play();
	System.out.println("do you wont to enter enother code? click 'y' to yes and 'n' to no!");
	String input1 = sc.nextLine(); 
	
	while(!(input1.equals("y")) || !(input1.equals("n")))
	if(input1.equals("y")) {
		break;
	}else if(input1.equals("n")) {
		flag=0;
		System.out.println("....goodbay....");
		break;
	}else {
		System.out.println("wrong enter! you most enter 'y' or 'n'");
		 input1 = sc.nextLine();
		
	}
	} while(flag!=0);
	
	}//close main

}
