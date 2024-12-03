/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Mauricio
 */
public class ContaEspecial extends Conta {

    public static void add(ContaCorrente c) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
  private double limite;

    public ContaEspecial(){

    }
    public ContaEspecial(String nome,String tipoConta, double saldo, double limite){
        super(nome, tipoConta, saldo);
        this.limite = limite;
    }

    public ContaEspecial(String nome, int numeroConta, String tipoConta, double saldo, double limite){
        super(nome, numeroConta, tipoConta, saldo);
        this.limite = limite;
    }

    public void setLimite(double valorL){
        this.limite = valorL;
    }

    public double getLimite(){
        return this.limite;
    }

    @Override
    public String sacar(double valorS) throws Exception {
        if(valorS <= 0){
            throw new Exception("Digite um novo valor para sacar.");
        }else if(valorS > (getSaldo() + getLimite())){
            throw new Exception("Limite de saque insuficiente.");
        }else {
           this.setSaldo(getSaldo() - valorS);
           return("Saque realizado!");
        }
    }

    @Override
    public String depositar(double valorD) throws Exception {
         if(valorD <= 0){
            throw new Exception("Digite um novo valor para depositar.");
         }else{
            valorD = this.getSaldo() + valorD;
            this.setSaldo(valorD); 
            return("Deposito realizado com sucesso!");
         }

    }
public String consultarSaldo() {
    return "Saldo atual: R$ " + String.format("%.2f", getSaldo());
}

}