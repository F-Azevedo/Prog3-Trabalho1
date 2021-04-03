package Eleicoes;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * Classe para trarar coisas relacionadas a um candidato da eleição.
 */
public class Candidato implements Comparable<Candidato>{

	private final String nome;
	private final String status;
	private final String nome_urna;
	private final int votos;
	private final int idade;
	private final int numero;
	private final int num_partido;
	private final char sexo;
	private final LocalDate nasc;

	/**
	 * Construtor da classe candidato.
	 * @param numero número do candidato.
	 * @param votos quantidade de votos recebidos pelo candidato.
	 * @param status se o candidato foi ou não eleito.
	 * @param nome nome do candidato.
	 * @param nome_urna nome de urna do candidato.
	 * @param sexo sexo do candidato.
	 * @param nasc data de nascimento do candidato.
	 * @param num_partido numero do partido ao qual o candidato pertence.
	 * @param eleicao data de realização da eleição.
	 */
	public Candidato(int numero, int votos, String status, String nome, String nome_urna,
					 char sexo, LocalDate nasc, int num_partido, LocalDate eleicao) {
		this.nome = nome;
		this.nome_urna = nome_urna;
		this.numero = numero;
		this.num_partido = num_partido;
		this.status = status;
		this.sexo = sexo;
		this.nasc = nasc;
		this.votos = votos;
		this.idade = (int) nasc.until(eleicao, ChronoUnit.YEARS);
	}

	/**
	 * Função para verificar se o candidato foi eleito.
	 * @return True se o candidato foi eleito, falso do contrario.
	 */
	public boolean foiEleito() {
		return this.status.equals("Eleito");
	}

	/**
	 * Getter de votos do candidato.
	 * @return a quantidade de votos do candidato.
	 */
	public int getVotos() {
		return votos;
	}

	/**
	 * Getter de idade do candidato.
	 * @return a idade do candidato.
	 */
	public int getIdade() {
		return idade;
	}

	/**
	 * Getter do número do partido do candidato.
	 * @return o número do partido do candidato.
	 */
	public int getNum_partido() {
		return num_partido;
	}

	/**
	 * Getter do sexo do candidato.
	 * @return o sexo do candidato.
	 */
	public char getSexo() {
		return sexo;
	}

	/**
	 * Função de comparação necessária para inserir na TreeSet de forma ordenada.
	 * Compara por quantidade de votos.
	 * @param outro o candidato com o qual sera comparado.
	 * @return o resultado da comparação.
	 */
	@Override
	public int compareTo(Candidato outro) {
		//Insere de forma decrescente
		return Integer.compare(outro.votos, this.votos);
	}

	/**
	 * Função para imprimir o nome do candidato, seu nome de urna, a sigla do partido e os votos do candidato.
	 * @param sigla_partido sigla do partido.
	 * @return retorna a string formatada.
	 */
	public String simpleString(String sigla_partido) {
		return nome + " / " + nome_urna + " (" + sigla_partido + ", " + votos + " votos)";
	}

	/**
	 * toString do candidato, imprime o nome de urna, numero e quantidade de votos.
	 * @return a string formatada.
	 */
	@Override
	public String toString() {
		return nome_urna + " (" + numero + ", " + votos + " votos)";
	}
}

