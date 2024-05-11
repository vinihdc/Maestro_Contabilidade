package Model.BancoDeDados;

import Model.Entidade.BalanceteEntidade;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class FornecedorPagoBD {

    private ConexaoBD Conexao = new ConexaoBD();

    CaixaBD RegistrarNoCaixa = new CaixaBD();


    public void FornecedorPago(String Data) throws SQLException {

        int DebitoFornecedor = 0;
        int CreditoFornecedor = 0;
        int SomaDebitoFornecedor = 0;
        int SomaCreditoFornecedor = 0;
        int Subtracao = 0;
        int SaldoFinal = 0;

        try {
            Conexao.AbrirConexao();
            String sql9 = "SELECT * FROM FORNECEDOR ORDER BY ID DESC LIMIT 1";
            ResultSet FornecedorPago = Conexao.getConexao().createStatement().executeQuery(sql9);

            while (FornecedorPago.next()) {
                DebitoFornecedor = Integer.parseInt(FornecedorPago.getString("DEBITO"));
                CreditoFornecedor = Integer.parseInt(FornecedorPago.getString("CREDITO"));
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
            String InsertFornecedorPago = String.format("INSERT INTO FORNECEDORPAGO(CREDITO, DEBITO, DATA) VALUES('%d', '%d', '%s')", SaldoFinal, SaldoFinal, Data);
            int TransferirDadosTabela = Conexao.getConexao().createStatement().executeUpdate(InsertFornecedorPago);

        }

        catch (SQLException e) {
            throw new RuntimeException(e);
        }


       // RegistrarNoCaixa.RegistroNoCaixaCredito(SaldoFinal, "Pagamento Fornecedor ");

        try {
            Conexao.AbrirConexao();
            String Truncate = "TRUNCATE FORNECEDOR";
            int LimparTabela = Conexao.getConexao().createStatement().executeUpdate(Truncate);
        }


        catch (SQLException e) {
            throw new RuntimeException(e);
        }



        finally {
            Conexao.FecharConexao();
        }
    }



}
