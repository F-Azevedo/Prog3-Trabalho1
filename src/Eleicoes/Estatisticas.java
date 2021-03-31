package Eleicoes;

import javax.lang.model.type.NullType;
import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import static java.lang.Math.round;

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
            Partido aux = e.getPartidos().get(sorted_eleitos.get(i).getNum_partido());
            System.out.println((i+1) + " - " + sorted_eleitos.get(i).simpleString(aux.getSigla_partido()));
        }
        System.out.println();

    }

    public static void imprimeTotalVotos(Eleicao e){
        int legenda = e.get_total_votos_legenda();
        int nominal = e.get_total_votos_nominais();
        int total = legenda + nominal;

        System.out.println(Cores.RED + "Total de votos válidos: \t" + Cores.RESET + total);
        System.out.println(String.format("Total de votos nominais:\t%d (%.2f%%)", nominal, ((float)nominal/(float)total)*100) );
        System.out.println(String.format("Total de votos de Legenda:\t%d (%.2f%%)", legenda, ((float)legenda/(float)total)*100) );
    }
}
