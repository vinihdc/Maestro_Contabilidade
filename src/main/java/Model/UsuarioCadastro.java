package Model;

import org.springframework.stereotype.Service;

@Service
public class UsuarioCadastro {

    public UsuarioCadastro(String nome, String cpf, String password) {
        Nome = nome;
        CPF = cpf;
        Password = password;
    }

    public UsuarioCadastro() {
        System.out.println("Nome: " + getNome());
        System.out.println("CPF: " + getCPF());
        System.out.println("Password: " + getPassword());

    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    private String Nome;

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    private String CPF;

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    private String Password;

    @Override
    public String toString() {
        return "DataRegistrer{" +
                "Nome='" + Nome + '\'' +
                ", CPF='" + CPF + '\'' +
                ", Password='" + Password + '\'' +
                '}';
    }
}
