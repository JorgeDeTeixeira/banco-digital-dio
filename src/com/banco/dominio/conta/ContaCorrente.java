package com.banco.dominio.conta;

import com.banco.dominio.cliente.Cliente;
import com.banco.utils.ValidadorUtils;

/**
 * Representa uma conta corrente com limite de cheque especial.
 */
public class ContaCorrente extends Conta {
    private double limiteChequeEspecial;

    /**
     * Construtor para inicializar uma conta corrente com um cliente.
     *
     * @param cliente Cliente associado à conta.
     */
    public ContaCorrente(Cliente cliente) {
        super(cliente);
        this.limiteChequeEspecial = 1000.0; // Limite padrão
    }

    /**
     * Retorna o limite de cheque especial da conta corrente.
     *
     * @return Limite de cheque especial.
     */
    public double getLimiteChequeEspecial() {
        return limiteChequeEspecial;
    }

    /**
     * Define o limite de cheque especial da conta corrente.
     *
     * @param limiteChequeEspecial Novo limite de cheque especial.
     */
    public void setLimiteChequeEspecial(double limiteChequeEspecial) {
        ValidadorUtils.validarValor(limiteChequeEspecial);
        this.limiteChequeEspecial = limiteChequeEspecial;
    }

    @Override
    public void sacar(double valor) {
        try {
            ValidadorUtils.validarValor(valor);
            if (getSaldo() + limiteChequeEspecial < valor) {
                throw new IllegalArgumentException("Saldo insuficiente, incluindo limite de cheque especial.");
            }
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
            if (getSaldo() + limiteChequeEspecial < valor) {
                throw new IllegalArgumentException(
                        "Saldo insuficiente para transferência, incluindo limite de cheque especial.");
            }
            setSaldo(getSaldo() - valor);
            contaDestino.depositar(valor);
        } catch (IllegalArgumentException e) {
            System.err.println("Erro ao transferir: " + e.getMessage());
        }
    }

    @Override
    public String toString() {
        return String.format("Conta Corrente Número: %d, Saldo: %.2f, Limite Cheque Especial: %.2f, Cliente: %s",
                getNumero(), getSaldo(), limiteChequeEspecial, getCliente().getNome());
    }
}
