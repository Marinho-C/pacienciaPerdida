import java.io.*;
import java.util.List;

public class ManipularArquivoVendas {
    private String nomeArquivo;
    private GerenciamentoCliente gerenciamentoCliente;
    private GerenciamentoPedido gerenciamentoPedido;
    private ControladorDeEstoque controladorDeEstoque;

    // Construtor atualizado para incluir o ControladorDeEstoque
    public ManipularArquivoVendas(String nomeArquivo, GerenciamentoCliente gerenciamentoCliente,
            GerenciamentoPedido gerenciamentoPedido, ControladorDeEstoque controladorDeEstoque) {
        this.nomeArquivo = nomeArquivo;
        this.gerenciamentoCliente = gerenciamentoCliente;
        this.gerenciamentoPedido = gerenciamentoPedido;
        this.controladorDeEstoque = controladorDeEstoque;
        criarArquivo();
    }

    // Metodo:
    // Cria arquivos se eles não existirem
    private void criarArquivo() {
        File arquivo = new File(nomeArquivo); // Estamos criando o arquivo de vendas
        try {
            if (arquivo.createNewFile()) { // será que arquivo vai ser criado?
                System.out.println("Arquivo criado com sucesso: " + arquivo.getName()); // queremos aplausos, o arquvo
                                                                                        // foi criado
            }
        } catch (IOException e) {
            System.out.println("Erro ao criar arquivo: " + e.getMessage()); // infelizmente não deu
        }
    }

    // Agora vamos carregar os pedidos salvos no arquivo
    public void carregarPedidos() {
        try (BufferedReader reader = new BufferedReader(new FileReader(nomeArquivo))) {
            String linha;
            boolean cabecalho = true;

            while ((linha = reader.readLine()) != null) {
                if (cabecalho) {
                    cabecalho = false;
                    continue; // Pula o cabeçalho
                }

                String[] dados = linha.split(",");
                if (dados.length < 4) {
                    System.out.println("Linha inválida no arquivo de pedidos, ignorando...");
                    continue;
                }

                try {
                    // Aqui a gente pega os dados e transforma em informações (como o CPF do
                    // cliente e o nome do produto)
                    int codigoPedido = Integer.parseInt(dados[0]);
                    String cpfCliente = dados[1];
                    String nomeProduto = dados[2];
                    int quantidade = Integer.parseInt(dados[3]);

                    // aqui, nesse momento, procuraremos o cliente e o produto
                    Cliente cliente = gerenciamentoCliente.buscarCliente(cpfCliente);
                    if (cliente == null) {
                        System.out.println("Cliente com CPF " + cpfCliente + " não encontrado. Pedido ignorado.");
                        continue;
                    }

                    Produto produto = controladorDeEstoque.buscarProduto(nomeProduto);
                    if (produto == null) { // se não tem pedido, não tem pedido (lógica)
                        System.out.println("Produto " + nomeProduto + " não encontrado no estoque. Pedido ignorado.");
                        continue;
                    }

                    // Criamos o pedido e colocamos os itens nele
                    Pedido pedido = new Pedido(codigoPedido, cliente);
                    pedido.adicionarItem(new ItemPedido(produto, quantidade));
                    gerenciamentoPedido.adicionarPedido(pedido);
                } catch (NumberFormatException e) {
                    System.out.println("Erro ao converter valores no arquivo de pedidos: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler arquivo de pedidos: " + e.getMessage());
        }
    }

    public void salvarPedidos() {
        List<Pedido> pedidos = gerenciamentoPedido.getPedidos();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivo, false))) {
            writer.write("Código Pedido,CPF Cliente,Nome Produto,Quantidade");
            writer.newLine();

            for (Pedido pedido : pedidos) {
                for (ItemPedido item : pedido.getItens()) {
                    String linha = pedido.getCodigoPedido() + "," +
                            pedido.getCliente().getCpf() + "," +
                            item.getProduto().getNome() + "," +
                            item.getQuantidade();
                    writer.write(linha);
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar pedidos no arquivo: " + e.getMessage());
        }
    }

    public void imprimirPedidos() {
        try (BufferedReader reader = new BufferedReader(new FileReader(nomeArquivo))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                System.out.println(linha);
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler pedidos: " + e.getMessage());
        }
    }
}
