import java.util.Scanner;

public class Controle {
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
       if(media >= 7){
        System.out.println("Aprovado!");
       }
       else if(media >= 5){
        System.out.println("recuperacao");
       } else{
        System.out.println("REPROVADO!");
       }
    }
}