package Eleicoes;

import java.time.LocalDate;
import java.util.*;

/**
 * Classe para tratar coisas relacionadas a eleição.
 */
public class Eleicao {
    private int total_votos_nominais = 0;
    private int total_votos_legenda = 0;
    private final TreeSet<Candidato> eleitos = new TreeSet<>();
    private final Map<Integer, Partido> partidos = new HashMap<>();
    private final TreeSet<Candidato> candidatos = new TreeSet<>();
    private final LocalDate data;

    /**
     * Construtor da eleição.
     * @param dia data de realização da eleição.
     */
    public Eleicao(LocalDate dia) {
        this.data = dia;
    }

    /**
     * Função para incrementar os votos nominais totais da eleição.
     * @param incremento quantidade a ser adicionada aos votos nominais.
     */
    public void add_total_votos_nominais(int incremento) {
        this.total_votos_nominais += incremento;
    }

    /**
     * Getter para o total de votos nominais da eleição.
     * @return total de votos nominais da eleição.
     */
    public int get_total_votos_nominais(){ return total_votos_nominais; }

    /**
     * Função para incrementar os votos de legenda totais da eleição.
     * @param incremento quantidade a ser adicionada aos votos de legenda.
     */
    public void add_total_votos_legenda(int incremento) {
        this.total_votos_legenda += incremento;
    }

    /**
     * Getter para o total de votos de legenda da eleição.
     * @return total de votos de legenda da eleição.
     */
    public int get_total_votos_legenda(){ return total_votos_legenda; }

    /**
     * Getter dos candidatos que foram eleitos na eleição.
     * @return os candidatos que foram eleitos.
     */
    public TreeSet<Candidato> getEleitos() {
        return eleitos;
    }

    /**
     * Getter dos partidos da eleição.
     * @return os partidos da eleição.
     */
    public Map<Integer, Partido> getPartidos() { return partidos; }

    /**
     * Getter dos candidatos que participaram da eleição.
     * @return os candidatos que participaram da eleição.
     */
    public TreeSet<Candidato> getCandidatos() { return candidatos; }

    /**
     * Adiciona um candidato ao TreeSet de candidatos eleitos.
     * @param candidato novo candidato a ser adicionado.
     */
    public void adicionaEleito(Candidato candidato) {
        this.eleitos.add(candidato);
    }

    /**
     * Adiciona um novo partido à eleição.
     * @param num_partido numero do partido a ser adicionado.
     * @param partido novo partido a ser adicionado.
     */
    public void addPartidoEleicao(Integer num_partido, Partido partido) {
        //Adiciona o partido ao conjunto de partidos.
        this.partidos.put(num_partido, partido);

        //Adicoina os votos de legenda recebidos pelo partido ao total de votos de legenda da eleição.
        this.add_total_votos_legenda(partido.getVotos_legenda());
    }

    /**
     * Adiciona candidatos ao TreeSet de candidatos que participaram da eleição.
     * @param candidato novo candidato a ser adicionado.
     */
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

    /**
     * Retorna a quantidade de vereadores que foram eleitos na eleição.
     * @return a quantidade de vereadores eleitos.
     */
    public int qtdEleitos(){
        return eleitos.size();
    }
}