package comandosTiny;

import java.io.*;
import java.util.HashMap;


public abstract class Comando {

	private int linhaInicio;
	public static HashMap <String, Integer> variaveis;

	public Comando(int linhaInicio){
		this.linhaInicio = linhaInicio;
		variaveis = new HashMap <String, Integer> ();
	}
	
	public void setLinhaInicio(int linhaInicio){
		this.linhaInicio = linhaInicio;
	}
	
	public int getLinhaInicio(){
		return linhaInicio;
	}
	
	public abstract int executar();
}
