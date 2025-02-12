public class ProdutoPerecivel extends Produto{
    //instancia novo verificador para checar as variaveis
    Verificador verificador = new Verificador();
    //variaveis extras da classe
    private String dataDeValidade;

    //construtor base
    public ProdutoPerecivel(String nome, int quantidade, double preco, String dataDeValidade, int limiteEstoque){
        super(nome, quantidade, preco, limiteEstoque);
        this.dataDeValidade = dataDeValidade;
    }

    //gets e sets
    public String getDataDeValidade() {
        return dataDeValidade;
    }
    public void setDataDeValidade(String dataDeValidade) {
        this.dataDeValidade = dataDeValidade;
    }
}
