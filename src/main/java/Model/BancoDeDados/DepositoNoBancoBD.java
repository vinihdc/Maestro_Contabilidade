package Model.BancoDeDados;

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



        try {
            Conexao.AbrirConexao();
            String SQL = "TRUNCATE CAIXA";
            int ValorCaixa = Conexao.getConexao().createStatement().executeUpdate(SQL);
            System.out.println("Boa cadastrou no BD, debito");
        }

        catch (SQLException e) {
            throw new RuntimeException(e);
        }




    }


}
