package Model.BancoDeDados;

import javax.xml.crypto.Data;
import java.sql.SQLException;

public class FinanciamentoBD {

    private ConexaoBD Conexao = new ConexaoBD();

    public void RegistrarFinanciamentoCredito(String CodFato, int Valor, String Detalhes, String Data) throws SQLException {
        try {
            Conexao.AbrirConexao();
            String SQL = String.format("INSERT INTO FINANCIAMENTO(COD_FATO, CREDITO, DEBITO, DETALHES, DATA) VALUES('%s', '%d', '%d', '%s', '%s')", CodFato, Valor, 0, Detalhes, Data);
            int ValorCaixa = Conexao.getConexao().createStatement().executeUpdate(SQL);
            System.out.println("Boa cadastrou no BD, credito");
        }

        catch (SQLException e) {
            throw new RuntimeException(e);
        }

        finally {
            Conexao.FecharConexao();
        }

    }

    public void RegistrarFinanciamentoDebito(String CodFato, int Valor, String Detalhes, String Data) throws SQLException {
        try {
            Conexao.AbrirConexao();
            String SQL = String.format("INSERT INTO FINANCIAMENTO(COD_FATO, CREDITO, DEBITO, DETALHES, DATA) VALUES('%s', '%d', '%d', '%s', '%s')", CodFato, 0, Valor, Detalhes, Data);
            int ValorCaixa = Conexao.getConexao().createStatement().executeUpdate(SQL);
            System.out.println("Boa cadastrou no BD, Debito-Financiamento");
        }

        catch (SQLException e) {
            throw new RuntimeException(e);
        }

        finally {
            Conexao.FecharConexao();
        }

    }
}
