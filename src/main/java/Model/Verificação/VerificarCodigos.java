package Model.Verificação;

import Model.BancoDeDados.ConexaoBD;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VerificarCodigos {

    private ConexaoBD Conexao = new ConexaoBD();

    public boolean VerificarCodigos(String CodigoUsuario) {
        List<String> CodigosDosEventos = new ArrayList<>();
        boolean ValidarCodigo = false;


        try {
            Conexao.AbrirConexao();
            String SQL = "SELECT CODIGOS FROM CODIGOSEVENTOS";
            ResultSet Codigos = Conexao.getConexao().createStatement().executeQuery(SQL);
            while(Codigos.next()) {
                CodigosDosEventos.add(Codigos.getString("CODIGOS"));

            }

            for(String i : CodigosDosEventos) {
                i = CodigoUsuario;
                if(CodigosDosEventos.contains(i)) {
                    ValidarCodigo = true;
                }

                else {
                    ValidarCodigo = false;

                }
            }
        }


        catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return ValidarCodigo;


    }
}
