/**************************************************************************
Cindy Zheng 
Playfair Cipher

Uses a playfair key to encode and decode text
 *************************************************************************/

import java.util.regex.*;

public class playfair{

    //make playfair keytable
    public static char[][] assemblekey (String letters){
	char keytxt[][] = new char[5][5];
	
        int h = 0;
        for(int i= 0; i<keytxt.length; i++){
            for(int j=0; j<keytxt[0].length; j++){
                keytxt[i][j]= letters.charAt(h);
                h++;
            }
        }
	return keytxt;
    }

    //edit cipher text to remove punctuation, spaces, make uppercase, and J to I
    public static String editinput(String text){
	text = text.replaceAll("[^A-Za-z]+", "").toUpperCase();
	text = text.replaceAll("[J]+", "I");
	return text;
    }

    //in the case of doubleletters
    public static String twoletters(String str){
	int i = 0;
	while (i <str.length()){
	    if ((str.charAt(i)==(str.charAt(i+1)))){
		str = str.substring(0, i+1) + "X" + str.substring(i+1);
	    }
	    i+=2;
	}
	
	return str;
    }
    
    public static void main(String[] args){
	//encode or decode
	String command = args[0];
	//encrypted text
	String cipher = args[1];
	//key
	String alpha = args[2];

	//making a key
	char[][] pfkey = assemblekey(alpha);

	String mystr = twoletters(editinput(cipher));
	System.out.println(mystr);
    } 
}

