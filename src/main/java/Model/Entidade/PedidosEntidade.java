package Model.Entidade;

import lombok.Getter;

public class PedidosEntidade {

    @Getter
    private String NivelQueRecusou;

    @Getter
    private String SituacaoFato;

    @Getter
    private int ID;



    @Getter
    private int Valor;


    @Getter
    private String Detalhes;



    @Getter
    private String Data;

    @Getter
    private String Credito;


    @Getter
    private String Debito;


    @Getter
    private String CodFato;



    public PedidosEntidade(int id, String nivelquerecusou, String situacaofato, String codFato, String data, String detalhes, String debito, String credito, int valor) {
        NivelQueRecusou = nivelquerecusou;
        SituacaoFato = situacaofato;
        ID = id;
        CodFato = codFato;
        Data = data;
        Detalhes = detalhes;
        Debito = debito;
        Credito = credito;
        Valor = valor;

    }








}
