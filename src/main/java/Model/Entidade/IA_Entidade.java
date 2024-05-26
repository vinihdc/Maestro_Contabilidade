package Model.Entidade;

import lombok.Getter;
import lombok.Setter;

public class IA_Entidade {


    public String getRespostas() {
        return Respostas;
    }

    private String Respostas;

    public IA_Entidade(String respostas) {
        Respostas = respostas;
    }
}
