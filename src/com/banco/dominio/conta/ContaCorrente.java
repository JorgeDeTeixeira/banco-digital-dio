package com.banco.dominio.conta;

import com.banco.dominio.cliente.Cliente;
import com.banco.utils.ValidadorUtils;

public class ContaCorrente extends Conta {
    private double limiteChequeEspecial;

    public ContaCorrente(Cliente cliente) {
        super(cliente);
        this.limiteChequeEspecial = 1000;
    }

    public double getLimiteChequeEspecial() {
        return limiteChequeEspecial;
    }

    public void setLimiteChequeEspecial(Double limiteChequeEspecial) {
        ValidadorUtils.validarValor(limiteChequeEspecial);
        this.limiteChequeEspecial = limiteChequeEspecial;
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
            ValidadorUtils.validarSaldoSuficiente(saldo, valor);
            setSaldo(getSaldo() - valor);
            contaDestino.depositar(valor);
        } catch (IllegalArgumentException e) {
            System.err.println("Erro ao transferir: " + e.getMessage());
        }
    }

}
