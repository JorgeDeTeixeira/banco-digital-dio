2. Especificação das Classes

## 2.1. Classe Banco

**Pacote:** com.banco.dominio.banco  
**Responsabilidade:** Representar o banco e gerenciar as contas dos clientes.  
**Atributos:**

```java
private String nome;
private List<Conta> contas; // Lista para armazenar todas as contas do banco
```

**Métodos:**

```java
public void adicionarConta(Conta conta); // Adiciona uma nova conta à lista de contas do banco.
public Conta buscarConta(int numeroConta); // Busca uma conta pelo número.
public List<Conta> listarContas(); // Retorna a lista de todas as contas.
```

## 2.2. Classe Cliente

**Pacote:** com.banco.dominio.cliente  
**Responsabilidade:** Representar um cliente do banco.  
**Atributos:**

```java
private String nome;
private String cpf;
private String endereco;
```

**Métodos:**

- Construtores, getters e setters para os atributos encapsulados.

## 2.3. Classe Abstrata Conta

**Pacote:** com.banco.dominio.conta  
**Responsabilidade:** Representar a abstração de uma conta bancária.  
**Atributos:**

```java
protected int numero;
protected double saldo;
protected Cliente cliente;
```

**Métodos:**

```java
public abstract void sacar(double valor);
public abstract void depositar(double valor);
public abstract void transferir(double valor, Conta contaDestino);
public int getNumero(); // Retorna o número da conta.
public double getSaldo(); // Retorna o saldo da conta.
```

## 2.4. Classe ContaCorrente

**Pacote:** com.banco.dominio.conta  
**Herança:** extends Conta  
**Responsabilidade:** Representar uma conta corrente, com funcionalidades específicas.  
**Atributos:**

```java
private double limiteChequeEspecial; // Limite de cheque especial, por exemplo
```

**Métodos:**

- Implementar os métodos abstratos da classe Conta.
- Métodos específicos para o cheque especial, se necessário.

## 2.5. Classe ContaPoupanca

**Pacote:** com.banco.dominio.conta  
**Herança:** extends Conta  
**Responsabilidade:** Representar uma conta poupança.  
**Atributos:**

```java
private double taxaRendimento; // Taxa de rendimento da poupança
```

**Métodos:**

- Implementar os métodos abstratos da classe Conta.
- Métodos específicos para calcular o rendimento, se necessário.

## 2.6. Interface Transacao

**Pacote:** com.banco.dominio.transacao  
**Responsabilidade:** Definir o contrato para transações bancárias.  
**Métodos:**

```java
public void realizar(); // Método para realizar uma transação.
public String getDetalhes(); // Retorna os detalhes da transação.
```

## 2.7. Classe Deposito

**Pacote:** com.banco.dominio.transacao  
**Implementação:** implements Transacao  
**Responsabilidade:** Representar uma transação de depósito.  
**Atributos:**

```java
private Conta conta;
private double valor;
private Date data;
```

**Métodos:**

- Implementar os métodos da interface Transacao.

## 2.8. Classe Saque

**Pacote:** com.banco.dominio.transacao  
**Implementação:** implements Transacao  
**Responsabilidade:** Representar uma transação de saque.  
**Atributos:**

```java
private Conta conta;
private double valor;
private Date data;
```

**Métodos:**

- Implementar os métodos da interface Transacao.

## 2.9. Classe Transferencia

**Pacote:** com.banco.dominio.transacao  
**Implementação:** implements Transacao  
**Responsabilidade:** Representar uma transação de transferência entre contas.  
**Atributos:**

```java
private Conta contaOrigem;
private Conta contaDestino;
private double valor;
private Date data;
```

**Métodos:**

- Implementar os métodos da interface Transacao.

## 3. Serviços

### 3.1. Classe ServicoConta

**Pacote:** com.banco.servicos  
**Responsabilidade:** Fornecer métodos para operações comuns com contas.  
**Métodos:**

```java
public void realizarSaque(Conta conta, double valor);
public void realizarDeposito(Conta conta, double valor);
public void realizarTransferencia(Conta origem, Conta destino, double valor);
```

## 4. Utilitários

### 4.1. Classe ValidadorCPF

**Pacote:** com.banco.utils  
**Responsabilidade:** Fornecer métodos para validação de CPF.  
**Métodos:**

```java
public static boolean validar(String cpf);
```

## 5. Testes

Colocar testes unitários no pacote src/test/java, seguindo a mesma estrutura de pacotes da aplicação. Para cada classe, crie testes que validem o comportamento esperado.

## 6. Boas Práticas

- Encapsulamento: Todos os atributos devem ser privados, acessíveis apenas via getters e setters.
- Interfaces: Use interfaces para definir contratos claros, como no caso das transações.
- Polimorfismo: Explore o polimorfismo nos métodos que operam sobre contas e transações.
- Herança: Mantenha a hierarquia de classes simples e evite heranças profundas.
- Coesão: Mantenha as classes coesas, focadas em uma única responsabilidade.

Esta especificação deve fornecer uma base sólida para o desenvolvimento de um Banco Digital orientado a objetos em Java, aplicando conceitos essenciais como abstração, encapsulamento, herança e polimorfismo.
