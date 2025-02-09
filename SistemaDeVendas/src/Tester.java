import java.util.Scanner;

public class Tester {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Pra quê esse scanner? Pra ler o que o usuário digitar
        GerenciamentoCliente gerenciamento = new GerenciamentoCliente(); // Cria o gerenciador de clientes
        boolean continuar = true;

        while (continuar) { // Loop pra cadastrar vários clientes:
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
                    // Ai é VIP, então vai cria um cliente VIP
                    novoCliente = new ClienteVip(nome, cpf, endereco, telefone, email, senha);
                    System.out.println("Cliente VIP cadastrado com sucesso!");
                } else {
                    // Ai não é Vip, então vai cria um cliente comum
                    novoCliente = new CadastroCliente(nome, cpf, endereco, telefone, email, senha);
                    System.out.println("Cliente comum cadastrado com sucesso!");
                }

                // Adiciona o cliente à lista de gerenciamento
                gerenciamento.addCadastroCliente(novoCliente);

                // Verificar se o cliente é VIP
                if (gerenciamento.verificarClienteVip(cpf)) {
                    System.out.println("O cliente " + nome + " é VIP!");
                } else {
                    System.out.println("O cliente " + nome + " não é VIP.");
                }

            } catch (Exception e) {
                // E se der algum erro? Vai aparecer o aviso:
                System.out.println("Erro ao cadastrar cliente: " + e.getMessage());
            }

            // Aqui pergunta se deseja cadastrar um novo cliente
            System.out.print("\nDeseja cadastrar outro cliente? (s/n): ");
            String resposta = scanner.nextLine();
            if (resposta.equalsIgnoreCase("n")) {
                continuar = false; // se não quiser vamos embora. Acaba o loop
            }
        }

        // O que é isso? Um menu, para quê? Para depois de cadastrar olhar
        int opcao;
        do {
            System.out.println("\n=== MENU ===");
            System.out.println("1 - Listar Clientes");
            System.out.println("2 - Buscar Cliente por ID");
            System.out.println("3 - Remover Cliente por ID");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            opcao = Integer.parseInt(scanner.nextLine()); // O porque de usar 'parse' é para evita erro ao misturar
                                                          // nextInt() e nextLine()

            switch (opcao) {
                case 1:
                    // Lista todos os clientes:
                    gerenciamento.listarClientes();
                    break;
                case 2:
                    // Busca um cliente pelo ID:
                    System.out.print("Digite o ID do cliente: ");
                    String idBusca = scanner.nextLine();
                    CadastroCliente encontrado = gerenciamento.buscarCliente(idBusca);
                    if (encontrado != null) {
                        System.out.println("Cliente encontrado: " + encontrado);
                    } else {
                        System.out.println("Cliente não encontrado.");
                    }
                    break;
                case 3:
                    System.out.print("Digite o ID do cliente para remover: ");
                    String idRemove = scanner.nextLine();
                    gerenciamento.removerCliente(idRemove);
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
}