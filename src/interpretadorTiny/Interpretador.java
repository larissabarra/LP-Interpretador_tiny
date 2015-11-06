package interpretadorTiny;

import java.io.*;
import comandosTiny.*;
import tiny.InvalidTokenException;
import java.util.Vector;
import java.util.Stack;


public class Interpretador {

	private String arquivo;
	private ArquivoFonte leitor;
	private Vector <Comando> comandos;
	private Stack <ComandoWhile> pilhaWhiles;
	private boolean espera;
	private int linha;
	private InvalidTokenException ex;

	public Interpretador(String arg) {
		arquivo = arg;
		espera = false;
		linha = 0;
		leitor = new ArquivoFonte(arquivo);
		comandos = new Vector <Comando> ();
		pilhaWhiles = new Stack <ComandoWhile> ();
	}
	
	public void lerArquivo() throws InvalidTokenException {
		String palavra = leitor.proximaPalavra();
		while(!palavra.equals("EOF")) {
			//System.out.println("palavra antes: " + palavra + "\tespera antes: " + espera);
			String p;
			if(palavra.equals("print")) {
				leitor.saltaPalavra(); //vai ser o (
				p = leitor.proximaPalavra(); //vai ser uma var ou '
				if(p.equals("\'")) {
					String frase = "";
					p = leitor.proximaPalavra();
					while (!p.equals("\'")) {
						frase += p;
						p = leitor.proximaPalavra();
					}
					comandos.addElement(new ComandoPrint(linha, frase, false));
				} else {
					comandos.addElement(new ComandoPrint(linha, p, true));
				}
				leitor.saltaPalavra(); //vai ser o )
			}
			else if(palavra.equals("println")) {
				comandos.addElement(new ComandoPrintln(linha));
			}
			else if(palavra.equals("readInt")) {
				leitor.saltaPalavra(); //vai ser o (
				comandos.addElement(new ComandoReadint(linha, leitor.proximaPalavra()));
				leitor.saltaPalavra(); //vai ser o )
			}
			else if(palavra.equals("while")) {
				leitor.saltaPalavra(); //vai ser o (
				String op1 = leitor.proximaPalavra();
				String condicao = leitor.proximaPalavra();
				String op2 = leitor.proximaPalavra();
				leitor.saltaPalavra(); //vai ser o )
				ComandoWhile w = new ComandoWhile(linha, op1, condicao, op2);
				comandos.addElement(w);
				pilhaWhiles.addElement(w);
			}
			else if(palavra.equals("endw")) {
				if(pilhaWhiles.empty()) {
					ex = new InvalidTokenException("Erro! Fim de comando inválido:");
					ex.setToken("endw");
					ex.setLinha(linha+1);
					throw ex;
				} else {
					ComandoWhile w = pilhaWhiles.pop();
					ComandoWhile w2 = w;
					ComandoEndw ew = new ComandoEndw(linha, w.getLinhaInicio());
					w.setLinhaFinal(linha);
					comandos.set(comandos.indexOf(w2), w);
					comandos.addElement(ew);
				}
			}
			else if(palavra.equals("endp")) {
				comandos.addElement(new ComandoEndp(linha));
			}
			else {
				String atribuicao = leitor.proximaPalavra(); //deve ser o =
				if(atribuicao.equals("=")) {
					String parcela1 = leitor.proximaPalavra();
					String op = leitor.proximaPalavra();			
					if(op.equals("+") || op.equals("-") || op.equals("*") || op.equals("/")) {
						String parcela2 = leitor.proximaPalavra();
						try {
							int valor = Integer.parseInt(parcela2);
							comandos.addElement(new ComandoAtrib(linha, palavra, parcela1, op, valor));
			 			} catch(NumberFormatException e){
			 				comandos.addElement(new ComandoAtrib(linha, palavra, parcela1, op, parcela2));
			 			}
					} else {
						comandos.addElement(new ComandoAtrib(linha, palavra, Integer.parseInt(parcela1)));
						palavra = op;
						espera = true;
					}
				} else {
					ex = new InvalidTokenException("Erro! Token inválido:");
					ex.setToken(palavra);
					ex.setLinha(linha+1);
					throw ex;
				}
			}
			if(!espera) palavra = leitor.proximaPalavra();
			espera = false;
			linha++;
		}
	}
	
	public void executar() {
		int pc = 0;
		do {
			pc = comandos.elementAt(pc).executar();
		} while (pc != -1);
	}
}
