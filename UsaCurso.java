package br.com.fiap.sprint.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;
import br.com.fiap.sprint.bean.*;

public class UsaCurso {

    private static final String URL = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:orcl"; 
    private static final String USUARIO = "seu_usuario"; // Substitua pelo seu usuário do banco
    private static final String SENHA = "sua_senha"; // Substitua pela sua senha do banco

    public static void main(String[] args) {

        String aux, titulo = "", descricao = "", dataE, nome = "";
        int opcao = 0, codigo = 0;
        Modulo modulo = null;

        Aluno aluno = new Aluno(JOptionPane.showInputDialog("Digite o seu nome: "),
                JOptionPane.showInputDialog("Digite o seu e-mail: "),
                JOptionPane.showInputDialog("Digite a sua matrícula: "));

        UsaCurso curso = new UsaCurso(); 
        curso.inserirAlunoNoBanco(aluno); 

        Instrutor instrutor = new Instrutor("Dr. Silva", "silva@gmail.com", "Cirurgia");

        try {
            aux = JOptionPane.showInputDialog("Qual o módulo desejado?"
                    + "\n(1) Treinamento básico \n(2) Treinamento intermediário " + "\n(3) Treinamento avançado");
            opcao = Integer.parseInt(aux);

            switch (opcao) {
            case 1:
                titulo = "Treinamento Básico";
                descricao = "Introdução à cirurgia básica.";
                break;
            case 2:
                titulo = "Treinamento Intermediário";
                descricao = "Técnicas intermediárias de cirurgia.";
                break;
            case 3:
                titulo = "Treinamento Avançado";
                descricao = "Procedimentos avançados de cirurgia.";
                break;
            default:
                JOptionPane.showMessageDialog(null, "Opção inválida. Selecione uma opção entre 1 e 3.");
                break;
            }

            if (opcao >= 1 && opcao <= 3) {
                modulo = new Modulo(titulo, descricao);
                JOptionPane.showMessageDialog(null,
                        "Módulo selecionado: " + modulo.getTitulo() + " - " + modulo.getDescricao());
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        instrutor.criarModulo(modulo);
        aluno.participarTreinamento(modulo);

        SistemaEnsino se = new SistemaEnsino(nome, descricao);
        se.iniciarTreinamento();

        JOptionPane.showMessageDialog(null, "Aluno: " + aluno.getNome() + "\nMatrícula: " + aluno.getMatricula()
                + "\nInstrutor: " + instrutor.getNome());

        Conteudo conteudo = new Conteudo(titulo, descricao);
        conteudo.exibirConteudo();

        VideoAula videoAula = new VideoAula("Video 1", "Video", "Primeira aula", 20);
        Simulacao3D simulacao3D = new Simulacao3D("Simulação 1", "Simulação", "Simulação básica", "Fácil");

        modulo.adicionarConteudo(videoAula);
        modulo.adicionarConteudo(simulacao3D);

        videoAula.reproduzir();
        simulacao3D.iniciarSimulacao();

        Avaliacao avaliacao = new Avaliacao(8.5, "Excelente!");
        avaliacao.realizarAvaliacao();

        DateTimeFormatter dft = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        aux = "21/06/2024";

        LocalDate dataEmissao = LocalDate.parse(aux, dft);
        Certificado certificado = new Certificado(codigo, dataEmissao);
        dataE = certificado.getDataEmissao().format(dft);

        certificado.emitirCertificado(aluno, modulo);
        System.out.println("Na data: " + dataE);
    }

    public void inserirAlunoNoBanco(Aluno aluno) {
        String sql = "INSERT INTO alunos (nome, email, matricula) VALUES (?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL, USUARIO, SENHA);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, aluno.getNome());
            stmt.setString(2, aluno.getEmail());
            stmt.setString(3, aluno.getMatricula());

            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Aluno inserido com sucesso!");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao inserir aluno no banco de dados: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
