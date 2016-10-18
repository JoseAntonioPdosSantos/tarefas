package br.com.tarefas.model.util;

import java.util.ArrayList;
import java.util.List;

public class ASCIIUtil {

	private static List<Character> caracteres;

	static {
		caracteres = new ArrayList<>();
		caracteres.add('A');
		caracteres.add('B');
		caracteres.add('C');
		caracteres.add('D');
		caracteres.add('E');
		caracteres.add('F');
		caracteres.add('G');
		caracteres.add('H');
		caracteres.add('I');
		caracteres.add('J');
		caracteres.add('K');
		caracteres.add('L');
		caracteres.add('M');
		caracteres.add('N');
		caracteres.add('O');
		caracteres.add('P');
		caracteres.add('Q');
		caracteres.add('R');
		caracteres.add('S');
		caracteres.add('T');
		caracteres.add('U');
		caracteres.add('V');
		caracteres.add('W');
		caracteres.add('X');
		caracteres.add('Y');
		caracteres.add('Z');

		caracteres.add('a');
		caracteres.add('b');
		caracteres.add('c');
		caracteres.add('d');
		caracteres.add('e');
		caracteres.add('f');
		caracteres.add('g');
		caracteres.add('h');
		caracteres.add('i');
		caracteres.add('j');
		caracteres.add('k');
		caracteres.add('l');
		caracteres.add('m');
		caracteres.add('n');
		caracteres.add('o');
		caracteres.add('p');
		caracteres.add('q');
		caracteres.add('r');
		caracteres.add('s');
		caracteres.add('t');
		caracteres.add('u');
		caracteres.add('v');
		caracteres.add('w');
		caracteres.add('x');
		caracteres.add('y');
		caracteres.add('z');

		caracteres.add('0');
		caracteres.add('1');
		caracteres.add('2');
		caracteres.add('3');
		caracteres.add('4');
		caracteres.add('5');
		caracteres.add('6');
		caracteres.add('7');
		caracteres.add('8');
		caracteres.add('9');
	}

	public static char getChar(int index){
		return caracteres.get(index);
	}
	
	public static int length(){
		return caracteres.size();
	}
}
