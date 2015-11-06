package tiny;

import interpretadorTiny.*;

public class Main {

	public static void main(String[] args) {
		Interpretador inter = new Interpretador(args[0]);
		if (args.length == 1) {
			try {
				inter.lerArquivo();
			   inter.executar();
			} catch(InvalidTokenException ex) {
				System.out.println(ex.getMessage() + " " + ex.getToken() + ", linha " + ex.getLinha());
			}
		}
	}
}



