import java.util.Scanner;

public class Conversao {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite um número inteiro: ");
        int numeroInteiro = scanner.nextInt();

        Integer numeroWrapper = Integer.valueOf(numeroInteiro);

        System.out.println("O número inteiro digitado foi: " + numeroInteiro);
        System.out.println("O número como um objeto Integer é: " + numeroWrapper);
    }
}
