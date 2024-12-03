package View;

import connection.Conexao;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Interface extends JFrame {

    private JTable tabela;
    private DefaultTableModel modeloTabela;
    private JTextField texto;
    private JButton adicionar, editar, deletar, pesquisar;
    private Conexao conexao;

    public Interface() {
        // Inicializar a conexão com o banco
        conexao = new Conexao("banco", "root", "root");

        criarJanela();
        carregarDados(); // Carregar os dados na tabela
    }

    private void criarJanela() {
        setTitle("Tela Funcionario");
        setSize(560, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Configurar o modelo da tabela
        modeloTabela = new DefaultTableModel(new String[]{"ID", "Nome", "TipoConta", "NumConta", "Saldo"}, 0);
        tabela = new JTable(modeloTabela);
        
        JScrollPane scrollPane = new JScrollPane(tabela);
        add(scrollPane, BorderLayout.CENTER);

        JPanel painelInferior = new JPanel();
        painelInferior.setLayout(new FlowLayout());

        adicionar = new JButton("Adicionar");
        editar = new JButton("Editar");
        deletar = new JButton("Deletar");
        pesquisar = new JButton("Pesquisar");

        painelInferior.add(adicionar);
        painelInferior.add(editar);
        painelInferior.add(deletar);
        painelInferior.add(pesquisar);

        add(painelInferior, BorderLayout.SOUTH);
    }

    private void carregarDados() {
        Connection conn = conexao.obterConexao();
        if (conn == null) {
            JOptionPane.showMessageDialog(this, "Erro ao conectar ao banco de dados.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String sql = "SELECT id, nome, tipoConta, numConta, saldo FROM Conta";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            modeloTabela.setRowCount(0); // Limpar dados antigos

            while (rs.next()) {
                long id = rs.getLong("id");
                String nome = rs.getString("nome");
                String tipoConta = rs.getString("tipoConta");
                int numeroConta = rs.getInt("numConta");
                double saldo = rs.getDouble("saldo");

                modeloTabela.addRow(new Object[]{id, nome, tipoConta, numeroConta, saldo});
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao carregar dados: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Interface().setVisible(true);
        });
    }
}