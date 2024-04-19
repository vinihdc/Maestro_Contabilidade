package Model.Verificação;


import static java.lang.System.exit;
import static java.lang.System.loadLibrary;

public class VerificarCPF {

    private String CPF;

    public VerificarCPF(String CPF) {
        this.CPF = CPF;

    }

    public boolean isCPFReal() {
        return CPFReal;
    }

    private boolean CPFReal;

    public boolean AutenticarCPF() {


        int CPFVetor[] = new int[11];


        for(int i = 0; i < 11; i++) {
            CPFVetor[i] = Character.getNumericValue(CPF.charAt(i));


        }

        int[] Multi1 = {10,9,8,7,6,5,4,3,2};
        int [] Multi2 = {11,10,9,8,7,6,5,4,3,2};
        int soma = 0;
        int Resto = 0;
        int PrimeiroDigitoVerificador = 0;
        int soma2 = 0;
        int Resto2 = 0;
        int SegundoDigitoVerificador = 0;

        for(int i = 0; i <= 8; i++) {
            soma += CPFVetor[i] * Multi1[i];
            Resto = soma % 11;
            PrimeiroDigitoVerificador = 11 - Resto;

        }


        for(int i = 0; i <= 9; i++) {
            soma2 += CPFVetor[i] * Multi2[i];
            Resto2 = soma2 % 11;
            SegundoDigitoVerificador = 11 - Resto2;

        }




        if(PrimeiroDigitoVerificador == CPFVetor[9] && SegundoDigitoVerificador == CPFVetor[10]) {
            System.out.println("CPF Valido");
            CPFReal = true;
        }

        else {
            System.out.println("\nCPF não valido");
            CPFReal = false;

        }

        return CPFReal;













    }



}
