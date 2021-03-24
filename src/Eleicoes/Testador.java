package Eleicoes;

import java.time.LocalDate;
import java.util.*;

public class Testador {
	
	public static void main(String args[]) {

		// Input data
		String str_candidatos = "input/mycandidatos.csv",
		       str_partido = "input/mypartidos.csv";
		LocalDate data_eleicao = LocalDate.of(2020, 1, 29);

		// Cria os partidos
		Map<Integer, Partido> partidos = Leitor.leTodosPartidos(str_partido);

		Leitor.leTodosCandidatos(str_candidatos, data_eleicao, partidos);
		for (Integer key: partidos.keySet()){
			Partido p = partidos.get(key);
			System.out.println(p);
			System.out.println("---------------------------------------------");
		}
	}

}
