package com.example.MaestroContabilidade;
import org.springframework.web.bind.annotation.GetMapping;

@org.springframework.stereotype.Controller
public class PrincipalControlador {

    @GetMapping("/Index")
    public String Index(){
        return "Index";
    }

    @GetMapping("/Diario")
    public String Diario(){
        return "Diario";
    }

    @GetMapping("/Cadastro")
    public String Cadastro(){
        return "Cadastro";
    }

    @GetMapping("/PosCadastro")
    public String PosCadastro(){
        return "PosCadastro";
    }

    @GetMapping("/InfoIncorreta")
    public String InfoIncorreta(){
        return "InfoIncorreta";
    }

}
