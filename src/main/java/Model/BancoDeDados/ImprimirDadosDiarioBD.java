package Model.BancoDeDados;

import Model.Entidade.FatosEntidade;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ImprimirDadosDiarioBD {

    private ConexaoBD Conexao = new ConexaoBD();





    public List<FatosEntidade> SELECTDIARIO() throws SQLException {



        List<FatosEntidade> Fatos = new ArrayList<>();

       try {
           Conexao.AbrirConexao();
           // dá o select no database
           String sql1 = "SELECT ID, DETALHES_FATO, VALOR_FATO, DATA_FATO, TIPODEOPERACAO_FATO FROM FATOSGERAL";
           //Armazena os dados em um objeto result set
           ResultSet S = Conexao.getConexao().createStatement().executeQuery(sql1);
            //while(S.next()) quer dizer que ele vai percorrer as colunas do banco enquanto não for null
               while(S.next()) {
                   int id = Integer.parseInt(S.getString("ID"));
                   String Detalhes = S.getString("DETALHES_FATO");
                   int valor = Integer.parseInt(S.getString("VALOR_FATO"));
                   String Data = S.getString("DATA_FATO");
                   String TipoOp = S.getString("TIPODEOPERACAO_FATO");
                   //Fatos.add(new FatosEntidade(id, Detalhes, valor, Data, TipoOp));
               }


        //try with resources do java 7 não preciso fechar a conexão ele fecha automaticamente após o termino do bloco try




       }

       catch (SQLException e) {
           throw new RuntimeException(e);
       }
       return Fatos;
    }
}
