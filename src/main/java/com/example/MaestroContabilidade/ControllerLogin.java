package com.example.MaestroContabilidade;

import Model.BancoDeDados.LoginBD;
import Model.BancoDeDados.UsuarioLogadoNoSistemaBD;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLException;
@RequiredArgsConstructor
@Controller
public class ControllerLogin {


    private String UsuarioLogado;


    @RequestMapping("/login")
    public String Login(@RequestParam String CPF, @RequestParam String Password, Model model) throws SQLException {


        try {
            UsuarioLogadoNoSistemaBD QuemLogou = new UsuarioLogadoNoSistemaBD();
            UsuarioLogado = QuemLogou.QualUsuarioLogouNoSistema();
            model.addAttribute("NomeUsuario",  UsuarioLogado);
            if (CPF != null && Password != null) {
                LoginBD Login = new LoginBD();
                Login.Login(CPF, Password);
                if(Login.isUsuarioTemCadastroCPF() == true && Login.isUsuarioTemCadastroSenha() == true) {
                    return "HomePage";
                }

                else {
                    return "InfoIncorreta";
                }
            }

            else {
                return "InfoIncorreta";
            }

        }

        catch (SQLException e) {
            return "InfoIncorreta";
        }




    }



}
