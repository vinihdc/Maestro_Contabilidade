package Model.BancoDeDados;

import com.example.MaestroContabilidade.ControllerLogin;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;

import javax.naming.ldap.Control;
import javax.xml.transform.Result;
import java.sql.ResultSet;
import java.sql.SQLException;
@Repository
public class IdentificarUsuarioBD {

    private ConexaoBD Conexao = new ConexaoBD();

    public String getCPFUsuario() {
        return CPFUsuario;
    }

    private String CPFUsuario; //chave primaria, necessario para descobrir o nome do usuario

    public String getNomeUsuarioAp() {
        return NomeUsuarioAp;
    }

    private String NomeUsuarioAp;

    public boolean isCPFValido() {
        return CPFValido;
    }

    private boolean CPFValido;

    public IdentificarUsuarioBD(String CPFUsuario) {
        this.CPFUsuario = CPFUsuario;
    }

    public void IdentificarUsuarioBD() {
        try {
            Conexao.AbrirConexao();
            String sql = String.format("SELECT * FROM USUARIO WHERE CPF = '%s'", CPFUsuario);
            ResultSet NomeUsuario = Conexao.getConexao().createStatement().executeQuery(sql);
            while(NomeUsuario.next()) {
                NomeUsuarioAp = NomeUsuario.getString("Nome");

            }



        }

        catch (SQLException e) {
            throw new RuntimeException(e);
        }


        try {
            Conexao.AbrirConexao();
            String sql = String.format("INSERT INTO USUARIOLOGADONOSISTEMA(USUARIOLOGADO) VALUES('%s')", NomeUsuarioAp);
            int UsuarioSistema = Conexao.getConexao().createStatement().executeUpdate(sql);

        }

        catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


}
