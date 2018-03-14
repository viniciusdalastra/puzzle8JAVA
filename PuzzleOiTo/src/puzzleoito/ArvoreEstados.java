package puzzleoito;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe de Arvore com os estados
 * @author Vinícius Dalastra
 */
class ArvoreEstados {

    private Estado raiz;

    public Estado getRaiz() {
        return this.raiz;
    }

    void setRaiz(Estado estado) {
        this.raiz = estado;
    }

    void adicionaFilhoRaiz(Estado estado) {
        this.getRaiz().adicionaFilho(estado);
    }

    public boolean Contem(Estado estado){
        boolean retorno = false;
        for (Estado filhoRaiz : this.getRaiz().getFilhos()) {
            if (filhoRaiz.equals(estado)){
                    retorno = true;
                    break;
            }else if (filhoRaiz.numeroFilhos()> 0)
                for (Estado estadoFilho : estado.getFilhos()) {
                    retorno = Contem(estadoFilho);
                    if (retorno)
                        break;
                }
            if (retorno)
                break;
        }
        return retorno;
    }

    public List<Estado> trasformaLista() {
        List<Estado> list = new ArrayList<Estado>();
        percorre(this.getRaiz(), list);
        return list;
    }

    public void percorre(Estado element, List<Estado> list) {
        list.add(element);
        for (Estado data : element.getFilhos()) {
            percorre(data, list);
        }
    }
}
