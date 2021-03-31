package Eleicoes;

import javax.lang.model.type.NullType;
import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

// Classe responsável por gerar os relatórios
public class Estatisticas {

    public static void imprimeNumVagas(Eleicao e){
        System.out.println(Cores.BLUE + "Número de vagas: " + Cores.RESET +
                           e.qtdEleitos() + "\n");
    }

    // Imprime eleitos por ordem descrescenete de votos
    public static void imprimeEleitos(Eleicao e){
        System.out.println(Cores.GREEN + "Vereadores eleitos:" + Cores.RESET);
        // Ordena os eleitos pelo número de votos
        Set<Candidato> eleitos = e.getEleitos();
        // Coverte o set para uma lista a fim de ordenar
        List<Candidato> sorted_eleitos = new LinkedList<>(eleitos);
        sorted_eleitos.sort(null); // Ordena eleitos pelo compareTo

        // Imprime a lista ordenada
        for (int i=0; i < sorted_eleitos.size(); i++){
            System.out.println((i+1) + " - " + sorted_eleitos.get(i).simpleString());
        }
        System.out.println();

    }

}
