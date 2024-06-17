package Model.BancoDeDados;

import Model.BancoDeDados.ConexaoBD;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LoginBD {
    private ConexaoBD Conexao = new ConexaoBD();


    public boolean isUsuarioTemCadastroCPF() {
        return UsuarioTemCadastroCPF;
    }

    private boolean UsuarioTemCadastroCPF;

    public boolean isUsuarioTemCadastroSenha() {
        return UsuarioTemCadastroSenha;
    }

    private boolean UsuarioTemCadastroSenha;

    private ArrayList<String> CPFS = new ArrayList<>();

    private ArrayList <String> Senhas = new ArrayList<>();

    private ArrayList <String> NomeReduzidoEmpresas = new ArrayList<>();


    public void Login(String CPF, String Senha, String NomeReduzidoEmpresa) throws SQLException {
        try {
            Conexao.AbrirConexao();
            String ComandoSQLCPF = "SELECT CPF FROM USUARIO";
            String ComandoSQLSENHA = "SELECT PASSWORD FROM USUARIO";
            String ComandoSQLEmpresas = "SELECT EMPRESA FROM USUARIO";
            ResultSet Select1 = Conexao.getConexao().createStatement().executeQuery(ComandoSQLCPF);
            ResultSet Select2 = Conexao.getConexao().createStatement().executeQuery(ComandoSQLSENHA);
            ResultSet Select3 = Conexao.getConexao().createStatement().executeQuery(ComandoSQLEmpresas);
            while(Select1.next() && Select2.next() && Select3.next()) {
              CPFS.add(Select1.getString("CPF"));
              Senhas.add(Select2.getString("Password"));
              NomeReduzidoEmpresas.add(Select3.getString("Empresa"));
            }

            for(String i : CPFS) {
                i = CPF;
                if(CPFS.contains(i)) {
                    UsuarioTemCadastroCPF = true;



                }

                else {
                    UsuarioTemCadastroCPF = false;
                }
            }

            for(String i : Senhas) {
                i = Senha;
                if(Senhas.contains(i)) {
                    UsuarioTemCadastroSenha = true;
                }

                else {
                    UsuarioTemCadastroSenha = false;
                }

            }

            for(String i : NomeReduzidoEmpresas) {
                i = NomeReduzidoEmpresa;
                if(NomeReduzidoEmpresas.contains(i)) {
                    UsuarioTemCadastroSenha = true;
                }

                else {
                    UsuarioTemCadastroSenha = false;
                }
            }



        }

        catch (SQLException e) {
            throw new RuntimeException(e);
        }


        finally {
            Conexao.FecharConexao();
        }
    }
}
