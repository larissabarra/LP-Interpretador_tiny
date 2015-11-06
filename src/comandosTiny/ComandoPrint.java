package comandosTiny;

import java.io.*;
import java.util.HashMap;


public class ComandoPrint extends Comando {

	private String valor;
	private boolean variavel;

	public ComandoPrint(int linha, String valor, boolean variavel){
		super(linha);
		this.valor = valor;
		this.variavel = variavel;
	}
	
	public int executar() {
		if(variavel) {
			Integer var = variaveis.get(valor);
			if(var != null) System.out.print(var);
		} else {
			System.out.print(valor);
		}
		return (this.getLinhaInicio() + 1);
	}
}
