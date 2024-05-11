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

    private DiarioBD CadastrarDadosDiario = new DiarioBD();

    private AplicacoesBD CadastrarAplicacoes = new AplicacoesBD();

    private ClientesBD CadastrarDinheiroClientes = new ClientesBD();


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
           CadastrarDadosDiario.InserirDadosDiario(CodFato, DataFato, DetalhesFato, "Ativo", "Caixa", ValorFato);
        }

        //002 - Compra a prazo (Ativo pago a prazo)
        if(CodFato.equals("002")) {
           CadastroFinanciamento.RegistrarFinanciamentoCredito(CodFato, ValorFato, DetalhesFato, DataFato);
            CadastroAtivo.AtivoDebito(CodFato, ValorFato, DetalhesFato, DataFato);
            CadastrarDadosDiario.InserirDadosDiario(CodFato, DataFato, DetalhesFato, "Ativo", "Financiamento", ValorFato);
        }
        // 003 - Compra 50% a vista 50% a prazo
        if(CodFato.equals("003")) {
            int DividirPelaMetade = ValorFato / 2;
            CadastroFinanciamento.RegistrarFinanciamentoCredito(CodFato, DividirPelaMetade, DetalhesFato, DataFato);
            CadastroCaixa.RegistroNoCaixaCredito(CodFato, DividirPelaMetade, DetalhesFato, DataFato);
            CadastroAtivo.AtivoDebito(CodFato, ValorFato, DetalhesFato, DataFato);
            CadastrarDadosDiario.InserirDadosDiario(CodFato, DataFato, DetalhesFato, "-", "Caixa e Financiamento", ValorFato);
        }

        // 004 - Investimento
        if(CodFato.equals("004")) {
            CadastroInvestimento.RegistrarInvestimento(CodFato, ValorFato, DetalhesFato, DataFato);
            CadastroCaixa.RegistroNoCaixaDebito(CodFato, ValorFato, DetalhesFato, DataFato);
            CadastrarDadosDiario.InserirDadosDiario(CodFato, DataFato, DetalhesFato, "Caixa", "Investimento", ValorFato);
        }


        //005 - Emprestimo
        if(CodFato.equals("005")) {
           CadastroAtivo.AtivoCredito(CodFato, ValorFato, DetalhesFato, DataFato);
           CadastroCaixa.RegistroNoCaixaDebito(CodFato, ValorFato, DetalhesFato, DataFato);
           CadastrarDadosDiario.InserirDadosDiario(CodFato, DataFato, DetalhesFato, "Caixa", "Emprestimo", ValorFato);
        }

        //006 - Pagamento de Divida

        if(CodFato.equals("006")) {
            CadastroCaixa.RegistroNoCaixaCredito(CodFato, ValorFato, DetalhesFato, DataFato);
            CadastroFinanciamento.RegistrarFinanciamentoDebito(CodFato, ValorFato, DetalhesFato, DataFato);
            CadastrarDadosDiario.InserirDadosDiario(CodFato, DataFato, DetalhesFato, "Financiamento", "Caixa", ValorFato);
        }

        // 007 - Pagamento com o cartão Banco 80 - Banco - 20% - Fornecedor
        if(CodFato.equals("007")) {
            int PorcentagemBanco = (int) (ValorFato * 0.8);
            int PorcentagemFornecedor =  (int) (ValorFato * 0.2);
            CadastroBanco.BancoCredito(CodFato, PorcentagemBanco, DetalhesFato, DataFato);
            CadastroFornecedor.FornecedorCredito(CodFato, PorcentagemFornecedor, DetalhesFato, DataFato);
            CadastroAtivo.AtivoDebito(CodFato, ValorFato, DetalhesFato, DataFato);
            CadastrarDadosDiario.InserirDadosDiario(CodFato, DataFato, DetalhesFato, "-", "Banco, Fornecedor", ValorFato);


        }

        //008 - Compra a prazo fornecedor

        if(CodFato.equals("008")) {
            CadastroFornecedor.FornecedorCredito(CodFato, ValorFato, DetalhesFato, DataFato);
            CadastroAtivo.AtivoDebito(CodFato, ValorFato, DetalhesFato, DataFato);
            CadastrarDadosDiario.InserirDadosDiario(CodFato, DataFato, DetalhesFato, "-", "Fornecedor", ValorFato);
        }

        //009 - Deposito no banco
        if(CodFato.equals("009")) {
            CadastroDeposito.RegistrarDeposito(CodFato, DetalhesFato, DataFato);
            CadastrarDadosDiario.InserirDadosDiario(CodFato, DataFato, DetalhesFato, "-", "-", ValorFato);
        }

        //010 - Pagamento Financiamento
        if(CodFato.equals("010")) {
            CadastroFinanciamentoPago.FinanciamentoPago(CodFato, DetalhesFato, DataFato);
            CadastrarDadosDiario.InserirDadosDiario(CodFato, DataFato, DetalhesFato, "-", "-", ValorFato);
        }

        //011 - Pagamento Fornecedor
        if(CodFato.equals("011")) {
            CadastroFornecedorPago.FornecedorPago(CodFato, DetalhesFato, DataFato);
            CadastrarDadosDiario.InserirDadosDiario(CodFato, DataFato, DetalhesFato, "-", "-", ValorFato);
        }

        //012 - Pagamento de coisas com o cartão do banco
        if(CodFato.equals("012")) {
            CadastroBanco.BancoCredito(CodFato, ValorFato, DetalhesFato, DataFato);
            CadastroAtivo.AtivoDebito(CodFato, ValorFato, DetalhesFato, DataFato);
            CadastrarDadosDiario.InserirDadosDiario(CodFato, DataFato, DetalhesFato, "-", "-", ValorFato);
        }


        //013 - Colocar dinheiro no Debito do Caixa
        if(CodFato.equals("013")) {
           CadastroCaixa.RegistroNoCaixaDebito(CodFato, ValorFato, DetalhesFato, DataFato);
            CadastrarDadosDiario.InserirDadosDiario(CodFato, DataFato, DetalhesFato, "Caixa", "-", ValorFato);
        }


        //014 - Colocar dinheiro no debito do banco
        if(CodFato.equals("014")) {
            CadastroBanco.BancoDebito(CodFato, ValorFato, DetalhesFato, DataFato);
            CadastrarDadosDiario.InserirDadosDiario(CodFato, DataFato, DetalhesFato, "Banco", "-", ValorFato);
        }

        //015 - Aplicacoes - Colocar no Debito

        if(CodFato.equals("015")) {
            CadastrarAplicacoes.RegistroAplicacoesDebito(CodFato, ValorFato, DetalhesFato, DataFato);
            CadastrarDadosDiario.InserirDadosDiario(CodFato, DataFato, DetalhesFato, "Aplicacoes", "-", ValorFato);
        }

        //016 - Clientes
        if(CodFato.equals("016")) {
            CadastrarDinheiroClientes.RegistroClientesDebito(CodFato, ValorFato, DetalhesFato, DataFato);
            CadastrarDadosDiario.InserirDadosDiario(CodFato, DataFato, DetalhesFato, "Clientes", "-", ValorFato);
        }

















    }







}
