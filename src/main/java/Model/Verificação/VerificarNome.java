package Model.Verificação;

public class VerificarNome {

    public boolean isNomeReal() {
        return NomeReal;
    }

    public void setNomeReal(boolean nomeReal) {
        NomeReal = nomeReal;
    }

    private boolean NomeReal;

    public boolean isString(String Nome) {
        NomeReal = Nome.matches("\\d"); //se for nome é false se for digito é true é assim que esse metodo funciona
        return NomeReal;
    }
}
