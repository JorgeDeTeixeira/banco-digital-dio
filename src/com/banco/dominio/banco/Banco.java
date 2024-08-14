package com.banco.dominio.banco;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

import com.banco.dominio.conta.Conta;
import com.banco.utils.ValidadorUtils;

public class Banco {
    private String nome;
    private List<Conta> contas = new ArrayList<>();

    public void adicionarConta(Conta conta) {
        try {
            ValidadorUtils.validarContaNull(conta);
            contas.add(conta);
        } catch (NullPointerException e) {
            throw new IllegalStateException("Erro ao adicionar conta: a conta fornecida é nula.");
        } catch (Exception e) {
            throw new IllegalStateException("Erro inesperado ao adicionar conta.", e);
        }

    }

    public Conta buscarConta(int numeroConta) {
        try {
            if (contas.isEmpty()) {
                throw new IllegalStateException("Não há contas cadastradas.");
            }
            return contas.stream().filter(c -> c.getNumero() == numeroConta).findFirst()
                    .orElseThrow(() -> new IllegalArgumentException());
        } catch (Exception e) {
            throw new IllegalStateException("Conta não encontrada.");
        }
    }

    public List<Conta> listarContas() {
        try {
            if (contas.isEmpty()) {
                throw new IllegalStateException("Não há contas cadastradas.");
            }
            return Collections.unmodifiableList(contas);
        } catch (Exception e) {
            throw new IllegalStateException("Erro ao listar contas.");
        }
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Conta> getContas() {
        return contas;
    }

    public void setContas(List<Conta> contas) {
        this.contas = contas;
    }
}
