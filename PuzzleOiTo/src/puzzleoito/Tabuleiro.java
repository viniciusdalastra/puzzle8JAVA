package puzzleoito;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe de Modelo do Tabuleiro, aonde o mágico coloca a varinha para funcionar
 *
 * @author Vinícius Dalastra
 */
public class Tabuleiro {

    private ArvoreEstados ArvoreEstados;
    private List<Estado> EstadoFechado;
    private List<Estado> EstadoAberto;
    private Estado EstadoFinal;
    private Boolean usaHeuristica = false;

    public Boolean getUsaHeuristica() {
        return this.usaHeuristica;
    }

    public void setUsaHeuristica(Boolean usaHeuristica) {
        this.usaHeuristica = usaHeuristica;
    }

    public ArvoreEstados getArvoreEstados() {
        if (ArvoreEstados == null) {
            this.setArvoreEstados(new ArvoreEstados());
        }

        return ArvoreEstados;
    }

    public void setArvoreEstados(ArvoreEstados ArvoreEstados) {
        this.ArvoreEstados = ArvoreEstados;
    }

    public List<Estado> getEstadoFechado() {
        if (EstadoFechado == null) {
            this.setEstadoFechado(new ArrayList<>());
        }

        return EstadoFechado;
    }

    public void setEstadoFechado(List<Estado> EstadoFechado) {
        this.EstadoFechado = EstadoFechado;
    }

    public List<Estado> getEstadoAberto() {
        if (EstadoAberto == null) {
            this.setEstadoAberto(new ArrayList<>());
        }

        return EstadoAberto;
    }

    public void setEstadoAberto(List<Estado> EstadoAberto) {
        this.EstadoAberto = EstadoAberto;
    }

    public Estado getEstadoFinal() {
        return EstadoFinal;
    }

    public void setEstadoFinal(Estado EstadoFinal) {
        this.EstadoFinal = EstadoFinal;
    }

    public Tabuleiro(Integer[] vetorEstadoInicial, Integer[] vetorResultadoFinalModelo1) {
        this.setEstadoFinal(new Estado(vetorResultadoFinalModelo1));
        this.getArvoreEstados().setRaiz(new Estado());
        this.getArvoreEstados().adicionaFilhoRaiz(new Estado(vetorEstadoInicial));
    }

    public void realizaBuscaValores() {
        boolean resolveu = false;
        for (Estado EstadoAtual : this.getArvoreEstados().getRaiz().getFilhos()) {
            if (EstadoAtual.comparaEstado(this.getEstadoFinal())) {
                resolveu = true;
                System.out.println("O Vetor ja esta ordenado");
                break;
            } else {
                if (EstadoAtual.getAberto()) {
                    abreEstado(EstadoAtual);
                    for (Estado EstadoNovo : EstadoAtual.getFilhos()) {
                        this.getEstadoAberto().add(EstadoNovo);
                    }
                    this.getEstadoFechado().add(EstadoAtual);
                    EstadoAtual.setAberto(false);
                }
            }
        }

        while ((this.getEstadoAberto().size() > 0) && !(resolveu)) {
            resolveu = PercorreFilhos();
        }
        if ((this.getEstadoAberto().size() == 0) && (!resolveu)) {
            System.out.println("Vetor de abertos vazio.");
        }
    }

    public void abreEstado(Estado estado) {
        for (Movimento movimentos : this.buscaMovimentosPossiveis(estado.getPosicaoVetor())) {
            Estado estadoProximo = new Estado(movimentos.vetor);
            estadoProximo.setMovimento(movimentos.Movimento);
            if (!(this.isEstadoPresente(this.getEstadoFechado(), estadoProximo)) && !(this.isEstadoPresente(this.getEstadoAberto(), estadoProximo))) {
                estado.adicionaFilho(estadoProximo);
            }
        }
    }

    public Boolean isEstadoPresente(List<Estado> lista, Estado estado) {
        for (Estado estdadoAtual : lista) {
            if (estado.comparaEstado(estdadoAtual)) {
                return true;
            }
        }

        return false;
    }

//    @todo Refatorar 4 case
    public List<Movimento> buscaMovimentosPossiveis(Integer[] vetor) {
        List<Movimento> list = new ArrayList<Movimento>();

        for (int i = 0; i <= 8; i++) {
            if (vetor[i] != 0) {
                continue;
            }

            switch (i) {
                case 0: {
                    if ((i == 0)) {
                        list.add(criaProximoMovimento(vetor, i, 1, Estado.MOVIMENTO_DIREITA));
                        list.add(criaProximoMovimento(vetor, i, 3, Estado.MOVIMENTO_ABAIXO));
                    }
                }
                case 1: {
                    if ((i == 1)) {
                        list.add(criaProximoMovimento(vetor, i, 2, Estado.MOVIMENTO_DIREITA));
                        list.add(criaProximoMovimento(vetor, i, 4, Estado.MOVIMENTO_ABAIXO));
                        list.add(criaProximoMovimento(vetor, i, 0, Estado.MOVIMENTO_ESQUERDA));
                    }
                }
                case 2: {
                    if ((i == 2)) {
                        list.add(criaProximoMovimento(vetor, i, 1, Estado.MOVIMENTO_ESQUERDA));
                        list.add(criaProximoMovimento(vetor, i, 5, Estado.MOVIMENTO_ABAIXO));
                    }
                }
                case 3: {
                    if ((i == 3)) {
                        list.add(criaProximoMovimento(vetor, i, 0, Estado.MOVIMENTO_ACIMA));
                        list.add(criaProximoMovimento(vetor, i, 4, Estado.MOVIMENTO_DIREITA));
                        list.add(criaProximoMovimento(vetor, i, 6, Estado.MOVIMENTO_ABAIXO));
                    }
                }
                case 4: {
                    if ((i == 4)) {
                        list.add(criaProximoMovimento(vetor, i, 1, Estado.MOVIMENTO_ACIMA));
                        list.add(criaProximoMovimento(vetor, i, 3, Estado.MOVIMENTO_ESQUERDA));
                        list.add(criaProximoMovimento(vetor, i, 5, Estado.MOVIMENTO_DIREITA));
                        list.add(criaProximoMovimento(vetor, i, 7, Estado.MOVIMENTO_ABAIXO));
                    }
                }
                case 5: {
                    if ((i == 5)) {
                        list.add(criaProximoMovimento(vetor, i, 2, Estado.MOVIMENTO_ACIMA));
                        list.add(criaProximoMovimento(vetor, i, 4, Estado.MOVIMENTO_ESQUERDA));
                        list.add(criaProximoMovimento(vetor, i, 8, Estado.MOVIMENTO_ABAIXO));
                    }
                }
                case 6: {
                    if ((i == 6)) {
                        list.add(criaProximoMovimento(vetor, i, 3, Estado.MOVIMENTO_ACIMA));
                        list.add(criaProximoMovimento(vetor, i, 7, Estado.MOVIMENTO_DIREITA));
                    }
                }
                case 7: {
                    if ((i == 7)) {
                        list.add(criaProximoMovimento(vetor, i, 4, Estado.MOVIMENTO_ACIMA));
                        list.add(criaProximoMovimento(vetor, i, 6, Estado.MOVIMENTO_ESQUERDA));
                        list.add(criaProximoMovimento(vetor, i, 8, Estado.MOVIMENTO_DIREITA));
                    }
                }
                case 8: {
                    if ((i == 8)) {
                        list.add(criaProximoMovimento(vetor, i, 5, Estado.MOVIMENTO_ACIMA));
                        list.add(criaProximoMovimento(vetor, i, 7, Estado.MOVIMENTO_ESQUERDA));
                    }
                }
            }
        }

        return list;
    }

    protected Movimento criaProximoMovimento(Integer[] vetor, Integer indiceZero, Integer indiceMovimento, Integer movimento) {
        Integer[] proximoMovimento = vetor.clone();

        proximoMovimento[indiceZero] = proximoMovimento[indiceMovimento];
        proximoMovimento[indiceMovimento] = 0;

        return new Movimento(proximoMovimento, movimento);
    }

    protected boolean PercorreFilhos() {
        if (this.getUsaHeuristica()){
            this.ordenaVetoresPelaHeuristica();
        }
        
        List<Estado> ListaTemp = new ArrayList<>();

        for (Estado estadoAtual : this.getEstadoAberto()) {
            if (estadoAtual.comparaEstado(this.getEstadoFinal())) {
                estadoAtual.imprimeVetor();
                return true;
            } else if (estadoAtual.getAberto()) {
                this.abreEstado(estadoAtual);
                ListaTemp.add(estadoAtual);
                estadoAtual.setAberto(false);
            } else {
                System.out.println("Registro fechado na lista aberta");
            }
        }

        for (Estado estadoAtual : ListaTemp) {
            this.getEstadoAberto().remove(estadoAtual);
            this.getEstadoFechado().add(estadoAtual);
            for (Estado estadoFilho : estadoAtual.getFilhos()) {
                if (!(this.isEstadoPresente(this.getEstadoFechado(), estadoFilho)) && !(this.isEstadoPresente(this.getEstadoAberto(), estadoFilho))) {
                    this.getEstadoAberto().add(estadoFilho);
                }
            }
        }
        return false;
    }
    public void ordenaVetoresPelaHeuristica(){
//        encontra a média da heuristica e remove caso o valor for maior que o da média
        if (this.getEstadoAberto().size() > 500){
            List<Estado> listaEstadoVazio = new ArrayList<>();
            int Media = 0;
            for(Estado estadosAbertos: this.getEstadoAberto()){
                Media += estadosAbertos.getValorHeuristica();
            }
            Media = Media / this.getEstadoAberto().size();            
            for(Estado estadosAbertos: this.getEstadoAberto()){
                if (estadosAbertos.getValorHeuristica() > Media)
                    listaEstadoVazio.add(estadosAbertos);
            }
            for(Estado estadosAbertos: listaEstadoVazio){
                this.getEstadoAberto().remove(estadosAbertos);
            }            
        }

        //percorre os estados que estão em aberto
        for (int indiceEstadoAtual = 0; indiceEstadoAtual < this.getEstadoAberto().size(); ++indiceEstadoAtual) {
            for (int indiceEstadoAnterior = 0; indiceEstadoAnterior < this.getEstadoAberto().size() - 1; ++indiceEstadoAnterior) {
                Estado estadoAnterior = this.getEstadoAberto().get(indiceEstadoAnterior);
                Estado estadoProximo = this.getEstadoAberto().get(indiceEstadoAnterior+1);
                if (estadoAnterior.getValorHeuristica() > estadoProximo.getValorHeuristica()) {
                    this.getEstadoAberto().set(indiceEstadoAnterior, estadoProximo);
                    this.getEstadoAberto().set(indiceEstadoAnterior+1, estadoAnterior);
                }
            }
        }
    }
}
