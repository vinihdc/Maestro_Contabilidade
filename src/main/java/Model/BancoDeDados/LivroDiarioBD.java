package Model.BancoDeDados;

import Model.Entidade.DiarioEntidade;
import lombok.Getter;
import lombok.Setter;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LivroDiarioBD {

    private ConexaoBD Conexao = new ConexaoBD();

    List<DiarioEntidade> DiarioList = new ArrayList<>();

    @Setter
    @Getter
    private boolean DiarioImpresso;

    public List<DiarioEntidade> SELECTDIARIO() throws SQLException {

        try {
            Conexao.AbrirConexao();
            String SQL = "SELECT ID, COD_FATO, DATA_FATO, DETALHES, DEBITO, CREDITO, VALOR_FATO, FATOS_EDITADOS FROM DIARIO";
            ResultSet LivroDiario = Conexao.getConexao().createStatement().executeQuery(SQL);
            while(LivroDiario.next()) {
                    String ID = LivroDiario.getString("ID");
                    String CodFato = LivroDiario.getString("COD_FATO");
                    String DataFato = LivroDiario.getString("DATA_FATO");
                    String Detalhes = LivroDiario.getString("DETALHES");
                    String Debito = LivroDiario.getString("DEBITO");
                    String Credito = LivroDiario.getString("CREDITO");
                    int Valor = Integer.parseInt(LivroDiario.getString("VALOR_FATO"));
                    String FatoEditado = LivroDiario.getString("FATOS_EDITADOS");
                    DiarioList.add(new DiarioEntidade(ID, CodFato, DataFato, Detalhes, Debito, Credito, Valor, FatoEditado));




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
