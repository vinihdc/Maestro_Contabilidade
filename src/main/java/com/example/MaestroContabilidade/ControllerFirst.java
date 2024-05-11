package com.example.MaestroContabilidade;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;

@org.springframework.stereotype.Controller
@RequiredArgsConstructor
public class ControllerFirst {

    @GetMapping("/Index")
    public String Index(){
        return "Index";
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
