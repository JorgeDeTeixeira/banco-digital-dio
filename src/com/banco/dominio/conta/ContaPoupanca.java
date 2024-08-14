package com.banco.dominio.conta;

import com.banco.dominio.cliente.Cliente;
import com.banco.utils.ValidadorUtils;

/**
 * Representa uma conta poupança com taxa de rendimento.
 */
public class ContaPoupanca extends Conta {
    private double taxaRendimento;

    /**
     * Construtor para inicializar uma conta poupança com um cliente.
     *
     * @param cliente Cliente associado à conta.
     */
    public ContaPoupanca(Cliente cliente) {
        super(cliente);
        this.taxaRendimento = 0.02; // Taxa padrão de 2%
    }

    /**
     * Retorna a taxa de rendimento da conta poupança.
     *
     * @return Taxa de rendimento.
     */
    public double getTaxaRendimento() {
        return taxaRendimento;
    }

    /**
     * Define a taxa de rendimento da conta poupança.
     *
     * @param taxaRendimento Nova taxa de rendimento.
     */
    public void setTaxaRendimento(double taxaRendimento) {
        ValidadorUtils.validarValor(taxaRendimento);
        if (taxaRendimento < 0) {
            throw new IllegalArgumentException("A taxa de rendimento não pode ser negativa.");
        }
        this.taxaRendimento = taxaRendimento;
    }

    @Override
    public void sacar(double valor) {
        try {
            ValidadorUtils.validarValor(valor);
            ValidadorUtils.validarSaldoSuficiente(getSaldo(), valor);
            setSaldo(getSaldo() - valor);
        } catch (IllegalArgumentException e) {
            System.err.println("Erro ao sacar: " + e.getMessage());
        }
    }

    @Override
    public void depositar(double valor) {
        try {
            ValidadorUtils.validarValor(valor);
            setSaldo(getSaldo() + valor);
        } catch (IllegalArgumentException e) {
            System.err.println("Erro ao depositar: " + e.getMessage());
        }
    }

    @Override
    public void transferir(double valor, Conta contaDestino) {
        try {
            ValidadorUtils.validarValor(valor);
            ValidadorUtils.validarSaldoSuficiente(getSaldo(), valor);
            setSaldo(getSaldo() - valor);
            contaDestino.depositar(valor);
        } catch (IllegalArgumentException e) {
            System.err.println("Erro ao transferir: " + e.getMessage());
        }
    }

    /**
     * Aplica o rendimento à conta poupança.
     */
    public void aplicarRendimento() {
        double rendimento = getSaldo() * taxaRendimento;
        setSaldo(getSaldo() + rendimento);
    }

    @Override
    public String toString() {
        return String.format("Conta Poupança Número: %d, Saldo: %.2f, Taxa de Rendimento: %.2f%%, Cliente: %s",
                getNumero(), getSaldo(), taxaRendimento * 100, getCliente().getNome());
    }
}
