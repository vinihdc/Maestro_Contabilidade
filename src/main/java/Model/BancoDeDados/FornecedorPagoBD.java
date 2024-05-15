package Model.BancoDeDados;

import Model.Entidade.BalanceteEntidade;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class FornecedorPagoBD {

    private ConexaoBD Conexao = new ConexaoBD();


    public void FornecedorPago() throws SQLException {

        int DebitoFornecedor = 0;
        int CreditoFornecedor = 0;
        int SomaDebitoFornecedor = 0;
        int SomaCreditoFornecedor = 0;
        int Subtracao = 0;
        int SaldoFinal = 0;

        try {
            Conexao.AbrirConexao();
            String sql9 = "SELECT FORNECEDOR_CREDITO, FORNECEDOR_DEBITO FROM RAZONETE ORDER BY ID DESC LIMIT 1";
            ResultSet FornecedorPago = Conexao.getConexao().createStatement().executeQuery(sql9);

            while (FornecedorPago.next()) {
                DebitoFornecedor = Integer.parseInt(FornecedorPago.getString("FORNECEDOR_DEBITO"));
                CreditoFornecedor = Integer.parseInt(FornecedorPago.getString("FORNECEDOR_CREDITO"));
                SomaDebitoFornecedor += DebitoFornecedor;
                SomaCreditoFornecedor += CreditoFornecedor;
                if(SomaDebitoFornecedor > SomaCreditoFornecedor) {
                    Subtracao = SomaDebitoFornecedor - SomaCreditoFornecedor;
                    SaldoFinal = SomaCreditoFornecedor + Subtracao;
            }

                else {
                    Subtracao = SomaCreditoFornecedor - SomaDebitoFornecedor;
                    SaldoFinal = SomaDebitoFornecedor + Subtracao;
                }



        }

    }


        catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            Conexao.AbrirConexao();
            String InsertFornecedorPago = String.format("INSERT INTO RAZONETE(FORNECEDORPAGO) VALUES('%d')", SaldoFinal);
            int TransferirDadosTabela = Conexao.getConexao().createStatement().executeUpdate(InsertFornecedorPago);

        }

        catch (SQLException e) {
            throw new RuntimeException(e);
        }



        try {
            Conexao.AbrirConexao();
            String SQL = String.format("INSERT INTO RAZONETE(CAIXA_CREDITO) VALUES('%d')", SaldoFinal);
            int MandarProCaixa = Conexao.getConexao().createStatement().executeUpdate(SQL);
        }


        catch (SQLException e) {
            throw new RuntimeException(e);
        }


        try {
            Conexao.AbrirConexao();
            String SQL = "UPDATE RAZONETE SET FORNECEDOR_CREDITO = 0";
            String SQL2 = "UPDATE RAZONETE SET FORNECEDOR_CDEBITO = 0";
            int UpdateFornecedorCredito = Conexao.getConexao().createStatement().executeUpdate(SQL);
            int UpdateFornecedorDebito = Conexao.getConexao().createStatement().executeUpdate(SQL2);
        }

        catch (SQLException e) {
            throw new RuntimeException(e);
        }



        finally {
            Conexao.FecharConexao();
        }
    }



}
