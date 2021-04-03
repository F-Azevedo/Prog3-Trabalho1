package Eleicoes;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.*;

/**
 * Classe para realizar a leitura dos arquivos da eleição.
 */
public class Leitor {

	/**
	 * Função para transformar uma string em uma data.
	 * @param dataStr string que é a data de realização da eleição.
	 * @return data em formato LocalDate.
	 */
	public static LocalDate leData(String dataStr){
		//Cria um scanner da data.
		Scanner scan_data = new Scanner(dataStr);
		scan_data.useDelimiter("/");
		
		//Le as informacoes da data.
		int day = Integer.parseInt(scan_data.next()),
			month = Integer.parseInt(scan_data.next()),
			year = Integer.parseInt(scan_data.next());

		//Cria a data.
		LocalDate data = LocalDate.of(year, month, day);

		//Fecha o scanner da data.
		scan_data.close();

		return data;
	}

	/**
	 * Função para ler as informações de um candidato da eleição.
	 * @param scan scanner para o arquivo que contém as informações do candidato.
	 * @param dia_eleicao data de realização da eleição.
	 * @return o novo candidato.
	 */
	public static Candidato leCandidato(Scanner scan, LocalDate dia_eleicao) {
		//Lê as informações do candidato.
		int numero = scan.nextInt(), 
			votos = scan.nextInt();
		String situacao = scan.next(),
			   nome_candidato = scan.next(),
			   nome_urna = scan.next();
		char sexo = scan.next().charAt(0);
		LocalDate nascimento = leData(scan.next());

		// Pega o destino do voto que não é armazenado
		scan.next();

		int num_partido = scan.nextInt();

		//Cria um novo candidato e o retorna.
		return new Candidato(numero, votos, situacao, nome_candidato, nome_urna, sexo, nascimento, num_partido, dia_eleicao);
	}

	/**
	 * Função para ler todos os candidatos da eleição.
	 * @param nome_arq_entrada endereço do arquivo onde se encontram as informaçoẽs dos candidatos.
	 * @param dia_eleicao data de realização da eleição.
	 * @param eleicao estrutura da classe eleição.
	 */
	public static void leTodosCandidatos(String nome_arq_entrada, LocalDate dia_eleicao, Eleicao eleicao) {
		try {
			//Cria Scanner e abre o arquivo.
			Locale loc = new Locale("pt", "BR");
			Scanner sc = new Scanner(new FileInputStream(nome_arq_entrada), "UTF-8");
			sc.useLocale(loc);

			while(sc.hasNext()) {
				//Le a linha do candidato.
				String line = sc.nextLine();

				//Checa se o candidato é válido, caso não seja ignora.
				if (!line.contains("Válido")) continue;

				//Define as configurações do scanner do candidato, linha do arquivo.
				Scanner lineSc = new Scanner(line);
				lineSc.useDelimiter(",");

				// Lê um novo candidato.
				Candidato novo_candidato = leCandidato(lineSc, dia_eleicao);

				//Adiciona o candidato ao conjunto de candidatos da eleição.
				eleicao.addCandidatoEleicao(novo_candidato);

				//Fecha o scanner da linha.
				lineSc.close();
			}
			//Fecha o scanner da leitura dos candidatos.
			sc.close();

		} catch(FileNotFoundException exc){
			//Trata a exceção para caso ocorra um erro na abertura do arquivo.
			System.out.println("Um erro ocorreu ao abrir o arquivo " + nome_arq_entrada);
			exc.printStackTrace();
			System.exit(1);
		}
	}

	/**
	 * Função para ler as informações de um partido da eleição.
	 * @param scan scanner para o arquivo que contém as informações do partido.
	 * @return o novo partido.
	 */
	public static Partido lePartido(Scanner scan){
		//Lê as informações, cria e retorna um partido.
		return new Partido(scan.nextInt(), scan.nextInt(), scan.next(), scan.next());
	}

	/**
	 * Função para ler todos os partidos da eleição.
	 * @param nome_arq_entrada endereço do arquivo onde se encontram as informaçoẽs dos partidos.
	 * @param eleicao estrutura da classe eleição.
	 */
	public static void leTodosPartidos(String nome_arq_entrada, Eleicao eleicao){
		try {
			//Cria o scanner e abre o arquivo.
			Locale loc = new Locale("pt", "BR");
			Scanner sc = new Scanner(new FileInputStream(nome_arq_entrada), "UTF-8");
			sc.useLocale(loc);

			// Pega a linha de cabeçalho
			sc.nextLine();

			while(sc.hasNextLine()){
				//Le a linha do partido.
				String line = sc.nextLine();

				//Define as configurações do scanner do partido, linha do arquivo.
				Scanner lineSc = new Scanner(line);
				lineSc.useDelimiter(",");

				//Lê um novo partido.
				Partido novo_partido = lePartido(lineSc);

				//Adicona o partido ao conjunto de partidos da eleição.
				eleicao.addPartidoEleicao(novo_partido.getNumero_partido(), novo_partido);

				//Fecha o scanner da linha.
				lineSc.close();
			}
			//Fecha o scanner da leitura dos partidos.
			sc.close();

		} catch (FileNotFoundException fe){
			//Trata a exceção para caso ocorra um erro na abertura do arquivo.
			System.out.println("Um erro ocorreu ao abrir o arquivo " + nome_arq_entrada);
			fe.printStackTrace();
			System.exit(1);
		}
	}
}
