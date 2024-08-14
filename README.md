Banco Digital
# Banco Digital

O Banco Digital é um sistema de gerenciamento bancário desenvolvido em Java. O sistema permite a criação e gerenciamento de contas bancárias, incluindo funcionalidades para depósitos, saques, transferências e visualização de saldo. Ele é projetado para ser executado no terminal e oferece uma interface de usuário simples e intuitiva.

## Funcionalidades

- Criar Conta: Permite a criação de contas correntes ou contas poupança para clientes.
- Acessar Conta: Permite que os clientes acessem suas contas usando um número de conta e uma senha.
- Realizar Depósitos: Permite o depósito de valores em uma conta.
- Realizar Saques: Permite o saque de valores de uma conta.
- Transferir Valores: Permite a transferência de valores entre contas.
- Visualizar Saldo: Permite a visualização do saldo atual de uma conta.

## Estrutura do Projeto

O projeto é estruturado da seguinte forma:

```
src/
├── main/
│   └── java/
│       └── com/
│           └── banco/
│               ├── Main.java
│               ├── Sistema.java
│               ├── dominio/
│               │   ├── banco/
│               │   │   └── Banco.java
│               │   ├── cliente/
│               │   │   └── Cliente.java
│               │   ├── conta/
│               │   │   ├── Conta.java
│               │   │   ├── ContaCorrente.java
│               │   │   └── ContaPoupanca.java
│               │   └── transacao/
│               │       ├── Transacao.java
│               │       ├── Deposito.java
│               │       ├── Saque.java
│               │       └── Transferencia.java
│               └── utils/
│                   ├── Menus.java
│                   └── ValidadorUtils.java
└── test/
    └── java/
        └── com/
            └── banco/
                └── (testes unitários e de integração)
```

## Configuração

Para executar o projeto, siga as etapas abaixo:

1. Clone o repositório:
```
git clone https://github.com/seu-usuario/banco-digital.git
```

2. Navegue até o diretório do projeto:
```
cd banco-digital
```

3. Compile o projeto:
   - Se estiver usando o Maven, você pode compilar o projeto com:
   ```
   mvn compile
   ```
   - Ou, se estiver usando uma IDE, execute a classe Main diretamente.

## Uso

### Menu Principal

Quando o sistema for iniciado, o menu principal será exibido:

```
1. Criar Conta
2. Acessar Conta
3. Sair
```

Escolha uma opção digitando o número correspondente e siga as instruções exibidas na tela.

### Criar Conta

Para criar uma conta, você deve fornecer o nome do cliente, CPF e senha. Em seguida, escolher o tipo de conta (Conta Corrente ou Conta Poupança).

### Acessar Conta

Para acessar uma conta, forneça o número da conta e a senha associada. Após autenticação, você poderá realizar operações na conta.

### Operações da Conta

- Depositar: Insira o valor a ser depositado.
- Sacar: Insira o valor a ser sacado.
- Transferir: Insira o número da conta de destino e o valor a ser transferido.
- Visualizar Saldo: Mostra o saldo atual da conta.

### Exemplo de Uso

Criar uma Conta Corrente:

```yaml
Nome: João Silva
CPF: 12345678901
Senha: 1234
Tipo de Conta: 1 (Conta Corrente)
```

Acessar a Conta e Realizar uma Transferência:

```yaml
Número da Conta: 123
Senha: 1234
Valor a ser transferido: 100
Número da conta de destino: 456
```

## Contribuição

Se você quiser contribuir para este projeto, siga estas etapas:

1. Faça um fork do repositório.
2. Crie uma branch para sua feature (`git checkout -b minha-feature`).
3. Faça commit das suas alterações (`git commit -am 'Adiciona nova feature'`).
4. Faça push para a branch (`git push origin minha-feature`).
5. Abra um Pull Request.

## Licença

Este projeto está licenciado sob a Licença MIT. Veja o arquivo LICENSE para mais detalhes.