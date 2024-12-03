    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/***
 * @author Mauricio
 */
public class Conexao {
    protected String nomeBanco;
    protected String usuario;
    protected String senha;
    private String ip, porta;
    
    private void setarValores (String nomeBanco, String usuario, String senha, String ip, String porta){
        this.nomeBanco = nomeBanco;
        this.usuario = usuario;
        this.usuario = senha;
        this.ip = ip;
        this.porta = porta;
    }
    public Conexao (String nomeBanco, String usuario, String senha, String ip, String porta){
        this.setarValores(nomeBanco, usuario, senha, ip, porta);
    }
     public Conexao (String nomeBanco, String usuario, String senha){
        this.setarValores(nomeBanco, usuario, senha, "localhost", "3306");
     }
     public Connection obterConexao(){
         Connection conexao = null;
         try{
             String url;
             url = "jdbc:mysql://" + this.ip + ":" + this.porta + "/" + this.nomeBanco;
             Class.forName("com.mysql.cj.jdbc.Driver");
             conexao = DriverManager.getConnection(url,this.usuario,this.senha);
             System.out.println("Conexão realizada com sucesso!");
         }catch (ClassNotFoundException error){
             System.out.println("Driver não encontrado!");
         }catch (SQLException erro){
             System.out.println("SQL Invalido!");
         }finally{
     return conexao;
   }
 }
}