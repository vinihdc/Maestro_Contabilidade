package Model.BancoDeDados;

public interface Pedidos_BD {

    public void EnviarPedido();

    public String ExistePedidosParaN2N3(String Nivel);

    public void AprovarPedidos(String Nivel);

    public void RecusarPedidos(String Nivel);

    public String StatusPedidosN1();
}
