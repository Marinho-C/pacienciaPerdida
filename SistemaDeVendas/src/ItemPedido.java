public class ItemPedido {
    private Produto produto; // Ah, mas por que armazenar o produto aqui? Porque cada item do pedido precisa
                             // saber de qual produto se trata!
    private int quantidade; // E a quantidade? Sem isso, como vamos saber quantos foram comprados?

    // Construtor para inicializar o item do pedido
    public ItemPedido(Produto produto, int quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;
    }

    // Método para recuperar o produto associado ao item
    public Produto getProduto() {
        return produto;
    }

    // Método para recuperar a quantidade do produto no item
    public int getQuantidade() {
        return quantidade;
    }

    // Método para alterar a quantidade do produto
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade; // Isso aqui permite modificar a quantidade caso necessário
    }

    // Método para calcular o subtotal do item (preço * quantidade)
    public double calcularSubtotal() {
        return produto.getPreco() * quantidade; // Aqui acontece a conta => o preço do produto pela quantidade comprada
    }

    // Método para recuperar o nome do produto
    public String getNomeProduto() {
        return produto.getNome();
    }

    // Método para exibir as informações do item de forma formatada
    public void exibirItem() {
        System.out.println("Produto: " + produto.getNome());
        System.out.println("Quantidade: " + quantidade);
        System.out.println("Subtotal: R$ " + calcularSubtotal());
    }
}
