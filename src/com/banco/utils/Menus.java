package com.banco.utils;

public class Menus {
    public static void menuPrincipal() {
        System.out.println("1. Criar Conta");
        System.out.println("2. Acessar Conta");
        System.out.println("3. Sair");
    }

    public static void menuConta() {
        System.out.println("1. Depositar");
        System.out.println("2. Sacar");
        System.out.println("3. Transferir");
        System.out.println("4. Saldo");
        System.out.println("5. Voltar");
    }

    public static void menuTransferir() {
        System.out.println("1. Transferir para Conta Corrente");
        System.out.println("2. Transferir para Conta Poupança");
        System.out.println("3. Voltar");
    }

    public static void menuTipoConta() {
        System.out.println("1. Conta Corrente");
        System.out.println("2. Conta Poupança");
        System.out.println("3. Voltar");
    }
}
