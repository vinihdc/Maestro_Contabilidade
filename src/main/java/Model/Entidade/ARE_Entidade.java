package Model.Entidade;

public class ARE_Entidade {

    public int getReceita() {
        return Receita;
    }

    private int Receita;

    public int getDespesa() {
        return Despesa;
    }

    private int Despesa;

    public int getResultadoAre() {
        return ResultadoAre;
    }

    private int ResultadoAre;


    public String getSituacao() {
        return Situacao;
    }

    private String Situacao;

    public String getMes() {
        return Mes;
    }

    private String Mes;

    public ARE_Entidade(int receita, int despesa, int resultadoare, String situacao, String mes) {
        Receita = receita;
        Despesa = despesa;
        ResultadoAre = resultadoare;
        Situacao = situacao;
        Mes = mes;
    }
}
