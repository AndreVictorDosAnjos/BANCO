/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Mauricio
 */
public class ContaCorrente extends Conta {
    private double taxa;

    public ContaCorrente(){

    }
    public ContaCorrente(String nome, String tipoConta, double saldo, double taxa){
        super(nome, tipoConta, saldo);
        this.taxa = taxa;
    }

    public ContaCorrente(String nome, int numeroConta,String tipoConta, double saldo, double taxa){
        super(nome, numeroConta, tipoConta, saldo);
        this.taxa = taxa;
    }

@Override
public String sacar(double valorS) throws Exception {
    // Verifica se o valor a ser sacado é maior que zero
    if (valorS <= 0) {
        throw new Exception("Digite um valor positivo para sacar.");
    }
    // Verifica se há saldo suficiente para o saque
    if (valorS > this.getSaldo()) {
        throw new Exception("Saldo insuficiente para realizar o saque.");
    }
    // Calcula o valor total do saque, incluindo a taxa
    double valorComTaxa = valorS + (valorS * getTaxa());
    // Verifica se o saldo cobre o valor com a taxa
    if (valorComTaxa > this.getSaldo()) {
        throw new Exception("Saldo insuficiente para cobrir o valor do saque e a taxa.");
    }
    // Deduz o valor com taxa do saldo
    setSaldo(getSaldo() - valorComTaxa);
    return "Saque realizado com sucesso! Saldo atual: " + getSaldo();
}

@Override
public String depositar(double valorD) throws Exception {
    // Verifica se o valor a ser depositado é maior que zero
    if (valorD <= 0) {
        throw new Exception("Digite um valor positivo para depositar.");
    }
    // Atualiza o saldo adicionando o valor do depósito
    setSaldo(this.getSaldo() + valorD);
    return "Depósito realizado com sucesso! Saldo atual: " + getSaldo();
}
public String consultarSaldo() {
    return "Saldo atual: R$ " + String.format("%.2f", getSaldo());
}


    public void setTaxa(double valorT){
        this.taxa = valorT;
    }

    public double getTaxa(){
        return this.taxa;
    }
    
}