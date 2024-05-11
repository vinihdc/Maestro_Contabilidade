package com.example.MaestroContabilidade;


import Model.BancoDeDados.ConexaoBD;
import Model.BancoDeDados.RazoneteBD;
import Model.Entidade.RazoneteEntidade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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

    @GetMapping("/DadosRazonete")
    public String Razonete(Model model) throws SQLException {
        List<RazoneteEntidade> DadosDoRazonete = ObjetoRazoneteBD.SELECTRAZONETE(CaixaZerado);
        model.addAttribute("DadosRazonete", DadosDoRazonete);
        return "Razonete";
    }


}
