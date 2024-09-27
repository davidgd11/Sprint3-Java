package br.com.fiap.sprint.main;

import br.com.fiap.sprint.bean.Aluno;
import br.com.fiap.sprint.dao.AlunoDao;

public class UsaAlunoDao {
    public static void main(String[] args) {
        Aluno aluno = new Aluno("João", "joao@gmail.com", "12345");
        
        AlunoDao alunoDao = new AlunoDao();
        
        alunoDao.inserirAluno(aluno);
    }
}
