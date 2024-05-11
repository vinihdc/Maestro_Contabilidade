package Model.Entidade;

public class DiarioEntidade {



    public int getValor() {
        return Valor;
    }

    private int Valor;

    public String getDetalhes() {
        return Detalhes;
    }

    private String Detalhes;

    public String getData() {
        return Data;
    }

    private String Data;


    public String getCredito() {
        return Credito;
    }

    private String Credito;

    public String getDebito() {
        return Debito;
    }

    private String Debito;

    public String getCodFato() {
        return CodFato;
    }

    private String CodFato;



    public DiarioEntidade(String codFato, String data, String detalhes, String debito, String credito, int valor) {
            CodFato = codFato;
            Data = data;
            Detalhes = detalhes;
            Debito = debito;
            Credito = credito;
            Valor = valor;

    }
}
