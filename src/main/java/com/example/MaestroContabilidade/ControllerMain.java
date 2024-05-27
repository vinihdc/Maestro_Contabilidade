package com.example.MaestroContabilidade;

import Model.BancoDeDados.IdentificarUsuarioBD;
import Model.BancoDeDados.LoginBD;
import Model.BancoDeDados.PedidosBD;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
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


    @Setter
    public String Nivel;

    @Setter
    public String CPFUsuario;

    @Setter
    public String Status;



    @GetMapping("StatusPedidos")
    public Model StatusPedidos(Model model) {

        if(Nivel.equals("N1")) {
            setStatus(ObjetoPedidos.StatusPedidosN1());
        }

        else {
            setStatus(ObjetoPedidos.ExistePedidosParaN2N3(Nivel));
        }

        model.addAttribute("Pedido", Status);

        return model;
    }


    @GetMapping("NivelDeAcesso")
    public Model NivelDeAcesso(Model model) throws SQLException {
        IdentificarUsuarioBD NivelDeAcesso = new IdentificarUsuarioBD();
        setNivel(NivelDeAcesso.IdentificarUsuarioBD(CPFUsuario));
        model.addAttribute("NivelDeAcesso", Nivel);
        return model;

    }

    /*
    Login -> HomePage -> Fatos
    CPF,
    UsuarioFezOLogin -> Home



     */






    @RequestMapping("/login")
    public String Login(@RequestParam String CPF, @RequestParam String Password, Model model, Model model2) throws SQLException {
        setCPFUsuario(CPF);

        NivelDeAcesso(model);
        StatusPedidos(model2);


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
            NivelDeAcesso(model);
            StatusPedidos(model);
            return "Diario";
        }

        else {
            return "Index";
        }



    }

    @GetMapping("/Fatos")
    public String Fatos(Model model) throws SQLException {
        if(UsuarioFezLogin == true) {
            NivelDeAcesso(model);
            StatusPedidos(model);
            return "Fatos";
        }

        else {
            return "Index";
        }

    }

    @GetMapping("/Razonete")
    public String Razonete(Model model) throws SQLException {

        if(UsuarioFezLogin == true) {
            NivelDeAcesso(model);
            StatusPedidos(model);
            return "Razonete";
        }

        else {
            return "Index";
        }


    }

    @GetMapping("/Balancete")
    public String Balancete(Model model) throws SQLException {
        if(UsuarioFezLogin == true) {
            NivelDeAcesso(model);
            StatusPedidos(model);
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




    @GetMapping("")
    public String Restart() {
       UsuarioFezLogin = false;
       return "Index";
    }


    @GetMapping("/Codigos")
    public String Codigos(Model model) throws SQLException {
        if(UsuarioFezLogin == true) {
            NivelDeAcesso(model);
            StatusPedidos(model);
            return "IA";
        }

        else {
            return "Index";
        }
    }

    @GetMapping("/ARE")
    public String ARE(Model model) throws SQLException {
        if(UsuarioFezLogin == true) {
            NivelDeAcesso(model);
            StatusPedidos(model);
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


    @GetMapping("/HomePage")
    public String HomePage(Model model) throws SQLException {
        if(UsuarioFezLogin == true) {
            NivelDeAcesso(model);
            StatusPedidos(model);
            return "HomePage";
        }

        else {
            return "Index";
        }
    }



}
