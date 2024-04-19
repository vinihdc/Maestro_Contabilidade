package com.example.MaestroContabilidade;


import Model.BancoDeDados.RazoneteBD;
import Model.Entidade.RazoneteEntidade;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.sql.SQLException;
import java.util.List;

@Controller
public class ControllerRazonete {
    private RazoneteBD ObjetoRazoneteBD = new RazoneteBD();

    @GetMapping("/DadosRazonete")
    public String Razonete(Model model) throws SQLException {
        List<RazoneteEntidade> DadosDoRazonete = ObjetoRazoneteBD.SELECTRAZONETE();
        model.addAttribute("DadosRazonete", DadosDoRazonete);
        return "Razonete";
    }


}
