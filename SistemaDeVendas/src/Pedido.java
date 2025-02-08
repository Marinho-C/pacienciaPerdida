import java.util.List;
import java.util.ArrayList;

public class Pedido {

    // utilização do 'map' para pegar o registro. P/ acessa mais rápido;
    // atributos id nome descrição preco
    private CadastroCliente cliente;
    private List<Produto> produtos;
    private double total;

    // Construtor
    public Pedido(CadastroCliente cliente) {
        this.cliente = cliente;
        this.produtos = new ArrayList<>();
        this.total = 0.0;
    }
    // Método para adicionar produtos ao pedido
    public void adicionarProduto(Produto produto, int quantidade, ControladorDeEstoque estoque) {
        if (estoque.removerProduto(produto.getCodigo(), quantidade)) {
            produtos.add(produto);
            total += produto.getPreco() * quantidade;
            System.out.println("Produto " + produto.getNome() + " adicionado ao pedido.");
        } else {
            System.out.println("Estoque insuficiente para " + produto.getNome());
        }
    }

    // Método para exibir os produtos do pedido
    public void exibirPedido() {
        System.out.println("Pedido do cliente: " + cliente.getCliente());
        System.out.println("Produtos no pedido:");
        for (Produto p : produtos) {
            System.out.println("- " + p.getNome() + " | R$ " + p.getPreco());
        }
        System.out.println("Total do pedido: R$ " + total);
    }

    // Método para retornar o valor total do pedido
    public double getTotal() {
        return total;
    }


    }
}
