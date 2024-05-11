package Model.Entidade;

import Model.BancoDeDados.*;

import java.sql.SQLException;

public class FatosEntidade {

    private CadastroDeAtivosBD CadastroAtivo = new CadastroDeAtivosBD();

    private CaixaBD CadastroCaixa = new CaixaBD();

    private FinanciamentoBD CadastroFinanciamento = new FinanciamentoBD();

    private InvestimentoBD CadastroInvestimento = new InvestimentoBD();

    private FornecedorBD CadastroFornecedor = new FornecedorBD();

    private BancoContabilidade CadastroBanco = new BancoContabilidade();

    private DepositoNoBancoBD CadastroDeposito = new DepositoNoBancoBD();

    private FinanciamentoPagoBD CadastroFinanciamentoPago = new FinanciamentoPagoBD();

    private FornecedorPagoBD CadastroFornecedorPago = new FornecedorPagoBD();


        private String CodFato;

        private int ValorFato;

        private String DetalhesFato;

        private String DataFato;


    public FatosEntidade(String codFato, int valorFato, String detalhesFato, String dataFato) {
        CodFato = codFato;
        ValorFato = valorFato;
        DetalhesFato = detalhesFato;
        DataFato = dataFato;
    }


    public void z() throws SQLException {
        // 003 - Investimento
        if(CodFato.equals("003")) {
            CadastroInvestimento.RegistrarInvestimento(CodFato, ValorFato, DetalhesFato, DataFato);
        }
    }



    public void DefinirCaminho() throws SQLException {

        // 001 - Compra a vista (Ativo)
        if(CodFato.equals("001")) {
           CadastroAtivo.AtivoDebito(CodFato, ValorFato, DetalhesFato, DataFato);
           CadastroCaixa.RegistroNoCaixaCredito(CodFato, ValorFato, DetalhesFato, DataFato);
        }

        //002 - Compra a prazo (Ativo pago a prazo)
        if(CodFato.equals("002")) {
           CadastroFinanciamento.RegistrarFinanciamentoCredito(CodFato, ValorFato, DetalhesFato, DataFato);
            CadastroAtivo.AtivoDebito(CodFato, ValorFato, DetalhesFato, DataFato);
        }
        // 003 - Compra 50% a vista 50% a prazo
        if(CodFato.equals("003")) {
            int DividirPelaMetade = ValorFato / 2;
            CadastroFinanciamento.RegistrarFinanciamentoCredito(CodFato, DividirPelaMetade, DetalhesFato, DataFato);
            CadastroCaixa.RegistroNoCaixaCredito(CodFato, DividirPelaMetade, DetalhesFato, DataFato);
            CadastroAtivo.AtivoDebito(CodFato, ValorFato, DetalhesFato, DataFato);
        }

        // 004 - Investimento
        if(CodFato.equals("004")) {
            CadastroInvestimento.RegistrarInvestimento(CodFato, ValorFato, DetalhesFato, DataFato);
            CadastroCaixa.RegistroNoCaixaDebito(CodFato, ValorFato, DetalhesFato, DataFato);
        }


        //005 - Emprestimo
        if(CodFato.equals("005")) {
           CadastroAtivo.AtivoCredito(CodFato, ValorFato, DetalhesFato, DataFato);
           CadastroCaixa.RegistroNoCaixaDebito(CodFato, ValorFato, DetalhesFato, DataFato);
        }

        //006 - Pagamento de Divida

        if(CodFato.equals("006")) {
            CadastroCaixa.RegistroNoCaixaCredito(CodFato, ValorFato, DetalhesFato, DataFato);
            CadastroFinanciamento.RegistrarFinanciamentoDebito(CodFato, ValorFato, DetalhesFato, DataFato);
        }

        // 007 - Pagamento com o cartão Banco 80 - Banco - 20% - Fornecedor
        if(CodFato.equals("007")) {
            int PorcentagemBanco = (int) (ValorFato * 0.8);
            int PorcentagemFornecedor =  (int) (ValorFato * 0.2);
            CadastroBanco.BancoCredito(CodFato, PorcentagemBanco, DetalhesFato, DataFato);
            CadastroFornecedor.FornecedorCredito(CodFato, PorcentagemFornecedor, DetalhesFato, DataFato);
            CadastroAtivo.AtivoDebito(CodFato, ValorFato, DetalhesFato, DataFato);


        }

        //008 - Compra a prazo fornecedor

        if(CodFato.equals("008")) {
            CadastroFornecedor.FornecedorCredito(CodFato, ValorFato, DetalhesFato, DataFato);
            CadastroAtivo.AtivoDebito(CodFato, ValorFato, DetalhesFato, DataFato);
        }

        //009 - Deposito no banco
        if(CodFato.equals("009")) {
            CadastroDeposito.RegistrarDeposito(CodFato, DetalhesFato, DataFato);
        }

        //010 - Pagamento Financiamento
        if(CodFato.equals("010")) {
            CadastroFinanciamentoPago.FinanciamentoPago(DataFato);
        }

        //011 - Pagamento Fornecedor
        if(CodFato.equals("011")) {
            CadastroFornecedorPago.FornecedorPago(DataFato);
        }

















    }







}
