import java.util.List;
import java.util.Scanner;

public class Tester {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Pra quê esse scanner? Pra ler o que o usuário digitar
        GerenciamentoCliente gerenciamento = new GerenciamentoCliente(); // Cria o gerenciador de clientes
        ControladorDeEstoque controladorEstoque = new ControladorDeEstoque();

        boolean continuar = true;

        // Loop pra cadastrar vários clientes:
        while (continuar) {
            try {
                System.out.println("\n--- Cadastro de Cliente ---");
                System.out.print("Digite o seu nome e sobrenome (ex.: Jurema Batista): ");
                String nome = scanner.nextLine();
                System.out.print("Digite o seu CPF (somente números, ex.: 12345678901): ");
                String cpf = scanner.nextLine();
                System.out.print("Digite seu endereço (ex.: Rua Pedra Branca): ");
                String endereco = scanner.nextLine();
                System.out.print("Digite seu telefone (ex.: (11) 98765-4321): ");
                String telefone = scanner.nextLine();
                System.out.print("Digite seu e-mail: ");
                String email = scanner.nextLine();
                System.out.print("Digite sua senha: ");
                String senha = scanner.nextLine();
                System.out.print("O cliente será VIP? (S/N): ");
                String opcaoVip = scanner.nextLine();

                CadastroCliente novoCliente;
                if (opcaoVip.equalsIgnoreCase("S")) {
                    // Cliente VIP - ganha desconto
                    novoCliente = new ClienteVip(nome, cpf, endereco, telefone, email, senha);
                    System.out.println("Cliente VIP cadastrado com sucesso!");
                } else {
                    // Cliente comum
                    novoCliente = new CadastroCliente(nome, cpf, endereco, telefone, email, senha);
                    System.out.println("Cliente comum cadastrado com sucesso!");
                }

                // Adiciona o cliente à lista de gerenciamento
                gerenciamento.addCadastroCliente(novoCliente);

                // Verifica se o cliente é VIP e faz os avisos necessários
                if (gerenciamento.verificarClienteVip(cpf)) {
                    System.out.println("O cliente " + nome + " é VIP!");
                } else {
                    System.out.println("O cliente " + nome + " não é VIP.");
                }

            } catch (Exception e) {
                // E se der algum erro? Vai aparecer o aviso:
                System.out.println("Erro ao cadastrar cliente: " + e.getMessage());
            }

            // Pergunta se deseja cadastrar um novo cliente
            System.out.print("\nDeseja cadastrar outro cliente? (S/N): ");
            String resposta = scanner.nextLine();
            if (resposta.equalsIgnoreCase("N")) {
                continuar = false; // se não quiser, acaba o loop
            }
        }

        // Menu para gerenciar os clientes
        int opcao;
        do {
            System.out.println("\n---- MENU ----");
            System.out.println("1 - Listar Clientes");
            System.out.println("2 - Buscar Cliente por CPF");
            System.out.println("3 - Exibir Produtos Disponíveis");
            System.out.println("4 - Processar Pedido de Venda");
            System.out.println("5 - Gerar Relatório de Vendas");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            // Verifica se o usuário digitou um número válido
            try {
                opcao = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida! Digite um número.");
                opcao = -1; // Força a repetição do menu
            }

            // Ações conforme a opção escolhida
            switch (opcao) {
                case 1:
                    // Lista todos os clientes
                    gerenciamento.listarClientes();
                    break;
                case 2:
                    System.out.print("Digite o CPF do cliente: ");
                    String cpfBusca = scanner.nextLine();
                    CadastroCliente clienteEncontrado = gerenciamento.buscarClientePorCPF(cpfBusca);
                    if (clienteEncontrado != null) {
                        System.out.println("Cliente encontrado: " + clienteEncontrado);
                    } else {
                        System.out.println("Cliente não encontrado.");
                    }
                    break;
                case 3:
                    exibirProdutosDisponiveis(controladorEstoque);
                    break;
                case 4:
                    processarPedido(scanner, controladorEstoque, gerenciamento);
                    break;
                case 5:
                    System.out.println("\n--- Processamento de Pedido ---");
                    // Seleciona o cliente para o pedido
                    System.out.print("Digite o CPF do cliente que está fazendo o pedido: ");
                    String cpfPedido = scanner.nextLine();

                    // Verifica se o cliente existe
                    CadastroCliente clientePedido = gerenciamento.buscarClientePorCPF(cpfPedido);
                    if (clientePedido != null) {
                        Pedido pedido = new Pedido(clientePedido, controladorEstoque);
                        boolean pedidoEmAndamento = true;

                        // Loop para adicionar produtos ao pedido
                        while (pedidoEmAndamento) {
                            System.out.println("Digite o nome do produto ou 'sair' para finalizar o pedido: ");
                            String produtoNome = scanner.nextLine();

                            if (produtoNome.equalsIgnoreCase("sair")) {
                                pedidoEmAndamento = false; // Finaliza o pedido
                                pedido.finalizarPedido();
                                System.out.println("Pedido finalizado.");
                            } else {
                                System.out.print("Digite a quantidade: ");
                                int quantidade = scanner.nextInt();
                                scanner.nextLine(); // Limpa o buffer

                                // Verifica se o produto está disponível e adiciona
                                Produto produto = controladorEstoque.buscarProduto(produtoNome);
                                if (produto != null) {
                                    pedido.adicionarProduto(produto, quantidade);
                                } else {
                                    System.out.println("Produto não encontrado.");
                                }
                            }
                        }

                        // Salva o pedido em arquivo CSV
                        pedido.salvarPedidoCSV();
                        System.out.println("Pedido registrado com sucesso!");
                    } else {
                        System.out.println("Cliente não encontrado!");
                    }
                    break;
                case 6:
                    System.out.println("\n--- Gerar Relatório de Vendas ---");
                    // Gera o relatório de vendas
                    System.out.print("Digite o período do relatório (ex: 01/01/2025 a 31/01/2025): ");
                    String periodo = scanner.nextLine();

                    // Geração do relatório (feito por outra pessoa)
                    ManipularArquivoVendas manipuladorVendas = new ManipularArquivoVendas();
                    manipuladorVendas.gerarRelatorio(periodo);
                    break;
                case 0:
                    System.out.println("Saindo do sistema...");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        } while (opcao != 0);

        scanner.close(); // Fecha o scanner pra não gastar recurso à toa.

    }

    private static void exibirProdutosDisponiveis(ControladorDeEstoque controladorEstoque) {
        System.out.println("\n--- Produtos Disponíveis ---");
        List<Produto> produtos = controladorEstoque.getProdutos(); // Use getProdutos() ao invés de listarProdutos()
        if (produtos.isEmpty()) {
            System.out.println("Não há produtos disponíveis.");
        } else {
            for (Produto produto : produtos) {
                System.out.println(produto.getNome() + " - Preço: " + produto.getPreco());
            }
        }
    }

    // Método para processar o pedido de venda
    private static void processarPedido(Scanner scanner, ControladorDeEstoque controladorEstoque,
            GerenciamentoCliente gerenciamento) {
        System.out.println("\n--- Processamento de Pedido ---");
        System.out.print("Digite o CPF do cliente que está fazendo o pedido: ");
        String cpfPedido = scanner.nextLine();

        // Verifica se o cliente existe
        CadastroCliente clientePedido = gerenciamento.buscarClientePorCPF(cpfPedido);
        if (clientePedido != null) {
            Pedido pedido = new Pedido(clientePedido, controladorEstoque);
            boolean pedidoEmAndamento = true;

            // Loop para adicionar produtos ao pedido
            while (pedidoEmAndamento) {
                System.out.print("Digite o nome do produto ou 'sair' para finalizar o pedido: ");
                String produtoNome = scanner.nextLine();

                if (produtoNome.equalsIgnoreCase("sair")) {
                    pedidoEmAndamento = false; // Finaliza o pedido
                    pedido.finalizarPedido();
                    System.out.println("Pedido finalizado.");
                } else {
                    System.out.print("Digite a quantidade: ");
                    int quantidade = scanner.nextInt();
                    scanner.nextLine(); // Limpa o buffer

                    Produto produto = controladorEstoque.buscarProduto(produtoNome);
                    if (produto != null) {
                        pedido.adicionarProduto(produto, quantidade);
                        System.out.println("Produto adicionado ao pedido.");
                    } else {
                        System.out.println("Produto não encontrado.");
                    }
                }
            }

            // Salva o pedido em arquivo CSV
            pedido.salvarPedidoCSV();
            System.out.println("Pedido registrado com sucesso!");
        } else {
            System.out.println("Cliente não encontrado!");
        }
    }
}
