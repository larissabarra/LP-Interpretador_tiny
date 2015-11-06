package comandosTiny;

import java.io.*;
import java.util.HashMap;
import java.util.Scanner;


public class ComandoReadint extends Comando {

	private int valor;
	private String nome;
	private Scanner scan;
	
	public ComandoReadint(int linha, String nome){
		super(linha);
		scan = new Scanner (System.in);
		this.nome = nome;
	}
	
	public int executar() {
		valor = scan.nextInt();
		variaveis.put(nome, valor);
		return (this.getLinhaInicio() + 1);
	}
}
