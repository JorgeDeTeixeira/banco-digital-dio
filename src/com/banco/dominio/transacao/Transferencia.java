package com.banco.dominio.transacao;

import java.time.LocalDate;

import com.banco.dominio.conta.Conta;
import com.banco.utils.ValidadorUtils;

public class Transferencia implements Transacao {
    private final Conta contaOrigem;
    private final Conta contaDestino;
    private final double valor;
    private final LocalDate data;

    public Transferencia(Conta contaOrigem, Conta contaDestino, double valor) {
        ValidadorUtils.validarContaNull(contaOrigem);
        ValidadorUtils.validarContaNull(contaDestino);
        ValidadorUtils.validarValor(valor);

        this.contaOrigem = contaOrigem;
        this.contaDestino = contaDestino;
        this.valor = valor;
        this.data = LocalDate.now();
    }

    @Override
    public void realizar() {
        try {
            ValidadorUtils.contasIguais(contaOrigem, contaDestino);
            contaOrigem.sacar(valor);
            contaDestino.depositar(valor);
        } catch (Exception e) {
            throw new IllegalStateException("Erro ao realizar a transferência.");
        }
    }

    @Override
    public String getDetalhes() {
        return String.format("Transferência de R$%.2f realizada em %s da conta %d para a conta %d",
                valor, data.toString(), contaOrigem.getNumero(), contaDestino.getNumero());
    }

    public Conta getContaOrigem() {
        return contaOrigem;
    }

    public Conta getContaDestino() {
        return contaDestino;
    }

    public double getValor() {
        return valor;
    }

    public LocalDate getData() {
        return data;
    }

}
