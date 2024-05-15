package Model.BancoDeDados;

import Model.Entidade.DiarioEntidade;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DiarioBD {

    private ConexaoBD Conexao = new ConexaoBD();





    public void InserirDadosDiario(String CodFato, String Data, String Detalhes, String Debito, String Credito, int Valor) throws SQLException {
        try {
            Conexao.AbrirConexao();
            String SQL = String.format("INSERT INTO DIARIO(COD_FATO, DATA_FATO, DETALHES, DEBITO, CREDITO, VALOR_FATO) VALUES('%s', '%s', '%s', '%s', '%s' ,'%d')", CodFato, Data, Detalhes, Debito, Credito, Valor);
            int InserirDados = Conexao.getConexao().createStatement().executeUpdate(SQL);



    }

        catch (SQLException ex) {
            throw new RuntimeException(ex);
        }



        finally {
            Conexao.FecharConexao();
        }
    }





    }


