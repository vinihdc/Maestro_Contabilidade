package Model.BancoDeDados;

import Model.Entidade.IA_Entidade;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class IA_BD {

    private ConexaoBD Conexao = new ConexaoBD();


    public boolean ExisteRespostaIA(String Conteudo) throws SQLException {
        ArrayList<String> DadosDoBanco = new ArrayList<>();
        boolean Validar = false;


        try {
            Conexao.AbrirConexao();
            String SQL = String.format("SELECT PERGUNTA FROM IA");
            ResultSet Saudar = Conexao.getConexao().createStatement().executeQuery(SQL);
            while(Saudar.next()) {
                DadosDoBanco.add(Saudar.getString("PERGUNTA"));
            }

            for(String i : DadosDoBanco) {
                i = Conteudo;
                if(DadosDoBanco.contains(i)) {
                    Validar = true;
                }

                else {
                    Validar = false;
                }
            }


        }


        catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return Validar;

    }


    public List<IA_Entidade> GerarRespostaIA(String Conteudo) {
        List<IA_Entidade> RespostasIA = new ArrayList<>();
        String Respostas = "";

        try {
            Conexao.AbrirConexao();
            String SQL = String.format("SELECT * FROM IA WHERE PERGUNTA = '%s'", Conteudo);
            ResultSet PegarRespostas = Conexao.getConexao().createStatement().executeQuery(SQL);
            while(PegarRespostas.next()) {
                Respostas = PegarRespostas.getString("RESPOSTA");
            }

            RespostasIA.add(new IA_Entidade(Respostas));

        }





        catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return RespostasIA;
    }


    public List<IA_Entidade> SemRespostaIA() {
        String Resposta = "Lamento, mas meu criador não colocou em minha base de dados, tente outra pergunta ";
        List<IA_Entidade> RespostasIA = new ArrayList<>();
        RespostasIA.add(new IA_Entidade(Resposta));
        return RespostasIA;

    }




}
