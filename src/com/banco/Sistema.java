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

/**
 * Classe responsável pela interação com o usuário e execução das operações
 * bancárias.
 */
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
            Menus.exibirMenuPrincipal();
            System.out.print("Digite uma opção:");
            int opcao = obterOpcao();
            scanner.nextLine(); // Limpa o buffer do scanner

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

    private int obterOpcao() {
        while (!scanner.hasNextInt()) {
            System.out.println("Entrada inválida. Digite um número.");
            scanner.next(); // Descarta a entrada inválida
        }
        return scanner.nextInt();
    }

    private void criarConta() {
        System.out.println("Criando conta...");
        String nome = obterEntrada("Nome: ");
        String cpf = obterEntrada("CPF: ");
        String senha = obterEntrada("Senha: ");
        Cliente cliente = new Cliente(nome, cpf, senha);

        Menus.exibirMenuTipoConta();
        System.out.print("Tipo da conta: ");
        int tipoConta = obterOpcao();

        Conta novaConta = criarTipoConta(cliente, tipoConta);

        if (novaConta != null) {
            banco.adicionarConta(novaConta);
            System.out.println("Conta criada com sucesso!");
            System.out.println("Cliente: " + cliente.getNome());
            System.out.println("Número da Conta: " + novaConta.getNumero());
        } else {
            System.out.println("Tipo de conta inválido. Conta não criada.");
        }
    }

    private Conta criarTipoConta(Cliente cliente, int tipoConta) {
        switch (tipoConta) {
            case 1:
                return new ContaCorrente(cliente);
            case 2:
                return new ContaPoupanca(cliente);
            default:
                return null;
        }
    }

    private void acessarConta() {
        System.out.println("Acessando conta...");
        System.out.print("Digite o número da conta:");
        int numeroConta = obterOpcao();
        scanner.nextLine(); // Limpa o buffer do scanner

        Conta conta = buscarConta(numeroConta);
        if (conta == null) {
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
            System.out.println("Conta não encontrada. " + e.getMessage());
            return null;
        }
    }

    private boolean autenticarConta(Conta conta) {
        String senha = obterEntrada("Digite a senha:");
        int tentativasRestantes = 3;

        while (tentativasRestantes > 0) {
            if (senha.equals(conta.getCliente().getSenha())) {
                return true;
            } else {
                tentativasRestantes--;
                System.out.println("Senha incorreta, " + tentativasRestantes + " tentativas restantes.");
                if (tentativasRestantes > 0) {
                    senha = obterEntrada("Digite a senha:");
                }
            }
        }
        return false;
    }

    private void realizarOperacaoConta(Conta conta) {
        boolean continuar = true;
        while (continuar) {
            Menus.exibirMenuContaOperacoes();
            System.out.print("Digite uma opção:");
            int opcaoConta = obterOpcao();
            scanner.nextLine(); // Limpa o buffer do scanner

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
        double valorDeposito = obterValor("Valor a ser depositado: ");
        try {
            Deposito deposito = new Deposito(conta, valorDeposito);
            deposito.realizar();
            System.out.println(deposito.getDetalhes());
        } catch (Exception e) {
            System.out.println("Erro ao realizar o depósito: " + e.getMessage());
        }
    }

    private void realizarSaque(Conta conta) {
        double valorSacado = obterValor("Valor a ser sacado: ");
        try {
            Saque saque = new Saque(conta, valorSacado);
            saque.realizar();
            System.out.println(saque.getDetalhes());
        } catch (Exception e) {
            System.out.println("Erro ao realizar saque: " + e.getMessage());
        }
    }

    private void realizarTransferencia(Conta conta) {
        System.out.print("Digite o número da conta de destino: ");
        int numeroContaDestino = obterOpcao();
        scanner.nextLine(); // Limpa o buffer do scanner
        Conta contaDestino = buscarConta(numeroContaDestino);

        if (contaDestino == null || contaDestino.equals(conta)) {
            System.out.println("Conta de destino inválida. Voltando ao menu principal.");
            return;
        }

        double valorTransferencia = obterValor("Valor a ser transferido: ");
        try {
            Transferencia transferencia = new Transferencia(conta, contaDestino, valorTransferencia);
            transferencia.realizar();
            System.out.println(transferencia.getDetalhes());
        } catch (Exception e) {
            System.out.println("Erro ao realizar a transferência: " + e.getMessage());
        }
    }

    private double obterValor(String mensagem) {
        System.out.print(mensagem);
        while (!scanner.hasNextDouble()) {
            System.out.println("Valor inválido. Digite um número válido.");
            scanner.next(); // Descarta a entrada inválida
        }
        return scanner.nextDouble();
    }

    private String obterEntrada(String mensagem) {
        System.out.print(mensagem);
        return scanner.nextLine();
    }

    private void mostrarSaldo(Conta conta) {
        System.out.println("Saldo: R$" + conta.getSaldo());
    }
}
