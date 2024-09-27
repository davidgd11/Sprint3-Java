package br.com.fiap.sprint.dao;

import java.sql.Connection;

public class TesteConexao {
    public static void main(String[] args) {
        Connection conexao = ConexaoOracle.conectar();
        
        if (conexao != null) {
            System.out.println("Conectado com sucesso!");

            ConexaoOracle.desconectar(conexao);
        } else {
            System.out.println("Falha na conexão.");
        }
    }
}
