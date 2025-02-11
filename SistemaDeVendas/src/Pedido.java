import java.util.List;
import java.util.ArrayList;

public class Pedido {

    // Por que precisamos de um identificador para o pedido?
    // Sem um ID único, como vamos referenciar um pedido específico depois?
    private static int contadorPedidos = 1001; // Começa do 1001 e vai aumentando
    private int id;

    private CadastroCliente cliente; // Quem é o cliente que fez o pedido? Sem cliente, não tem pedido!
    private List<ItemPedido> itensPedido; // Lista de itens que foram comprados (produto + quantidade)
    private double total; // Total do pedido, que vai somando conforme a gente adiciona os itens
    private ControladorDeEstoque controladorEstoque; // Quem vai garantir que temos o produto no estoque?

    // Por que esse construtor é importante?
    // Ele garante que todo pedido tenha um cliente e um controlador de estoque
    // válidos.
    public Pedido(CadastroCliente cliente, ControladorDeEstoque controladorEstoque) {
        this.id = contadorPedidos++; // Cada pedido recebe um número único
        this.cliente = cliente;
        this.itensPedido = new ArrayList<>(); // Iniciamos a lista de itens vazia
        this.total = 0.0; // Começa com total igual a zero
        this.controladorEstoque = controladorEstoque; // O controlador de estoque que vai ser usado no pedido
    }

    // Por que precisamos de um método para adicionar produtos ao pedido?
    // Porque um pedido sem produtos não faz sentido, né?
    public void adicionarProduto(Produto produto, int quantidade) {
        try {
            // Para que serve isso? Para garantir que a quantidade seja positiva antes de
            // tentar adicionar o produto.
            if (quantidade <= 0) {
                System.out.println("Quantidade inválida!");
                return;
            }

            // Verifica se o produto está disponível no estoque em quantidade suficiente.
            // Se estiver, adiciona o item ao pedido e atualiza o total com o preço do
            // produto.
            // Caso contrário, exibe uma mensagem de erro.
            if (controladorEstoque.verificarDisponibilidade(produto, quantidade)) {
                itensPedido.add(new ItemPedido(produto, quantidade)); // Agora sabemos quantos foram comprados!
                total += produto.getPreco() * quantidade; // Atualiza o total do pedido
                System.out.println("Produto " + produto.getNome() + " adicionado ao pedido. Quantidade: " + quantidade);
            } else {
                // E se o estoque não for suficiente? A gente avisa.
                System.out.println("Estoque insuficiente para " + produto.getNome());
            }
        } catch (Exception e) {
            // O que fazer se der algum erro? A gente informa o erro aqui.
            System.out.println("Erro ao adicionar produto: " + e.getMessage());
        }
    }

    // E se a gente não atualizar o estoque ao finalizar o pedido?
    // O estoque ficaria incorreto, podendo vender produtos que não temos mais!
    public void finalizarPedido() {
        try {
            // Aqui, estamos percorrendo todos os itens do pedido para atualizar o estoque.
            // Subtraímos as quantidades dos produtos vendidos.
            for (ItemPedido item : itensPedido) {
                controladorEstoque.atualizarEstoque(item.getProduto(), -item.getQuantidade()); // Reduz a quantidade do
                                                                                               // estoque
            }
            System.out.println("Pedido #" + id + " finalizado com sucesso!");
        } catch (Exception e) {
            // E se der erro na hora de finalizar? A gente avisa também.
            System.out.println("Erro ao finalizar pedido: " + e.getMessage());
        }
    }

    // Como o cliente vai saber o que comprou? Aqui, exibimos um resumo do pedido.
    public void exibirPedido() {
        System.out.println("Pedido #" + id + " do cliente: " + cliente.getNome());
        System.out.println("Produtos no pedido:");
        for (ItemPedido item : itensPedido) {
            // Exibimos cada produto do pedido com quantidade e preço.
            System.out.println("- " + item.getProduto().getNome() + " | Quantidade: " + item.getQuantidade() + " | R$ "
                    + item.getProduto().getPreco());
        }
        // Exibe o total do pedido ao final.
        System.out.println("Total do pedido: R$ " + total);
    }

    // Esse método é realmente necessário?
    // Sim! Se precisarmos consultar o total do pedido em outro lugar, já temos um
    // método pronto.
    public double getTotal() {
        return total; // Retorna o total do pedido
    }

// Por que precisamos desse método? Porque, caso precisemos salvar os dados do
// pedido em um arquivo CSV, temos que formatar assim.
public List<String> getDadosPedidoParaCSV() {
        List<String> dadosPedido = new ArrayList<>(); // Criamo
