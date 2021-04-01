package Eleicoes;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

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

	public boolean foiEleito() {
		return this.status.equals("Eleito");
	}

	// Getters
	public String getNome() {
		return nome;
	}

	public String getStatus() {
		return status;
	}

	public String getNome_urna() {
		return nome_urna;
	}

	public int getVotos() {
		return votos;
	}

	public int getIdade() {
		return idade;
	}

	public int getNumero() {
		return numero;
	}

	public int getNum_partido() {
		return num_partido;
	}

	public char getSexo() {
		return sexo;
	}

 	public LocalDate getNasc() {
		return nasc;
	}

	// Compara candidatos pelo número de votos nominais de forma decrescente
	@Override
	public int compareTo(Candidato outro) {
		return Integer.compare(outro.votos, this.votos);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Candidato candidato = (Candidato) o;
		return numero == candidato.numero;
	}

	@Override
	public int hashCode() {
		return Objects.hash(numero);
	}


	public String simpleString(String nome_partido) {
		return nome + " / " + nome_urna + " (" + nome_partido + ", " + votos + " votos)";
	}

	// Imprime o candidato de forma mais simplória
	@Override
	public String toString() {
		return nome_urna + " (" + numero + ", " + votos + " votos)";
	}

	// Método que imprime todas as informações do candidato
	public String detailedString(){
		String space ="           ";
		return "Candidato: nome= " + nome + ",\n" +
				space + "nome_urna= " + nome_urna + ",\n" +
				space + "numero= " + numero + ",\n" +
				space + "num_partido= " + num_partido + ",\n" +
				space + "status= " + status + ",\n" +
				space + "sexo= " + sexo + ",\n" +
				space + "nasc= " + nasc + ",\n" +
				space + "votos= " + votos + ",\n" +
				space + "idade= " + idade + "\n\n";
	}

}

