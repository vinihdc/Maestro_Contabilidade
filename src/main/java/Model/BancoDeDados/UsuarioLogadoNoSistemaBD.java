package Model.BancoDeDados;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioLogadoNoSistemaBD {

    private ConexaoBD Conexao = new ConexaoBD();
    
    public String QualUsuarioLogouNoSistema() {
        String NomeUsuarioLogado = "";

        try {
            Conexao.AbrirConexao();
            String sql = String.format("SELECT u.ID, u.UsuarioLogado FROM USUARIOLOGADONOSISTEMA u JOIN (SELECT MAX(ID) AS maxID FROM USUARIOLOGADONOSISTEMA) AS maxIDTable ON u.ID = maxIDTable.maxID;");
            ResultSet NomeUsuario = Conexao.getConexao().createStatement().executeQuery(sql);
            while(NomeUsuario.next()) {
                NomeUsuarioLogado = NomeUsuario.getString("UsuarioLogado");


            }


            return NomeUsuarioLogado;



        }

        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
