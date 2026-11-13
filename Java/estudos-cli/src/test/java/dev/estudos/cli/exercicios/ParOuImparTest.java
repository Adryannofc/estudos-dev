package dev.estudos.cli.exercicios;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ParOuImparTest {

    private final ParOuImpar exercicio = new ParOuImpar();

    @Test
    void reconhecePar() {
        assertEquals("4 é par", exercicio.executar("4"));
    }

    @Test
    void reconheceImpar() {
        assertEquals("7 é ímpar", exercicio.executar("7"));
    }

    @Test
    void zeroEPar() {
        assertEquals("0 é par", exercicio.executar("0"));
    }

    @Test
    void negativosFuncionamComoOsPositivos() {
        assertEquals("-4 é par", exercicio.executar("-4"));
        assertEquals("-3 é ímpar", exercicio.executar("-3"));
    }

    @Test
    void ignoraEspacosAoRedor() {
        assertEquals("8 é par", exercicio.executar("  8 "));
    }

    @Test
    void entradaInvalidaDevolveMensagemEmVezDeExcecao() {
        assertEquals("Digite um número inteiro.", exercicio.executar("abc"));
        assertEquals("Digite um número inteiro.", exercicio.executar(""));
        assertEquals("Digite um número inteiro.", exercicio.executar("3.5"));
    }
}
