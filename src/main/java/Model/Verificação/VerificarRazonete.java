package Model.Verificação;

import Model.BancoDeDados.ConexaoBD;
import org.springframework.web.cors.reactive.PreFlightRequestWebFilter;
import javax.xml.transform.Result;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class VerificarRazonete {


    private ConexaoBD Conexao = new ConexaoBD();


    public boolean ValidarElementoRazonete(String ConteudoValidar) {
        ArrayList<String> ElementosValidos = new ArrayList<>();
        boolean Validar = false;


        try {
            Conexao.AbrirConexao();
            String SQL = "SELECT * FROM ELEMENTOSRAZONETE";
            ResultSet ElementosNoBanco = Conexao.getConexao().createStatement().executeQuery(SQL);
            while(ElementosNoBanco.next()) {
                ElementosValidos.add(ElementosNoBanco.getString("Elementos"));

            }

            for(String i : ElementosValidos) {
                i = ConteudoValidar;
                if(ElementosValidos.contains(i)) {
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

    public boolean ExceptionsElementosRazonete(String ConteudoValidar) {
        ArrayList<String> ElementosValidos = new ArrayList<>();
        boolean Validar = false;


        try {
            Conexao.AbrirConexao();
            String SQL = "SELECT * FROM ElementosRazonete WHERE ID >= 17 AND id <= 24";
            ResultSet Elemento = Conexao.getConexao().createStatement().executeQuery(SQL);
            while(Elemento.next()) {
                ElementosValidos.add(Elemento.getString("Elementos"));

            }

            for(String i : ElementosValidos) {
                i = ConteudoValidar;
                if(ElementosValidos.contains(i)) {
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


}
