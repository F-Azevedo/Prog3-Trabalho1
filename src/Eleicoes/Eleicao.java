package Eleicoes;

import java.time.LocalDate;
import java.util.*;

public class Eleicao {
    private int total_votos_nominais = 0;
    private int total_votos_legenda = 0;
    private final Set<Candidato> eleitos = new HashSet<>();
    private final Map<Integer, Partido> partidos = new HashMap<>();
    private final Set<Candidato> candidatos = new HashSet<>();
    private final LocalDate data;

    public Eleicao(LocalDate dia) {
        this.data = dia;
    }

    public void add_total_votos_nominais(int incremento) {
        this.total_votos_nominais += incremento;
    }

    public int get_total_votos_nominais(){ return total_votos_nominais; }

    public void add_total_votos_legenda(int incremento) {
        this.total_votos_legenda += incremento;
    }

    public int get_total_votos_legenda(){ return total_votos_legenda; }

    public Set<Candidato> getEleitos() {
        return eleitos;
    }

    public Map<Integer, Partido> getPartidos() { return partidos; }

    public Set<Candidato> getCandidatos() { return candidatos; }

    public void adicionaEleito(Candidato candidato) {
        this.eleitos.add(candidato);
    }

    public void addPartidoEleicao(Integer num_partido, Partido partido) {
        //Adiciona o partido ao conjunto de partidos.
        this.partidos.put(num_partido, partido);

        //Adicoina os votos de legenda recebidos pelo partido ao total de votos de legenda da eleição.
        this.add_total_votos_legenda(partido.getVotos_legenda());
    }

    public void addCandidatoEleicao(Candidato candidato) {
        //Adiciona o candidato ao set de candidatos da eleição.
        this.candidatos.add(candidato);

        //Se o candidato foi eleito, adiciona o candidato ao set de eleitos.
        if (candidato.foiEleito()) adicionaEleito(candidato);

        //Adiciona os votos que o candidato recebeu ao total de votos nominais da eleição.
        this.add_total_votos_nominais(candidato.getVotos());

        //Determina a qual partido o candidato pertence.
        Partido aux = this.partidos.get(candidato.getNum_partido());
        //Adiciona o candidato ao seu respectivo partido.
        aux.add_CandidatoPartido(candidato);
    }

    public int qtdEleitos(){
        return eleitos.size();
    }

    @Override
    public String toString() {
        return "Eleicao realizada em " + data + ":\n" +
                "Total_votos_nominais = " + total_votos_nominais + "\n" +
                "Total_votos_legenda = " + total_votos_legenda + "\n" +
                "Eleitos = " + eleitos.size() + "\n" +
                "Partidos =\n" + partidos + "\n" +
                "Candidatos = " + candidatos.size() + "\n\n";
    }
}