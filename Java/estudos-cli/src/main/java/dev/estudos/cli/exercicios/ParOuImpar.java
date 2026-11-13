package dev.estudos.cli.exercicios;

import dev.estudos.cli.nucleo.Funcionalidade;

/** Diz se um número inteiro é par ou ímpar. */
public class ParOuImpar implements Funcionalidade {

    @Override
    public String nome() {
        return "Par ou ímpar";
    }

    @Override
    public String executar(String entrada) {
        int numero;
        try {
            numero = Integer.parseInt(entrada.trim());
        } catch (NumberFormatException e) {
            return "Digite um número inteiro.";
        }
        // n % 2 == 0 também vale para negativos: -4 % 2 é 0, e -3 % 2 é -1 (diferente de 0).
        return numero + (numero % 2 == 0 ? " é par" : " é ímpar");
    }
}
