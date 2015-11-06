package comandosTiny;

import java.io.*;
import java.util.HashMap;


public class ComandoWhile extends Comando {

	private String var1, var2, condicao;
	private int linhaFinal;
	
	public ComandoWhile(int linha, String var1, String condicao, String var2){
		super(linha);
		this.condicao = condicao;
		this.var1 = var1;
		this.var2 = var2;
		this.linhaFinal = 0;
	}
	
	public void setLinhaFinal(int linhaFinal){
		this.linhaFinal = linhaFinal;
	}
	
	public int getLinhaFinal(){
		return linhaFinal;
	}
	
	public int executar() {
		Integer variavel1 = variaveis.get(var1);
		Integer variavel2 = variaveis.get(var2);
		if(variavel1 != null && variavel2 != null) {
			if((condicao.equals(">") && variavel1 > variavel2) ||
				(condicao.equals("<") && variavel1 < variavel2) ||
				(condicao.equals("=") && variavel1 == variavel2)) {
					return (this.getLinhaInicio() + 1);
			} 
			return (this.getLinhaFinal() + 1);
		}
		return -1;
	}
}
