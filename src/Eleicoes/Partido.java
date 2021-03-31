package Eleicoes;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Partido {
    private final String nome_partido;
    private final String sigla_partido;
    private final int numero_partido;
    private final int votos_legenda;
    private int votos_nominais = 0;
    private final Set<Candidato> candidatos = new HashSet<Candidato>();

    public Partido(int numero_partido, int votos_legenda, String nome_partido, String sigla_partido){
        this.nome_partido = nome_partido;
        this.sigla_partido = sigla_partido;
        this.numero_partido = numero_partido;
        this.votos_legenda = votos_legenda;
    }

    public String getNome_partido() {
        return nome_partido;
    }

    public String getSigla_partido() {
        return sigla_partido;
    }

    public int getNumero_partido() {
        return numero_partido;
    }

    public int getVotos_legenda() {
        return votos_legenda;
    }

    public int getVotos_nominais() {
        return votos_nominais;
    }

    public void add_Votos_nominais(int incremento) {
        this.votos_nominais += incremento;
    }

    public Set<Candidato> getCandidatos() {
        return candidatos;
    }

    public void add_CandidatoPartido(Candidato candidato) {
        //Atribui os votos recebidos pelo candidato aos votos totais do partido.
        this.add_Votos_nominais(candidato.getVotos());
        //Adiciona o candidato ao partido.
        this.candidatos.add(candidato);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Partido partido = (Partido) o;
        return numero_partido == partido.numero_partido;
    }

    @Override
    public int hashCode() {
        return Objects.hash(numero_partido);
    }

    @Override
    public String toString() {
        String space = "         ";
        return  "Partido: " + "nome_partido= " + nome_partido + ",\n" +
                space + "sigla_partido= " + sigla_partido + ",\n" +
                space + "numero_partido= " + numero_partido + ",\n" +
                space + "votos_legenda= " + votos_legenda + ",\n" +
                space + "votos_nominais_totais= " + votos_nominais + ",\n" +
                space + "Candidatos:\n" +
                "----------------------------------------\n" + candidatos + "\n\n";
    }
}