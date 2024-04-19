package Model.Entidade;

public class BalanceteEntidade {


    public BalanceteEntidade(String elementoFato, int valorCredito, int valorDebito, int saldoFinal) {
        ElementoFato = elementoFato;
        ValorCredito = valorCredito;
        ValorDebito = valorDebito;
        SaldoFinal = saldoFinal;

    }



    public String getElementoFato() {
        return ElementoFato;
    }

    private String ElementoFato;

    public String getTipoDeOperacao() {
        return TipoDeOperacao;
    }

    private String TipoDeOperacao;

    public int getValor() {
        return Valor;
    }

    private Integer Valor;

    public int getValorCredito() {
        return ValorCredito;
    }

    private Integer ValorCredito;

    public int getValorDebito() {
        return ValorDebito;
    }

    private Integer ValorDebito;


    public int getSaldoFinal() {
        return SaldoFinal;
    }


    private Integer SaldoFinal;








}
