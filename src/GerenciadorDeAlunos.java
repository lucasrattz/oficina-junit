package xyz.rattz.oficinajunit;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class GerenciadorDeAlunos {

    Map<String, Aluno> listaDeAlunos = new ConcurrentHashMap<String, Contact>();

    public void matriculaAluno(String nome, String cpf, String[] disciplinas) {
        Aluno aluno = new Aluno(nome, cpf, disciplinas);
        validaAluno(aluno);
        checarSeAlunoJaEstaMatriculado(aluno);
        listaDeAlunos.put(generateKey(aluno), aluno);
    }

    public Collection<Aluno> obterTodosAlunos() {
        return listaDeAlunos.values();
    }

    private void checarSeAlunoJaEstaMatriculado(Aluno aluno) {
        if (listaDeAlunos.containsKey(generateKey(aluno)))
            throw new RuntimeException("Aluno já matriculado");
    }

    private void validaAluno(Aluno aluno) {
        aluno.validaNome();
        aluno.validaCpf();
        aluno.validaDisciplinas();
    }

}

