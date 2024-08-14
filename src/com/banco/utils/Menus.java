package com.banco.utils;

/**
 * Classe responsável pela exibição dos menus no sistema bancário.
 */
public class Menus {

    // Constantes para os menus
    private static final String MENU_PRINCIPAL = "1. Criar Conta\n" +
            "2. Acessar Conta\n" +
            "3. Sair";

    private static final String MENU_CONTA = "1. Depositar\n" +
            "2. Sacar\n" +
            "3. Transferir\n" +
            "4. Saldo\n" +
            "5. Voltar";

    private static final String MENU_TRANSFERIR = "1. Transferir para Conta Corrente\n" +
            "2. Transferir para Conta Poupança\n" +
            "3. Voltar";

    private static final String MENU_TIPO_CONTA = "1. Conta Corrente\n" +
            "2. Conta Poupança\n" +
            "3. Voltar";

    /**
     * Exibe o menu principal.
     */
    public static void exibirMenuPrincipal() {
        exibirMenu(MENU_PRINCIPAL);
    }

    /**
     * Exibe o menu de operações da conta.
     */
    public static void exibirMenuContaOperacoes() {
        exibirMenu(MENU_CONTA);
    }

    /**
     * Exibe o menu de opções para transferência.
     */
    public static void exibirMenuTransferir() {
        exibirMenu(MENU_TRANSFERIR);
    }

    /**
     * Exibe o menu para selecionar o tipo de conta.
     */
    public static void exibirMenuTipoConta() {
        exibirMenu(MENU_TIPO_CONTA);
    }

    /**
     * Exibe um menu com as opções fornecidas.
     * 
     * @param menu O menu a ser exibido.
     */
    private static void exibirMenu(String menu) {
        System.out.println(menu);
    }
}
