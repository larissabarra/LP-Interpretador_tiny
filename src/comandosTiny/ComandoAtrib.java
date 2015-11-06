package comandosTiny;

import java.io.*;
import java.util.HashMap;


public class ComandoAtrib extends Comando {

	private int valor, tipo;
	private String atribuir, parcela1, parcela2, operador;
	
	public ComandoAtrib(int linha, String atribuir, int valor){
		super(linha);
		this.atribuir = atribuir;
		this.valor = valor;
		tipo = 1;
	}
	
	public ComandoAtrib(int linha, String atribuir, String parcela1, String operador, int valor){
		super(linha);
		this.atribuir = atribuir;
		this.valor = valor;
		this.operador = operador;
		this.parcela1 = parcela1;
		tipo = 2;
	}
	
	public ComandoAtrib(int linha, String atribuir, String parcela1, String operador, String parcela2){
		super(linha);
		this.atribuir = atribuir;
		this.operador = operador;
		this.parcela1 = parcela1;
		this.parcela2 = parcela2;
		tipo = 3;
	}
	
	public int executar() {
		Integer resultado = null;
		boolean flag;
		switch(tipo) {
			case 1:
				variaveis.put(atribuir, valor);
				break;
			case 2:
				resultado = variaveis.get(parcela1);
				flag = true;
				if(resultado != null) {
					if(operador.equals("+")) resultado += valor;
					else if(operador.equals("-")) resultado -= valor;
					else if(operador.equals("*")) resultado *= valor;
					else if(operador.equals("/")) resultado /= valor;
					else flag = false;
					
					if(flag) variaveis.put(atribuir, resultado);
				}
				break;
			case 3:
				resultado = variaveis.get(parcela1);
				Integer p2 = variaveis.get(parcela2);
				flag = true;
				if(resultado != null && p2 != null) {
					if(operador.equals("+")) resultado += p2;
					else if(operador.equals("-")) resultado -= p2;
					else if(operador.equals("*")) resultado *= p2;
					else if(operador.equals("/")) resultado /= p2;
					else flag = false;
					
					if(flag) variaveis.put(atribuir, resultado);
				}
				break;
		}
		return (this.getLinhaInicio() + 1);
	}
}

