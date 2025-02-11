public class ClienteVip extends CadastroCliente {
    public ClienteVip(String nome, String cpf, String enderecoCliente, String telefoneCliente,
            String emailCliente, String senhaCliente) throws Exception {
        super(nome, cpf, enderecoCliente, telefoneCliente, emailCliente, senhaCliente);
    }

    /*
     * nessa classe, que herda atributos da classe cliente, temos um 'plus' o
     * cliente vai conseguir ter uma porcentagem pelo produto (Valor do Desconto =
     * (Preço do Produto) x (5/ 100)). Seria só 5% de desconto para os clientes
     * cadastrados;
     */

    // metodo p/ calcular desconto:
    public double calcularDesconto(double precoProduto) {
        return precoProduto * 0.05;
    }

    // Qual valor ficou? Tá aqui o método pra mostrar o resultado com o desconto
    // aplicado.
    public double precoComDesconto(double precoProduto) {
        return precoProduto - calcularDesconto(precoProduto);
    }

    // Método pra mostrar os dados do cliente VIP:
    @Override
    public void exibirDados() {
        super.exibirDados();
        System.out.println("Cliente VIP (Desconto de 5% nas compras!)");
    }
}
