package com.example.MaestroContabilidade;

import Model.BancoDeDados.EditarFatosBD;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@EnableScheduling
@RequiredArgsConstructor
@Component
public class ControllerEditarFatos {

    EditarFatosBD FatoEditavel = new EditarFatosBD();

    @Setter
    private int ID;

    @Setter
    private String AbateFato;





    @Scheduled(fixedRate = 86400000) //24h para editar um fato 86400000
    public void EditarFatoTempo() {
        FatoEditavel.EditarFatosDiario(ID);
        FatoEditavel.EditarFatosRazao(ID, AbateFato);
    }


    @GetMapping("/EditarFato")
    public String EditarFato(@RequestParam int ID_Fato, @RequestParam String LocalAbate) {
        setID(ID_Fato);
        setAbateFato(LocalAbate);
        EditarFatoTempo();
        return "EditarFatos";

    }
}
