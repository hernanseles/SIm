package poo;
import java.util.Scanner;
public class Utilitarios {
    private Scanner sc = new Scanner(System.in);

    public int lerInt() {
        while(true){
            try{
                return sc.nextInt();
            }catch (Exception e){
                System.out.println("Digite apenas números!!");
                sc.nextLine();
            }
        }
    }

    public String lerString() {
        sc.nextLine(); // limpar buffer
        return sc.nextLine();
    }

    // pra usar com Strings esse aqui ai nao tem q digitar duas vezes
    public String lerstring() {
        return sc.nextLine();
    }

    public double lerDouble() {
        return sc.nextDouble();
    }

    public static void limparConsole() {
        for (int i = 0; i < 30; i++) {
            System.out.println();
        }
    }
}




