package dev.estudos.cli;

import dev.estudos.cli.exercicios.ParOuImpar;
import dev.estudos.cli.nucleo.Funcionalidade;
import dev.estudos.cli.ui.Home;

import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        // O registro: para ganhar uma funcionalidade nova, basta incluí-la nesta lista.
        List<Funcionalidade> funcionalidades = List.of(
                new ParOuImpar()
        );

        new Home(funcionalidades).run();
    }
}
