package br.com.fiap.sprint.dao;

import br.com.fiap.sprint.bean.Aluno;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AlunoDao {

    // Inserir um aluno no banco de dados Oracle
    public void inserirAluno(Aluno aluno) {
        String sql = "INSERT INTO Aluno (nome, email, matricula) VALUES (?, ?, ?)";
        
        try (Connection conn = ConexaoOracle.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, aluno.getNome());
            pstmt.setString(2, aluno.getEmail());
            pstmt.setString(3, aluno.getMatricula());
            
            pstmt.executeUpdate();
            System.out.println("Aluno inserido com sucesso no banco Oracle!");
        } catch (SQLException e) {
            System.out.println("Erro ao inserir aluno: " + e.getMessage());
        }
    }

    // Listar todos os alunos
    public List<Aluno> listarAlunos() {
        String sql = "SELECT * FROM Aluno";
        List<Aluno> alunos = new ArrayList<>();
        
        try (Connection conn = ConexaoOracle.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            
            while (rs.next()) {
                String nome = rs.getString("nome");
                String email = rs.getString("email");
                String matricula = rs.getString("matricula");
                
                Aluno aluno = new Aluno(nome, email, matricula);
                alunos.add(aluno);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar alunos: " + e.getMessage());
        }
        
        return alunos;
    }

    // Atualizar um aluno
    public void atualizarAluno(Aluno aluno) {
        String sql = "UPDATE Aluno SET nome = ?, email = ? WHERE matricula = ?";
        
        try (Connection conn = ConexaoOracle.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, aluno.getNome());
            pstmt.setString(2, aluno.getEmail());
            pstmt.setString(3, aluno.getMatricula());
            
            pstmt.executeUpdate();
            System.out.println("Aluno atualizado com sucesso no banco Oracle!");
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar aluno: " + e.getMessage());
        }
    }

    // Deletar um aluno
    public void deletarAluno(String matricula) {
        String sql = "DELETE FROM Aluno WHERE matricula = ?";
        
        try (Connection conn = ConexaoOracle.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, matricula);
            pstmt.executeUpdate();
            System.out.println("Aluno deletado com sucesso no banco Oracle!");
        } catch (SQLException e) {
            System.out.println("Erro ao deletar aluno: " + e.getMessage());
        }
    }
}
