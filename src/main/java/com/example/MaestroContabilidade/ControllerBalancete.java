package com.example.MaestroContabilidade;


import Model.BancoDeDados.BalanceteBD;
import Model.BancoDeDados.RazoneteBD;
import Model.Entidade.BalanceteEntidade;
import Model.Entidade.RazoneteEntidade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.sql.SQLException;
import java.util.List;

@Controller
public class ControllerBalancete {


    private BalanceteBD ObjetoRazoneteBD = new BalanceteBD();


    @GetMapping("/BalanceteSaldo")
    public String Balancete(Model model) throws SQLException {
        BalanceteBD ObjetoRazoneteBD = new BalanceteBD();
        List<BalanceteEntidade> BalanceteDados = ObjetoRazoneteBD.SELECTBalancete();
        model.addAttribute("BalanceteDados", BalanceteDados);
        return "Balancete";
    }








}