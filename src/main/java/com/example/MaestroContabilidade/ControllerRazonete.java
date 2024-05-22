package com.example.MaestroContabilidade;


import Model.BancoDeDados.ConexaoBD;
import Model.BancoDeDados.RazoneteBD;
import Model.Entidade.RazoneteEntidade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ControllerRazonete {
    private RazoneteBD ObjetoRazoneteBD = new RazoneteBD();

    private boolean CaixaZerado;
    public ControllerRazonete(boolean caixaZerado) {
        CaixaZerado = caixaZerado;
    }


    /*

    @PostMapping("/ElementoRazao")
    public String ElementoRazao(@RequestParam String ElementoContabil) {
        ObjetoRazoneteBD.SELECTRAZONETE()
    }

     */

    @GetMapping("/DadosRazonete")
    public String Razonete(Model model) throws SQLException {
        String n = "";
        List<RazoneteEntidade> DadosDoRazonete = ObjetoRazoneteBD.SELECTRAZONETE(n);
        model.addAttribute("DadosRazonete", DadosDoRazonete);
        return "Razonete";
    }


}
