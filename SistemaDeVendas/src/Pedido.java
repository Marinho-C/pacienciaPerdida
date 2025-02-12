import java.util.List;
import java.util.ArrayList;

public class Pedido {

    private static int contadorPedidos = 1001;
    private int id;
    private CadastroCliente cliente;
    private List<ItemPedido> itensPedido; // Lista dos itens que ele mandou ver no pedido
    private double total;
    private ControladorDeEstoque controladorEstoque; // Esse aqui vai ver se tem estoque pra tudo que ele quer

    public Pedido(CadastroCliente cliente, ControladorDeEstoque controladorEstoque) {
        this.id = contadorPedidos++;
        this.cliente = cliente;
        this.itensPedido = new ArrayList<>(); // Aqui guardamos os itens que o cliente vai levar
        this.total = 0.0;
        this.controladorEstoque = controladorEstoque;
    }

    public void adicionarProduto(Produto produto, int quantidade) {
        try {
            if (quantidade <= 0) {
                System.out.println("Quantidade inválida! Faça direito, meu chapa!");
                return;
            }

            // Aqui vamos verifica se tem no estoque antes de adicionar. Além do mais,
            // ninguém irá ser enganado
            if (controladorEstoque.verificarDisponibilidade(produto, quantidade)) {
                itensPedido.add(new ItemPedido(produto, quantidade));
                total += produto.getPreco() * quantidade; // Atualiza o total pedido
                System.out.println("Produto " + produto.getNome() + " adicionado ao pedido. Quantidade: " + quantidade);
            }
            // temos avisos aqui em baixo, em cima também. Porém, são diferentes
            else {
                System.out.println("Estoque insuficiente para " + produto.getNome());
            }
        } catch (Exception e) {
            System.out.println("Erro ao adicionar produto: " + e.getMessage());
        }
    }

    public void finalizarPedido() {
        try {
            // O que vai acontecer aqui é o seguinte: vamos diminuir no estoque o que foi
            // pedido
            for (ItemPedido item : itensPedido) {
                controladorEstoque.removerProduto(item.getProduto().getNome(), item.getQuantidade());
            }
            System.out.println("Pedido #" + id + " finalizado com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao finalizar pedido: " + e.getMessage());
        }
    }

    public void exibirPedido() {
        System.out.println("Pedido #" + id + " do cliente: " + cliente.getNome());
        System.out.println("Produtos no pedido:");
        for (ItemPedido item : itensPedido) {
            System.out.println("- " + item.getProduto().getNome() + " | Quantidade: " + item.getQuantidade() + " | R$ "
                    + item.getProduto().getPreco()); // Detalhamento
        }
        System.out.println("Total do pedido: R$ " + total); // 'Pague o aluguel' informa sobre
    }

    public double getTotal() {
        return total;
    }

    // Declara o manipulador como atributo da classe, porque o pedido vai precisar
    // de um mordomo (String string)
    ManipularArquivoVendas manipulador = new ManipularArquivoVendas();

    public String formatarArqVendas() {
        StringBuilder sb = new StringBuilder(); // Aqui começamos a construir a string do arquivo CSV
        sb.append(id).append(";");
        sb.append(cliente.getNome()).append(";");

        for (ItemPedido item : itensPedido) {
            sb.append(item.getProduto().getNome()).append("-");
            sb.append(item.getQuantidade()).append(",");
        }

        sb.append(";").append(total); // pedido total
        return sb.toString();
    }

    public void salvarPedidoCSV() {
        // Aqui 'podemos perceber que:' mandaremos o pedido pro arquivo. Além do mais,
        // tudo é documentado
        manipulador.escreverCSV(this);
    }
}
