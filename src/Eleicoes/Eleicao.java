package Eleicoes;

import java.util.LinkedList;
import java.util.List;

public class Eleicao {
    private int total_votos_nominais = 0;
    private int total_votos_legenda = 0;
    private final List<Candidato> eleitos = new LinkedList<Candidato>();

    public void add_total_votos_nominais(int incremento) {
        this.total_votos_nominais += incremento;
    }

    public void add_total_votos_legenda(int incremento) {
        this.total_votos_legenda += incremento;
    }

    public List<Candidato> getEleitos() {
        return eleitos;
    }

    public void setEleitos(Candidato candidato) {
        this.eleitos.add(candidato);
    }
}