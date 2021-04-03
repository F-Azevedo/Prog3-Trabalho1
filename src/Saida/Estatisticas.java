package Saida;

import Eleicoes.*;

import java.util.*;

/**
 * Classe responsável pelos relatórios.
 */
public class Estatisticas {

    /**
     * Relatório 1.
     * Imprime o número de vaga de vereadores.
     * @param e estrutura da classe eleição.
     */
    public static void imprimeNumVagas(Eleicao e) {
        System.out.println(Cores.RED + "Número de vagas: " + Cores.RESET + e.qtdEleitos() + "\n");
    }

    /**
     * Relatório 2.
     * Imprime os vereadores eleitos.
     * @param e estrutura da classe eleição.
     */
    public static void imprimeEleitos(Eleicao e) {
        int i = 0;

        System.out.println(Cores.GREEN + "Vereadores eleitos:" + Cores.RESET);

        // Vereadores eleitos já foram armazenados ordenadamente pelos mais votados de maneira decrescente.

        for (Candidato eleito : e.getEleitos()) {
            Partido aux = e.getPartidos().get(eleito.getNum_partido());
            System.out.println((++i) + " - " + eleito.simpleString(aux.getSigla_partido()));
        }
        System.out.println();
    }

    /**
     * Relatório 3.
     * Imprime os N candidatos com mais votos.
     * N sendo o número de candidatos que foram eleitos.
     * @param e estrutura da classe eleição.
     */
    public static void imprimeMaisVotados(Eleicao e){
        int i = 0;

        System.out.println(Cores.YELLOW + "Candidatos mais votados (em ordem decrescente de votação e respeitando número de vagas):" + Cores.RESET);// Em vez de ordenar irei tirar os n maiores da lista

        // Candidatos já foram armazenados ordenadamente pelo número de votos de maneira decrescente.

        for (Candidato maior: e.getCandidatos()){
            Partido aux = e.getPartidos().get(maior.getNum_partido());
            System.out.println((++i) + " - " + maior.simpleString(aux.getSigla_partido()));
            if (i >= e.qtdEleitos()) break;
        }
        System.out.println();
    }

    /**
     * Relatório 4.
     * Imprime os candidatos que foram prejudicados pela votação proporcional.
     * @param e estrutura da classe eleição.
     */
    public static void imprimePrejudicados(Eleicao e){
        int pos = 1;

        System.out.println(Cores.BLUE + "Teriam sido eleitos se a votação fosse majoritária, e não foram eleitos:\n" +
                "(com sua posição no ranking de mais votados)" + Cores.RESET);

        // Percorre os candidatos da eleição até o número de candidatos que foi eleito.
        // Se o candidato não estiver dentro da lista dos que foram eleitos, como a lista de candidatos
        // esta ordenada de maneira decrescente por votos, podemos afirmar que ele foi prejudicado pela votação.
        for(Candidato i : e.getCandidatos()){
            if(!e.getEleitos().contains(i)){
                Partido aux = e.getPartidos().get(i.getNum_partido());
                System.out.println((pos) + " - " + i.simpleString(aux.getSigla_partido()));
            }
            pos++;
            if(pos > e.qtdEleitos()) break;
        }
        System.out.println();
    }

    /**
     * Relatório 5.
     * Imprime os candidatos que foram beneficiados pela votação proporcional.
     * @param e estrutura da classe eleição.
     */
    public static void imprimeBeneficiados(Eleicao e){
        int totalEleitos = e.qtdEleitos();
        int qtdEleitos = 0, pos = 0;

        System.out.println(Cores.MAGENTA + "Eleitos, que se beneficiaram do sistema proporcional:\n" +
                "(com sua posição no ranking de mais votados)" + Cores.RESET);

        // Percorre a os candidatos da eleição, se o candidato possuir menos votos que os N candidatos que
        // foram eleitos, e ainda não tiver passado por todos os eleitos, podemos afirmar que ele foi beneficiado pela votação.
        for(Candidato i : e.getCandidatos()){
            pos++;
            if(e.getEleitos().contains(i)){
                qtdEleitos++;
                if(pos > totalEleitos){
                    Partido aux = e.getPartidos().get(i.getNum_partido());
                    System.out.println((pos) + " - " + i.simpleString(aux.getSigla_partido()));
                }
            }
            if(qtdEleitos == totalEleitos)  break;
        }
        System.out.println();
    }

    /**
     * Relatório 6.
     * Imprime os partidos, ordenado pelo numero de votos de forma decrescente.
     * @param e estrutura da classe eleição.
     */
    public static void imprimePartidosMaisVotados(Eleicao e){
        int i = 0;

        System.out.println(Cores.CYAN + "Votação dos partidos e número de candidatos eleitos:" + Cores.RESET);

        // Cria uma cópia dos partidos em forma de lista, para que seja possível efetuar a ordenação.
        List<Partido> partidos = new LinkedList<>(e.getPartidos().values());

        // Ordena a lista a partir do numero de votos totais do partido de forma decrescente.
        partidos.sort(null);

        for(Partido p : partidos){
            System.out.println(++i + " - " + p.simplesString());
        }
        System.out.println();
    }

    /**
     * Relatório 7.
     * Imprime o candidato mais votado e o menos votado de cada partido.
     * Partidos ordenados pelo candidato mais votado de forma decrescente.
     * @param e estrutura da classe eleição.
     */
    public static void imprimeMelhorPiorCandidatoPorPartido(Eleicao e){
        int i=0;

        System.out.println(Cores.BLUE + "Primeiro e último colocados de cada partido:" + Cores.RESET);

        // Cria uma cópia dos partidos em forma de lista, para que seja possível efetuar a ordenação.
        List<Partido> partidos = new LinkedList<>(e.getPartidos().values());

        // Ordena a lista de partidos a partir do numero de votos do candidato com mais votos de forma decrescente.
        partidos.sort(new comparaMaisVotado());

        for(Partido p: partidos){
            if (p.getCandidatos().size() == 0 || p.votosTotais() <= 0) continue;
            System.out.println(++i + " - " + p + ", " + p.getCandidatos().first() + " / " + p.getCandidatos().last());
        }
        System.out.println();
    }

    /**
     * Relatório 8.
     * Imprime a quantidade de candidatos eleitos em determinadas faixas etárias, e a porcentagem.
     * @param e estrutura da classe eleição.
     */
    public static void imprimeIdade(Eleicao e) {
        int total = e.qtdEleitos();
        int menor30 = 0, menor40 = 0, menor50 = 0, menor60 = 0, maior60 = 0;

        for (Candidato i : e.getEleitos()) {
            int idade = i.getIdade();
            if (idade < 30) menor30++;
            else if (idade < 40) menor40++;
            else if (idade < 50) menor50++;
            else if (idade < 60) menor60++;
            else maior60++;
        }

        System.out.println(Cores.RED + "Eleitos, por faixa etária (na data da eleição):" + Cores.RESET);
        System.out.printf("\t  Idade < 30: %d (%.2f%%)\n", menor30, ((float) menor30 / (float) total) * 100);
        System.out.printf("30 <= Idade < 40: %d (%.2f%%)\n", menor40, ((float) menor40 / (float) total) * 100);
        System.out.printf("40 <= Idade < 50: %d (%.2f%%)\n", menor50, ((float) menor50 / (float) total) * 100);
        System.out.printf("50 <= Idade < 60: %d (%.2f%%)\n", menor60, ((float) menor60 / (float) total) * 100);
        System.out.printf("60 <= Idade\t\t: %d (%.2f%%)\n", maior60, ((float) maior60 / (float) total) * 100);
        System.out.println();
    }

    /**
     * Relatório 9.
     * Imprime a porcentagem de candidatos eleitos que são homem e mulheres.
     * @param e estrutura da classe eleição.
     */
    public static void imprimeSexo(Eleicao e) {
        int total = e.qtdEleitos();
        int fem = 0, masc = 0;

        for (Candidato i : e.getEleitos()) {
            if (i.getSexo() == 'F')
                fem++;
            else
                masc++;
        }

        System.out.println(Cores.GREEN + "Eleitos, por sexo:" + Cores.RESET);
        System.out.printf("Feminino:\t%d (%.2f%%)\n", fem, ((float) fem / (float) total) * 100);
        System.out.printf("Masculino:\t%d (%.2f%%)\n", masc, ((float) masc / (float) total * 100));
        System.out.println();
    }

    /**
     * Relatório 10.
     * Imprime a quantidade total de votos válidos, quantidade de votos em candidatos e quantidade de votos de legenda.
     * @param e estrutura da classe eleição.
     */
    public static void imprimeTotalVotos(Eleicao e) {
        int legenda = e.get_total_votos_legenda();
        int nominal = e.get_total_votos_nominais();
        int total = legenda + nominal;

        System.out.println(Cores.YELLOW + "Total de votos válidos: \t" + Cores.RESET + total);
        System.out.printf("Total de votos nominais:\t%d (%.2f%%)\n", nominal, ((float) nominal / (float) total) * 100);
        System.out.printf("Total de votos de Legenda:\t%d (%.2f%%)\n", legenda, ((float) legenda / (float) total) * 100);
    }
}
