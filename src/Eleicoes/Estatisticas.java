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

    public static void imprimeIdade(Eleicao e){
        int total = e.qtdEleitos();
        int menor30 = 0, menor40 = 0, menor50 = 0, menor60 = 0, maior60 = 0;

        for(Candidato i : e.getEleitos()){
            int idade = i.getIdade();
            if(idade < 30)
                menor30++;
            else if(idade < 40)
                menor40++;
            else if(idade < 50)
                menor50++;
            else if(idade < 60)
                menor60++;
            else
                maior60++;
        }

        System.out.println(Cores.CYAN + "Eleitos, por faixa etária (na data da eleição):" + Cores.RESET);
        System.out.printf("      Idade < 30: %d (%.2f%%)\n", menor30, ((float)menor30/(float)total)*100 );
        System.out.printf("30 <= Idade < 40: %d (%.2f%%)\n", menor40, ((float)menor40/(float)total)*100 );
        System.out.printf("40 <= Idade < 50: %d (%.2f%%)\n", menor50, ((float)menor50/(float)total)*100 );
        System.out.printf("50 <= Idade < 60: %d (%.2f%%)\n", menor60, ((float)menor60/(float)total)*100 );
        System.out.printf("60 <= Idade     : %d (%.2f%%)\n", maior60, ((float)maior60/(float)total)*100 );
        System.out.println();
    }

    public static void imprimeSexo(Eleicao e){
        int total = e.qtdEleitos();
        int fem = 0, masc = 0;

        for(Candidato i : e.getEleitos()){
            if(i.getSexo() == 'F')
                fem++;
            else
                masc++;
        }

        System.out.println(Cores.YELLOW + "Eleitos, por sexo:" + Cores.RESET);
        System.out.printf("Feminino: \t%d (%.2f%%)\n", fem, ((float)fem/(float)total)*100 );
        System.out.printf("Masculino:\t%d (%.2f%%)\n", masc, ((float)masc/(float)total*100) );
        System.out.println();
    }

    public static void imprimeTotalVotos(Eleicao e){
        int legenda = e.get_total_votos_legenda();
        int nominal = e.get_total_votos_nominais();
        int total = legenda + nominal;

        System.out.println(Cores.RED + "Total de votos válidos: \t" + Cores.RESET + total);
        System.out.printf("Total de votos nominais:\t%d (%.2f%%)\n", nominal, ((float)nominal/(float)total)*100);
        System.out.printf("Total de votos de Legenda:\t%d (%.2f%%)\n", legenda, ((float)legenda/(float)total)*100);
    }
}
