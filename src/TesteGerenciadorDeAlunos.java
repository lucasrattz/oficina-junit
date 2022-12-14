import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class TesteGerenciadorDeAlunos {

    private GerenciadorDeAlunos GerenciadorDeAlunos;

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
        GerenciadorDeAlunos.matriculaAluno("Bruno Freitas", "12345678901", ["Matemática", "Física", "Química"]);
        assertFalse(GerenciadorDeAlunos.obterTodosAlunos().isEmpty());
        assertEquals(1, GerenciadorDeAlunos.obterTodosAlunos().size());
    }

    @Test
    @DisplayName("Não deveria matricular aluno com nome nulo")
    public void deveriaNaoFuncionarComNomeNulo() {
        Assertions.assertThrows(RuntimeException.class, () -> {
            GerenciadorDeAlunos.matriculaAluno(null, "12345678901", ["Matemática", "Física", "Química"]);
        });
    }

    @Test
    @DisplayName("Não deveria matricular aluno com CPF nulo")
    public void deveriaNaoFuncionarComCPFNulo() {
        Assertions.assertThrows(RuntimeException.class, () -> {
            GerenciadorDeAlunos.matriculaAluno("Bruno Freitas", null, ["Matemática", "Física", "Química"]);
        });
    }

    @Test
    @DisplayName("Não deveria matricular aluno sem lista de disciplinas")
    public void deveriaNaoFuncionarComDiscplinasNulo() {
        Assertions.assertThrows(RuntimeException.class, () -> {
            GerenciadorDeAlunos.matriculaAluno("Bruno Freitass", "12345678901", null);
        });
    }

    @Test
    @DisplayName("Deveria matricular aluno")
    @EnabledOnOs(value = OS.MAC, disabledReason = "Should Run only on MAC")
    public void deveriaMatricularAlunoEmMAC() {
        GerenciadorDeAlunos.matriculaAluno("Bruno Freitas", "12345678901", ["Matemática", "Física", "Química"]);
        assertFalse(GerenciadorDeAlunos.obterTodosAlunos().isEmpty());
        assertEquals(1, GerenciadorDeAlunos.obterTodosAlunos().size());
    }

    @Nested
    class RepeatedTests {
        @DisplayName("Repetir teste de matrícula de aluno 5 vezes")
        @RepeatedTest(value = 5,
                name = "Repetindo teste de matrícula de aluno {currentRepetition} de {totalRepetitions}")
        public void deveriaMatricularDiversosAlunos() {
            GerenciadorDeAlunos.matriculaAluno("Bruno Freitas", "12345678901", ["Matemática", "Física", "Química"]);
            assertFalse(GerenciadorDeAlunos.obterTodosAlunos().isEmpty());
            assertEquals(1, GerenciadorDeAlunos.obterTodosAlunos().size());
        }
    }

    @Nested
    class ParameterizedTests {
        @DisplayName("Lista de disciplinas deveria atender os requisitos")
        @ParameterizedTest
        @ValueSource(arrays = {["Matemática", "Física", "Química"], ["Matemática", "Física", "Química", "Biologia"]})
        public void deveriaTestarDisciplinasValueSource(String listaDeDisciplinas) {
            GerenciadorDeAlunos.matriculaAluno("Bruno Freitas", "12345678901", listaDeDisciplinas);
            assertFalse(GerenciadorDeAlunos.obterTodosAlunos().isEmpty());
            assertEquals(1, GerenciadorDeAlunos.obterTodosAlunos().size());
        }
    }

    @Test
    @DisplayName("Este teste não deveria rodar")
    @Disabled
    public void deveriaEstarDesabilitado() {
        throw new RuntimeException("Este teste não deveria ser executado");
    }

    @AfterEach
    public void finalizouUm() {
        System.out.println("Deveria aparecer após cada teste");
    }

    @AfterAll
    public static void finalizouTodos() {
        System.out.println("Deveria aparecer após todos os testes");
    }
}
