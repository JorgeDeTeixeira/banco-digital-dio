package com.banco.dominio.transacao;

import java.time.LocalDate;

import com.banco.dominio.conta.Conta;
import com.banco.utils.ValidadorUtils;

/**
 * Representa uma transação de transferência entre contas.
 */
public class Transferencia implements Transacao {
    private final Conta contaOrigem;
    private final Conta contaDestino;
    private final double valor;
    private final LocalDate data;

    /**
     * Construtor para inicializar uma transação de transferência.
     *
     * @param contaOrigem  Conta de origem da transferência.
     * @param contaDestino Conta de destino da transferência.
     * @param valor        Valor a ser transferido.
     */
    public Transferencia(Conta contaOrigem, Conta contaDestino, double valor) {
        ValidadorUtils.validarContaNull(contaOrigem);
        ValidadorUtils.validarContaNull(contaDestino);
        ValidadorUtils.validarValor(valor);

        if (contaOrigem.equals(contaDestino)) {
            throw new IllegalArgumentException("Conta de origem e conta de destino não podem ser iguais.");
        }

        this.contaOrigem = contaOrigem;
        this.contaDestino = contaDestino;
        this.valor = valor;
        this.data = LocalDate.now();
    }

    @Override
    public void realizar() {
        try {
            contaOrigem.sacar(valor);
            contaDestino.depositar(valor);
        } catch (Exception e) {
            throw new IllegalStateException("Erro ao realizar a transferência: " + e.getMessage(), e);
        }
    }

    @Override
    public String getDetalhes() {
        return String.format("Transferência de R$%.2f realizada em %s da conta %d para a conta %d",
                valor, data, contaOrigem.getNumero(), contaDestino.getNumero());
    }

    /**
     * Retorna a conta de origem da transferência.
     *
     * @return Conta de origem da transferência.
     */
    public Conta getContaOrigem() {
        return contaOrigem;
    }

    /**
     * Retorna a conta de destino da transferência.
     *
     * @return Conta de destino da transferência.
     */
    public Conta getContaDestino() {
        return contaDestino;
    }

    /**
     * Retorna o valor da transferência.
     *
     * @return Valor da transferência.
     */
    public double getValor() {
        return valor;
    }

    /**
     * Retorna a data da transferência.
     *
     * @return Data da transferência.
     */
    public LocalDate getData() {
        return data;
    }
}
