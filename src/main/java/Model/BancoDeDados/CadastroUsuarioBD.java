package Model.BancoDeDados;

import java.sql.SQLException;

public class CadastroUsuarioBD {
   Model.BancoDeDados.ConexaoBD ConexaoBD = new ConexaoBD();
    public void CadastrarUsuarioNoBD(String CPF, String Nome, String Password) throws SQLException {

        try {
            ConexaoBD.AbrirConexao();
            String ComandoSQL = String.format("INSERT INTO USUARIO(CPF, NOME, PASSWORD) VALUES('%s', '%s', '%s')", CPF, Nome, Password);
            int NumDeLinhas = ConexaoBD.getConexao().createStatement().executeUpdate(ComandoSQL);
            System.out.println("Informacoes cadastradas com sucesso!");

        }

        catch (Exception e) {
            throw new RuntimeException(e);
        }


        finally {
            ConexaoBD.FecharConexao();
        }


    }
}
