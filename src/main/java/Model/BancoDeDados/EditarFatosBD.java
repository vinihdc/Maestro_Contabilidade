package Model.BancoDeDados;

import java.sql.SQLException;

public class EditarFatosBD {

    private ConexaoBD Conexao = new ConexaoBD();


    public void EditarFatosRazaoCredito(int ID, String Local) {
        try {
            Conexao.AbrirConexao();
            String SQL = String.format("UPDATE RAZONETE SET %s_CREDITO WHERE ID = %d", Local, ID);
            String SQL2 =  String.format("UPDATE RAZONETE SET %s_DEBITO WHERE ID = %d", Local, ID);
            int Exec = Conexao.getConexao().createStatement().executeUpdate(SQL);
            int Exec2 = Conexao.getConexao().createStatement().executeUpdate(SQL2);
        }


        catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }


    public void EditarFatosDiario(int ID) {
           try {
               Conexao.AbrirConexao();

           } catch (Exception e) {
               throw new RuntimeException(e);
           }
    }
}
