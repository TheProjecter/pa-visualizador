package analiselexica;

/**
 *
 * @author Jaizo
 */
public class Verificador {

    private char[] alfabeto_minusculo = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
                                 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's','t',
                                 'u', 'v', 'x', 'y', 'z' };
    
    private char[] alfabeto_maiusculo = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
                                 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S','T',
                                 'U', 'V', 'X', 'Y', 'Z' };


    private char[] digitos = { '1', '2', '3', '4', '5', '6', '7', '8', '9'};

    private char[] delimitadores = { ';', '(', ')', '{', '}', '[', ']'};

    private char[] operadores = { '+', '-', '*', '/', '=', '!', '>', '<',
    '&', '|'};

    private String[] palavras_reservadas = {"algoritmo","variaveis","constantes",
                                            "registro","tipo", "funcao","retorno",
                                            "vazio","principal","se","entao","senao",
                                            "enquanto","para","leia","escreva",
                                            "inteiro","real","logico","caractere",
                                            "verdadeiro","flso"};

    private char anderline = '_';


    public boolean verificaCaracter(char caracter){

        boolean retorno = false;

        for(int i = 0; i < alfabeto_maiusculo.length; i++){

            if(caracter == (alfabeto_maiusculo[i])) {retorno = true;}
            if(caracter == (alfabeto_minusculo[i])) {retorno = true;}
        }

        return retorno;
    }

    public boolean verificaDigito(char caracter){

        boolean retorno = false;

        for(int i = 0; i < digitos.length; i++){

            if(caracter == (digitos[i])) {retorno = true;}
        }
        return retorno;
    }

    public boolean verificaDelimitador(char caracter){

        boolean retorno = false;

        for(int i = 0; i < delimitadores.length; i++){

            if(caracter == (delimitadores[i])) {retorno = true;}
        }
        return retorno;
    }

    public boolean verificaOperador(char caracter){

        boolean retorno = false;

        for(int i = 0; i < operadores.length; i++){

            if(caracter == (operadores[i])) {retorno = true;}
        }
        return retorno;
    }

    public boolean verificaPalavraR(String palavra){

        boolean retorno = false;

        for(int i = 0; i < palavras_reservadas.length; i++){

            if(palavra.equals(palavras_reservadas[i])) {retorno = true;}
        }
        return retorno;
    }

    public boolean verificaAnderline(char caracter){

        boolean retorno = false;
        if(caracter==anderline){retorno = true;}
        return retorno;

    }

    public static void main(String args[]) {

        Verificador analisador = new Verificador();
        Manipula teste = new Manipula();
        String a = "Jaizo   333====    SSSS))";
        char[] b = teste.toArrayChar(a);

        String c = "funcao2";
        boolean resp = analisador.verificaPalavraR(c);
        System.out.println(resp);
        for(int i=0; i < b.length; i++){

           //boolean resp = analisador.verificaOperador(b[i]);
           //System.out.println(resp);
        }

    }
}
