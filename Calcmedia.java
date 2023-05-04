import java.util.Scanner;

public class Calcmedia {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        double soma = 0;
        for (int i = 1; i <= 4; i++) {
            System.out.print("Digite a nota " + i + ": ");
            double nota = sc.nextDouble();
            soma += nota;
        }

        double media = soma / 4;
        System.out.println("A média das notas é: " + media);

        sc.close();
    }
}