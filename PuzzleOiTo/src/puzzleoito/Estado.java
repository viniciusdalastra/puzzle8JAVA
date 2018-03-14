package puzzleoito;

import java.util.ArrayList;
import java.util.List;

class Estado {

    final static int MOVIMENTO_ACIMA = 1;
    final static int MOVIMENTO_ABAIXO = 2;
    final static int MOVIMENTO_ESQUERDA = 3;
    final static int MOVIMENTO_DIREITA = 4;

    private Integer[] posicaoVetor;
    private List<Estado> filhos;
    private int profundidade;
    private Estado pai;
    private String Movimento;
    private Boolean Aberto = true;
    private int valorHeuristica = 0;

    public int getValorHeuristica() {
        return valorHeuristica;
    }

    public void setValorHeuristica(int valorHeuristica) {
        this.valorHeuristica = valorHeuristica;
    }

    public Integer[] getPosicaoVetor() {
        return posicaoVetor;
    }

    public void setPosicaoVetor(Integer[] posicaoVetor) {
        this.posicaoVetor = posicaoVetor;
    }

    public int getProfundidade() {
        return profundidade;
    }

    public void setProfundidade(int profundidade) {
        this.profundidade = profundidade;
    }

    public Estado getPai() {
        return pai;
    }

    public void setPai(Estado pai) {
        this.pai = pai;
    }

    public Boolean getAberto() {
        return Aberto;
    }

    public void setAberto(Boolean Aberto) {
        this.Aberto = Aberto;
    }

    public String getMovimento() {
        return Movimento;
    }

    public void setMovimento(int iMovimento) {
        String descricao = "";
        switch (iMovimento) {
            case Estado.MOVIMENTO_ACIMA:
                descricao = " Movimentar para Cima";
                break;
            case Estado.MOVIMENTO_ABAIXO:
                descricao = " Movimentar para Baixo";
                break;
            case Estado.MOVIMENTO_ESQUERDA:
                descricao = " Movimentar para Esquerda";
                break;
            case Estado.MOVIMENTO_DIREITA:
                descricao = " Movimentar para Direita";
                break;
        }

        this.Movimento = descricao;
    }

    public List<Estado> getFilhos() {
        if (this.filhos == null) {
            this.setFilhos(new ArrayList<Estado>());
        }

        return this.filhos;
    }

    public void setFilhos(List<Estado> filhos) {
        this.filhos = filhos;
    }

    public Estado() {
        super();
        this.pai = null;
        this.filhos = null;
        this.profundidade = 0;
    }

    public Estado(Integer[] vetorEstadoInicial) {
        this.setPosicaoVetor(vetorEstadoInicial);
        this.pai = null;
        this.filhos = null;
        this.profundidade = 0;
        this.achaHeuristica();
    }

    public int numeroFilhos() {
        if (this.getFilhos() == null) {
            return 0;
        }
        return this.getFilhos().size();
    }

    void adicionaFilho(Estado estado) {
        if (this.getFilhos() == null) {
            this.setFilhos(new ArrayList<Estado>());
        }
        this.getFilhos().add(estado);
        estado.setProfundidade(this.getProfundidade() + 1);
        estado.setPai(this);
    }

    private String getTextoImpressao(String movimento, Integer[] vetor) {
        String mensagem = movimento + "\n";
        for (int i = 0; i < vetor.length; i++) {
            mensagem = mensagem + " | " + vetor[i] + " | " + vetor[i + 1] + " | " + vetor[i + 2] + " | " + "\n";
            i = i + 2;
        }
        mensagem = mensagem + "----------------------\n";
        return mensagem;
    }

    public void imprimeVetor() {
        System.out.println(imprimeVetor(this));
    }

    private String imprimeVetor(Estado estado) {
        String movimento = " Inicial ";
        if ((estado.getPai() != null) && (estado.getMovimento() != null)) {
            movimento = imprimeVetor(estado.getPai()) + " \n " + estado.getMovimento();
        }

        return this.getTextoImpressao(movimento, estado.getPosicaoVetor());
    }

    public boolean comparaEstado(Object estadoComparar) {
        if (this == estadoComparar) {
            return true;
        }
        if (estadoComparar == null) {
            return false;
        }
        if (getClass() != estadoComparar.getClass()) {
            return false;
        }
        Estado objetoEstado = (Estado) estadoComparar;
        if (this.getPosicaoVetor() == null) {
            if (objetoEstado.getPosicaoVetor() != null) {
                return false;
            }
        } else if ((this.getPosicaoVetor()[0] == objetoEstado.getPosicaoVetor()[0])
                && (this.getPosicaoVetor()[1] == objetoEstado.getPosicaoVetor()[1])
                && (this.getPosicaoVetor()[2] == objetoEstado.getPosicaoVetor()[2])
                && (this.getPosicaoVetor()[3] == objetoEstado.getPosicaoVetor()[3])
                && (this.getPosicaoVetor()[4] == objetoEstado.getPosicaoVetor()[4])
                && (this.getPosicaoVetor()[5] == objetoEstado.getPosicaoVetor()[5])
                && (this.getPosicaoVetor()[6] == objetoEstado.getPosicaoVetor()[6])
                && (this.getPosicaoVetor()[7] == objetoEstado.getPosicaoVetor()[7])
                && (this.getPosicaoVetor()[8] == objetoEstado.getPosicaoVetor()[8])) {
            return true;
        }
        return false;
    }

    public void achaHeuristica() {
        for(int i = 0; i <= 8; i++) {
            switch(i){
                case 0:{
                    if (this.getPosicaoVetor()[i] == 2) { this.setValorHeuristica(this.getValorHeuristica() + 1);}
                    if (this.getPosicaoVetor()[i] == 3) { this.setValorHeuristica(this.getValorHeuristica() + 2);}
                    if (this.getPosicaoVetor()[i] == 4) { this.setValorHeuristica(this.getValorHeuristica() + 1);}
                    if (this.getPosicaoVetor()[i] == 5) { this.setValorHeuristica(this.getValorHeuristica() + 2);}
                    if (this.getPosicaoVetor()[i] == 6) { this.setValorHeuristica(this.getValorHeuristica() + 3);}
                    if (this.getPosicaoVetor()[i] == 7) { this.setValorHeuristica(this.getValorHeuristica() + 2);}
                    if (this.getPosicaoVetor()[i] == 8) { this.setValorHeuristica(this.getValorHeuristica() + 3);}  
                    break;
                }
                case 1:{
                    if (this.getPosicaoVetor()[i] == 1) { this.setValorHeuristica(this.getValorHeuristica() + 1);}
                    if (this.getPosicaoVetor()[i] == 3) { this.setValorHeuristica(this.getValorHeuristica() + 1);}
                    if (this.getPosicaoVetor()[i] == 4) { this.setValorHeuristica(this.getValorHeuristica() + 2);}
                    if (this.getPosicaoVetor()[i] == 5) { this.setValorHeuristica(this.getValorHeuristica() + 1);}
                    if (this.getPosicaoVetor()[i] == 6) { this.setValorHeuristica(this.getValorHeuristica() + 2);}
                    if (this.getPosicaoVetor()[i] == 7) { this.setValorHeuristica(this.getValorHeuristica() + 3);}
                    if (this.getPosicaoVetor()[i] == 8) { this.setValorHeuristica(this.getValorHeuristica() + 2);} 
                    break;                           
                }
                case 2:{
                    if (this.getPosicaoVetor()[i] == 1) { this.setValorHeuristica(this.getValorHeuristica() + 2);}
                    if (this.getPosicaoVetor()[i] == 2) { this.setValorHeuristica(this.getValorHeuristica() + 1);}
                    if (this.getPosicaoVetor()[i] == 4) { this.setValorHeuristica(this.getValorHeuristica() + 3);}
                    if (this.getPosicaoVetor()[i] == 5) { this.setValorHeuristica(this.getValorHeuristica() + 2);}
                    if (this.getPosicaoVetor()[i] == 6) { this.setValorHeuristica(this.getValorHeuristica() + 1);}
                    if (this.getPosicaoVetor()[i] == 7) { this.setValorHeuristica(this.getValorHeuristica() + 4);}
                    if (this.getPosicaoVetor()[i] == 8) { this.setValorHeuristica(this.getValorHeuristica() + 3);}
                    break;
                }
                case 3:{
                    if (this.getPosicaoVetor()[i] == 1) { this.setValorHeuristica(this.getValorHeuristica() + 1);}
                    if (this.getPosicaoVetor()[i] == 2) { this.setValorHeuristica(this.getValorHeuristica() + 2);}
                    if (this.getPosicaoVetor()[i] == 3) { this.setValorHeuristica(this.getValorHeuristica() + 3);}
                    if (this.getPosicaoVetor()[i] == 5) { this.setValorHeuristica(this.getValorHeuristica() + 1);}
                    if (this.getPosicaoVetor()[i] == 6) { this.setValorHeuristica(this.getValorHeuristica() + 2);}
                    if (this.getPosicaoVetor()[i] == 7) { this.setValorHeuristica(this.getValorHeuristica() + 1);}
                    if (this.getPosicaoVetor()[i] == 8) { this.setValorHeuristica(this.getValorHeuristica() + 3);}
                    break;
                }
                case 4:{
                    if (this.getPosicaoVetor()[i] == 1) { this.setValorHeuristica(this.getValorHeuristica() + 2);}
                    if (this.getPosicaoVetor()[i] == 2) { this.setValorHeuristica(this.getValorHeuristica() + 1);}
                    if (this.getPosicaoVetor()[i] == 3) { this.setValorHeuristica(this.getValorHeuristica() + 2);}
                    if (this.getPosicaoVetor()[i] == 4) { this.setValorHeuristica(this.getValorHeuristica() + 1);}
                    if (this.getPosicaoVetor()[i] == 6) { this.setValorHeuristica(this.getValorHeuristica() + 1);}
                    if (this.getPosicaoVetor()[i] == 7) { this.setValorHeuristica(this.getValorHeuristica() + 2);}
                    if (this.getPosicaoVetor()[i] == 8) { this.setValorHeuristica(this.getValorHeuristica() + 1);}
                    break;
                }
                case 5:{
                    if (this.getPosicaoVetor()[i] == 1) { this.setValorHeuristica(this.getValorHeuristica() + 3);}
                    if (this.getPosicaoVetor()[i] == 2) { this.setValorHeuristica(this.getValorHeuristica() + 2);}
                    if (this.getPosicaoVetor()[i] == 3) { this.setValorHeuristica(this.getValorHeuristica() + 1);}
                    if (this.getPosicaoVetor()[i] == 4) { this.setValorHeuristica(this.getValorHeuristica() + 2);}
                    if (this.getPosicaoVetor()[i] == 5) { this.setValorHeuristica(this.getValorHeuristica() + 1);}
                    if (this.getPosicaoVetor()[i] == 7) { this.setValorHeuristica(this.getValorHeuristica() + 3);}
                    if (this.getPosicaoVetor()[i] == 8) { this.setValorHeuristica(this.getValorHeuristica() + 2);}
                    break;
                }
                case 6:{
                    if (this.getPosicaoVetor()[i] == 1) { this.setValorHeuristica(this.getValorHeuristica() + 2);}
                    if (this.getPosicaoVetor()[i] == 2) { this.setValorHeuristica(this.getValorHeuristica() + 3);}
                    if (this.getPosicaoVetor()[i] == 3) { this.setValorHeuristica(this.getValorHeuristica() + 4);}
                    if (this.getPosicaoVetor()[i] == 4) { this.setValorHeuristica(this.getValorHeuristica() + 1);}
                    if (this.getPosicaoVetor()[i] == 5) { this.setValorHeuristica(this.getValorHeuristica() + 2);}
                    if (this.getPosicaoVetor()[i] == 6) { this.setValorHeuristica(this.getValorHeuristica() + 3);}
                    if (this.getPosicaoVetor()[i] == 8) { this.setValorHeuristica(this.getValorHeuristica() + 1);}
                    break;
                }
                case 7:{
                    if (this.getPosicaoVetor()[i] == 1) { this.setValorHeuristica(this.getValorHeuristica() + 3);}
                    if (this.getPosicaoVetor()[i] == 2) { this.setValorHeuristica(this.getValorHeuristica() + 2);}
                    if (this.getPosicaoVetor()[i] == 3) { this.setValorHeuristica(this.getValorHeuristica() + 3);}
                    if (this.getPosicaoVetor()[i] == 4) { this.setValorHeuristica(this.getValorHeuristica() + 2);}
                    if (this.getPosicaoVetor()[i] == 5) { this.setValorHeuristica(this.getValorHeuristica() + 1);}
                    if (this.getPosicaoVetor()[i] == 6) { this.setValorHeuristica(this.getValorHeuristica() + 2);}
                    if (this.getPosicaoVetor()[i] == 7) { this.setValorHeuristica(this.getValorHeuristica() + 1);}
                    break;
                }
                case 8:{
                    if (this.getPosicaoVetor()[i] == 1) { this.setValorHeuristica(this.getValorHeuristica() + 4);}
                    if (this.getPosicaoVetor()[i] == 2) { this.setValorHeuristica(this.getValorHeuristica() + 3);}
                    if (this.getPosicaoVetor()[i] == 3) { this.setValorHeuristica(this.getValorHeuristica() + 2);}
                    if (this.getPosicaoVetor()[i] == 4) { this.setValorHeuristica(this.getValorHeuristica() + 3);}
                    if (this.getPosicaoVetor()[i] == 5) { this.setValorHeuristica(this.getValorHeuristica() + 2);}
                    if (this.getPosicaoVetor()[i] == 6) { this.setValorHeuristica(this.getValorHeuristica() + 1);}
                    if (this.getPosicaoVetor()[i] == 7) { this.setValorHeuristica(this.getValorHeuristica() + 2);}
                    if (this.getPosicaoVetor()[i] == 8) { this.setValorHeuristica(this.getValorHeuristica() + 1);}  
                    break;                         
                }
            }  
        }
    }
}
