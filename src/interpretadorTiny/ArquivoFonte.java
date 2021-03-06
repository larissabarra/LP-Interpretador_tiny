package interpretadorTiny;

import java.io.*;
import java.util.StringTokenizer;


public class ArquivoFonte {

 	private FileReader arq;
	private BufferedReader dados;
	private String linha;
	private StringTokenizer st;
	private static String delim= "+-*/()=<> '";
	private boolean em_string= false;

	public ArquivoFonte(String name) {
    	try {
      	arq= new FileReader(name);
      	dados= new BufferedReader (arq);
      	linha= dados.readLine();
      	if (linha == null)
         	st= null;
      	else st= new StringTokenizer(linha, delim, true);
    	} catch (Exception e) {
	  		System.out.println(e);
    	}
  	}

  	public String proximaPalavra() {
   	if (st.hasMoreTokens()) {
      	String s= st.nextToken();
		   if (s.equals("'"))
		      em_string= !em_string;
		   if (s.equals(" ") && (!em_string))
		      return proximaPalavra();
			else return s;
		} else {
     		try {
        		linha= dados.readLine();
        		if (linha == null)
           		return "EOF";
        		else {
		  			st = new StringTokenizer(linha, delim, true);
		  			return proximaPalavra();
        		}
      	} catch (Exception e) {
				System.out.println(e);
      	}
      	return "EOF";
    	}
	}

  	public void saltaPalavra() {
		String s= proximaPalavra();
  	}
}
