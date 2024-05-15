package Model.BancoDeDados;

import Model.BancoDeDados.ConexaoBD;
import Model.Entidade.BalanceteEntidade;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DepositoNoBancoBD {

    private ConexaoBD Conexao = new ConexaoBD();

    public void RegistrarDeposito() throws SQLException {



        int SomaDebitoCaixa = 0;
        int SomaCreditoCaixa = 0;
        int SaldoFinalCaixa = 0;
        int SaldoBanco = 0;
        try {
            Conexao.AbrirConexao();
            String sql = "SELECT CAIXA_CREDITO, CAIXA_DEBITO FROM RAZONETE";
            ResultSet Caixa = Conexao.getConexao().createStatement().executeQuery(sql);
            while (Caixa.next()) {
                int DebitoCaixa = Integer.parseInt(Caixa.getString("CAIXA_CREDITO"));
                int CreditoCaixa = Integer.parseInt(Caixa.getString("CAIXA_DEBITO"));
                SomaDebitoCaixa += DebitoCaixa;
                SomaCreditoCaixa += CreditoCaixa;



            }

            if(SomaDebitoCaixa > SomaCreditoCaixa) {
                SaldoBanco = SomaDebitoCaixa - SomaCreditoCaixa;
                SaldoFinalCaixa = SaldoBanco;
            }

            else {
                SaldoBanco = SomaCreditoCaixa - SomaDebitoCaixa;
                SaldoFinalCaixa = SaldoBanco;
            }

        }

        catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            Conexao.AbrirConexao();
            String sql = String.format("INSERT INTO RAZONETE(CAIXA_ZERADO) VALUES('%d')", SaldoFinalCaixa);
            int RegistrarSaldo = Conexao.getConexao().createStatement().executeUpdate(sql);
            System.out.println("Deposito Registrado no Banco");


        }

        catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            Conexao.AbrirConexao();
            String SQL = String.format("INSERT INTO RAZONETE(BANCO_DEBITO) VALUES('%d')", SaldoBanco);
            int ValorCaixa = Conexao.getConexao().createStatement().executeUpdate(SQL);

        }

        catch (SQLException e) {
            throw new RuntimeException(e);
        }



        try {
            Conexao.AbrirConexao();
            String SQL = "UPDATE RAZONETE SET CAIXA_CREDITO = 0";
            String SQL2 = "UPDATE RAZONETE SET CAIXA_DEBITO = 0";
            int UpdateCaixaCredito = Conexao.getConexao().createStatement().executeUpdate(SQL);
            int UpdateCaixaDebito = Conexao.getConexao().createStatement().executeUpdate(SQL2);
        }

        catch (SQLException e) {
            throw new RuntimeException(e);
        }


        finally {
            Conexao.FecharConexao();
        }

    }


}
