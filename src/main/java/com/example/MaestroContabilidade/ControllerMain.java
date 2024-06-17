package com.example.MaestroContabilidade;

import Model.BancoDeDados.ColetaDeDadosGraficoBD;
import Model.BancoDeDados.IdentificarUsuarioBD;
import Model.BancoDeDados.LoginBD;
import Model.BancoDeDados.PedidosBD;
import Model.Entidade.Graficos;
import Model.Entidade.PedidosEntidade;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.jfree.chart.ChartUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.SQLException;
import java.util.Base64;
import java.util.List;

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


    private ColetaDeDadosGraficoBD ObjColeta = new ColetaDeDadosGraficoBD();




    @GetMapping("StatusPedidos")
    public Model StatusPedidos(Model model) throws SQLException {

        if(Nivel.equals("N1")) {
            setStatus(ObjetoPedidos.StatusPedidosN1());
        }

        else {
           setStatus(ObjetoPedidos.ExistenciaDePedidosN2N3(Nivel));
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
    public String Login(@RequestParam String CPF, @RequestParam String Password, @RequestParam String NomeReduzidoEmpresa, Model model, Model model2) throws SQLException {
        setCPFUsuario(CPF);

        NivelDeAcesso(model);
        StatusPedidos(model2);


        try {
            setCPFUsuario(CPF);
            if (CPF != null && Password != null) {
                LoginBD Login = new LoginBD();
                Login.Login(CPF, Password, NomeReduzidoEmpresa);
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

    @GetMapping("/EditarFatos")
    public String DeletarFato() {
        if(UsuarioFezLogin == true) {
            return "EditarFatos";
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
        ObjetoPedidos.EnviarPedidosN1();
        return "Pedido_Enviado";
    }

    @RequestMapping("/Aprovar_Ou_Recusar_Pedidos")
    public String Aprovar_Ou_Recusar_Pedidos(@RequestParam int ID_Fatos, @RequestParam String Situacao) throws SQLException {

        if (Situacao.equals("Aprovado")) {
            ObjetoPedidos.AprovarPedidos(Nivel, ID_Fatos);
            ObjetoPedidos.VerificarPedidosN2N3(Nivel);
        } else {
            ObjetoPedidos.RecusarPedidos(Nivel, ID_Fatos);
            ObjetoPedidos.VerificarPedidosN2N3(Nivel);
        }

        return "PaginaPedidosN2N3";
    }


    @GetMapping("/PaginaPedidosN2N3")
    public String PaginaPedidosN2N3() {
        if(UsuarioFezLogin == true) {
            return "PaginaPedidosN2N3";
        }

        else {
            return "Index";
        }

    }


    @GetMapping("/PaginaPedidosN1")
    public String PedidosN1() {
        if(UsuarioFezLogin == true) {
            return "PaginaPedidosN1";
        }

        else {
            return "Index";
        }
    }











    @GetMapping("/RelatorioPedidos")
    public String RelatorioPedidos(Model model) throws SQLException {

        List<PedidosEntidade> RespostaPedidos = ObjetoPedidos.RelatorioGeralPedidos();
       model.addAttribute("StatusDeTodosPedidos", RespostaPedidos);
       return "PaginaPedidosN1";

    }











    @GetMapping("/HomePage")
    public String HomePage(Model model) throws SQLException, IOException {
        if(UsuarioFezLogin == true) {
            NivelDeAcesso(model);
            StatusPedidos(model);
            ObjColeta.ColetarDados();
            Graficos ExibirGraficos = new Graficos(ObjColeta.getDespesa(), ObjColeta.getReceita());

            return "HomePage";
        }

        else {
            return "Index";
        }
    }



}
