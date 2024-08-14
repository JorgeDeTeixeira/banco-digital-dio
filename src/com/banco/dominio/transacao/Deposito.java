package com.banco.dominio.transacao;

import java.time.LocalDate;

import com.banco.dominio.conta.Conta;
import com.banco.utils.ValidadorUtils;

public class Deposito implements Transacao {
    private final Conta conta;
    private final double valor;
    private final LocalDate data;

    public Deposito(Conta conta, Double valor) {
        ValidadorUtils.validarContaNull(conta);
        ValidadorUtils.validarValor(valor);

        this.conta = conta;
        this.valor = valor;
        this.data = LocalDate.now();
    }

    @Override
    public void realizar() {
        try {
            conta.depositar(valor);
        } catch (Exception e) {
            throw new IllegalStateException("Erro ao realizar a transferência.");
        }
    }

    @Override
    public String getDetalhes() {
        return String.format("Depósito de R$%.2f realizado em %s na conta %d",
                valor, data.toString(), conta.getNumero());
    }

    public Conta getConta() {
        return conta;
    }

    public double getValor() {
        return valor;
    }

    public LocalDate getData() {
        return data;
    }

}
