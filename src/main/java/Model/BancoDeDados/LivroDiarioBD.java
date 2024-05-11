package Model.BancoDeDados;

import Model.Entidade.DiarioEntidade;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LivroDiarioBD {

    private ConexaoBD Conexao = new ConexaoBD();

    List<DiarioEntidade> DiarioList = new ArrayList<>();

    public List<DiarioEntidade> SELECTDIARIO() throws SQLException {
        try {
            Conexao.AbrirConexao();
            String SQL = "SELECT COD_FATO, DATA_FATO, DETALHES, DEBITO, CREDITO, VALOR_FATO FROM DIARIO";
            ResultSet LivroDiario = Conexao.getConexao().createStatement().executeQuery(SQL);
            while(LivroDiario.next()) {
                String CodFato = LivroDiario.getString("COD_FATO");
                String DataFato = LivroDiario.getString("DATA_FATO");
                String Detalhes = LivroDiario.getString("DETALHES");
                String Debito = LivroDiario.getString("DEBITO");
                String Credito = LivroDiario.getString("CREDITO");
                int Valor = Integer.parseInt(LivroDiario.getString("VALOR_FATO"));
                DiarioList.add(new DiarioEntidade(CodFato, DataFato, Detalhes, Debito, Credito, Valor));
            }
        }



        catch (SQLException e) {
            throw new RuntimeException(e);
        }

        finally {
            Conexao.FecharConexao();
        }

        return DiarioList;
    }
}
