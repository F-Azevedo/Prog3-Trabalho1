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

    /**
     * Construtor do partido.
     * @param numero_partido o número do partido.
     * @param votos_legenda a quantidade de votos de legenda recebida pelo partido.
     * @param nome_partido o nome do partido.
     * @param sigla_partido a sigla do partido.
     */
    public Partido(int numero_partido, int votos_legenda, String nome_partido, String sigla_partido){
        this.nome_partido = nome_partido;
        this.sigla_partido = sigla_partido;
        this.numero_partido = numero_partido;
        this.votos_legenda = votos_legenda;
    }

    /**
     * Getter da sigla do partido.
     * @return a sigla do partido.
     */
    public String getSigla_partido() {
        return sigla_partido;
    }

    /**
     * Getter do número do partido.
     * @return o número do partido.
     */
    public int getNumero_partido() {
        return numero_partido;
    }

    /**
     * Getter dos votos de legenda do partido.
     * @return os votos de legenda do partido.
     */
    public int getVotos_legenda() {
        return votos_legenda;
    }

    /**
     * Função para incrementar os votos nominais do partido.
     * @param incremento numero de votos que servira de incremento.
     */
    public void add_Votos_nominais(int incremento) {
        this.votos_nominais += incremento;
    }

    /**
     * Getter dos candidatos do partido.
     * @return os candidatos do partido.
     */
    public TreeSet<Candidato> getCandidatos() {
        return candidatos;
    }

    /**
     * Função para calcular os votos totais do partido.
     * @return os votos totais do partido.
     */
    public int votosTotais(){ return this.votos_legenda + this.votos_nominais; }

    /**
     * Função para adicionar um candidato ao partido.
     * @param candidato o candidato a ser adicionado.
     */
    public void add_CandidatoPartido(Candidato candidato) {
        //Atribui os votos recebidos pelo candidato aos votos totais do partido.
        this.add_Votos_nominais(candidato.getVotos());

        //Adiciona o candidato ao partido.
        this.candidatos.add(candidato);

        //Se o candidato foi eleito, incrementa a quantidade de vereadores eleitos pelo partido.
        if (candidato.foiEleito()) this.qtdCandidatosEleitos++;
    }

    /**
     * Função de comparação necessária para inserir no TreeSet de forma ordenada.
     * Compara pela quantidade de votos totais do partido.
     * @param outro o partido com o qual será comparado.
     * @return o resultado da comparação.
     */
    @Override
    public int compareTo(Partido outro) {
        //Insere de forma decrescente.
        return Integer.compare(outro.votosTotais(), this.votosTotais());
    }

    /**
     * Função para imprimir as informações básicas do partido.
     * @return a string formatada.
     */
    @Override
    public String toString() {
        return sigla_partido + " - " + numero_partido;
    }

    /**
     * Função para imprimir as informações detalhadas do partido.
     * Incluindo sigla, numero do partido, quantidade de votos nominais, quantidade de votos de legenda e
     * quantidade de candidatos eleitos
     * @return a string formatada.
     */
    public String simplesString(){
        String s = sigla_partido + " - " + numero_partido + ", " + votosTotais();
        if(votosTotais() == 0)
            s += " voto (" + votos_nominais;
        else
            s += " votos (" + votos_nominais;

        if(votos_nominais == 0)
            s += " nominal e " + votos_legenda + " de legenda), " + qtdCandidatosEleitos;
        else
            s += " nominais e " + votos_legenda + " de legenda), " + qtdCandidatosEleitos;

        if(qtdCandidatosEleitos < 2)
            s += " candidato eleito";
        else
            s += " candidatos eleitos";

        return s;
    }
}