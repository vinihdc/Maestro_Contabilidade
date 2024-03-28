package com.example.MaestroContabilidade;
import Model.CadastroUsuarioBD;
import Model.UsuarioCadastro;
import Model.VerificarCPF;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;


@Controller
public class DadosController {

    @RequestMapping("/form")
    public String HandleForm(@RequestParam String Nome, @RequestParam String CPF, @RequestParam String Password) throws IOException, SQLException {
        CadastroUsuarioBD Cadastrar = new CadastroUsuarioBD();


            if(Nome != null && CPF != null && Password != null) {

                VerificarCPF CPFUsuario = new VerificarCPF(CPF);
                boolean CPFVerdadeiro = false;
                CPFUsuario.AutenticarCPF(CPFVerdadeiro);

                if(CPFVerdadeiro == true) {
                    System.out.println("\nCPF verdadeiro");
                    UsuarioCadastro DadosUsuario = new UsuarioCadastro(Nome, CPF, Password);
                    Cadastrar.CadastrarUsuarioNoBD(DadosUsuario.getCPF(), DadosUsuario.getNome(), DadosUsuario.getPassword());
                    return "PosCadastro";
                }

                else {
                    return "InfoIncorreta";
                }

            }

            return "Index";




    }

}
