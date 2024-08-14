package com.banco.dominio.conta;

import com.banco.dominio.cliente.Cliente;
import com.banco.utils.ValidadorUtils;

public class ContaPoupanca extends Conta {
    private Double taxaRendimento;

    public ContaPoupanca(Cliente cliente) {
        super(cliente);
        this.taxaRendimento = 1.02;
    }

    public Double getTaxaRendimento() {
        return taxaRendimento;
    }

    public void setTaxaRendimento(Double taxaRendimento) {
        this.taxaRendimento = taxaRendimento;
    }

    @Override
    public void sacar(Double valor) {
        try {
            ValidadorUtils.validarValor(valor);
            ValidadorUtils.validarSaldoSuficiente(saldo, valor);
            setSaldo(getSaldo() - valor);
        } catch (IllegalArgumentException e) {
            System.err.println("Erro ao sacar: " + e.getMessage());
        }
    }

    @Override
    public void depositar(Double valor) {
        try {
            ValidadorUtils.validarValor(valor);
            setSaldo(getSaldo() + valor);
        } catch (IllegalArgumentException e) {
            System.err.println("Erro ao depositar: " + e.getMessage());
        }
    }

    @Override
    public void transferir(Double valor, Conta contaDestino) {
        try {
            ValidadorUtils.validarValor(valor);
            ValidadorUtils.validarSaldoSuficiente(getSaldo(), valor);
            setSaldo(getSaldo() - valor);
            contaDestino.depositar(valor);
        } catch (IllegalArgumentException e) {
            System.err.println("Erro ao transferir: " + e.getMessage());

        }
    }

    public void calcularRendimento() {
        setSaldo(getSaldo() + getSaldo() * taxaRendimento);
    }

}
