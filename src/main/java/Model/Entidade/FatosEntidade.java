package Model.Entidade;

import Model.BancoDeDados.*;

import java.sql.SQLException;

public class FatosEntidade {

    private CadastroDeAtivosBD CadastroAtivo = new CadastroDeAtivosBD();

    private CaixaBD CadastroCaixa = new CaixaBD();

    private FinanciamentoBD CadastroFinanciamento = new FinanciamentoBD();

    private InvestimentoBD CadastroInvestimento = new InvestimentoBD();

    private FornecedorBD CadastroFornecedor = new FornecedorBD();


        private String OperacaoFato;

        private int ValorFato;

        private String DetalhesFato;

        private String FormaDePagamento;

        private String DataFato;


    public FatosEntidade(String operacaoFato, int valorFato, String detalhesFato, String formaDePagamento, String dataFato) {
        OperacaoFato = operacaoFato;
        ValorFato = valorFato;
        DetalhesFato = detalhesFato;
        FormaDePagamento = formaDePagamento;
        DataFato = dataFato;
    }

    public void DefinirCaminho() throws SQLException {

        if(OperacaoFato.equals("Ativo")) {
           CadastroAtivo.AtivoDebito(OperacaoFato, ValorFato, DetalhesFato, DataFato);
        }


        if(OperacaoFato.equals("Investimento")) {
            CadastroInvestimento.RegistrarInvestimento(ValorFato);
            CadastroCaixa.RegistroNoCaixaDebito(ValorFato, DetalhesFato);
        }



        if(OperacaoFato.equals("Emprestimo")) {
           CadastroAtivo.AtivoCredito(OperacaoFato, ValorFato, DetalhesFato, DataFato);
           CadastroCaixa.RegistroNoCaixaDebito(ValorFato, DetalhesFato);
        }

        if(OperacaoFato.equals("PagamentoDeDivida")) {
            CadastroCaixa.RegistroNoCaixaCredito(ValorFato, DetalhesFato);
            CadastroFinanciamento.RegistrarFinanciamentoDebito(ValorFato);
        }

        if(OperacaoFato.equals("Banco") && FormaDePagamento.equals("Credito")) {

        }











    }







}
