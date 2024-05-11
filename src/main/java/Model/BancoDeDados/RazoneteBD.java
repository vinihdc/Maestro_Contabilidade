package Model.BancoDeDados;

import Model.Entidade.BalanceteEntidade;
import Model.Entidade.RazoneteEntidade;
import org.apache.logging.log4j.message.ReusableMessage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class RazoneteBD {

    private ConexaoBD Conexao = new ConexaoBD();


    public int getSaldoGeral() {
        return SaldoGeral;
    }

    public void setSaldoGeral(int saldoGeral) {
        SaldoGeral = saldoGeral;
    }

    private int SaldoGeral;

    private String Operacao;



    public List<RazoneteEntidade> SELECTRAZONETE(boolean CaixaZerado) throws SQLException {



        List<RazoneteEntidade> DadosRazonete = new ArrayList<>();

        String Detalhes = "";

        //Variaveis Saldo Fatos

        //Variaveis Caixa
        int DebitoCaixaSemDeposito = 0;
        int CreditoCaixaSemDeposito = 0;
        int DebitoCaixaComDeposito = 0;
        int CreditoCaixaComDeposito = 0;
        int SomaDebitoCaixaSemDeposito = 0;
        int SomaCreditoCaixaSemDeposito = 0;



        //Variaveis de Soma Financiamento
        int CreditoFinanciamentoPago = 0;
        int DebitoFinanciamentoPago = 0;
        int CreditoFinanciamentoNaoPago = 0;
        int DebitoFinanciamentoNaoPago = 0;
        int SomaCreditoFinanciamentoNaoPago = 0;
        int SomaDebitoFinanciamentoNaoPago = 0;
        int SomaCreditoFinanciamentoPago = 0;
        int SomaDebitoFinanciamentoPago = 0;
        int SaldoFinalFinanciamentoCredito = 0;
        int SaldoFinalFinanciamentoDebito = 0;


        //Variaveis de Soma Investimento
        int CreditoInvestimento = 0;
        int SaldoFinalInvestimentoCredito = 0;




        //Variaveis de SomaBanco
        int CreditoBanco = 0;
        int DebitoBanco = 0;
        int SomaCreditoBanco = 0;
        int SomaDebitoBanco = 0;
        int SaldoFinalCreditoBanco = 0;
        int SaldoFinalDebitoBanco = 0;


        //Variaveis de Fornecedor
        int DebitoFornecedor = 0;
        int CreditoFornecedor = 0;
        int SomaDebitoFornecedor = 0;
        int SomaCreditoFornecedor = 0;
        int DebitoFornecedorPago = 0;
        int CreditoFornecedorPago = 0;



        try {
            Conexao.AbrirConexao();
            // dá o select no database
            String sql1 = "SELECT DETALHES_FATO, VALOR_FATO, FORMADEPAGAMENTO_FATO FROM FATOSGERAL";//SELECT CREDITO, DEBITO FROM CAIXA -> Caixa
            String sql3 = "SELECT * FROM FINANCIAMENTO";
            String sql4 = "SELECT * FROM INVESTIMENTO";
            String sql5 = "SELECT * FROM BANCO";
            String sql7 = "SELECT * FROM FORNECEDOR";
            String sql8 = "SELECT * FROM CAIXA";
            String sql9 = "SELECT * FROM CAIXAZERADO";
            String sql10 = "SELECT * FROM FINANCIAMENTOPAGO";
            String sql11 = "SELECT * FROM FORNECEDORPAGO";
            ResultSet Fatos = Conexao.getConexao().createStatement().executeQuery(sql1);
            ResultSet Financiamento = Conexao.getConexao().createStatement().executeQuery(sql3);
            ResultSet Investimento = Conexao.getConexao().createStatement().executeQuery(sql4);
            ResultSet Banco = Conexao.getConexao().createStatement().executeQuery(sql5);
            ResultSet Fornecedor = Conexao.getConexao().createStatement().executeQuery(sql7);
            ResultSet CaixaSemDeposito = Conexao.getConexao().createStatement().executeQuery(sql8);
            ResultSet CaixaComDeposito = Conexao.getConexao().createStatement().executeQuery(sql9);
            ResultSet FinanciamentoPago = Conexao.getConexao().createStatement().executeQuery(sql10);
            ResultSet FornecedorPago = Conexao.getConexao().createStatement().executeQuery(sql11);


            //while(S.next()) quer dizer que ele vai percorrer as colunas do banco enquanto não for null

            while (Fatos.next()) {
                Detalhes = Fatos.getString("DETALHES_FATO");
                int valor = Integer.parseInt(Fatos.getString("VALOR_FATO"));
                String TipoOp = Fatos.getString("FORMADEPAGAMENTO_FATO");


                if (TipoOp.equals("Debito")) {  //se a operação for debito
                    DadosRazonete.add(new RazoneteEntidade(Detalhes, 0, valor));

                }

                else {
                    DadosRazonete.add(new RazoneteEntidade(Detalhes, valor, 0));

                }


            }

            //Operacoes Caixa, ve se foi feito deposito ou não, se foi faz uma coisa se não foi faz outra


            //Quando o caixa não for zerado
            while(CaixaSemDeposito.next()) {
                DebitoCaixaSemDeposito = Integer.parseInt(CaixaSemDeposito.getString("DEBITO"));
                CreditoCaixaSemDeposito = Integer.parseInt(CaixaSemDeposito.getString("CREDITO"));
                SomaDebitoCaixaSemDeposito += DebitoCaixaSemDeposito;
                SomaCreditoCaixaSemDeposito += CreditoCaixaSemDeposito;

            }



            if(SomaDebitoCaixaSemDeposito != 0 || SomaCreditoCaixaSemDeposito != 0)  {
                DadosRazonete.add(new RazoneteEntidade(Detalhes, SomaCreditoCaixaSemDeposito, SomaDebitoCaixaSemDeposito));

            }

            else {

                while(CaixaComDeposito.next()) {
                    DebitoCaixaComDeposito = Integer.parseInt(CaixaComDeposito.getString("DEBITO"));
                    CreditoCaixaComDeposito = Integer.parseInt(CaixaComDeposito.getString("CREDITO"));
                }


                DadosRazonete.add(new RazoneteEntidade("Caixa", DebitoCaixaComDeposito, CreditoCaixaComDeposito));

            }


















            //Operacoes Financiamento
            while (Financiamento.next()) {
                DebitoFinanciamentoNaoPago = Integer.parseInt(Financiamento.getString("DEBITO"));
                CreditoFinanciamentoNaoPago = Integer.parseInt(Financiamento.getString("CREDITO"));
                SomaCreditoFinanciamentoNaoPago += CreditoFinanciamentoNaoPago;
                SomaDebitoFinanciamentoNaoPago += DebitoFinanciamentoNaoPago;
            }



            if (SomaCreditoFinanciamentoNaoPago != 0 && SomaDebitoFinanciamentoNaoPago != 0) {  //Quando financiamento não estiver zerado
                while (Financiamento.next()) {
                    DebitoFinanciamentoNaoPago = Integer.parseInt(Financiamento.getString("DEBITO"));
                    CreditoFinanciamentoNaoPago = Integer.parseInt(Financiamento.getString("CREDITO"));
                    SomaCreditoFinanciamentoNaoPago += CreditoFinanciamentoNaoPago;
                    SomaDebitoFinanciamentoNaoPago += DebitoFinanciamentoNaoPago;
                }
                DadosRazonete.add(new RazoneteEntidade("Financiamento",  SomaCreditoFinanciamentoNaoPago, SomaDebitoFinanciamentoNaoPago));



            }

            else { //Quando financiamento estiver zerado
                while(FinanciamentoPago.next()) {
                    CreditoFinanciamentoPago = Integer.parseInt(FinanciamentoPago.getString("CREDITO"));
                    DebitoFinanciamentoPago = Integer.parseInt(FinanciamentoPago.getString("DEBITO"));
                }
                DadosRazonete.add(new RazoneteEntidade("Financiamento", CreditoFinanciamentoPago, DebitoFinanciamentoPago));
            }


            //Operacoes Investimento


            while (Investimento.next()) {
                CreditoInvestimento = Integer.parseInt(Investimento.getString("CREDITO"));
                SaldoFinalInvestimentoCredito += CreditoInvestimento;



            }

            DadosRazonete.add(new RazoneteEntidade("Investimento", SaldoFinalInvestimentoCredito, 0));


            //Operacoes Banco


            while (Banco.next()) {
                DebitoBanco = Integer.parseInt(Banco.getString("DEBITO"));
                CreditoBanco = Integer.parseInt(Banco.getString("CREDITO"));
                SomaCreditoBanco += CreditoBanco;
                SomaDebitoBanco += DebitoBanco;


            }

           DadosRazonete.add(new RazoneteEntidade("Banco", SomaCreditoBanco, SomaDebitoBanco));

            //Operacoes Fornecedor
            while (Fornecedor.next()) {
                DebitoFornecedor = Integer.parseInt(Fornecedor.getString("DEBITO"));
                CreditoFornecedor = Integer.parseInt(Fornecedor.getString("CREDITO"));
                SomaDebitoFornecedor += DebitoFornecedor;
                SomaCreditoFornecedor += CreditoFornecedor;

            }

            if (SomaDebitoFornecedor != 0 || SomaCreditoFornecedor != 0) {


                DadosRazonete.add(new RazoneteEntidade("Fornecedor", CreditoFornecedor, DebitoFornecedor));



            }

            else {
                while(FornecedorPago.next()) {
                    DebitoFornecedorPago = Integer.parseInt(FornecedorPago.getString("DEBITO"));
                    CreditoFornecedorPago = Integer.parseInt(FornecedorPago.getString("CREDITO"));
                }

                DadosRazonete.add(new RazoneteEntidade("Fornecedor", CreditoFornecedorPago, DebitoFornecedorPago));
            }

        }

        catch (SQLException e) {
            throw new RuntimeException(e);
        }

        finally {
            Conexao.FecharConexao();
        }

        return DadosRazonete;
    }





}

