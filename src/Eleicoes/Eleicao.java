package Eleicoes;

import java.time.LocalDate;
import java.util.*;

public class Eleicao {
    private int total_votos_nominais = 0;
    private int total_votos_legenda = 0;
    private Set<Candidato> eleitos = new HashSet<>();
    private Map<Integer, Partido> partidos = new HashMap<>();
    private Set<Candidato> candidatos = new HashSet<>();
    private LocalDate data;

    public Eleicao(LocalDate dia) {
        this.data = dia;
    }

    public void add_total_votos_nominais(int incremento) {
        this.total_votos_nominais += incremento;
    }

    public void add_total_votos_legenda(int incremento) {
        this.total_votos_legenda += incremento;
    }

    public Set<Candidato> getEleitos() {
        return eleitos;
    }

    public Map<Integer, Partido> getPartidos() { return partidos; }

    public Set<Candidato> getCandidatos() { return candidatos; }

    public void adicionaEleito(Candidato candidato) {
        this.eleitos.add(candidato);
    }

    public void addPartido(Integer num_partido, Partido partido) { this.partidos.put(num_partido, partido); }

    public void addCandidato(Candidato candidato) {
        this.candidatos.add(candidato);
        if (candidato.foiEleito()) this.eleitos.add(candidato);
        this.partidos.get(candidato.getNum_partido()).add_Candidato(candidato);
    }

    public int qtdEleitos(){
        return eleitos.size();
    }

    @Override
    public String toString() {
        return "Eleicao realizada em " + data + ":\n" +
                "Total_votos_nominais=" + total_votos_nominais + "\n" +
                "Total_votos_legenda=" + total_votos_legenda + "\n" +
                "Eleitos= " + eleitos.size() +
                "Partidos=\n" + partidos + "\n" +
                "Candidatos= " + candidatos.size() + "\n\n";
    }
}