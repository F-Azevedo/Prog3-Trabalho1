package Eleicoes;

import java.io.File;
import java.io.FileInputStream;
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
		//Lê as informações do candidato.
		int numero = scan.nextInt(), 
			votos = scan.nextInt();
		String situacao = scan.next(),
			   nome_candidato = scan.next(),
			   nome_urna = scan.next();
		char sexo = scan.next().charAt(0);
		LocalDate nascimento = leData(scan.next());
		scan.next(); // Pega o destino do voto que não é armazenado
		int num_partido = scan.nextInt();

		//Cria um novo candidato e o retorna.
		return new Candidato(numero, votos, situacao, nome_candidato, nome_urna,
				            sexo, nascimento, num_partido, dia_eleicao);
	}
	
	public static void leTodosCandidatos(String nome_arq_entrada, LocalDate dia_eleicao, Eleicao eleicao) {
		try {
			// Cria Scanner e abre o arquivo
			Locale loc = new Locale("pt", "BR");
			Scanner sc = new Scanner(new FileInputStream(new File(nome_arq_entrada)), "UTF-8");
			sc.useLocale(loc);

			while(sc.hasNext()) {
				String line = sc.nextLine(); // Le a linha do candidato.
				// Checa se o candidato é válido, caso não seja ignora.
				if (!line.contains("Válido")) continue; 
				// Define as configurações do scanner do candidato, linha do arquivo.
				Scanner lineSc = new Scanner(line);
				lineSc.useDelimiter(",");
				// Lê um novo candidato.
				Candidato novo_candidato = leCandidato(lineSc, dia_eleicao);
				//Adiciona o candidato à eleição.
				eleicao.addCandidatoEleicao(novo_candidato);

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
		//Lê as informações, cria e retorna um partido.
		return new Partido(linhaScan.nextInt(), linhaScan.nextInt(),
				           linhaScan.next(), linhaScan.next());
	}

	// Le todos os partidos
	public static void leTodosPartidos(String arq_nome, Eleicao eleicao){
		// Cria set de partidos
		try {
			Locale loc = new Locale("pt", "BR");
			Scanner sc = new Scanner(new FileInputStream(new File(arq_nome)), "UTF-8");
			sc.useLocale(loc);
			sc.nextLine(); // Pega a linha de cabeçalho

			while(sc.hasNextLine()){
				String line = sc.nextLine(); // Le a linha do partido.
				// Define as configurações do scanner do partido, linha do arquivo.
				Scanner lineSc = new Scanner(line);
				lineSc.useDelimiter(",");
				// Lê um novo partido.
				Partido novo_partido = lePartido(lineSc);
				//Adicona o partido ao conjunto de partidos.
				eleicao.addPartidoEleicao(novo_partido.getNumero_partido(), novo_partido);
				lineSc.close();
			}
			sc.close();
		} catch (FileNotFoundException fe){
			// Trata a exceção para caso ocorra um erro na abertura do arquivo
			System.out.println("Um erro ocorreu ao abrir o arquivo" + arq_nome);
			fe.printStackTrace();
			System.exit(1);
		}
	}

}
