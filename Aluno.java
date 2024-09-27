package br.com.fiap.sprint.bean;

public class Aluno {
    private String nome;
    private String email;
    private String matricula;

    public Aluno(String nome, String email, String matricula) {
        this.nome = nome;
        this.email = email;
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getMatricula() {
        return matricula;
    }

	public void participarTreinamento(Modulo modulo) {
		
		
	}
}