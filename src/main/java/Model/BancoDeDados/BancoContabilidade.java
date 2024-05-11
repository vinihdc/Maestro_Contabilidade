package Model.BancoDeDados;

import java.sql.SQLException;

public class BancoContabilidade {

    private ConexaoBD Conexao = new ConexaoBD();


    public void BancoCredito(String CodFato, int Valor, String Detalhes, String Data) {
        try {
            Conexao.AbrirConexao();
            String SQL = String.format("INSERT INTO BANCO(COD_FATO, CREDITO, DEBITO, DETALHES, DATA) VALUES('%s', '%d', '%d', '%s', '%s')", CodFato, Valor, 0, Detalhes, Data);
            int ValorCaixa = Conexao.getConexao().createStatement().executeUpdate(SQL);
            System.out.println("Boa cadastrou no BD, debito");
        }

        catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}
