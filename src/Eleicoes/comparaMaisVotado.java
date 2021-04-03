package Eleicoes;

import java.util.Comparator;

/**
 * Classe para ordenar os partidos.
 */
public class comparaMaisVotado implements Comparator<Partido> {

    /**
     * Função para comparar dois partidos.
     * Compara pelos votos do candidato mais votado do partido.
     * O desempate é pelo número do partido.
     *
     * @param esse  partido 1.
     * @param outro partido 2.
     * @return o resultado da comparação.
     */
    @Override
    public int compare(Partido esse, Partido outro) {
        //Trata casos de cantornos caso esse ou outro não tenha candidatos.
        boolean vazioEsse = esse.getCandidatos().size() == 0,
                vazioOutro = outro.getCandidatos().size() == 0;
        if (vazioEsse && vazioOutro) return 0;
        else if (vazioEsse) return -1;
        else if (vazioOutro) return 1;

        //Após tratamentos dos casos de contornos faz a comparação real.
        int votos_outro = outro.getCandidatos().first().getVotos(),
                votos_esse = esse.getCandidatos().first().getVotos();

        if (votos_outro > votos_esse) return 1;
        else if (votos_outro < votos_esse) return -1;
        else if (esse.getNumero_partido() > outro.getNumero_partido()) return 1;
        else if (esse.getNumero_partido() < outro.getNumero_partido()) return -1;
        return 0;
    }
}
