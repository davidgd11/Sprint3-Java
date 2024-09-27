package br.com.fiap.sprint.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoOracle {
    // Definindo as variáveis para conexão
    private static final String URL = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:orcl";
    private static final String USUARIO = "rm98603";
    private static final String SENHA = "150205";

    public static Connection conectar() {
        Connection conexao = null;
        
        try {
            System.out.println("Tentando carregar o driver JDBC...");
            // Carregar o driver JDBC do Oracle
            Class.forName("oracle.jdbc.driver.OracleDriver");
            
            System.out.println("Driver carregado com sucesso. Tentando conectar ao banco de dados...");
            // Estabelecer a conexão
            conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
            System.out.println("Conexão com o banco de dados Oracle estabelecida com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao conectar com o banco de dados: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Driver JDBC do Oracle não encontrado: " + e.getMessage());
        }
        
        return conexao;
    }

    // Método para fechar a conexão
    public static void desconectar(Connection conexao) {
        if (conexao != null) {
            try {
                conexao.close();
                System.out.println("Conexão fechada com sucesso.");
            } catch (SQLException e) {
                System.out.println("Erro ao fechar a conexão: " + e.getMessage());
            }
        }
    }
}
