package com.banco.dominio.transacao;

import java.time.LocalDate;

import com.banco.dominio.conta.Conta;
import com.banco.utils.ValidadorUtils;

public class Saque implements Transacao {
    private final Conta conta;
    private final Double valor;
    private final LocalDate data;

    public Saque(Conta conta, Double valor) {
        ValidadorUtils.validarContaNull(conta);
        ValidadorUtils.validarValor(valor);

        this.conta = conta;
        this.valor = valor;
        this.data = LocalDate.now();
    }

    @Override
    public void realizar() {
        try {
            conta.sacar(valor);
        } catch (Exception e) {
            throw new IllegalStateException("Erro ao realizar a saque.");
        }
    }

    @Override
    public String getDetalhes() {
        return String.format("Saque de R$%.2f realizado em %s na conta %d",
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
