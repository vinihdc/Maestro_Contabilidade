package Model.Entidade;

public class ElementosRazoneteEntidade {
    public ElementosRazoneteEntidade(int valor, String operacao, String tipoDePagamento, String detalhesFato, String fatoOperacao) {
        Valor = valor;
        Operacao = operacao;
        TipoDePagamento = tipoDePagamento;
        DetalhesFato = detalhesFato;
        FatoOperacao = fatoOperacao;
    }


    public int getValor() {
        return Valor;
    }

    public void setValor(int valor) {
        Valor = valor;
    }

    private int Valor;

    public String getOperacao() {
        return Operacao;
    }

    public void setOperacao(String operacao) {
        Operacao = operacao;
    }

    private String Operacao; //Credito ou Debito


    public String getTipoDePagamento() {
        return TipoDePagamento;
    }

    public void setTipoDePagamento(String tipoDePagamento) {
        TipoDePagamento = tipoDePagamento;
    }

    private String TipoDePagamento; //A vista ou a prazo ai sei qual eu mando para financiamento ou caixa


    public String getFatoOperacao() {
        return FatoOperacao;
    }

    public void setFatoOperacao(String fatoOperacao) {
        FatoOperacao = fatoOperacao;
    }

    private String FatoOperacao; //Investimento, Compra, Pagamento

    public String getDetalhesFato() {
        return DetalhesFato;
    }

    public void setDetalhesFato(String detalhesFato) {
        DetalhesFato = detalhesFato;
    }

    private String DetalhesFato;





}
