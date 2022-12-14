package xyz.rattz.oficinajunit;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class TesteGerenciadorDeAlunos {

    private GerenciadorDeAlunos GerenciadorDeAlunos;
    String[] disciplinas = {"Matem�tica", "F�sica", "Qu�mica"};

    @BeforeAll
    public static void setupAll() {
        System.out.println("Deveria aparecer antes dos testes");
    }

    @BeforeEach
    public void setup() {
        System.out.println("Instanciando Gerenciador de Alunos");
        GerenciadorDeAlunos = new GerenciadorDeAlunos();
    }
    
    @Test
    @DisplayName("Deveria matricular aluno")
    public void deveriaMatricularAluno() {
        GerenciadorDeAlunos.matriculaAluno("Bruno Freitas", "12345678901", disciplinas);
        assertFalse(GerenciadorDeAlunos.obterTodosAlunos().isEmpty());
        assertEquals(1, GerenciadorDeAlunos.obterTodosAlunos().size());
    }

    @Test
    @DisplayName("N�o deveria matricular aluno com nome nulo")
    public void deveriaNaoFuncionarComNomeNulo() {
        Assertions.assertThrows(RuntimeException.class, () -> {
            GerenciadorDeAlunos.matriculaAluno(null, "12345678901", disciplinas);
        });
    }

    @Test
    @DisplayName("N�o deveria matricular aluno com CPF nulo")
    public void deveriaNaoFuncionarComCPFNulo() {
        Assertions.assertThrows(RuntimeException.class, () -> {
            GerenciadorDeAlunos.matriculaAluno("Bruno Freitas", null, disciplinas);
        });
    }

    @Test
    @DisplayName("N�o deveria matricular aluno sem lista de disciplinas")
    public void deveriaNaoFuncionarComDiscplinasNulo() {
        Assertions.assertThrows(RuntimeException.class, () -> {
            GerenciadorDeAlunos.matriculaAluno("Bruno Freitass", "12345678901", null);
        });
    }

    @Test
    @DisplayName("Deveria matricular aluno")
    @EnabledOnOs(value = OS.MAC, disabledReason = "Somente deveria funcionar em um MAC")
    public void deveriaMatricularAlunoEmMAC() {
        GerenciadorDeAlunos.matriculaAluno("Bruno Freitas", "12345678901", disciplinas);
        assertFalse(GerenciadorDeAlunos.obterTodosAlunos().isEmpty());
        assertEquals(1, GerenciadorDeAlunos.obterTodosAlunos().size());
    }

    @Nested
    class RepeatedTests {
        @DisplayName("Repetir teste de matr�cula de aluno 5 vezes")
        @RepeatedTest(value = 5,
                name = "Repetindo teste de matr�cula de aluno {currentRepetition} de {totalRepetitions}")
        public void deveriaMatricularDiversosAlunos() {
            GerenciadorDeAlunos.matriculaAluno("Bruno Freitas", "12345678901", disciplinas);
            assertFalse(GerenciadorDeAlunos.obterTodosAlunos().isEmpty());
            assertEquals(1, GerenciadorDeAlunos.obterTodosAlunos().size());
        }
    }

    @Test
    @DisplayName("Este teste n�o deveria rodar")
    @Disabled
    public void deveriaEstarDesabilitado() {
        throw new RuntimeException("Este teste n�o deveria ser executado");
    }

    @AfterEach
    public void finalizouUm() {
        System.out.println("Deveria aparecer ap�s cada teste");
    }

    @AfterAll
    public static void finalizouTodos() {
        System.out.println("Deveria aparecer ap�s todos os testes");
    }
}
