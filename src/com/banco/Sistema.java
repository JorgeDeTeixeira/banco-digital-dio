package com.banco;

import java.util.Scanner;

import com.banco.dominio.banco.Banco;
import com.banco.dominio.cliente.Cliente;
import com.banco.dominio.conta.Conta;
import com.banco.dominio.conta.ContaCorrente;
import com.banco.dominio.conta.ContaPoupanca;
import com.banco.dominio.transacao.Deposito;
import com.banco.dominio.transacao.Saque;
import com.banco.dominio.transacao.Transferencia;
import com.banco.utils.Menus;

public class Sistema {

    private Banco banco;
    private Scanner scanner;

    public Sistema() {
        this.banco = new Banco();
        this.scanner = new Scanner(System.in);
    }

    public void iniciar() {
        System.out.println("Sistema iniciado");
        System.out.println("Bem-vindo ao Banco Digital");

        boolean executando = true;
        while (executando) {
            Menus.menuPrincipal();
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    criarConta();
                    break;
                case 2:
                    acessarConta();
                    break;
                case 3:
                    System.out.println("Sistema encerrado. Obrigado por utilizar o Banco Digital!");
                    executando = false;
                    break;
                default:
                    System.out.println("Opção inválida, tente novamente.");
                    break;
            }
        }
        scanner.close();
    }

    private void criarConta() {
        System.out.println("Criando conta...");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();
        System.out.print("Senha: ");
        int senha = scanner.nextInt();
        scanner.nextLine();
        Cliente cliente = new Cliente(nome, cpf, senha);

        Menus.menuTipoConta();
        System.out.print("Escolha uma opção: ");
        int tipoConta = scanner.nextInt();
        scanner.nextLine();

        Conta novaConta;
        if (tipoConta == 1) {
            novaConta = new ContaCorrente(cliente);
        } else if (tipoConta == 2) {
            novaConta = new ContaPoupanca(cliente);
        } else {
            System.out.println("Opção inválida, tente novamente.");
            return;
        }

        banco.adicionarConta(novaConta);
        System.out.println("Conta criada com sucesso!");
        System.out.println("Cliente: " + cliente.getNome());
        System.out.println("Número da Conta: " + novaConta.getNumero());
    }

    private void acessarConta() {
        System.out.println("Acessando conta...");
        System.out.print("Digite o número da conta: ");
        int numeroConta = scanner.nextInt();
        scanner.nextLine();

        Conta conta;
        try {
            conta = banco.buscarConta(numeroConta);
        } catch (Exception e) {
            System.out.println("Conta não encontrada. Voltando ao menu principal.");
            return;
        }

        if (!autenticarConta(conta)) {
            System.out.println("Número de tentativas excedido. Voltando ao menu principal.");
            return;
        }

        System.out.println(conta.getCliente().getNome() + ", seja bem-vindo ao Banco Digital!");
        realizarOperacaoConta(conta);
    }

    private Conta buscarConta(int numeroConta) {
        try {
            return banco.buscarConta(numeroConta);
        } catch (Exception e) {
            System.out.println("Conta não encontrada.");
            return null;
        }
    }

    private boolean autenticarConta(Conta conta) {
        System.out.print("Digite a senha:");
        int tentativasRestantes = 3;

        while (tentativasRestantes > 0) {
            int senha = scanner.nextInt();
            scanner.nextLine();

            if (senha == conta.getCliente().getSenha()) {
                return true;
            } else {
                tentativasRestantes--;
                System.out.println("Senha incorreta, " + tentativasRestantes + " tentativas restantes.");
            }
        }
        return false;
    }

    private void realizarOperacaoConta(Conta conta) {
        boolean continuar = true;
        while (continuar) {
            Menus.menuConta();
            System.out.print("Escolha uma opção: ");
            int opcaoConta = scanner.nextInt();
            scanner.nextLine();

            switch (opcaoConta) {
                case 1:
                    realizarDeposito(conta);
                    break;
                case 2:
                    realizarSaque(conta);
                    break;
                case 3:
                    realizarTransferencia(conta);
                    break;
                case 4:
                    mostrarSaldo(conta);
                    break;
                case 5:
                    System.out.println("Voltando ao menu principal.");
                    continuar = false; // Sai do loop e volta ao menu principal
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        }
    }

    private void realizarDeposito(Conta conta) {
        System.out.print("Valor a ser depositado: ");
        double valorDeposito = scanner.nextDouble();
        scanner.nextLine();
        try {
            Deposito deposito = new Deposito(conta, valorDeposito);
            deposito.realizar();
            System.out.println(deposito.getDetalhes());
        } catch (Exception e) {
            System.out.println("Erro ao realizar o depósito: " + e.getMessage());
        }
    }

    private void realizarSaque(Conta conta) {
        System.out.print("Valor a ser sacada: ");
        double valorSacado = scanner.nextDouble();
        scanner.nextLine();
        try {
            Saque saque = new Saque(conta, valorSacado);
            saque.realizar();
            System.out.println(saque.getDetalhes());
        } catch (Exception e) {
            System.out.println("Erro ao realizar saque: " + e.getMessage());
        }
    }

    private void realizarTransferencia(Conta conta) {
        System.out.print("Digite o número da conta de destino:");
        int numeroContaDestino = scanner.nextInt();
        scanner.nextLine();

        Conta contaDestino = buscarConta(numeroContaDestino);
        if (contaDestino == null) {
            System.out.println("Conta de destino não encontrada. Voltando ao menu principal.");
            return;
        }

        System.out.print("Valor a ser transferido: ");
        double valorTransferencia = scanner.nextDouble();
        scanner.nextLine();

        if (conta.getSaldo() < valorTransferencia) {
            System.out.println("Saldo insuficiente para realizar a transfência.");
            return;
        }

        try {
            Transferencia transferencia = new Transferencia(conta, contaDestino, valorTransferencia);
            transferencia.realizar();
            System.out.println(transferencia.getDetalhes());
        } catch (Exception e) {
            System.out.println("Erro ao realizar a tranferência: " + e.getMessage());
        }
    }

    private void mostrarSaldo(Conta conta) {
        System.out.println("Saldo: R$" + conta.getSaldo());
    }
}
