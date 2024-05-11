package Model.BancoDeDados;

import Model.Entidade.BalanceteEntidade;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DepositoNoBancoBD {

    private ConexaoBD Conexao = new ConexaoBD();

    public void RegistrarDeposito(String CodFato, String Detalhes, String Data) throws SQLException {



        int SomaDebitoCaixa = 0;
        int SomaCreditoCaixa = 0;
        int SaldoFinalCaixa = 0;
        int SaldoBanco = 0;
        try {
            Conexao.AbrirConexao();
            String sql = "SELECT CREDITO, DEBITO FROM CAIXA";
            ResultSet Caixa = Conexao.getConexao().createStatement().executeQuery(sql);
            while (Caixa.next()) {
                int DebitoCaixa = Integer.parseInt(Caixa.getString("DEBITO"));
                int CreditoCaixa = Integer.parseInt(Caixa.getString("CREDITO"));
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
            String sql = String.format("INSERT INTO CAIXAZERADO(CREDITO, DEBITO, DETALHES, DATA) VALUES('%d', '%d', '%s', '%s')", SaldoFinalCaixa, SaldoFinalCaixa, Detalhes, Data);
            int RegistrarSaldo = Conexao.getConexao().createStatement().executeUpdate(sql);
            System.out.println("Deposito Registrado no Banco");


        }

        catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            Conexao.AbrirConexao();
            String SQL = String.format("INSERT INTO BANCO(COD_FATO, CREDITO, DEBITO, DETALHES, DATA) VALUES('%s', '%d', '%d', '%s', '%s')", CodFato, 0, SaldoBanco, Detalhes, Data);
            int ValorCaixa = Conexao.getConexao().createStatement().executeUpdate(SQL);

        }

        catch (SQLException e) {
            throw new RuntimeException(e);
        }



        try {
            Conexao.AbrirConexao();
            String SQL = "TRUNCATE CAIXA";
            int ValorCaixa = Conexao.getConexao().createStatement().executeUpdate(SQL);
        }

        catch (SQLException e) {
            throw new RuntimeException(e);
        }


        finally {
            Conexao.FecharConexao();
        }

    }


}
