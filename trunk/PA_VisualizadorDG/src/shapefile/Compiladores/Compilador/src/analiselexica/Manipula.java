package analiselexica;

/**
 *
 * @author Jaizo
 */
public class Manipula {


    public char[] toArrayChar(String string){


        return string.toCharArray();
    }

    public String[] toArrayStringForSpace(String string){

        String[] arrayStrings = string.split(" ");
        return arrayStrings;
    }

    public String toString(char[] arraychar){

        String string = new String(arraychar);
        return string;
    }

    public static void main(String args[]) {

        //TESTE DO toArrayChar
   /*
        ManipulaString teste = new ManipulaString();
        String nome = "Jaizo Araujo Santos Junior";
        char[] array = teste.toArrayChar(nome);
        for(int i = 0; i < array.length; i++){

            System.out.println(array[i]);
        }
    */
        //teste do separador de string por espaco
     
   /*     String teste = "TESTE COM SPLIT";

        String[] teste2 = teste.split(" ");

        System.out.println(teste2.length);
        for(int i = 0; i<teste2.length;i++) {

             System.out.println(teste2[i]);
        }
    */
    
    }
}
