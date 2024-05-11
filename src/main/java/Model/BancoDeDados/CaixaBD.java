package Model.BancoDeDados;

import javax.xml.crypto.Data;
import java.sql.SQLException;

public class CaixaBD {
    private ConexaoBD Conexao = new ConexaoBD();

    public void RegistroNoCaixaDebito(String CodFato, int Valor, String Detalhes, String Data) throws SQLException {
        try {
            Conexao.AbrirConexao();
            String SQL = String.format("INSERT INTO CAIXA(COD_FATO, CREDITO, DEBITO, DETALHES, DATA) VALUES('%s', '%d', '%d', '%s', '%s')", CodFato, 0, Valor, Detalhes, Data);
            int ValorCaixa = Conexao.getConexao().createStatement().executeUpdate(SQL);

        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }

        finally {
            Conexao.FecharConexao();
        }
    }

    public void RegistroNoCaixaCredito(String CodFato, int Valor, String Detalhes, String Data) throws SQLException {
        try {
            Conexao.AbrirConexao();
            String SQL = String.format("INSERT INTO CAIXA(COD_FATO, CREDITO, DEBITO, DETALHES, DATA) VALUES('%s', '%d', '%d', '%s', '%s')", CodFato, Valor, 0, Detalhes, Data);
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
