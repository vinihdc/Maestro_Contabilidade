package Model.Entidade;

import Model.BancoDeDados.*;

import java.sql.SQLException;

public class FatosEntidade {

    private CadastroDeAtivosBD CadastroAtivo = new CadastroDeAtivosBD();

    private CaixaBD CadastroCaixa = new CaixaBD();

    private FinanciamentoBD CadastroFinanciamento = new FinanciamentoBD();

    private InvestimentoBD CadastroInvestimento = new InvestimentoBD();

    private FornecedorBD CadastroFornecedor = new FornecedorBD();

    private BancoBradescoBD CadastroBanco = new BancoBradescoBD();

    private DepositoNoBancoBD CadastroDeposito = new DepositoNoBancoBD();


        private String OperacaoFato;

        private int ValorFato;

        private String DetalhesFato;

        private String DataFato;


    public FatosEntidade(String operacaoFato, int valorFato, String detalhesFato, String dataFato) {
        OperacaoFato = operacaoFato;
        ValorFato = valorFato;
        DetalhesFato = detalhesFato;
        DataFato = dataFato;
    }

    public void DefinirCaminho() throws SQLException {

        if(OperacaoFato.equals("Ativo")) {
           CadastroAtivo.AtivoDebito(OperacaoFato, ValorFato, DetalhesFato, DataFato);
           CadastroCaixa.RegistroNoCaixaCredito(ValorFato, DetalhesFato);
        }

        if(OperacaoFato.equals("AtivoPagoAPrazo")) {
           CadastroFinanciamento.RegistrarFinanciamentoCredito(ValorFato, DetalhesFato);
        }

        if(OperacaoFato.equals("Investimento")) {
            CadastroInvestimento.RegistrarInvestimento(ValorFato, DetalhesFato);
            CadastroCaixa.RegistroNoCaixaDebito(ValorFato, DetalhesFato);
        }


        if(OperacaoFato.equals("Emprestimo")) {
           CadastroAtivo.AtivoCredito(OperacaoFato, ValorFato, DetalhesFato, DataFato);
           CadastroCaixa.RegistroNoCaixaDebito(ValorFato, DetalhesFato);
        }

        if(OperacaoFato.equals("PagamentoDeDivida")) {
            CadastroCaixa.RegistroNoCaixaCredito(ValorFato, DetalhesFato);
            CadastroFinanciamento.RegistrarFinanciamentoDebito(ValorFato, DetalhesFato);
        }

        if(OperacaoFato.equals("PagamentoBanco")) {
            CadastroBanco.BancoCredito(ValorFato, DetalhesFato);
        }

        if(OperacaoFato.equals("Fornecedor")) {
            CadastroFornecedor.FornecedorCredito(ValorFato, DetalhesFato);
        }

        if(OperacaoFato.equals("DepositoBanco")) {
            CadastroDeposito.RegistrarDeposito();
        }











    }







}
