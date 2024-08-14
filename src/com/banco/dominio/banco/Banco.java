package com.banco.dominio.banco;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.banco.dominio.conta.Conta;
import com.banco.utils.ValidadorUtils;

/**
 * Classe que representa um banco com uma lista de contas.
 */
public class Banco {
    private String nome;
    private List<Conta> contas;

    /**
     * Construtor padrão que inicializa a lista de contas.
     */
    public Banco() {
        this.contas = new ArrayList<>();
    }

    /**
     * Construtor que inicializa o nome e a lista de contas.
     *
     * @param nome   Nome do banco.
     * @param contas Lista inicial de contas.
     */
    public Banco(String nome, List<Conta> contas) {
        this.nome = nome;
        this.contas = new ArrayList<>(contas);
    }

    /**
     * Adiciona uma conta ao banco.
     *
     * @param conta Conta a ser adicionada.
     * @throws IllegalStateException Se a conta for nula.
     */
    public void adicionarConta(Conta conta) {
        ValidadorUtils.validarContaNull(conta);
        contas.add(conta);
    }

    /**
     * Busca uma conta pelo número.
     *
     * @param numeroConta Número da conta a ser buscada.
     * @return Conta correspondente ao número fornecido.
     * @throws IllegalStateException Se não houver contas cadastradas ou se a conta
     *                               não for encontrada.
     */
    public Conta buscarConta(int numeroConta) {
        if (contas.isEmpty()) {
            throw new IllegalStateException("Não há contas cadastradas.");
        }

        return contas.stream()
                .filter(c -> c.getNumero() == numeroConta)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Conta não encontrada."));
    }

    /**
     * Retorna uma lista não modificável de todas as contas.
     *
     * @return Lista de contas.
     * @throws IllegalStateException Se ocorrer um erro ao listar as contas.
     */
    public List<Conta> listarContas() {
        if (contas.isEmpty()) {
            throw new IllegalStateException("Não há contas cadastradas.");
        }
        return Collections.unmodifiableList(contas);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Conta> getContas() {
        return Collections.unmodifiableList(contas);
    }

    public void setContas(List<Conta> contas) {
        this.contas = new ArrayList<>(contas);
    }
}
