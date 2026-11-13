package dev.estudos.cli.ui;

import dev.estudos.cli.exercicios.ParOuImpar;
import dev.estudos.cli.nucleo.Funcionalidade;
import dev.tamboui.buffer.Buffer;
import dev.tamboui.layout.Rect;
import dev.tamboui.terminal.Frame;
import dev.tamboui.tui.event.KeyCode;
import dev.tamboui.tui.event.KeyEvent;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/** Testa a tela sem terminal: teclas entram por pressionar() e o desenho vai para um Buffer. */
class HomeTest {

    private static Funcionalidade fake(String nome, String resposta) {
        return new Funcionalidade() {
            @Override public String nome() { return nome; }
            @Override public String executar(String entrada) { return resposta; }
        };
    }

    private static void digitar(Home home, String texto) {
        for (char c : texto.toCharArray()) {
            home.pressionar(KeyEvent.ofChar(c));
        }
    }

    private static String desenhar(Home home) {
        Buffer buffer = Buffer.empty(Rect.of(60, 14));
        home.render(Frame.forTesting(buffer));
        var texto = new StringBuilder();
        for (int y = 0; y < 14; y++) {
            for (int x = 0; x < 60; x++) {
                texto.append(buffer.get(x, y).symbol());
            }
            texto.append('\n');
        }
        return texto.toString();
    }

    @Test
    void fluxoCompleto_escolhe_digita_executa() {
        var home = new Home(List.of(new ParOuImpar()));

        home.pressionar(KeyEvent.ofKey(KeyCode.ENTER)); // abre a entrada
        assertEquals(Home.Modo.ENTRADA, home.modo());

        digitar(home, "7");
        home.pressionar(KeyEvent.ofKey(KeyCode.ENTER)); // executa

        assertEquals("7 é ímpar", home.resultado());
        assertEquals(Home.Modo.LISTA, home.modo());
    }

    @Test
    void naEntrada_oQEhApenasUmaLetra() {
        var home = new Home(List.of(new ParOuImpar()));

        home.pressionar(KeyEvent.ofKey(KeyCode.ENTER));
        digitar(home, "q");
        home.pressionar(KeyEvent.ofKey(KeyCode.ENTER));

        assertEquals("Digite um número inteiro.", home.resultado()); // "q" foi texto, não saiu
    }

    @Test
    void backspaceApagaEEscCancela() {
        var home = new Home(List.of(new ParOuImpar()));

        home.pressionar(KeyEvent.ofKey(KeyCode.ENTER));
        digitar(home, "99");
        home.pressionar(KeyEvent.ofKey(KeyCode.BACKSPACE));
        digitar(home, "4");
        home.pressionar(KeyEvent.ofKey(KeyCode.ENTER));
        assertEquals("94 é par", home.resultado());

        home.pressionar(KeyEvent.ofKey(KeyCode.ENTER));
        home.pressionar(KeyEvent.ofKey(KeyCode.ESCAPE));
        assertEquals(Home.Modo.LISTA, home.modo());
        assertEquals("94 é par", home.resultado()); // cancelar não mexe no resultado
    }

    @Test
    void setasEscolhemEntreVariasFuncionalidades() {
        var home = new Home(List.of(fake("Primeira", "resposta 1"), fake("Segunda", "resposta 2")));

        home.pressionar(KeyEvent.ofKey(KeyCode.DOWN));
        home.pressionar(KeyEvent.ofKey(KeyCode.ENTER));
        home.pressionar(KeyEvent.ofKey(KeyCode.ENTER));

        assertEquals("resposta 2", home.resultado());
    }

    @Test
    void desenhoMostraNomeDaFuncionalidadeEOResultado() {
        var home = new Home(List.of(new ParOuImpar()));
        home.pressionar(KeyEvent.ofKey(KeyCode.ENTER));
        digitar(home, "10");
        home.pressionar(KeyEvent.ofKey(KeyCode.ENTER));

        String tela = desenhar(home);

        assertTrue(tela.contains("Par ou ímpar"), tela);
        assertTrue(tela.contains("10 é par"), tela);
        assertTrue(tela.contains("q sai"), tela);
    }
}
