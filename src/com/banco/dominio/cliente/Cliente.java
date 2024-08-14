package com.banco.dominio.cliente;

/**
 * Representa um cliente do banco.
 */
public class Cliente {
    private final String nome;
    private final String cpf;
    private final String senha; // Alterado para String para maior segurança

    /**
     * Construtor para criar um novo cliente.
     * 
     * @param nome  Nome do cliente.
     * @param cpf   CPF do cliente.
     * @param senha Senha do cliente (armazenada de forma segura).
     */
    public Cliente(String nome, String cpf, String senha) {
        validarNome(nome);
        validarCpf(cpf);
        validarSenha(senha);
        this.nome = nome;
        this.cpf = cpf;
        this.senha = senha; // Considerar aplicar hash em senha antes de armazenar
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getSenha() {
        return senha; // Evitar expor senha diretamente em aplicações reais
    }

    /**
     * Valida o nome do cliente.
     * 
     * @param nome Nome do cliente a ser validado.
     * @throws IllegalArgumentException se o nome for nulo ou vazio.
     */
    private void validarNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome não pode ser nulo ou vazio.");
        }
    }

    /**
     * Valida o CPF do cliente.
     * 
     * @param cpf CPF do cliente a ser validado.
     * @throws IllegalArgumentException se o CPF for nulo ou inválido.
     */
    private void validarCpf(String cpf) {
        if (cpf == null || cpf.trim().isEmpty() || !cpf.matches("\\d{11}")) {
            throw new IllegalArgumentException("CPF deve conter 11 dígitos numéricos.");
        }
    }

    /**
     * Valida a senha do cliente.
     * 
     * @param senha Senha do cliente a ser validada.
     * @throws IllegalArgumentException se a senha for nula ou vazia.
     */
    private void validarSenha(String senha) {
        if (senha == null || senha.trim().isEmpty()) {
            throw new IllegalArgumentException("Senha não pode ser nula ou vazia.");
        }
    }

    @Override
    public String toString() {
        return String.format("Cliente[nome=%s, cpf=%s]", nome, cpf);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Cliente cliente = (Cliente) o;
        return cpf.equals(cliente.cpf);
    }

    @Override
    public int hashCode() {
        return cpf.hashCode();
    }
}
