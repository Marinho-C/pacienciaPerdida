import java.util.List;
import java.util.ArrayList;

public class Pedido {

    // Atributos
    private CadastroCliente cliente;
    private List<Produto> produtos; // Lista de produtos no pedido, é só o que o cliente vai levar
    private double total; // Total do pedido, pra saber quanto é que deu
    private ControladorDeEstoque controladorEstoque; // Aqui é onde a gente mexe no estoque

    // Construtor
    public Pedido(CadastroCliente cliente, ControladorDeEstoque controladorEstoque) {
        this.cliente = cliente;
        this.produtos = new ArrayList<>(); // Inicia a lista de produtos, vazio por enquanto
        this.total = 0.0; // Começa com o total zerado, não tem jeito
        this.controladorEstoque = controladorEstoque; // Guarda o controlador de estoque aqui
    }

    // Método para adicionar produtos ao pedido
    public void adicionarProduto(Produto produto, int quantidade) {
        try {
            // Verifica se tem produto no estoque
            if (controladorEstoque.verificarDisponibilidade(produto, quantidade)) {
                produtos.add(produto); // Oxi, coloca o produto no pedido
                total += produto.getPreco() * quantidade; // Atualiza o preço total, não dá pra deixar de calcular
                System.out.println("Produto " + produto.getNome() + " adicionado ao pedido.");
            } else {
                System.out.println("Estoque insuficiente para " + produto.getNome()); // Se não tiver, já avisa
            }
        } catch (Exception e) {
            System.out.println("Erro ao adicionar produto: " + e.getMessage()); // Se der erro, fala o motivo
        }
    }

    // Método para finalizar o pedido e atualizar o estoque
    public void finalizarPedido() {
        try {
            for (Produto produto : produtos) {
                controladorEstoque.atualizarEstoque(produto, -1); // Diminui a quantidade no estoque, porque o pedido
                                                                  // foi feito
            }
            System.out.println("Pedido finalizado com sucesso!"); // Se deu certo, avisa que foi
        } catch (Exception e) {
            System.out.println("Erro ao finalizar pedido: " + e.getMessage()); // Se não der certo, já avisa também
        }
    }

    // Método para exibir os produtos do pedido
    public void exibirPedido() {
        System.out.println("Pedido do cliente: " + cliente.getNome()); // Mostra o nome do cliente
        System.out.println("Produtos no pedido:");
        for (Produto p : produtos) {
            System.out.println("- " + p.getNome() + " | R$ " + p.getPreco()); // Mostra os produtos e o preço
        }
        System.out.println("Total do pedido: R$ " + total); // No final, diz quanto deu o total
    }

    // Método para retornar o valor total do pedido
    public double getTotal() {
        return total; // Aqui, só devolve o valor total, sem mistério
    }
}
