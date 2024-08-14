package com.banco.dominio.transacao;

import java.time.LocalDate;

import com.banco.dominio.conta.Conta;
import com.banco.utils.ValidadorUtils;

/**
 * Representa uma transação de depósito em uma conta.
 */
public class Deposito implements Transacao {
    private final Conta conta;
    private final double valor;
    private final LocalDate data;

    /**
     * Construtor para inicializar uma transação de depósito.
     *
     * @param conta Conta na qual o depósito será realizado.
     * @param valor Valor a ser depositado.
     */
    public Deposito(Conta conta, double valor) {
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
            throw new IllegalStateException("Erro ao realizar o depósito: " + e.getMessage(), e);
        }
    }

    @Override
    public String getDetalhes() {
        return String.format("Depósito de R$%.2f realizado em %s na conta %d",
                valor, data.toString(), conta.getNumero());
    }

    /**
     * Retorna a conta associada ao depósito.
     *
     * @return Conta associada ao depósito.
     */
    public Conta getConta() {
        return conta;
    }

    /**
     * Retorna o valor do depósito.
     *
     * @return Valor do depósito.
     */
    public double getValor() {
        return valor;
    }

    /**
     * Retorna a data do depósito.
     *
     * @return Data do depósito.
     */
    public LocalDate getData() {
        return data;
    }
}
