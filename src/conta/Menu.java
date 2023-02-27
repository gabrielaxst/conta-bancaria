package conta;

import conta.controller.ContaController;
import conta.model.ContaCorrente;
import conta.model.ContaPoupanca;
import conta.util.Cores;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

public class Menu {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner input = new Scanner(System.in);
        int opcaoUsuario, numero, agencia, tipo, aniversario, numeroDestino;
        String titular;
        float saldo, limite, valor;

        ContaController contas = new ContaController();

        ContaCorrente cc1 = new ContaCorrente(contas.getNumero(), 123, 1, "Gabriela", 2000,
                500);
        contas.cadastrar(cc1);
        ContaCorrente cc2 = new ContaCorrente(contas.getNumero(), 124, 1, "Severina", 5000,
                600);
        contas.cadastrar(cc2);

        ContaPoupanca cp1 = new ContaPoupanca(contas.getNumero(), 125, 2, "Joel", 1000,
                6);
        contas.cadastrar(cp1);
        ContaPoupanca cp2 = new ContaPoupanca(contas.getNumero(), 126, 2, "Severina", 8000,
                24);
        contas.cadastrar(cp2);
        contas.listarTodas();
        while (true){
            System.out.println(Cores.TEXT_YELLOW+Cores.ANSI_BLACK_BACKGROUND+
                              "*****************************************************");
            System.out.println("                                                     ");
            System.out.println("                BANCO DO BRAZIL COM Z                ");
            System.out.println("                                                     ");
            System.out.println("*****************************************************");
            System.out.println("                                                     ");
            System.out.println("            1 - Criar Conta                          ");
            System.out.println("            2 - Listar todas as Contas               ");
            System.out.println("            3 - Buscar Conta por Numero              ");
            System.out.println("            4 - Atualizar Dados da Conta             ");
            System.out.println("            5 - Apagar Conta                         ");
            System.out.println("            6 - Sacar                                ");
            System.out.println("            7 - Depositar                            ");
            System.out.println("            8 - Transferir valores entre Contas      ");
            System.out.println("            9 - Sair                                 ");
            System.out.println("                                                     ");
            System.out.println("*****************************************************");
            System.out.println("Entre com a opção desejada:                          ");
            System.out.println("                                                     "+ Cores.TEXT_RESET);
            try {
                opcaoUsuario = input.nextInt();
            }
            catch (InputMismatchException inputMismatchException){
                System.out.println("\nDigite números inteiros!");
                input.nextLine();
                opcaoUsuario = 0;
            }

            if (opcaoUsuario == 9) {
                System.out.println(Cores.TEXT_WHITE_BOLD+"\nBanco do Brazil com Z - O seu Futuro começa aqui!");
                input.close();
                System.exit(0);
            }

            switch (opcaoUsuario) {
                case 1:
                    System.out.println(Cores.TEXT_WHITE+"Criar conta:\n\n");
                    System.out.println("Digite o número da agência: ");
                    agencia = input.nextInt();
                    input.nextLine();

                    System.out.println("Digite o nome do titular: ");
                    titular = input.nextLine();

                    do {
                        System.out.println("Digite o tipo da conta? (1-CC ou 2-CP)");
                        tipo = input.nextInt();
                    }while (tipo != 1 && tipo != 2);

                    System.out.println("Digite o saldo da conta: ");
                    saldo = input.nextFloat();
                    switch (tipo){
                        case 1 ->{
                            System.out.println("Digite o limite de crédito: ");
                            limite = input.nextFloat();
                            contas.cadastrar(new ContaCorrente(contas.getNumero(),agencia,tipo,titular,saldo,limite));
                        }
                        case 2 -> {
                            System.out.println("Digite o dia de aniversário: ");
                            aniversario = input.nextInt();
                            contas.cadastrar(new ContaPoupanca(contas.getNumero(),agencia,tipo,titular,saldo,aniversario));
                        }
                    }
                    keyPress();
                    break;
                case 2:
                    System.out.println(Cores.TEXT_WHITE+"Listar todas as contas:\n\n");
                    contas.listarTodas();

                    keyPress();
                    break;
                case 3:
                    System.out.println(Cores.TEXT_WHITE+"Consultar dados da Conta - por número:\n\n");
                    System.out.println("Digite o número da conta: ");
                    numero = input.nextInt();
                    contas.procurarPorNumero(numero);
                    keyPress();
                    break;
                case 4:
                    System.out.println(Cores.TEXT_WHITE+"Atualizar dados da conta:\n\n");
                    System.out.println("Digite o número da conta: ");
                    numero = input.nextInt();
                    if (contas.buscarNaCollection(numero) != null){
                        System.out.println("Digite o número da agência: ");
                        agencia = input.nextInt();

                        System.out.println("Digite o nome do titular: ");
                        input.nextLine();
                        titular = input.nextLine();

                        System.out.println("Digite o saldo da conta (R$): ");
                        saldo = input.nextFloat();

                        tipo = contas.retornaTipo(numero);
                        switch (tipo){
                            case 1 ->{
                                System.out.println("Digite o limite de crédito: ");
                                limite = input.nextFloat();
                                contas.atualizar(new ContaCorrente(numero,agencia,tipo,titular,saldo,limite));
                            }
                            case 2 -> {
                                System.out.println("Digite o dia de aniversário: ");
                                aniversario = input.nextInt();
                                contas.atualizar(new ContaPoupanca(numero,agencia,tipo,titular,saldo,aniversario));
                            }
                            default -> {
                                System.out.println("Tipo de conta inválido!");
                            }
                        }

                    }
                    else {
                        System.out.println("\nConta não encontrada!");
                    }
                    keyPress();
                    break;
                case 5:
                    System.out.println(Cores.TEXT_WHITE+"Apagar conta:\n\n");
                    System.out.println("Digite o número da conta: ");
                    numero = input.nextInt();
                    contas.deletar(numero);
                    keyPress();
                    break;
                case 6:
                    System.out.println(Cores.TEXT_WHITE+"Saque:\n\n");
                    System.out.println("Digite o número da conta: ");
                    numero = input.nextInt();

                    do {
                        System.out.println("Digite o valor: ");
                        valor = input.nextFloat();
                    }while (valor <= 0);

                    contas.sacar(numero, valor);

                    keyPress();
                    break;
                case 7:
                    System.out.println(Cores.TEXT_WHITE+"Depósito:\n\n");
                    System.out.println("Digite o número da conta: ");
                    numero = input.nextInt();

                    do {
                        System.out.println("Digite o valor: ");
                        valor = input.nextFloat();
                    }while (valor <= 0);

                    contas.depositar(numero, valor);

                    keyPress();
                    break;
                case 8:
                    System.out.println(Cores.TEXT_WHITE+"Transferência entre contas:\n\n");
                    System.out.println("Digite o número da conta de origem: ");
                    numero = input.nextInt();

                    System.out.println("Digite o número da conta de destino: ");
                    numeroDestino = input.nextInt();
                    do {
                        System.out.println("Digite o valor: ");
                        valor = input.nextFloat();
                    }while (valor <= 0);

                    contas.transferir(numero, numeroDestino, valor);
                    keyPress();
                    break;
                default:
                    System.out.println(Cores.TEXT_RED_BOLD+"Opção inválida!\n\n"+Cores.TEXT_RESET);
                    keyPress();
                    break;
            }

        }
    }
    public static void keyPress() {
        try {
            System.out.println(Cores.TEXT_RESET + "\n\nPressione Enter para Continuar...");
            System.in.read();
        }
        catch (IOException e) {
            System.out.println("Você pressionou uma tecla diferente de enter!");
        }
    }
}
