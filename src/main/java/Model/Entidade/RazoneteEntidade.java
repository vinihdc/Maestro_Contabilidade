package Model.Entidade;

public class RazoneteEntidade {


    public RazoneteEntidade(String elementoFato, int valorCredito, int valorDebito, int saldo) {
        ElementoFato = elementoFato;
        ValorCredito = valorCredito;
        ValorDebito = valorDebito;
        Saldo = saldo;
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

    public Integer getSaldo() {
        return Saldo;
    }



    private Integer Saldo;









}
