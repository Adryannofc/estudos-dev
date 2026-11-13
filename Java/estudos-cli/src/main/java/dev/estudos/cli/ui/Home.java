package dev.estudos.cli.ui;

import dev.estudos.cli.nucleo.Funcionalidade;
import dev.tamboui.layout.Constraint;
import dev.tamboui.layout.Layout;
import dev.tamboui.layout.Rect;
import dev.tamboui.style.Color;
import dev.tamboui.style.Style;
import dev.tamboui.terminal.Frame;
import dev.tamboui.text.Text;
import dev.tamboui.tui.TuiRunner;
import dev.tamboui.tui.event.Event;
import dev.tamboui.tui.event.KeyCode;
import dev.tamboui.tui.event.KeyEvent;
import dev.tamboui.widgets.block.Block;
import dev.tamboui.widgets.block.BorderType;
import dev.tamboui.widgets.block.Borders;
import dev.tamboui.widgets.input.TextInput;
import dev.tamboui.widgets.input.TextInputState;
import dev.tamboui.widgets.list.ListState;
import dev.tamboui.widgets.list.ListWidget;
import dev.tamboui.widgets.paragraph.Paragraph;

import java.util.List;

/**
 * Tela principal: uma lista de funcionalidades, um campo para digitar e o resultado.
 *
 * <p>A tela tem dois modos. Em {@link Modo#LISTA} as setas escolhem a funcionalidade e o
 * {@code q} sai. Em {@link Modo#ENTRADA} o {@code q} é só uma letra: tudo que se digita vai
 * para o campo, e o {@code esc} volta para a lista.
 */
public class Home {

    enum Modo { LISTA, ENTRADA }

    private final List<Funcionalidade> funcionalidades;
    private final ListState lista = new ListState();
    private final TextInputState entrada = new TextInputState();

    private Modo modo = Modo.LISTA;
    private String resultado = "";
    private boolean querSair = false;

    public Home(List<Funcionalidade> funcionalidades) {
        this.funcionalidades = funcionalidades;
        if (!funcionalidades.isEmpty()) {
            lista.select(0);
        }
    }

    /** Abre a tela e só retorna quando o usuário sair. */
    public void run() throws Exception {
        try (var tui = TuiRunner.create()) {
            tui.run(this::handle, this::render);
        }
    }

    /** Devolve true quando a tela precisa ser redesenhada. */
    private boolean handle(Event event, TuiRunner runner) {
        if (event instanceof KeyEvent key && key.isPressOrRepeat()) {
            boolean redesenhar = pressionar(key);
            if (querSair) {
                runner.quit();
            }
            return redesenhar;
        }
        return false;
    }

    /** Reage a uma tecla. Devolve true se algo mudou. (Sem TuiRunner, para dar para testar.) */
    boolean pressionar(KeyEvent key) {
        if (key.isCtrlC()) {
            querSair = true;
            return false;
        }
        return modo == Modo.LISTA ? pressionarNaLista(key) : pressionarNaEntrada(key);
    }

    private boolean pressionarNaLista(KeyEvent key) {
        if (key.isQuit()) {
            querSair = true;
            return false;
        }
        if (key.isDown() && !funcionalidades.isEmpty()) {
            lista.selectNext(funcionalidades.size());
            return true;
        }
        if (key.isUp() && !funcionalidades.isEmpty()) {
            lista.selectPrevious();
            return true;
        }
        if (key.isKey(KeyCode.ENTER) && selecionada() != null) {
            entrada.clear();
            modo = Modo.ENTRADA;
            return true;
        }
        return false;
    }

    private boolean pressionarNaEntrada(KeyEvent key) {
        if (key.isKey(KeyCode.ESCAPE)) {
            modo = Modo.LISTA;
            return true;
        }
        if (key.isKey(KeyCode.ENTER)) {
            resultado = selecionada().executar(entrada.text());
            modo = Modo.LISTA;
            return true;
        }
        if (key.isKey(KeyCode.BACKSPACE)) {
            entrada.deleteBackward();
            return true;
        }
        if (key.isKey(KeyCode.CHAR) && !key.hasCtrl() && !key.hasAlt()) {
            entrada.insert(key.character());
            return true;
        }
        return false;
    }

    private Funcionalidade selecionada() {
        Integer indice = lista.selected();
        return indice == null ? null : funcionalidades.get(indice);
    }

    String resultado() {
        return resultado;
    }

    Modo modo() {
        return modo;
    }

    // ---- desenho ----

    void render(Frame frame) {
        frame.renderWidget(moldura(" estudos-cli ").titleBottom(dica()).build(), frame.area());

        List<Rect> linhas = Layout.vertical()
                .constraints(Constraint.fill(), Constraint.length(3), Constraint.length(3))
                .margin(1)
                .split(frame.area());

        renderLista(frame, linhas.get(0));
        renderEntrada(frame, linhas.get(1));
        renderResultado(frame, linhas.get(2));
    }

    private void renderLista(Frame frame, Rect area) {
        String[] nomes = funcionalidades.stream().map(Funcionalidade::nome).toArray(String[]::new);
        var widget = ListWidget.builder()
                .items(nomes)
                .highlightSymbol("> ")
                .highlightStyle(Style.create().fg(Color.CYAN).bold())
                .build();
        frame.renderStatefulWidget(widget, area, lista);
    }

    private void renderEntrada(Frame frame, Rect area) {
        var campo = TextInput.builder()
                .block(moldura(" Entrada ").build())
                .placeholder("escolha na lista e aperte enter")
                .build();
        if (modo == Modo.ENTRADA) {
            campo.renderWithCursor(area, frame.buffer(), entrada, frame);
        } else {
            frame.renderStatefulWidget(campo, area, entrada);
        }
    }

    private void renderResultado(Frame frame, Rect area) {
        frame.renderWidget(Paragraph.builder()
                .text(Text.from(resultado))
                .block(moldura(" Resultado ").build())
                .build(), area);
    }

    private String dica() {
        return modo == Modo.LISTA
                ? " ↑/↓ escolhe · enter digita · q sai "
                : " enter executa · esc volta ";
    }

    private static Block.Builder moldura(String titulo) {
        return Block.builder()
                .borders(Borders.ALL)
                .borderType(BorderType.ROUNDED)
                .title(titulo);
    }
}
