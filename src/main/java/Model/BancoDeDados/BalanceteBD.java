package Model.BancoDeDados;

import Model.Entidade.BalanceteEntidade;
import com.example.MaestroContabilidade.FatoController;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BalanceteBD {
    private ConexaoBD Conexao = new ConexaoBD();
    public List<BalanceteEntidade> Balancete = new ArrayList<>();

    public boolean CaixaZerado;

    public boolean PagamentoFinanciamento;

    public boolean PagouFornecedor;


    public List<BalanceteEntidade> SELECTBalancete() throws SQLException {


        int SaldoFinalDebitoFatos = 0;
        int SaldoFinalCreditoFatos = 0;
        ElementosFixosRazoneteBD ObjetosFixos = new ElementosFixosRazoneteBD();


        try {
            Conexao.AbrirConexao();
            // dá o select no database
            String sql1 = "SELECT DETALHES_FATO, VALOR_FATO, TIPODEOPERACAO_FATO FROM FATOSGERAL";//SELECT CREDITO, DEBITO FROM CAIXA -> Caixa
            String sql3 = "SELECT CREDITO, DEBITO FROM FINANCIAMENTO";
            String sql4 = "SELECT * FROM INVESTIMENTO";
            String sql5 = "SELECT * FROM BANCO";
            String sql7 = "SELECT * FROM FORNECEDOR";
            String sql8 = "SELECT * FROM CAIXA";
            ResultSet Fatos = Conexao.getConexao().createStatement().executeQuery(sql1);
            ResultSet Financiamento = Conexao.getConexao().createStatement().executeQuery(sql3);
            ResultSet Investimento = Conexao.getConexao().createStatement().executeQuery(sql4);
            ResultSet Banco = Conexao.getConexao().createStatement().executeQuery(sql5);
            ResultSet Fornecedor = Conexao.getConexao().createStatement().executeQuery(sql7);
            ResultSet CaixaSemDeposito = Conexao.getConexao().createStatement().executeQuery(sql8);


            //while(S.next()) quer dizer que ele vai percorrer as colunas do banco enquanto não for null

            while (Fatos.next()) {
                String Detalhes = Fatos.getString("DETALHES_FATO");
                int valor = Integer.parseInt(Fatos.getString("VALOR_FATO"));
                String TipoOp = Fatos.getString("TIPODEOPERACAO_FATO");


                if (TipoOp.equals("Debito")) {  //se a operação for debito
                    Balancete.add(new BalanceteEntidade(Detalhes, 0, valor, valor));
                    SaldoFinalDebitoFatos += valor;
                }

                else {
                    Balancete.add(new BalanceteEntidade(Detalhes, valor, 0, valor));
                    SaldoFinalCreditoFatos += valor;
                }


            }

            //Variaveis de Soma Caixa
            int SaldoFinalDebitoCaixa = 0;
            int SaldoFinalCreditoCaixa = 0;

            //Variaveis de Soma Financiamento
            int SomaCreditoFinanciamento = 0;
            int SomaDebitoFinanciamento = 0;
            int SaldoFinalFinanciamentoCredito = 0;
            int SaldoFinalFinanciamentoDebito = 0;

            //Variaveis de Soma Investimento
            int SomaCreditoInvestimento = 0;
            int SaldoFinalInvestimentoCredito = 0;

            //Variaveis de SomaBanco
            int SomaCreditoBanco = 0;
            int SomaDebitoBanco = 0;
            int SaldoFinalCreditoBanco = 0;
            int SaldoFinalDebitoBanco = 0;


            //Variaveis de Fornecedor
            int SaldoFinalFornecedorDebito = 0;
            int SaldoFinalFornecedorCredito = 0;


            if (CaixaZerado == false) {
                while (CaixaSemDeposito.next()) {
                    int DebitoCaixa = Integer.parseInt(CaixaSemDeposito.getString("DEBITO"));
                    int CreditoCaixa = Integer.parseInt(CaixaSemDeposito.getString("CREDITO"));
                    int SomaDebitoCaixa = 0;
                    int SomaCreditoCaixa = 0;
                    SomaDebitoCaixa += DebitoCaixa;
                    SomaCreditoCaixa += CreditoCaixa;
                    if (SomaDebitoCaixa > SomaCreditoCaixa) {
                        SaldoFinalDebitoCaixa = SomaDebitoCaixa - SomaCreditoCaixa;
                        Balancete.add(new BalanceteEntidade("Caixa", SomaCreditoCaixa, SomaDebitoCaixa, SaldoFinalDebitoCaixa));
                    }
                    else {
                        SaldoFinalCreditoCaixa = SomaCreditoCaixa - SomaDebitoCaixa;
                        Balancete.add(new BalanceteEntidade("Caixa", SomaCreditoCaixa, SomaDebitoCaixa, SaldoFinalCreditoBanco));
                    }


                }
            }


            if (PagamentoFinanciamento == false) {
                while (Financiamento.next()) {

                    //if(PagFin == true): break;
                    //
                    int DebitoFinanciamento = Integer.parseInt(Financiamento.getString("DEBITO"));
                    int CreditoFinanciamento = Integer.parseInt(Financiamento.getString("CREDITO"));
                    SomaCreditoFinanciamento += CreditoFinanciamento;
                    SomaDebitoFinanciamento += DebitoFinanciamento;
                    if (SomaCreditoFinanciamento > SomaDebitoFinanciamento) {
                        SaldoFinalFinanciamentoCredito = SomaCreditoFinanciamento - SomaDebitoFinanciamento;
                        Balancete.add(new BalanceteEntidade("Financiamento", SomaCreditoFinanciamento, SomaDebitoFinanciamento, SaldoFinalFinanciamentoCredito));

                    } else {
                        SaldoFinalFinanciamentoDebito = SomaDebitoFinanciamento - SomaCreditoFinanciamento;
                        Balancete.add(new BalanceteEntidade("Financiamento", SomaCreditoFinanciamento, SomaDebitoFinanciamento, SaldoFinalFinanciamentoCredito));
                    }

                }

            }


            while (Investimento.next()) {
                int InvestimentoCredito = Integer.parseInt(Investimento.getString("CREDITO"));
                SaldoFinalInvestimentoCredito += InvestimentoCredito;
                Balancete.add(new BalanceteEntidade("Investimento", InvestimentoCredito, 0, SaldoFinalInvestimentoCredito));


            }


            while (Banco.next()) {
                int DebitoBanco = Integer.parseInt(Banco.getString("DEBITO"));
                int CreditoBanco = Integer.parseInt(Banco.getString("CREDITO"));
                SomaCreditoBanco += CreditoBanco;
                SomaDebitoBanco += DebitoBanco;
                if (SomaCreditoBanco > SomaDebitoBanco) {
                    SaldoFinalCreditoBanco = SomaCreditoBanco - SomaDebitoBanco;
                    Balancete.add(new BalanceteEntidade("Banco", CreditoBanco, DebitoBanco, SaldoFinalCreditoBanco));

                } else {
                    SaldoFinalDebitoBanco = SomaDebitoBanco - SomaCreditoBanco;
                    Balancete.add(new BalanceteEntidade("Banco", CreditoBanco, DebitoBanco, SaldoFinalDebitoBanco));
                }


            }



                if(PagouFornecedor == false) {

                    while (Fornecedor.next()) {
                        int DebitoFornecedor = Integer.parseInt(Fornecedor.getString("DEBITO"));
                        int CreditoFornecedor = Integer.parseInt(Fornecedor.getString("CREDITO"));
                        if (CreditoFornecedor > DebitoFornecedor) {
                            SaldoFinalFornecedorCredito = CreditoFornecedor - DebitoFornecedor;
                            Balancete.add(new BalanceteEntidade("Fornecedor", CreditoFornecedor, DebitoFornecedor, SaldoFinalFornecedorCredito));
                        } else {
                            SaldoFinalFornecedorDebito = CreditoFornecedor - DebitoFornecedor;
                            Balancete.add(new BalanceteEntidade("Fornecedor", CreditoFornecedor, DebitoFornecedor, SaldoFinalFornecedorDebito));
                        }


                    }
                }



            //Soma credito Caixa e Soma Debito Caixa são os unicos de diferentes de SaldoFInal
            int SaldoTotalCredito = SaldoFinalCreditoFatos + SaldoFinalCreditoBanco + SaldoFinalFinanciamentoCredito + SaldoFinalInvestimentoCredito + SaldoFinalFornecedorCredito;
            int SaldoTotalDebito = SaldoFinalDebitoFatos + SaldoFinalDebitoBanco + SaldoFinalFinanciamentoDebito + SaldoFinalFornecedorDebito;
            int SaldoUsuario = SaldoTotalCredito - SaldoTotalDebito;


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








        //chave final classe