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
        int SaldoBanco = 0;
        try {
            Conexao.AbrirConexao();
            String sql = "SELECT CAIXA_CREDITO, CAIXA_DEBITO FROM RAZONETE";
            ResultSet Caixa = Conexao.getConexao().createStatement().executeQuery(sql);
            while (Caixa.next()) {
                int DebitoCaixa = Integer.parseInt(Caixa.getString("CAIXA_DEBITO"));
                int CreditoCaixa = Integer.parseInt(Caixa.getString("CAIXA_CREDITO"));
                SomaDebitoCaixa += DebitoCaixa;
                SomaCreditoCaixa += CreditoCaixa;



            }

            if(SomaDebitoCaixa > SomaCreditoCaixa) {
                SaldoBanco = SomaDebitoCaixa - SomaCreditoCaixa;
            }

            else {
                SaldoBanco = SomaCreditoCaixa - SomaDebitoCaixa;

            }

        }

        catch (SQLException e) {
            //throw new RuntimeException(e);
        }

        try {
            Conexao.AbrirConexao();
            String sql = String.format("INSERT INTO RAZONETE(CAIXA_ZERADO,BANCO_DEBITO) VALUES('%d', '%d')", SaldoBanco, SaldoBanco);
            int RegistrarSaldo = Conexao.getConexao().createStatement().executeUpdate(sql);


        }

        catch (SQLException e) {
            //throw new RuntimeException(e);
        }





        try {
            Conexao.AbrirConexao();
            String UpdateSemWhere = "SET SQL_SAFE_UPDATES = 0"; //Desabilitar o modo seguro
            String SQL = "UPDATE RAZONETE SET CAIXA_CREDITO = 0, CAIXA_DEBITO = 0;";
            int DesabilitarModoSeguro = Conexao.getConexao().createStatement().executeUpdate(UpdateSemWhere);
            int UpdateCaixaCredito = Conexao.getConexao().createStatement().executeUpdate(SQL);

        }

        catch (SQLException e) {
            //throw new RuntimeException(e);
        }


        finally {
            Conexao.FecharConexao();
        }

    }


}
