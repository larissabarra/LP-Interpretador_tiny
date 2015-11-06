package comandosTiny;

import java.io.*;
import java.util.HashMap;

public class ComandoEndw extends Comando {

	private int linhaWhile;
	
	public ComandoEndw(int linha, int linhaWhile){
		super(linha);
		this.linhaWhile = linhaWhile;
	}
	
	public void setLinhaWhile(int linhaWhile){
		this.linhaWhile = linhaWhile;
	}
	
	public int getLinhaWhile(){
		return linhaWhile;
	}
	
	public int executar() {
		return linhaWhile;
	}
}
