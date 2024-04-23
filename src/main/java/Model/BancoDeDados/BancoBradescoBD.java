package Model.BancoDeDados;

import java.sql.SQLException;

public class BancoBradescoBD {

    private ConexaoBD Conexao = new ConexaoBD();


    public void BancoCredito(int Valor, String Detalhes) {
        try {
            Conexao.AbrirConexao();
            String SQL = String.format("INSERT INTO BANCO(DETALHES, CREDITO, DEBITO) VALUES('%s', %d', '%d')", Detalhes, Valor, 0);
            int ValorCaixa = Conexao.getConexao().createStatement().executeUpdate(SQL);
            System.out.println("Boa cadastrou no BD, debito");
        }

        catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}
