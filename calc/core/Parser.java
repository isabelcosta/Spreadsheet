package calc.core;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import calc.core.Conteudo.*;
import calc.core.Conteudo.Functions.BinaryFunctions.*;
import calc.core.Conteudo.Functions.IntervaloFunctions.*;

public class Parser {

    public static FolhaDeCalculo parseFile(String nomeFicheiro) throws IOException, FileNotFoundException, Exception {
		BufferedReader reader = null;
	
		try {
		    reader = new BufferedReader(new FileReader(nomeFicheiro));
		    String linha;
		    int linhas, colunas;
		    String[] args;
		    
		    linha = reader.readLine();
		    String line = new String(linha.getBytes(), "UTF-8");
		    args = line.split("=");
		    linhas = Integer.parseInt(args[1]);
		    
		    linha = reader.readLine();
		    line = new String(linha.getBytes(), "UTF-8");
		    args = line.split("=");
		    colunas = Integer.parseInt(args[1]);
		    
		    FolhaDeCalculo folha = new FolhaDeCalculo(linhas, colunas); // criar uma folha de calculo com tamanho linhas colunas
		    
		    while ((linha = reader.readLine()) != null) {
		    	line = new String(linha.getBytes(), "UTF-8");
		    	parseExpressao(folha, line);
		    }
		    
		    return folha;
		} finally {
		    if (reader != null)
		    	reader.close();
		}
	}

    public static void parseExpressao(FolhaDeCalculo folha, String expressao) throws Exception{
		String[] args = expressao.split("\\|");
		int[] endereco = null;

		endereco = parseEndereco(args[0], folha);
		
		Conteudo cont = null;
	
		if (args.length > 1)
		    cont = parseConteudo(folha, args[1]);
		
		folha.inserirNaFol(endereco[0], endereco[1], cont); // coloca conteudo cont na linha e coluna pretendidas da folha de calculo
    }

    public static int[] parseEndereco(String endereco, FolhaDeCalculo folha) throws Exception{
		String[] args = endereco.split(";");
		
		int[] vec = {Integer.parseInt(args[0]), Integer.parseInt(args[1])};
		
		if(vec[0] <=0 || vec[0] > folha.getLinhas())
			throw new Exception();
		if(vec[1] <=0 || vec[1] > folha.getColunas())	
			throw new Exception();
		return vec;
    }

    public static Conteudo parseConteudo(FolhaDeCalculo folha, String conteudo) throws Exception {
		
    	if (conteudo.contains("(")) { // é uma função
		    String funcao = conteudo.substring(1); // remove =
		    String nomeFuncao = parseNomeFuncao(funcao);
		    String Operando = parseOperandoFuncao(funcao);
	
		    if (conteudo.contains(","))
		    	return parseFuncaoBinaria(folha, nomeFuncao, Operando);
		    
		    else
		    	return parseFuncaoIntervalo(folha, nomeFuncao, Operando);
		    
		} else if (conteudo.contains("=")) {  // é uma referencia 
		    return parseReferencia(folha, conteudo.substring(1));
		
		} else
		    return parseLiteral(conteudo);
    }

    private static String parseNomeFuncao(String funcao) {
    	return funcao.substring(0, funcao.indexOf("("));
    }

    private static String parseOperandoFuncao(String funcao) {
    	return funcao.substring(funcao.indexOf("(") + 1, funcao.indexOf(")"));
    }

    public static BinaryFunction parseFuncaoBinaria(FolhaDeCalculo folha, String nomeFuncao, String Operando) throws Exception{
		String[] Operandos = Operando.split(",");
	
		Operando arg1 = parseOperando(folha, Operandos[0]);
		Operando arg2 = parseOperando(folha, Operandos[1]);
	
		switch(nomeFuncao) {
			case "MUL":
			    return new Mul(arg1, arg2);
			case "DIV":
			    return new Div(arg1, arg2);
			case "SUB":
			    return new Sub(arg1, arg2);
			case "ADD":
			    return new Add(arg1, arg2);
			}
		
		return null;
    }

    public static IntervaloFunction parseFuncaoIntervalo(FolhaDeCalculo folha, String nomeFuncao, String Operando) throws Exception{
		Intervalo intervalo = parseIntervalo(folha, Operando);
	
		switch(nomeFuncao) {
			case "PRD":
			    return new Prd(intervalo);
			case "AVG":
			    return new Avg(intervalo);
			}
		
		return null;
    }

    public static Operando parseOperando(FolhaDeCalculo folha, String Operando) throws Exception {
		if (Operando.contains(";")) {
		    return parseReferencia(folha, Operando);
		}
		
		return parseLiteral(Operando);
		
    }

    public static Intervalo parseIntervalo(FolhaDeCalculo folha, String intervalo) throws Exception{
		String[] enderecos = intervalo.split(":");
		
		int[] end1 = parseEndereco(enderecos[0], folha);
		int[] end2 = parseEndereco(enderecos[1], folha);
		
		if (end1[0] != end2[0] && end1[1] != end2[1])
			throw new Exception();
		
		return new Intervalo(folha.getCelulaEspecifica(end1[0],end1[1]), folha.getCelulaEspecifica(end2[0],end2[1]), folha);
    }

    public static Literal parseLiteral(String literal) {
    	return new Literal(Integer.parseInt(literal));/* o Operando que representa o literal */
    }

    public static Referencia parseReferencia(FolhaDeCalculo folha, String referencia) throws Exception{
			int[] vec = parseEndereco(referencia, folha);
			return new Referencia(folha.getCelulaEspecifica(vec[0],vec[1]));
    }
}