package xyz.rattz.oficinajunit;

import java.util.ArrayList;

public class GerenciadorDeAlunos {

    ArrayList<Aluno> listaDeAlunos = new ArrayList<Aluno>();

    public void matriculaAluno(String nome, String cpf, String[] disciplinas) {
        Aluno aluno = new Aluno(nome, cpf, disciplinas);
        validaAluno(aluno);
        checarSeAlunoJaEstaMatriculado(aluno);
        listaDeAlunos.add(aluno);
    }

    public ArrayList<Aluno> obterTodosAlunos() {
        return listaDeAlunos;
    }

    private void checarSeAlunoJaEstaMatriculado(Aluno aluno) {
        if (listaDeAlunos.contains(aluno))
            throw new RuntimeException("Aluno já matriculado");
    }

    private void validaAluno(Aluno aluno) {
        aluno.validaNome();
        aluno.validaCpf();
        aluno.validaDisciplinas();
    }

}

