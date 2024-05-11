package Model.Entidade;

import Model.BancoDeDados.FornecedorPagoBD;
import lombok.Value;

public class DiarioEntidade {


    public String getOperacao() {
        return Operacao;
    }

    private String Operacao;

    public int getValor() {
        return Valor;
    }

    private int Valor;

    public String getDetalhes() {
        return Detalhes;
    }

    private String Detalhes;


    public String getFormaDePagamento() {
        return FormaDePagamento;
    }

    private String FormaDePagamento;

    public String getMovimentacao() {
        return Movimentacao;
    }

    private String Movimentacao;

    public DiarioEntidade(String codfato, String data, String detalhes, String movimentacao) {



    }
}
