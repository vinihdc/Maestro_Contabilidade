package Model.Entidade;


import lombok.Getter;

public class DiarioEntidade {


    @Getter
    private String ID;

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

    @Getter
    private String FatoEditado;



    public DiarioEntidade(String id, String codFato, String data, String detalhes, String debito, String credito, int valor, String fatoEditado) {
            ID = id;
            CodFato = codFato;
            Data = data;
            Detalhes = detalhes;
            Debito = debito;
            Credito = credito;
            Valor = valor;
            FatoEditado = fatoEditado;

    }
}
