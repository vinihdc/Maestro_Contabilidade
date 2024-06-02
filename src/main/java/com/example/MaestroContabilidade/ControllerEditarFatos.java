package com.example.MaestroContabilidade;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller


public class ControllerEditarFatos {

    @GetMapping("/EditarFato")
    @Scheduled(initialDelay = 86400000) //24h para editar um fato
    public String EditarFato(@RequestParam int ID_Fato, String LocalParaAbate) {


        return "EditarFatos";
    }
}
