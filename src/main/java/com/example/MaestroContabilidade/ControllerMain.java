package com.example.MaestroContabilidade;

import Model.BancoDeDados.IdentificarUsuarioBD;
import Model.BancoDeDados.LoginBD;
import Model.BancoDeDados.PedidosBD;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLException;

@RequiredArgsConstructor
@Controller
public class ControllerMain {

    private PedidosBD ObjetoPedidos = new PedidosBD();

    public boolean UsuarioFezLogin;

    public String Nivel;

    public void setCPFUsuario(String CPFUsuario) {
        this.CPFUsuario = CPFUsuario;
    }

    public String CPFUsuario;






    @RequestMapping("/login")
    public String Login(@RequestParam String CPF, @RequestParam String Password, Model model, Model segunda_model) throws SQLException {
        String Status;


        IdentificarUsuarioBD NivelDeAcesso = new IdentificarUsuarioBD();
        Nivel = NivelDeAcesso.IdentificarUsuarioBD(CPF);
        model.addAttribute("NivelDeAcesso", Nivel);

        if(Nivel.equals("N1")) {
            Status = ObjetoPedidos.StatusPedidosN1();
        }

        else {
            Status = ObjetoPedidos.ExistePedidosParaN2N3(Nivel);
        }

        segunda_model.addAttribute("Pedido", Status);


        try {
            setCPFUsuario(CPF);
            if (CPF != null && Password != null) {
                LoginBD Login = new LoginBD();
                Login.Login(CPF, Password);
                if(Login.isUsuarioTemCadastroCPF() == true && Login.isUsuarioTemCadastroSenha() == true) {
                    UsuarioFezLogin = true;
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


    @GetMapping("/Diario")
    public String Diario(Model model) throws SQLException {


        if(UsuarioFezLogin == true) {
            IdentificarUsuarioBD NivelDeAcesso = new IdentificarUsuarioBD();
            Nivel = NivelDeAcesso.IdentificarUsuarioBD(CPFUsuario);
            model.addAttribute("NivelDeAcesso", Nivel);
            return "Diario";
        }

        else {
            return "Index";
        }



    }

    @GetMapping("/Fatos")
    public String Fatos() {
        if(UsuarioFezLogin == true) {
            return "Fatos";
        }

        else {
            return "Index";
        }

    }

    @GetMapping("/Razonete")
    public String Razonete(Model model) throws SQLException {
        if(UsuarioFezLogin == true) {
            IdentificarUsuarioBD NivelDeAcesso = new IdentificarUsuarioBD();
            Nivel = NivelDeAcesso.IdentificarUsuarioBD(CPFUsuario);
            model.addAttribute("NivelDeAcesso", Nivel);
            return "Razonete";
        }

        else {
            return "Index";
        }


    }

    @GetMapping("/Balancete")
    public String Balancete(Model model) throws SQLException {
        if(UsuarioFezLogin == true) {
            IdentificarUsuarioBD NivelDeAcesso = new IdentificarUsuarioBD();
            Nivel = NivelDeAcesso.IdentificarUsuarioBD(CPFUsuario);
            model.addAttribute("NivelDeAcesso", Nivel);
            return "Balancete";
        }

        else {
            return "Index";
        }

    }

    @GetMapping("/DeletarFato")
    public String DeletarFato() {
        if(UsuarioFezLogin == true) {
            return "DeletarFato";
        }

        else {
            return "Index";
        }



    }


    @GetMapping("/homePage")
    public String homePage() {
        if (UsuarioFezLogin == true) {
            return "homePage";
        } else {
            return "Index";
        }
    }




    @GetMapping("")
    public String Restart() {
       UsuarioFezLogin = false;
       return "Index";
    }


    @GetMapping("/Codigos")
    public String Codigos() {
        if(UsuarioFezLogin == true) {
            return "IA";
        }

        else {
            return "Index";
        }
    }

    @GetMapping("/ARE")
    public String ARE() {
        if(UsuarioFezLogin == true) {
            return "ARE";
        }

        else {
            return "ARE";
        }
    }



    @GetMapping("/EnviarPedido")
    public String EnviarPedido() {
        ObjetoPedidos.EnviarPedido();
        return "Pedido_Enviado";
    }




    @GetMapping("/Nao_Permitir")
    public String Permitir(Model model) throws SQLException {

        ObjetoPedidos.RecusarPedidos(Nivel);
        return "PedidoRecusado";



    }

    @GetMapping("/Permitir")
    public String  Conferir(Model model) throws SQLException {
        ObjetoPedidos.AprovarPedidos(Nivel);
       return "PedidoAprovado";
    }



}
