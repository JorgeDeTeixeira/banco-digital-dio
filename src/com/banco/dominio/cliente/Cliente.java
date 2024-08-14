package com.banco.dominio.cliente;

public class Cliente {
    private String nome;
    private String cpf;
    private int senha;

    public Cliente(String nome, String cpf, int senha) {
        this.nome = nome;
        this.cpf = cpf;
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public int getSenha() {
        return senha;
    }
}
