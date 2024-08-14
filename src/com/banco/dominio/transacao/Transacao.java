package com.banco.dominio.transacao;

/**
 * Interface que define o contrato para uma transação.
 */
public interface Transacao {

    /**
     * Realiza a transação.
     */
    void realizar();

    /**
     * Retorna os detalhes da transação.
     *
     * @return Detalhes da transação como uma string.
     */
    String getDetalhes();
}
