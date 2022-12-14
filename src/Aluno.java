package xyz.rattz.oficinajunit;

public class Aluno {
    private String nome;
    private String cpf;
    private String[] disciplinas;

    public Aluno(String nome, String cpf, String disciplinas[]) {
        this.nome = nome;
        this.cpf = cpf;
        this.disciplinas = disciplinas;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void validaNome() {
        if (this.nome.isBlank())
            throw new RuntimeException("O nome não pode estar em branco");
    }

    public void validaCpf() {
        if (this.cpf.isBlank())
            throw new RuntimeException("CPF não pode estar em branco");
    }

    public void validaDisciplinas() {
        if (this.disciplinas.length == 0) {
            throw new RuntimeException("O aluno deve estar matriculado em pelo menos uma disciplina");
        }

        if (this.disciplinas.length > 5) {
            throw new RuntimeException("O aluno não pode estar matriculado em mais de 5 disciplinas");
        }

        for (String disciplina : this.disciplinas) {
            if (disciplina.isBlank()) {
                throw new RuntimeException("Uma das disciplinas está em branco");
            }
        }
    }
}