/**************************************************************************            
Cindy Zheng                                                                             
Playfair Cipher                                                                         

The mapping rules to encode and decode Playfair ciphers
 *************************************************************************/

class Rules{

    public char[][] keytable;
    public String[] code;
    public String direction;

    //constructor
    public Rules(String command, char[][]pf, String mystr){
	keytable = pf;
	direction = command;

	code = new String[mystr.length()/2];
	for(int i =0; i<mystr.length(); i+=2){
	    code[i/2]= ""+mystr.charAt(i) + mystr.charAt(i+1);
	}
    }

    public String finding(char pair){
	String spot = "";
	for (int i= 0; i<keytable.length; i++){
	    for (int j=0; j<keytable[0].length; j++){
		if (pair==(keytable[i][j])){
		    spot = "" + i+ j;
		}
	    }
	}
	return spot;
    }
    
    public String message(){
	String text= "";
	for (int i = 0; i< code.length; i++){
	    String letters = pair(code[i]);
	    text= text+ letters;
	}
	return text; //returns final text
    }

    public String pair(String s){
	String location1 = finding(s.charAt(0));
	String location2 = finding(s.charAt(1));
	String cheese= "";
	if (location1.substring(0,1).equals(location2.substring(0,1))){
	    cheese = row(location1, location2);
	}
	else if (location1.substring(1).equals(location2.substring(1))){
           cheese =  column(location1, location2);
	}
	else{
	    cheese = rule3(location1, location2);
	}
	return cheese;
    }

    public String row(String one, String two){
	String back = "";
	int rownum = Integer.parseInt(one.substring(0,1));
	int[] cols = new int[2];
	cols[0] = Integer.parseInt(one.substring(1));
	cols[1] = Integer.parseInt(two.substring(1));

	if (direction.equals("encode")){
	    if (rownum == 4){
		for(int i=0; i<cols.length; i++){
		    back= back + keytable[0][cols[i]];
		}
	    }
	    else {
		for(int i=0; i<cols.length; i++){
                    back= back + keytable[rownum+1][cols[i]];
                }
		
	    }
	}

	if (direction.equals("decode")){
	     if (rownum == 0){
                for(int i=0; i<cols.length; i++){
                    back= back + keytable[4][cols[i]];
                }
            }  	
            else {
		for(int i=0; i<cols.length; i++){
                    back= back + keytable[rownum-1][cols[i]];
                }
	    }
	}
	return back;
    }

    public String column(String one, String two){
	String back = "";
        int colnum = Integer.parseInt(one.substring(1));
        int[] rows= new int[2];
        rows[0] = Integer.parseInt(one.substring(0,1));
        rows[1] = Integer.parseInt(two.substring(0,1));

        if (direction.equals("encode")){
            if (colnum == 4){
                for(int i=0; i<rows.length; i++){
                    back= back + keytable[rows[i]][0];
                }
            }
            else {
                for(int i=0; i<rows.length; i++){
                    back= back + keytable[rows[i]][colnum+1];
                }

            }
        }

        if (direction.equals("decode")){
             if (colnum == 0){
                for(int i=0; i<rows.length; i++){
                    back= back + keytable[rows[i]][4];
                }
            }
            else {
                for(int i=0; i<rows.length; i++){
                    back= back + keytable[rows[i]][colnum-1];
                }
            }
        }
        return back;
    }
    public String rule3(String one, String two){
	String back ="";
	int row1 = Integer.parseInt(one.substring(0,1));
	int col1= Integer.parseInt(one.substring(1));
	int row2 = Integer.parseInt(two.substring(0,1));
	int col2= Integer.parseInt(two.substring(1));
	back = back + keytable[row1][col2] + keytable[row2][col1];
	return back;
    }
    
    public String cracked(){
	String crackedCode="";
	crackedCode= message();
	return crackedCode;
    }
}
