package Eleicoes;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.*;

public class Leitor {
	
	public static LocalDate leData(String dataStr){
		// Cria um scanner da data
		Scanner scan_data = new Scanner(dataStr);
		scan_data.useDelimiter("/");
		
		// Le as informacoes da data
		int day = Integer.parseInt(scan_data.next()),
			month = Integer.parseInt(scan_data.next()),
			year = Integer.parseInt(scan_data.next());
		// Cria a data
		LocalDate data = LocalDate.of(year, month, day);
	
		scan_data.close(); // Fecha o scanner da data
		
		return data;
		
	}
	
	public static Candidato leCandidato(Scanner scan, LocalDate dia_eleicao) {
		int numero = scan.nextInt(), 
			votos = scan.nextInt();
		String situacao = scan.next(),
			   nome_candidato = scan.next(),
			   nome_urna = scan.next();
		char sexo = scan.next().charAt(0);
		LocalDate nascimento = leData(scan.next());
		scan.next(); // Pega o destino do voto que não é armazenado
		int num_partido = scan.nextInt();
		return new Candidato(numero, votos, situacao, nome_candidato, nome_urna,
				            sexo, nascimento, num_partido, dia_eleicao);
		
	}
	
	public static void leTodosCandidatos(String nome_arq_entrada, LocalDate dia_eleicao, Map<Integer, Partido> partidos) {
		try {
			// Cria Scanner e abre o arquivo
			File arq = new File(nome_arq_entrada);
			Scanner sc = new Scanner(arq);

			while(sc.hasNext()) {
				String line = sc.nextLine(); // Le a linha do candidato.
				// Checa se o candidato é válido, caso não seja ignora.
				if (!line.contains("Válido")) continue; 
				// Define as configurações do scanner do candidato, linha do arquivo.
				Scanner lineSc = new Scanner(line);
				lineSc.useDelimiter(",");
				// Le candidato o novo candidato e o adiciona ao respectivo partido
				Candidato novo_candidato = leCandidato(lineSc, dia_eleicao);
				partidos.get(novo_candidato.getNum_partido()).add_Candidato(novo_candidato);

				lineSc.close();
			}
			sc.close();
			
		} catch(FileNotFoundException exc){
			// Trata a exceção para caso ocorra um erro na abertura do arquivo
			System.out.println("Um erro ocorreu ao abrir o arquivo" + nome_arq_entrada);
			exc.printStackTrace();
			System.exit(1);
		}
	}

	// Leitura relacionados ao tipo Partido
	// ------------------------------------
	// Le apenas um partido
	public static Partido lePartido(Scanner linhaScan){
		return new Partido(linhaScan.nextInt(), linhaScan.nextInt(),
				           linhaScan.next(), linhaScan.next());
	}

	// Le todos os partidos
	public static Map<Integer, Partido> leTodosPartidos(String arq_nome){
		// Cria set de partidos
		Map<Integer, Partido> partidos = new HashMap<Integer, Partido>();
		try {
			File arq = new File(arq_nome);
			Scanner todoSc = new Scanner(arq);
			todoSc.nextLine(); // Pega a linha de cabeçalho

			while(todoSc.hasNextLine()){
				String line = todoSc.nextLine(); // Le a linha do partido.
				// Define as configurações do scanner do partido, linha do arquivo.
				Scanner lineSc = new Scanner(line);
				lineSc.useDelimiter(",");
				// Le partido e adiciona ao set.
				Partido novo_partido = lePartido(lineSc);
				partidos.put(novo_partido.getNumero_partido(), novo_partido);
				lineSc.close();
			}
			todoSc.close();
		} catch (FileNotFoundException fe){
			// Trata a exceção para caso ocorra um erro na abertura do arquivo
			System.out.println("Um erro ocorreu ao abrir o arquivo" + arq_nome);
			fe.printStackTrace();
			System.exit(1);
		}

		return partidos;
	}

}
