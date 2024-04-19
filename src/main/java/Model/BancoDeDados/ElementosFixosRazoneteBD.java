package Model.BancoDeDados;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ElementosFixosRazoneteBD {
    //Elementos Fixos Razonete = Caixa, Financiamento, Investimento
    private ConexaoBD Conexao = new ConexaoBD();






    /*

    public void RegistrarDeposito() throws SQLException {

        int SomaDebitoCaixa = 0;
        int SomaCreditoCaixa = 0;
        int SaldoFinalCaixa = 0;
        int SaldoBanco = 0;
        try {
            Conexao.AbrirConexao();
            String sql = "SELECT * FROM CAIXA";
            ResultSet Caixa = Conexao.getConexao().createStatement().executeQuery(sql);
            while (Caixa.next()) {
                int DebitoCaixa = Integer.parseInt(Caixa.getString("DEBITO"));
                int CreditoCaixa = Integer.parseInt(Caixa.getString("CREDITO"));
                SomaDebitoCaixa += DebitoCaixa;
                SomaCreditoCaixa += CreditoCaixa;



            }

            if(SomaDebitoCaixa > SomaCreditoCaixa) {
                SaldoBanco = SomaDebitoCaixa - SomaCreditoCaixa;
                SaldoFinalCaixa = SomaDebitoCaixa;
            }

            else {
                SaldoFinalCaixa = SomaCreditoCaixa;
            }

        }

        catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            Conexao.AbrirConexao();
            String sql = String.format("INSERT INTO CAIXA(CREDITO, DEBITO) VALUES('%d', '%d')", SaldoFinalCaixa, SaldoFinalCaixa);
            int RegistrarSaldo = Conexao.getConexao().createStatement().executeUpdate(sql);
            System.out.println("Deposito Registrado no Banco");


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            Conexao.AbrirConexao();
            String SQL = String.format("INSERT INTO BANCO(CREDITO, DEBITO) VALUES('%d', '%d')", 0, SaldoBanco);
            int ValorCaixa = Conexao.getConexao().createStatement().executeUpdate(SQL);
            System.out.println("Boa cadastrou no BD, debito");
        }

        catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }




     */



    public void FornecedorCredito(int Valor) {
        try {
            Conexao.AbrirConexao();
            String SQL = String.format("INSERT INTO FORNECEDOR(CREDITO, DEBITO) VALUES('%d', '%d')", Valor, 0);
            int ValorCaixa = Conexao.getConexao().createStatement().executeUpdate(SQL);
            System.out.println("Boa cadastrou no BD, debito");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void FornecedorDebito(int Valor) {
        try {
            Conexao.AbrirConexao();
            String SQL = String.format("INSERT INTO FORNECEDOR(CREDITO, DEBITO) VALUES('%d', '%d')", 0, Valor);
            int ValorCaixa = Conexao.getConexao().createStatement().executeUpdate(SQL);
            System.out.println("Boa cadastrou no BD, debito");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void BancoCredito(int Valor) {
        try {
            Conexao.AbrirConexao();
            String SQL = String.format("INSERT INTO BANCO(CREDITO, DEBITO) VALUES('%d', '%d')", Valor, 0);
            int ValorCaixa = Conexao.getConexao().createStatement().executeUpdate(SQL);
            System.out.println("Boa cadastrou no BD, debito");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }





/*
    public void PagarFinanciamento() {

            int SubtracaoCreditoDebito = 0;
            int SaldoFinanciamento = 0;


           try {
               Conexao.AbrirConexao();
               String sql = "SELECT * FROM INVESTIMENTO";
               ResultSet Financiamento = Conexao.getConexao().createStatement().executeQuery(sql);
               while(Financiamento.next()) {
                   int DebitoFinanciamento = Integer.parseInt(Financiamento.getString("DEBITO"));
                   int CreditoFinanciamento = Integer.parseInt(Financiamento.getString("CREDITO"));
                   if(CreditoFinanciamento > DebitoFinanciamento) {
                       SubtracaoCreditoDebito = CreditoFinanciamento - DebitoFinanciamento;
                       SaldoFinanciamento = SubtracaoCreditoDebito + DebitoFinanciamento; //soma no menor ou seja deb = 15.000 cre = 30.000 quanto falta 15.000
                       // Sub = 30 - 15 = 15;
                       // Saldo = Deb (15) + (15) = 30;

                   }

                   else {
                       SubtracaoCreditoDebito = DebitoFinanciamento - CreditoFinanciamento;
                       SaldoFinanciamento = SubtracaoCreditoDebito + CreditoFinanciamento; //soma no menor
                   }

               }


           }

           catch (SQLException e) {
               throw new RuntimeException(e);
           }

        try {
            Conexao.AbrirConexao();
            String SQL = String.format("INSERT INTO FINANCIAMENTO(CREDITO, DEBITO) VALUES('%d', '%d')", SaldoFinanciamento, SaldoFinanciamento);
            int ValorFinanciamento = Conexao.getConexao().createStatement().executeUpdate(SQL);
            System.out.println("Boa cadastrou no BD, Debito-Financiamento");
        }

        catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            Conexao.AbrirConexao();
            String SQL = String.format("INSERT INTO CAIXA(CREDITO, DEBITO) VALUES('%d', '%d')", SaldoFinanciamento, 0);
            int ValorFinanciamento = Conexao.getConexao().createStatement().executeUpdate(SQL);
            System.out.println("Boa cadastrou no BD, Debito-Financiamento");
        }


        catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public void PagarFornecedor() {
        int SaldoFinalFornecedor = 0;

        try {
            Conexao.AbrirConexao();
            String sql7 = "SELECT * FROM FORNECEDOR";
            ResultSet Fornecedor = Conexao.getConexao().createStatement().executeQuery(sql7);
            while(Fornecedor.next()) {
                int DebitoFornecedor = Integer.parseInt(Fornecedor.getString("DEBITO"));
                int CreditoFornecedor = Integer.parseInt(Fornecedor.getString("CREDITO"));
                if(CreditoFornecedor > DebitoFornecedor) {
                    SaldoFinalFornecedor = CreditoFornecedor - DebitoFornecedor;
                }

                else {
                    SaldoFinalFornecedor = CreditoFornecedor - DebitoFornecedor;
                }


            }
        }

        catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            Conexao.AbrirConexao();
            String SQL = String.format("INSERT INTO FORNECEDOR(CREDITO, DEBITO) VALUES('%d', '%d')", SaldoFinalFornecedor, SaldoFinalFornecedor);
            int ValorCaixa = Conexao.getConexao().createStatement().executeUpdate(SQL);
            System.out.println("Boa cadastrou no BD, debito");
        }

        catch (SQLException e) {
            throw new RuntimeException(e);
        }


        try {
            Conexao.AbrirConexao();
            String SQL = String.format("INSERT INTO CAIXA(CREDITO, DEBITO) VALUES('%d', '%d')", SaldoFinalFornecedor, 0);
            int ValorFinanciamento = Conexao.getConexao().createStatement().executeUpdate(SQL);
            System.out.println("Boa cadastrou no BD, Debito-Financiamento");
        }


        catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

 */


}






