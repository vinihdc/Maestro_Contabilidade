package com.example.MaestroContabilidade;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;

@org.springframework.stereotype.Controller
@RequiredArgsConstructor
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

    @GetMapping("/Fatos")
    public String Fatos() {
        return "Fatos";
    }

    @GetMapping("/Razonete")
    public String Razonete() {
        return "Razonete";
    }

    @GetMapping("/Balancete")

    public String Balancete() {
        return "Balancete";
    }


    @GetMapping("/DeletarFato")
    public String DeletarFato() {
        return "DeletarFato";
    }







}
