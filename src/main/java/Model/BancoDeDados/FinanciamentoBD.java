package Model.BancoDeDados;

import java.sql.SQLException;

public class FinanciamentoBD {

    private ConexaoBD Conexao = new ConexaoBD();

    public void RegistrarFinanciamentoCredito(int Valor, String Detalhes) throws SQLException {
        try {
            Conexao.AbrirConexao();
            String SQL = String.format("INSERT INTO FINANCIAMENTO(CREDITO, DEBITO) VALUES('%s', %d', '%d')", Detalhes, Valor, 0);
            int ValorCaixa = Conexao.getConexao().createStatement().executeUpdate(SQL);
            System.out.println("Boa cadastrou no BD, credito");
        }

        catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public void RegistrarFinanciamentoDebito(int Valor, String Detalhes) throws SQLException {
        try {
            Conexao.AbrirConexao();
            String SQL = String.format("INSERT INTO FINANCIAMENTO(CREDITO, DEBITO) VALUES('%s', %d', '%d')", Detalhes, 0, Valor);
            int ValorCaixa = Conexao.getConexao().createStatement().executeUpdate(SQL);
            System.out.println("Boa cadastrou no BD, Debito-Financiamento");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}
