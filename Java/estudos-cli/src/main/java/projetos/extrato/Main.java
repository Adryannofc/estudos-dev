package projetos.extrato;

import java.util.Scanner;

public class Main {

    static void main() {
        int opcao = 0;
        Scanner scan = new Scanner(System.in);
        double saldo = 1000;
        do {
            System.out.println("╔═════════════════════════════╗");
            System.out.println("║            CONTA            ║");
            System.out.println("╠═════════════════════════════╣");
            System.out.println("║ NOME: Adryann Felix         ║");
            System.out.println("║ TIPO CONTA: Corrente        ║");
            System.out.printf("║ Saldo: R$ %-18.2f║\n", saldo);
            System.out.println("╠═════════════════════════════╣");
            System.out.println("║ [1] Consultar Saldo         ║");
            System.out.println("║ [2] Receber Valor           ║");
            System.out.println("║ [3] Transferir Valor        ║");
            System.out.println("╚═════════════════════════════╝");
            System.out.print("Opcão: ");
            opcao = scan.nextInt();

            switch (opcao) {
                case 1:
                    double valor = scan.nextDouble();
                    saldo = receberValor(saldo, valor);
                    break;
                case 2:
                    valor = scan.nextDouble();
                    saldo = transferirValor(saldo, valor);
                    break;

            }
        } while (opcao != 0);
    }

    static String historico() {
        return "";
    }

    static double receberValor(double saldoAtual, double adicionar) {
        saldoAtual += adicionar;
        return saldoAtual;
    }

    static double transferirValor(double saldoAtual, double retirar) {
        saldoAtual -= retirar;
        return saldoAtual;
    }
}
