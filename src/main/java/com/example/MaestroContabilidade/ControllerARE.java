package com.example.MaestroContabilidade;


import Model.BancoDeDados.ARE;
import Model.BancoDeDados.ARE_BD;
import Model.BancoDeDados.ConexaoBD;
import Model.Entidade.ARE_Entidade;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


@Controller

public class ControllerARE {

    private ARE_BD Objeto_ARE_BD = new ARE_BD();


    @GetMapping("/ApurarResultado")
    public String  ApurarResultado(Model model) {
        Objeto_ARE_BD.Descobrir_E_Salvar_ARE();
        Objeto_ARE_BD.Zerar_ARE();
        List<ARE_Entidade> DadosAre = Objeto_ARE_BD.Exibir_ARE();
        model.addAttribute("ARE", DadosAre);
        return "ARE";



    }
}
