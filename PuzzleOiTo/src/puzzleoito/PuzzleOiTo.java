package puzzleoito;

/**
 * Classe inicial para fazer as parada
 * @author Vinícius Dalastra
 */
public class PuzzleOiTo {

//    static Integer[] vetorEstadoInicial = {7, 1, 3, 2, 6, 0, 5, 4, 8};//facil
//    static Integer[] vetorEstadoInicial = {4, 2, 3, 6, 0, 1, 7, 5, 8};//medio
    static Integer[] vetorEstadoInicial = {2, 3, 7, 5, 4, 8, 0, 6, 1};//dificil

    static Integer[] vetorResultadoFinalModelo1 = {1, 2, 3, 4, 5, 6, 7, 8, 0};//exemplo 1 professor
    static Integer[] vetorResultadoFinalModelo2 = {1, 2, 3, 8, 0, 4, 7, 6, 5};//exemplo 2 professor

    public static void main(String[] args) {
        Tabuleiro tabuleiroBase = new Tabuleiro(vetorEstadoInicial, vetorResultadoFinalModelo1);
        tabuleiroBase.setUsaHeuristica(true);
        tabuleiroBase.realizaBuscaValores();
    }
}
