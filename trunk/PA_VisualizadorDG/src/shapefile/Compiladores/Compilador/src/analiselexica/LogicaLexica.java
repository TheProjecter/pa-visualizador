package analiselexica;

import java.util.ArrayList;
import java.util.Vector;

/**
 *
 * @author Jaizo
 */
public class LogicaLexica {

    Verificador verificador = new Verificador();


    public ArrayList<String> identificadores(char[] string){

        //boolean retorno = false;
        int max = string.length;
        String identificador = "";
        int count = 0;
        String resto;
        ArrayList<String> retorno = new ArrayList();

       // System.out.println(max);
        while(count<max&&(verificador.verificaCaracter(string[count]) || verificador.verificaDigito(string[count]) || verificador.verificaAnderline(string[count]))){

            //System.out.println(count);
            identificador = identificador+string[count];
            count++;
        }
        if(count == max){

            //retorno = true;
            resto = "";
            System.out.println(identificador);
            System.out.println(count);
            retorno.add(identificador);
            retorno.add(resto);
            
        }else if(count < max&&(verificador.verificaDelimitador(string[count])||verificador.verificaOperador(string[count]))){

            //retorno = true;
            //System.out.println(identificador+"dsdss");
            resto = new String(string).replace(identificador, "");
            retorno.add(identificador);
            retorno.add(resto);
            //System.out.println(resto+" ::resto");

        }else{

            resto = new String(string);
            //System.out.println(resto+" ::resto");
            retorno.add("");
            retorno.add(resto);;
        }

        return retorno;
    }

    public static void main(String args[]) {

        LogicaLexica logica = new LogicaLexica();
        Manipula manipulador = new Manipula();
        String palavra = "aaaaa999";
        String[] separados = manipulador.toArrayStringForSpace(palavra);

        for(int i = 0; i<separados.length;i++){


            char[] arraychar = manipulador.toArrayChar(separados[i]);

            //logica.identificadores(arraychar);
            //palavraAtual = manipulador.toArrayChar(separados[i].trim());
            //System.out.println(separados[i].length());
            System.out.println(logica.identificadores(arraychar));
            //System.out.println(logica.identificadores(palavraAtual));

        }
    }
}
