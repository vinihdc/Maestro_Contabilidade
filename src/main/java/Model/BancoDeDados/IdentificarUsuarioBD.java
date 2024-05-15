package Model.BancoDeDados;

import com.example.MaestroContabilidade.ControllerCadastro;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.util.pattern.PathPattern;

import java.sql.ResultSet;
import java.sql.SQLException;
@Repository
public class IdentificarUsuarioBD {


    private ConexaoBD Conexao = new ConexaoBD();


    public String getCPFUsuario() {
        return CPFUsuario;
    }

    private String CPFUsuario; //chave primaria, necessario para descobrir o nome do usuario


    public IdentificarUsuarioBD(String CPFUsuario) {
        this.CPFUsuario = CPFUsuario;
    }

    public IdentificarUsuarioBD() {
    }




    public String IdentificarUsuarioBD(String CPFUsuario) throws SQLException {
        String NivelDeAcesso = "";

        try {
            Conexao.AbrirConexao();
            String sql = String.format("SELECT NIVEL FROM USUARIO WHERE CPF = '%s'", CPFUsuario);
            ResultSet NomeUsuario = Conexao.getConexao().createStatement().executeQuery(sql);
            while(NomeUsuario.next()) {
                NivelDeAcesso = NomeUsuario.getString("NIVEL");

            }

            return NivelDeAcesso;





        }

        catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }



}
