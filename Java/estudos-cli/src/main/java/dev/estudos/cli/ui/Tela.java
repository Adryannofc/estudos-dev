package dev.estudos.cli.ui;

import dev.tamboui.layout.Constraint;
import dev.tamboui.layout.Layout;
import dev.tamboui.layout.Rect;
import dev.tamboui.style.Color;
import dev.tamboui.style.Style;
import dev.tamboui.terminal.Frame;
import dev.tamboui.text.Text;
import dev.tamboui.tui.TuiConfig;
import dev.tamboui.tui.TuiRunner;
import dev.tamboui.tui.event.Event;
import dev.tamboui.tui.event.KeyEvent;
import dev.tamboui.tui.event.TickEvent;
import dev.tamboui.widgets.block.Block;
import dev.tamboui.widgets.block.BorderType;
import dev.tamboui.widgets.block.Borders;
import dev.tamboui.widgets.gauge.Gauge;
import dev.tamboui.widgets.paragraph.Paragraph;
import dev.tamboui.widgets.tabs.Tabs;
import dev.tamboui.widgets.tabs.TabsState;

import java.time.Duration;
import java.util.List;

/**
 * Tela cheia do estudos-cli: abas no topo, conteúdo no meio e dica de teclas no rodapé.
 *
 * <p>O TamboUI chama {@link #handle} a cada evento (tecla ou tique) e {@link #render} a cada quadro.
 * Todo o estado da tela mora aqui, em campos da instância, e não em variáveis estáticas.
 */
public class Tela {

    private static final String[] TABS = {"Início", "Exercícios", "Sobre"};
    private static final String[] SPINNER = {"⠋", "⠙", "⠹", "⠸", "⠼", "⠴", "⠦", "⠧", "⠇", "⠏"};
    private static final String WELCOME = "Bem-vindo ao estudos-cli. Aqui moram meus exercícios de Java.";
    private static final Duration TICK = Duration.ofMillis(80);

    private final TabsState tabsState = new TabsState(0);
    private long tick = 0;

    /** Abre a tela e só retorna quando o usuário sair (tecla q). */
    public void run() throws Exception {
        var config = TuiConfig.builder()
                .tickRate(TICK)
                .build();
        try (var tui = TuiRunner.create(config)) {
            tui.run(this::handle, this::render);
        }
    }

    /** Devolve true quando a tela precisa ser redesenhada. */
    private boolean handle(Event event, TuiRunner runner) {
        if (event instanceof TickEvent t) {
            tick = t.frameCount();
            return true; // anima: redesenha a cada tique
        }
        if (event instanceof KeyEvent key) {
            if (key.isQuit()) {
                runner.quit();
                return false;
            }
            if (key.isRight()) {
                tabsState.selectNext(TABS.length);
                return true;
            }
            if (key.isLeft()) {
                tabsState.selectPrevious(TABS.length);
                return true;
            }
        }
        return false;
    }

    private void render(Frame frame) {
        List<Rect> areas = Layout.vertical()
                .constraints(Constraint.length(3), Constraint.fill(), Constraint.length(1))
                .split(frame.area());

        renderTabs(frame, areas.get(0));
        renderBody(frame, areas.get(1));
        renderFooter(frame, areas.get(2));
    }

    private void renderTabs(Frame frame, Rect area) {
        var tabs = Tabs.builder()
                .titles(TABS)
                .highlightStyle(Style.create().fg(Color.CYAN).bold())
                .divider("│")
                .block(rounded().title("estudos").build())
                .build();
        frame.renderStatefulWidget(tabs, area, tabsState);
    }

    private void renderBody(Frame frame, Rect area) {
        int selected = tabsState.selected() == null ? 0 : tabsState.selected();
        switch (selected) {
            case 0 -> renderHome(frame, area);
            case 1 -> renderExercises(frame, area);
            default -> renderAbout(frame, area);
        }
    }

    /** Texto digitado letra a letra: uma letra por tique, com cursor piscando. */
    private void renderHome(Frame frame, Rect area) {
        int letters = (int) Math.min(WELCOME.length(), tick);
        String cursor = tick % 8 < 4 ? "█" : " ";
        frame.renderWidget(Paragraph.builder()
                .text(Text.from(WELCOME.substring(0, letters) + cursor))
                .block(rounded().build())
                .build(), area);
    }

    /** Spinner e barra de progresso: por enquanto só demonstram a animação. */
    private void renderExercises(Frame frame, Rect area) {
        frame.renderWidget(rounded().build(), area);
        List<Rect> rows = Layout.vertical()
                .constraints(Constraint.length(1), Constraint.length(3), Constraint.fill())
                .margin(1)
                .split(area);
        frame.renderWidget(Paragraph.builder()
                .text(Text.from(SPINNER[(int) (tick % SPINNER.length)] + " executando..."))
                .build(), rows.get(0));
        frame.renderWidget(Gauge.builder()
                .percent((int) (tick % 101))
                .gaugeColor(Color.GREEN)
                .build(), rows.get(1));
    }

    private void renderAbout(Frame frame, Rect area) {
        frame.renderWidget(Paragraph.builder()
                .text(Text.from("Meus estudos de Java e Spring, em forma de terminal."))
                .block(rounded().build())
                .build(), area);
    }

    private void renderFooter(Frame frame, Rect area) {
        frame.renderWidget(Paragraph.builder()
                .text(Text.from("←/→ abas · q sai"))
                .style(Style.create().fg(Color.DARK_GRAY))
                .build(), area);
    }

    private static Block.Builder rounded() {
        return Block.builder().borders(Borders.ALL).borderType(BorderType.ROUNDED);
    }
}
