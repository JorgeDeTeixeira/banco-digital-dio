package com.banco.dominio.conta;

import com.banco.dominio.cliente.Cliente;

/**
 * Classe abstrata que representa uma conta bancária.
 */
public abstract class Conta {
    private final int numero; // Tornar final para garantir a imutabilidade
    private double saldo;
    private final Cliente cliente; // Tornar final para garantir a imutabilidade

    /**
     * Construtor para inicializar uma conta com um cliente.
     *
     * @param cliente Cliente associado à conta
     */
    public Conta(Cliente cliente) {
        this.numero = gerarNumeroConta();
        this.saldo = 0.0;
        this.cliente = cliente;
    }

    /**
     * Método abstrato para sacar um valor da conta.
     *
     * @param valor Valor a ser sacado
     */
    public abstract void sacar(double valor);

    /**
     * Método abstrato para depositar um valor na conta.
     *
     * @param valor Valor a ser depositado
     */
    public abstract void depositar(double valor);

    /**
     * Método abstrato para transferir um valor para outra conta.
     *
     * @param valor        Valor a ser transferido
     * @param contaDestino Conta de destino para a transferência
     */
    public abstract void transferir(double valor, Conta contaDestino);

    /**
     * Retorna o número da conta.
     *
     * @return Número da conta
     */
    public int getNumero() {
        return numero;
    }

    /**
     * Retorna o saldo da conta.
     *
     * @return Saldo da conta
     */
    public double getSaldo() {
        return saldo;
    }

    /**
     * Retorna o cliente associado à conta.
     *
     * @return Cliente da conta
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * Define o saldo da conta.
     *
     * @param saldo Novo saldo da conta
     */
    protected void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    /**
     * Gera um número de conta único.
     *
     * @return Número da conta gerado
     */
    private int gerarNumeroConta() {
        // Implementar lógica para garantir unicidade e evitar colisões
        return (int) (Math.random() * 10000); // Exemplo simplificado
    }

    @Override
    public String toString() {
        return String.format("Conta Número: %d, Saldo: %.2f, Cliente: %s", numero, saldo, cliente.getNome());
    }
}
