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
        boolean Mensagem;
        boolean Status;


        IdentificarUsuarioBD NivelDeAcesso = new IdentificarUsuarioBD();
        Nivel = NivelDeAcesso.IdentificarUsuarioBD(CPF);
        model.addAttribute("NivelDeAcesso", Nivel);


        Mensagem = ObjetoPedidos.InformarPedido(Nivel);
        Status = ObjetoPedidos.Consultar_Status_Pedido(Nivel);


        if((Nivel.equals("N2") || Nivel.equals("N3")) && Mensagem) {
            segunda_model.addAttribute("Pedido", "Uma Requisição está em aberto");
        }


        else {
            segunda_model.addAttribute("Pedido", "Sem Requisição");
        }


        if(Nivel.equals("N1") && Mensagem == false) {
            segunda_model.addAttribute("Pedido", "Pedido Recusado");
        }

        if(Nivel.equals("N1") && Mensagem == true) {
            segunda_model.addAttribute("Pedido", "Pedido em analise");
        }


        if(Nivel.equals("N1") && Mensagem == true && Status == true) {
            segunda_model.addAttribute("Pedido", "Pedido Aprovado");

        }



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




    @GetMapping("")
    public String Restart() {
       UsuarioFezLogin = false;
       return "Index";
    }


    @GetMapping("/Codigos")
    public String Codigos() {
        if(UsuarioFezLogin == true) {
            return "Codigos";
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


    @GetMapping("/Nao_Permitir")
    public String Permitir(Model model) throws SQLException {

        ObjetoPedidos.RecusarPedido();
        return "PedidoRecusado";



    }

    @GetMapping("/Permitir")
    public String  Conferir(Model model) throws SQLException {
       return "PedidoAprovado";
    }



}
