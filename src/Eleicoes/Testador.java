package Eleicoes;

import java.time.LocalDate;
import java.util.*;

public class Testador {
	
	public static void main(String[] args) {

		// Le os dados de entrada da linha de comando
//		String arq_candidatos = args[3],
//		       arq_partidos = args[4],
//		       data = args[5];
 		String arq_candidatos = "input/capitais/vitória-candidatos.csv",
		       arq_partidos = "input/capitais/vitória-partidos.csv",
		       data = "15/11/2020";
		// Cria scanner para ler as informações da data
		LocalDate data_eleicao = Leitor.leData(data);
		Eleicao eleicao = new Eleicao(data_eleicao);
		// Cria os partidos
		Leitor.leTodosPartidos(arq_partidos, eleicao);
		Leitor.leTodosCandidatos(arq_candidatos, data_eleicao, eleicao);

		//System.out.println(eleicao);
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
