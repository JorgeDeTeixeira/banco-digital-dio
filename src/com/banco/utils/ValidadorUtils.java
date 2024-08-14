package com.banco.utils;

import com.banco.dominio.conta.Conta;

public class ValidadorUtils {
    public boolean isCPF(String cpf) {
        if (cpf == null || cpf.length() != 11 || cpf.matches(cpf.charAt(0) + "{11}")) {
            return false;
        }

        try {
            int[] pesos1 = { 10, 9, 8, 7, 6, 5, 4, 3, 2 };
            int[] pesos2 = { 11, 10, 9, 8, 7, 6, 5, 4, 3, 2 };

            int soma = 0;
            for (int i = 0; i < 9; i++) {
                soma += Character.getNumericValue(cpf.charAt(i)) * pesos1[i];
            }

            int digito1 = 11 - (soma % 11);
            digito1 = (digito1 > 9) ? 0 : digito1;

            soma = 0;
            for (int i = 0; i < 10; i++) {
                soma += Character.getNumericValue(cpf.charAt(i)) * pesos2[i];
            }

            int digito2 = 11 - (soma % 11);
            digito2 = (digito2 > 9) ? 0 : digito2;

            return cpf.equals(cpf.substring(0, 9) + digito1 + digito2);
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static void validarValor(double valor) {
        if (valor <= 0) {
            throw new IllegalArgumentException("O valor da operação deve ser positivo.");
        }
    }

    public static void validarSaldoSuficiente(double saldo, double valor) {
        if (saldo < valor) {
            throw new IllegalStateException("Saldo insuficiente para realizar a operação.");
        }
    }

    public static void validarContaNull(Conta conta) {
        if (conta == null) {
            throw new IllegalArgumentException("Conta não pode ser nula.");
        }
    }

    public static void contasIguais(Conta contaOrigem, Conta contaDestino) {
        if (contaOrigem.equals(contaDestino)) {
            throw new IllegalArgumentException("As contas de origem e destino não podem ser iguais.");
        }
    }
}
