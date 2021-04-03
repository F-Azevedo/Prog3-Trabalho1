package Eleicoes;

import java.time.LocalDate;
import java.util.*;

/**
 * Classe que roda o programa.
 */
public class Testador {

	/**
	 * Main do programa.
	 * @param args possui 3 argumentos, endereço para o arquivo dos candidatos,
	 *             endereço para o arquivo dos partidos e data de realização da eleição.
	 */
	public static void main(String[] args) {

		// Le os dados de entrada da linha de comando.
		String arq_candidatos = args[0],
		       arq_partidos = args[1],
		       data = args[2];

		//Lê a data de realização da eleição.
		LocalDate data_eleicao = Leitor.leData(data);

		//Cria a estrutura da classe eleição.
		Eleicao eleicao = new Eleicao(data_eleicao);

		//Lê os partidos.
		Leitor.leTodosPartidos(arq_partidos, eleicao);
		Leitor.leTodosCandidatos(arq_candidatos, data_eleicao, eleicao);

		//Imprime as estatísticas.
		Estatisticas.imprimeNumVagas(eleicao);
		Estatisticas.imprimeEleitos(eleicao);
		Estatisticas.imprimeMaisVotados(eleicao);
		Estatisticas.imprimePrejudicados(eleicao);
		Estatisticas.imprimeBeneficiados(eleicao);
		Estatisticas.imprimePartidosMaisVotados(eleicao);
		Estatisticas.imprimeMelhorPiorCandidatoPorPartido(eleicao);
		Estatisticas.imprimeIdade(eleicao);
		Estatisticas.imprimeSexo(eleicao);
		Estatisticas.imprimeTotalVotos(eleicao);
	}

}
