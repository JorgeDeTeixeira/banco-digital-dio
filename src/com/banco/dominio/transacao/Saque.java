package com.banco.dominio.transacao;

import java.time.LocalDate;

import com.banco.dominio.conta.Conta;
import com.banco.utils.ValidadorUtils;

/**
 * Representa uma transação de saque em uma conta.
 */
public class Saque implements Transacao {
    private final Conta conta;
    private final double valor;
    private final LocalDate data;

    /**
     * Construtor para inicializar uma transação de saque.
     *
     * @param conta Conta da qual o saque será realizado.
     * @param valor Valor a ser sacado.
     */
    public Saque(Conta conta, double valor) {
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
            throw new IllegalStateException("Erro ao realizar o saque: " + e.getMessage(), e);
        }
    }

    @Override
    public String getDetalhes() {
        return String.format("Saque de R$%.2f realizado em %s na conta %d",
                valor, data, conta.getNumero());
    }

    /**
     * Retorna a conta associada ao saque.
     *
     * @return Conta associada ao saque.
     */
    public Conta getConta() {
        return conta;
    }

    /**
     * Retorna o valor do saque.
     *
     * @return Valor do saque.
     */
    public double getValor() {
        return valor;
    }

    /**
     * Retorna a data do saque.
     *
     * @return Data do saque.
     */
    public LocalDate getData() {
        return data;
    }
}
