public class Operadores { // cria uma classe com acesso público chamada Operadores

    public static void main(String[] args) throws Exception { // cria um método chamado main, que indica como o Java deve iniciar a execução do programa; tem acesso público (pode ser acessado por qualquer outra classe); é estático, pertence à classe em si e não a instâncias (não é necessário criar um objeto); como é void, não retorna valores; tem como parâmetros um vetor de strings chamado args (ainda que esse vetor não tenha sido utilizado no código); e pode lançar qualquer tipo de exceção de execução da subclasse "Exception"
    
    System.out.println("Operador de atribuição: ="); // Imprime o texto entre parênteses
    String texto = "Texto"; // Cria uma variável texto com o valor "Texto"
    System.out.println("String texto = \"Texto\";"); // Imprime o texto entre parênteses, sendo que o \" imprime o caracter "
    System.out.println(texto); // Imprime o conteúdo da variável texto
   
    System.out.println("Operador de soma: +"); // Imprime o texto entre parênteses
    int soma = 1 + 1; // Realiza a soma de 1 + 1 e atribui à variável criada soma
    System.out.println("int soma = 1 + 1;"); // Imprime o texto entre parênteses
    System.out.println(soma); // Imprime o conteúdo da variável soma
   
    System.out.println("Operador de subtração: -"); // Imprime o texto entre parênteses
    int subtracao = 1 - 1; // Realiza a subtração de 1 - 1 e atribui à variável criada subtracao
    System.out.println("int subtracao = 1 - 1;"); // Imprime o texto entre parênteses
    System.out.println(subtracao); // Imprime o conteúdo da variável subtração
   
    System.out.println("Operador de divisão: /"); // Imprime o texto entre parênteses
    int divisao = 1 / 1; // Realiza a divisão de 1 por 1 e atribui à variável criada divisao
    System.out.println("int divisao = 1 / 1;"); // Imprime o texto entre parênteses
    System.out.println(divisao); // Imprime o conteúdo da variável divisao
   
    System.out.println("Operador de multiplicação: *"); // Imprime o texto entre parênteses
    int multiplicacao = 1 * 1; // Realiza a multiplicação de 1 por 1 e atribui à variável criada multiplicacao
    System.out.println("int multiplicacao = 1 * 1;"); // Imprime o texto entre parênteses
    System.out.println(multiplicacao); // Imprime o conteúdo da variável multiplicacao
   
    System.out.println("Operador de concatenação de strings: +"); // Imprime o texto entre parênteses
    String concatenacao = "Texto 1 + " + "Texto 2"; // Concatena as strings "Texto 1 + " e "Texto 2" e atribui à variável criada concatenacao
    System.out.println("String concatenacao = \"Texto 1 + \" + \"Texto 2\";"); // Imprime o texto entre parênteses, sendo que o \" imprime o caracter "
    System.out.println(concatenacao); // Imprime o conteúdo da variável concatenacao
    }
}
