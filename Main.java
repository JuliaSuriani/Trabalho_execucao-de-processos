// @author Julia Suriani
import java.util.Scanner;

class Processos{ //dados so dos processos
    
    int PID; //ID do Processo. Inteiro. Autoincremento. Deve começar em 0 e para cada inserção do usuário de novo processo, deve-se criar um PID novo com o incremento de 1;
    int memSize; //Tamanho de Memória utilizada pelo Processo. Inteiro, deve ser cadastrado pelo usuário.
    int timeExecution; //Tempo de execução. Inteiro. Deve ser um informando pelo usuário e deve estar entre 30 e 90 segundos.
    
    Processos(int PID,int memSize, int timeExecution){
       this.PID = PID;
       this.memSize = memSize;
       this.timeExecution = timeExecution; 
    }
}

class PilhaProcessos { //dados da pilha de processos
    public Processos[] stack;
    private int topo;
    int memoriaTotal;
    int tempoTotalExec;
    
    
    
    PilhaProcessos (int capacidade){
     stack = new Processos[capacidade]; 
     topo = 0;
     memoriaTotal = 0;
     tempoTotalExec = 0;
    }   
        
//adiciona um novo elemento no topo.
     void push (Processos processos){ 
        if (isFull()){ //se a pilha estiver CHEIA faça
         throw new IllegalStateException("A pilha está cheia");   
        }
        stack[++topo] = processos;
        memoriaTotal += processos.memSize;
     }
     
//remove o elemento do topo.
     Processos pop (){ 
        if (isEmpty()) { 
            throw new IllegalStateException("A pilha está vazia");
        }
        Processos processos = stack[topo];
        stack[topo--] = null; // Libera a referência do elemento removido
        memoriaTotal -= processos.memSize;
        tempoTotalExec += processos.timeExecution;
        return processos; 
     }
     
//retorna o elemento no topo sem removê-lo.     
     Processos peek (){ 
       if (isEmpty()) {
            throw new IllegalStateException("A pilha está vazia");
        }
        return stack[topo];  
     }    
    
    boolean isEmpty() {
        return topo == -1;
    }

    boolean isFull() {
        return topo == stack.length - 1;
    }
     
}
public class Main {
    public static void main(String[] args) {
     Scanner scan = new Scanner(System.in); 
     System.out.print("Insira a capacidade da pilha: ");
     int capacidade = scan.nextInt();
     PilhaProcessos stack = new PilhaProcessos (capacidade);
     int contPid = 0;
    
     
    boolean inputValido = false; 
    while (!inputValido){
     try{
      System.out.print("Digite o tamanho da memoria utilizada pelo processo: ");
      int memSize = scan.nextInt();
      System.out.print("Digite o tempo de execução (deve ser entre 30 e 90 seg) ");
      int timeExecution = scan.nextInt();
      if (timeExecution < 30 || timeExecution > 90){
        System.out.println("Tempo de execução deve estar entre 30 e 90 segundos.");
        continue;  
      }
      Processos processos = new Processos(contPid ++, memSize,timeExecution);
      stack.push(processos);
      inputValido = true; // mover esta linha para aqui
               
      System.out.print("Deseja adicionar mais processos?(S ou N) ");
      String maisProcessos = scan.next();
      if (!maisProcessos.equalsIgnoreCase("S")){
       break;   
        }
     }
       catch (Exception e){
        System.out.print("Entrada invalida. Digite apenas números inteiros");
        scan.nextLine();
        }         
    }
    System.out.print("\nResumo dos processos por ordem de inserção: ");
    for (Processos processos : stack.stack){
        System.out.println("PID (ID do Processo) - "+ processos.PID);
    }
    
    Processos processoAtual = stack.peek();
    System.out.println ("Processo a ser executado: ");
    System.out.println("PID:" + processoAtual.PID);
    
    Processos processoExec = stack.pop();
    System.out.println("\nProcesso " +processoExec.PID  +"executado com sucesso!");
    
    System.out.println("\nResumo da execução:");
    for (Processos processos : stack.stack){
    if (processos != null) {
        System.out.println("PID (ID do Processo) - "+ processos.PID);
    }
}
    System.out.println ("Consumo total de memória:" + stack.memoriaTotal);
    System.out.println ("Tempo total de execucação " + stack.tempoTotalExec +" segundos");    
   }
}
    

