package comandosTiny;

import java.io.*;


public class ComandoPrintln extends Comando {

	public ComandoPrintln(int linha){
		super(linha);
	}
	
	public int executar() {
		System.out.println("");
		return (this.getLinhaInicio() + 1);
	}
}
