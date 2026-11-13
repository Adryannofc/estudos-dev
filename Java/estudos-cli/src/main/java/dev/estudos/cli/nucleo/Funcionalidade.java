package dev.estudos.cli.nucleo;

/**
 * Contrato de tudo que o estudos-cli sabe fazer.
 *
 * <p>Fica no núcleo de propósito: não importa nada do TamboUI. Quem desenha a tela conhece
 * esta interface, e cada exercício a implementa; um não precisa saber do outro.
 */
public interface Funcionalidade {

    /** Nome mostrado na lista da tela. */
    String nome();

    /** Executa com o texto digitado e devolve o texto a mostrar. */
    String executar(String entrada);
}
