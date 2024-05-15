package com.example.MaestroContabilidade;
import Model.BancoDeDados.CadastroUsuarioBD;
import Model.Verificação.VerificarCPF;
import Model.Verificação.VerificarNome;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.sql.SQLException;


@Controller
public class ControllerCadastro {

    @RequestMapping("/form")
    public String HandleForm(@RequestParam String Nome, @RequestParam String CPF, @RequestParam String Password, @RequestParam String Empresa) throws IOException, SQLException {
        CadastroUsuarioBD Cadastrar = new CadastroUsuarioBD();
        VerificarCPF CPFUsuario = new VerificarCPF(CPF);
        VerificarNome NomeUsuario = new VerificarNome();


        NomeUsuario.isString(Nome);
        boolean NomeReal = NomeUsuario.isNomeReal();

            try {

            if(CPF != null && Password != null && CPF.length() == 11 && !NomeReal) {

                CPFUsuario.AutenticarCPF();
                if (CPFUsuario.isCPFReal() == true) {
                    System.out.println("\nCPF verdadeiro");
                    Cadastrar.CadastrarUsuarioNoBD(CPF, Nome, Password, Empresa);
                    return "PosCadastro";
                }

            }


            else {
                return "InfoIncorreta";
            }

            }

            catch (SQLException e) {
                return "InfoIncorreta";
            }

                return "Index";




    }

}
