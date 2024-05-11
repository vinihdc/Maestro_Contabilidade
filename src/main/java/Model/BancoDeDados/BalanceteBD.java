package Model.BancoDeDados;

import Model.Entidade.BalanceteEntidade;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BalanceteBD {
    private ConexaoBD Conexao = new ConexaoBD();
    public List<BalanceteEntidade> Balancete = new ArrayList<>();

    public boolean CaixaZerado;

    public boolean FinanciamentoFoiPago;

    public boolean FornecedorPago;

    public BalanceteBD(boolean caixaZerado, boolean financiamentoFoiPago, boolean fornecedorPago) {
        FinanciamentoFoiPago = financiamentoFoiPago;
        FornecedorPago = fornecedorPago;
    }

    public BalanceteBD() {

    }


    public List<BalanceteEntidade> SELECTBalancete() throws SQLException {

        //Variaveis Saldo Fatos
        int SaldoFinalDebitoFatos = 0;
        int SaldoFinalCreditoFatos = 0;

        //Variaveis Caixa
        int DebitoCaixaSemDeposito = 0;
        int CreditoCaixaSemDeposito = 0;
        int DebitoCaixaComDeposito = 0;
        int CreditoCaixaComDeposito = 0;
        int SomaDebitoCaixaSemDeposito = 0;
        int SomaCreditoCaixaSemDeposito = 0;
        int SaldoFinalCaixaDebito = 0;
        int SaldoFinalCaixaCredito = 0;



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
        int SaldoFinalFornecedorDebito = 0;
        int SaldoFinalFornecedorCredito = 0;
        int DebitoFornecedorPago = 0;
        int CreditoFornecedorPago = 0;


        int SaldoFinal = 0;


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
                String Detalhes = Fatos.getString("DETALHES_FATO");
                int valor = Integer.parseInt(Fatos.getString("VALOR_FATO"));
                String TipoOp = Fatos.getString("FORMADEPAGAMENTO_FATO");


                if (TipoOp.equals("Debito")) {  //se a operação for debito
                    Balancete.add(new BalanceteEntidade(Detalhes, 0, valor, valor));
                    SaldoFinalDebitoFatos += valor;
                   //SaldoFinal += SaldoFinalDebitoFatos;
                }

                else {
                    Balancete.add(new BalanceteEntidade(Detalhes, valor, 0, valor));
                    SaldoFinalCreditoFatos += valor;
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

                System.out.println("\nSomaCreditoCaixaSemDep:  " + SomaCreditoCaixaSemDeposito);
                System.out.println("\nSomaDebitoCaixaSemDep:  " + SomaDebitoCaixaSemDeposito);


            if(SomaDebitoCaixaSemDeposito != 0 || SomaCreditoCaixaSemDeposito != 0)  {
                    if (SomaDebitoCaixaSemDeposito > SomaCreditoCaixaSemDeposito) {
                        SaldoFinalCaixaDebito = SomaDebitoCaixaSemDeposito - SomaCreditoCaixaSemDeposito;
                        Balancete.add(new BalanceteEntidade("Caixa", SomaCreditoCaixaSemDeposito, SomaDebitoCaixaSemDeposito, SaldoFinalCaixaDebito));
                    }



                    else {
                        SaldoFinalCaixaCredito = SomaCreditoCaixaSemDeposito - SomaDebitoCaixaSemDeposito;
                        Balancete.add(new BalanceteEntidade("Caixa", SomaCreditoCaixaSemDeposito, SomaDebitoCaixaSemDeposito, SaldoFinalCaixaDebito));
                    }
            }

                else {

                    while(CaixaComDeposito.next()) {
                        DebitoCaixaComDeposito = Integer.parseInt(CaixaComDeposito.getString("DEBITO"));
                        CreditoCaixaComDeposito = Integer.parseInt(CaixaComDeposito.getString("CREDITO"));
                    }


                    Balancete.add(new BalanceteEntidade("Caixa", DebitoCaixaComDeposito, CreditoCaixaComDeposito, 0));

                }


















            //Operacoes Financiamento
            while (Financiamento.next()) {
                DebitoFinanciamentoNaoPago = Integer.parseInt(Financiamento.getString("DEBITO"));
                CreditoFinanciamentoNaoPago = Integer.parseInt(Financiamento.getString("CREDITO"));
                SomaCreditoFinanciamentoNaoPago += CreditoFinanciamentoNaoPago;
                SomaDebitoFinanciamentoNaoPago += DebitoFinanciamentoNaoPago;
            }

                System.out.println("SomaCreditoFinanciamentoPago: " + SomaCreditoFinanciamentoNaoPago);
                System.out.println("\nCredito financiamento: " + CreditoFinanciamentoNaoPago);

            if (SomaCreditoFinanciamentoNaoPago != 0 && SomaDebitoFinanciamentoNaoPago != 0) { //Quando financiamento não estiver zerado
                while (Financiamento.next()) {
                    DebitoFinanciamentoNaoPago = Integer.parseInt(Financiamento.getString("DEBITO"));
                    CreditoFinanciamentoNaoPago = Integer.parseInt(Financiamento.getString("CREDITO"));
                    SomaCreditoFinanciamentoNaoPago += CreditoFinanciamentoNaoPago;
                    SomaDebitoFinanciamentoNaoPago += DebitoFinanciamentoNaoPago;
                }
                    if (SomaCreditoFinanciamentoNaoPago > SomaDebitoFinanciamentoNaoPago) {
                        SaldoFinalFinanciamentoCredito = SomaCreditoFinanciamentoNaoPago - SomaDebitoFinanciamentoNaoPago;
                        Balancete.add(new BalanceteEntidade("Financiamento",  SomaCreditoFinanciamentoNaoPago, SomaDebitoFinanciamentoNaoPago, SaldoFinalFinanciamentoCredito));

                    }

                    else {
                        SaldoFinalFinanciamentoDebito = SomaDebitoFinanciamentoNaoPago - SomaCreditoFinanciamentoNaoPago;
                        Balancete.add(new BalanceteEntidade("Financiamento", SomaCreditoFinanciamentoNaoPago, SomaDebitoFinanciamentoNaoPago, SaldoFinalFinanciamentoCredito));
                    }



            }

            else { //Quando financiamento estiver zerado
                while(FinanciamentoPago.next()) {
                    CreditoFinanciamentoPago = Integer.parseInt(FinanciamentoPago.getString("CREDITO"));
                    DebitoFinanciamentoPago = Integer.parseInt(FinanciamentoPago.getString("DEBITO"));
                }
                Balancete.add(new BalanceteEntidade("Financiamento", CreditoFinanciamentoPago, DebitoFinanciamentoPago, 0));
            }


            //Operacoes Investimento


            while (Investimento.next()) {
                CreditoInvestimento = Integer.parseInt(Investimento.getString("CREDITO"));
                SaldoFinalInvestimentoCredito += CreditoInvestimento;



            }

            Balancete.add(new BalanceteEntidade("Investimento", SaldoFinalInvestimentoCredito, 0, SaldoFinalInvestimentoCredito));


            //Operacoes Banco


            while (Banco.next()) {
                DebitoBanco = Integer.parseInt(Banco.getString("DEBITO"));
                CreditoBanco = Integer.parseInt(Banco.getString("CREDITO"));
                SomaCreditoBanco += CreditoBanco;
                SomaDebitoBanco += DebitoBanco;


            }

            if (SomaCreditoBanco > SomaDebitoBanco) {
                SaldoFinalCreditoBanco = SomaCreditoBanco - SomaDebitoBanco;
                Balancete.add(new BalanceteEntidade("Banco", SomaCreditoBanco, SomaDebitoBanco, SaldoFinalCreditoBanco));

            }

            else {
                SaldoFinalDebitoBanco = SomaDebitoBanco - SomaCreditoBanco;
                Balancete.add(new BalanceteEntidade("Banco", SomaCreditoBanco, SomaDebitoBanco, SaldoFinalDebitoBanco));
            }

            //Operacoes Fornecedor
            while (Fornecedor.next()) {
                DebitoFornecedor = Integer.parseInt(Fornecedor.getString("DEBITO"));
                CreditoFornecedor = Integer.parseInt(Fornecedor.getString("CREDITO"));
                SomaDebitoFornecedor += DebitoFornecedor;
                SomaCreditoFornecedor += CreditoFornecedor;

            }

            if (SomaDebitoFornecedor != 0 || SomaCreditoFornecedor != 0) {


                    if (SomaCreditoFornecedor > SomaDebitoFornecedor) {
                        SaldoFinalFornecedorCredito = CreditoFornecedor - DebitoFornecedor;
                        Balancete.add(new BalanceteEntidade("Fornecedor", CreditoFornecedor, DebitoFornecedor, SaldoFinalFornecedorCredito));
                    }


                    else {
                        SaldoFinalFornecedorDebito = SomaDebitoFornecedor - SomaCreditoBanco;
                        Balancete.add(new BalanceteEntidade("Fornecedor", CreditoFornecedor, DebitoFornecedor, SaldoFinalFornecedorDebito));
                    }



            }

            else {
                while(FornecedorPago.next()) {
                    DebitoFornecedorPago = Integer.parseInt(FornecedorPago.getString("DEBITO"));
                    CreditoFornecedorPago = Integer.parseInt(FornecedorPago.getString("CREDITO"));
                }

                Balancete.add(new BalanceteEntidade("Fornecedor", CreditoFornecedorPago, DebitoFornecedorPago, 0));
            }



            int SaldoTotalCredito = SaldoFinalCreditoFatos + SaldoFinalCreditoBanco + SaldoFinalFinanciamentoCredito + SaldoFinalInvestimentoCredito + SaldoFinalFornecedorCredito + SaldoFinalCaixaCredito;
            int SaldoTotalDebito = SaldoFinalDebitoFatos + SaldoFinalDebitoBanco + SaldoFinalFinanciamentoDebito + SaldoFinalFornecedorDebito + SaldoFinalCaixaDebito;
            int SaldoUsuario = SaldoTotalDebito - SaldoTotalCredito;


            Balancete.add(new BalanceteEntidade("SaldoFinal", SaldoTotalCredito, SaldoTotalDebito, SaldoUsuario));



        }

        catch (SQLException e) {
            throw new RuntimeException(e);
        }

        finally {
            Conexao.FecharConexao();
        }

        return Balancete;
    }
}


    /*

    public List<BalanceteEntidade> CaixaZerado() {
        CaixaZerado = true;
        int SaldoFinalDebitoCaixa = 0;
        int SaldoFinalCreditoCaixa = 0;
        try {
            Conexao.AbrirConexao();
            String sql2 = "SELECT * FROM CAIXA ORDER BY ID DESC LIMIT 1;";
            String sql10 = "TRUNCATE CAIXA";
            ResultSet Caixa = Conexao.getConexao().createStatement().executeQuery(sql2);
            while (Caixa.next()) {


                int DebitoCaixa = Integer.parseInt(Caixa.getString("DEBITO"));
                int CreditoCaixa = Integer.parseInt(Caixa.getString("CREDITO"));
                Balancete.add(new BalanceteEntidade("Caixa", CreditoCaixa, DebitoCaixa, 0));
                SaldoFinalDebitoCaixa += DebitoCaixa;
                SaldoFinalCreditoCaixa += CreditoCaixa;
                int TruncarCaixa = Conexao.getConexao().createStatement().executeUpdate(sql10);


            }


        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return Balancete;
    }

    public List<BalanceteEntidade> FinanciamentoPago() {
        PagamentoFinanciamento = true;
        try {
            Conexao.AbrirConexao();
            String sql6 = "SELECT * FROM FINANCIAMENTO ORDER BY ID DESC LIMIT 1";
            String sql7 = "TRUNCATE FINANCIAMENTO";
            ResultSet FinanciamentoPago = Conexao.getConexao().createStatement().executeQuery(sql6);
            while (FinanciamentoPago.next()) {
                int DebitoFinanciamento = Integer.parseInt(FinanciamentoPago.getString("DEBITO"));
                int CreditoFinanciamento = Integer.parseInt(FinanciamentoPago.getString("CREDITO"));
                int SaldoFinalFinanciamento = 0;
                Balancete.add(new BalanceteEntidade("Financiamento", DebitoFinanciamento, CreditoFinanciamento, SaldoFinalFinanciamento));
                int TruncateFinanciamento = Conexao.getConexao().createStatement().executeUpdate(sql7);
            }

        }

        catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return Balancete;
    }

    public List<BalanceteEntidade> FornecedorPago() {
        PagouFornecedor = true;
        try {
            Conexao.AbrirConexao();
            String sql9 = "SELECT * FROM FORNECEDOR ORDER BY ID DESC LIMIT 1";
            String sql10 = "TRUNCATE FORNECEDOR";
            ResultSet FornecedorPago = Conexao.getConexao().createStatement().executeQuery(sql9);

            while (FornecedorPago.next()) {
                int DebitoFornecedor = Integer.parseInt(FornecedorPago.getString("DEBITO"));
                int CreditoFornecedor = Integer.parseInt(FornecedorPago.getString("CREDITO"));
                Balancete.add(new BalanceteEntidade("Fornecedor", CreditoFornecedor, DebitoFornecedor, 0));
                int TruncateFornecedor = Conexao.getConexao().createStatement().executeUpdate(sql10);
            }
        }


        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Balancete;
    }

}

     */








        //chave final classe