package com.example.MaestroContabilidade;


import Model.BancoDeDados.RazoneteBD;
import Model.Entidade.RazoneteEntidade;
import Model.Verificação.VerificarRazonete;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ControllerRazonete {
    private RazoneteBD ObjetoRazoneteBD = new RazoneteBD();

    private VerificarRazonete ObjetoVerificarRazonete = new VerificarRazonete();






    @GetMapping("/RetornarRazao")
    public String Razonete(Model model, @RequestParam("ElementoRazonete") String ElementoRazonete) throws SQLException {
        boolean Elemento_Existe;
        boolean Elemento_Diferente;
        Elemento_Existe = ObjetoVerificarRazonete.ValidarElementoRazonete(ElementoRazonete);
        Elemento_Diferente = ObjetoVerificarRazonete.ExceptionsElementosRazonete(ElementoRazonete);



            if(Elemento_Diferente == true) {
                List<RazoneteEntidade> DadosDoRazonete = ObjetoRazoneteBD.VerificarElementosDiferentes(ElementoRazonete);
                model.addAttribute("DadosRazonete", DadosDoRazonete);
            }

            else {
                List<RazoneteEntidade> DadosDoRazonete = ObjetoRazoneteBD.Razao(ElementoRazonete);
                model.addAttribute("DadosRazonete", DadosDoRazonete);
            }

            return "Razonete";





    }


}
