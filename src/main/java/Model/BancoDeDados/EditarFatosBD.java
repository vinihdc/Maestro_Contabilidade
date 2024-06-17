package Model.BancoDeDados;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EditarFatosBD {

    private ConexaoBD Conexao = new ConexaoBD();


    public void EditarFatosRazao(int ID, String Local) {
        int valor = 0;

        try {
            Conexao.AbrirConexao();
            String EditarTabelaRazonete = String.format("UPDATE RAZONETE SET %s_CREDITO = %d WHERE ID = %d", Local, 0, ID);
            String EditarTabelaRazonete2 = String.format("UPDATE RAZONETE SET %s_DEBITO = %d WHERE ID = %d", Local, 0, ID);
            int Exec = Conexao.getConexao().createStatement().executeUpdate(EditarTabelaRazonete);
            int Exec2 = Conexao.getConexao().createStatement().executeUpdate(EditarTabelaRazonete2);
        }



       catch (SQLException e) {
            //throw new RuntimeException(e);
       }


    }


    public void EditarFatosDiario(int ID) {
            String ConteudoFatoDetalhes = "";
            String FotoLapis = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAADIAAAAyCAYAAAAeP4ixAAAACXBIWXMAAAsTAAALEwEAmpwYAAABvElEQVR4nO2ZMUtDMRRGjyAIUlcruFhnF3+Bi45OdRTU6qO1XRzt5ubibjdx1E1XhwrddbcUQQd/gCKitk8C98E1VItC4UZzINDmpZDzvuRSEohEfssYME7ALANXwBvQBS6BeQJiGtgBekAKvIiM+/wEzBEA2/L2UxHZBUaBHHAi/ecEINGTybrWFoGMvPQ/EoBEF6gDtzLplpKZsi6SSbiWSN+MJzMBHMv3M4wvp7L3TMs8WN7s/p7Y7zNGy7i2hOE90VDltZ9MXZ69A5MYIhEBJ1KRviLwKhM+UGM31dgaRpMoec9WvGTKqgi435lOwqeokkmtJ+GWy3c0lERwSWRsWN0TWmLQ2y1FiSESk7BCTMJiEtW/UJ2qA8ZGCStJmP0Xm0QJIyQxCSMkMQmDSdQGjN0KocTWBoyNEsNk9QfHMGaPbEaAjpxi3MlRfnASjgV1pqSbOxHXVCxLOI5k4ofAtScTjEROLlHcBAuqPxPJhyDhWJcJN/lMlsx9CBLI9W8qQhkFWWb6LNa0xKy6dGmJTNO7iOlIaTbN3hfV6hk4BRalNJun7S0ft8zWvOvhILgAbiQZt8wikf/OB+i4E2KlT2dOAAAAAElFTkSuQmCC";

           try {
               Conexao.AbrirConexao();
               String SQL = String.format("SELECT DETALHES FROM DIARIO WHERE ID = '%d'", ID);
               ResultSet Conteudo = Conexao.getConexao().createStatement().executeQuery(SQL);
               while(Conteudo.next()) {
                    ConteudoFatoDetalhes = Conteudo.getString("DETALHES");
               }

               String UpdateFato = String.format("UPDATE DIARIO SET DETALHES = '%s - (Fato Editado)', FATOS_EDITADOS = '%s' WHERE ID = '%d'", ConteudoFatoDetalhes, FotoLapis, ID);
               int ExecUpdateDiario = Conexao.getConexao().createStatement().executeUpdate(UpdateFato);

           }



           catch (Exception e) {
               //throw new RuntimeException(e);
           }
    }
}
