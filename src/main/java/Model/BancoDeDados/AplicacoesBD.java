package Model.BancoDeDados;

import java.sql.SQLException;

public class AplicacoesBD {
    private ConexaoBD Conexao = new ConexaoBD();

    public void RegistroAplicacoesDebito(String CodFato, int Valor, String Detalhes, String Data) throws SQLException {
        try {
            Conexao.AbrirConexao();
            String SQL = String.format("INSERT INTO APLICACOES(COD_FATO, CREDITO, DEBITO, DETALHES, DATA) VALUES('%s', '%d', '%d', '%s', '%s')", CodFato, 0, Valor, Detalhes, Data);
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
