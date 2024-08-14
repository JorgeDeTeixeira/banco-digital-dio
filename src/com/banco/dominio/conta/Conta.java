package com.banco.dominio.conta;

import com.banco.dominio.cliente.Cliente;

/**
 * Classe abstrata que representa uma conta bancária.
 */
public abstract class Conta {
    private int numero;
    protected double saldo;
    private Cliente cliente;

    /**
     * Construtor para inicializar uma conta com número, saldo e cliente.
     *
     * @param numero  Número da conta
     * @param saldo   Saldo inicial da conta
     * @param cliente Cliente associado à conta
     */
    public Conta(Cliente cliente) {
        this.numero = (int) (Math.random() * 2000) + 1;
        this.saldo = 0;
        this.cliente = cliente;
    }

    /**
     * Método abstrato para sacar um valor da conta.
     *
     * @param valor Valor a ser sacado
     */
    public abstract void sacar(Double valor);

    /**
     * Método abstrato para depositar um valor na conta.
     *
     * @param valor Valor a ser depositado
     */
    public abstract void depositar(Double valor);

    /**
     * Método abstrato para transferir um valor para outra conta.
     *
     * @param valor        Valor a ser transferido
     * @param contaDestino Conta de destino para a transferência
     */
    public abstract void transferir(Double valor, Conta contaDestino);

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
    public Double getSaldo() {
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
    protected void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    /**
     * Exibe informações da conta.
     *
     * @return String formatada com informações da conta
     */
    public String exibirInformacoes() {
        return String.format("Conta Número: %d, Saldo: %.2f, Cliente: %s", numero, saldo, cliente.getNome());
    }
}