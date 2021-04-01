package Eleicoes;

import java.util.*;

public class Partido implements Comparable<Partido>{
    private final String nome_partido;
    private final String sigla_partido;
    private final int numero_partido;
    private final int votos_legenda;
    private int votos_nominais = 0;
    private final TreeSet<Candidato> candidatos = new TreeSet<>();
    private int qtdCandidatosEleitos = 0;

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

    public TreeSet<Candidato> getCandidatos() {
        return candidatos;
    }

    public int votosTotais(){ return this.votos_legenda + this.votos_nominais; }

    public void add_CandidatoPartido(Candidato candidato) {
        //Atribui os votos recebidos pelo candidato aos votos totais do partido.
        this.add_Votos_nominais(candidato.getVotos());
        //Adiciona o candidato ao partido.
        this.candidatos.add(candidato);
        if (candidato.foiEleito()) this.qtdCandidatosEleitos++;
    }

    // Compara o partido a partir dos votos totais e de forma decrescente
    @Override
    public int compareTo(Partido outro) { return Integer.compare(outro.votosTotais(), this.votosTotais()); }

    // Meio mais simples de imprimir o partido
    @Override
    public String toString() {
        return sigla_partido + ", " + numero_partido;
    }

    // Outro meio de imprimir algumas informações do partido
    public String simplesString(){
        return sigla_partido + " - " + numero_partido + ", " +
               this.votosTotais() + " votos (" + votos_nominais + " nominais e " +
               votos_legenda + " de legenda), " + qtdCandidatosEleitos + " candidatos eleitos";
    }

    // Imprime todas as informações do partido incluindo cada candidato
    public String detailedString(){
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

class comparaMaisVotado implements Comparator<Partido>{

    @Override
    public int compare(Partido esse, Partido outro){
        // Trata casos de cantornos caso esse ou outro não tenha candidatos
        boolean vazioEsse = esse.getCandidatos().size() == 0,
                vazioOutro = outro.getCandidatos().size() == 0;
        if (vazioEsse && vazioOutro) return 0;
        else if (vazioEsse) return -1;
        else if (vazioOutro) return 1;

        // Após tratamentos dos casos de contornos faz a comparação real
        int votos_outro = outro.getCandidatos().first().getVotos(),
                votos_esse = esse.getCandidatos().first().getVotos();
        if (votos_outro > votos_esse) return 1;
        else if (votos_outro < votos_esse) return -1;
        else if (esse.getNumero_partido() > outro.getNumero_partido()) return 1;
        else if (esse.getNumero_partido() < outro.getNumero_partido()) return -1;
        return 0;


    }
}