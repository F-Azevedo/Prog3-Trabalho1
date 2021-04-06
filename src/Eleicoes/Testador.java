package Eleicoes;

import java.time.LocalDate;
import Entrada.Leitor;
import Saida.Estatisticas;

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

		String arq_candidatos 	= null,
				arq_partidos 	= null,
				data 			= null;

		// Le os dados de entrada da linha de comando.
		try {
			arq_candidatos 	= args[0];
			arq_partidos 	= args[1];
			data 			= args[2];
		}
		catch (ArrayIndexOutOfBoundsException e){
			System.out.println("Quantidade de argumentos insuficiente, terminando o programa!");
			System.exit(1);
		}
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
