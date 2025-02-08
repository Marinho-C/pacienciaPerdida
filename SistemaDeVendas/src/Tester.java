import java.util.Scanner;

public class Tester {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GerenciamentoCliente gerenciamento = new GerenciamentoCliente();
        boolean continuar = true;

        while (continuar) { // Loop para permitir múltiplos cadastros
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
                    novoCliente = new ClienteVip(nome, cpf, endereco, telefone, email, senha);
                    System.out.println("Cliente VIP cadastrado com sucesso!");
                } else {
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
                System.out.println("Erro ao cadastrar cliente: " + e.getMessage());
            }

            // Perguntar se deseja continuar cadastrando
            System.out.print("\nDeseja cadastrar outro cliente? (s/n): ");
            String resposta = scanner.nextLine();
            if (resposta.equalsIgnoreCase("n")) {
                continuar = false;
            }
        }

        int opcao;
        do {
            System.out.println("\n=== MENU ===");
            System.out.println("1 - Listar Clientes");
            System.out.println("2 - Buscar Cliente por ID");
            System.out.println("3 - Remover Cliente por ID");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            opcao = Integer.parseInt(scanner.nextLine()); // Evita erro ao misturar nextInt() e nextLine()

            switch (opcao) {
                case 1:
                    gerenciamento.listarClientes();
                    break;
                case 2:
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

        scanner.close();
    }
}

/*
 * // Criando alguns clientes fictícios
 * CadastroCliente cliente1 = new CadastroCliente(1, "Jurema da Silva",
 * "Sítio Pé de Manga",
 * "99999-1234", "jurema@email.com", 1234);
 * CadastroCliente cliente2 = new CadastroCliente(2, "Bastião do Caminhão",
 * "Rua dos Tratoristas, 77",
 * "98888-5678", "bastiao@email.com", 5678);
 * gerenciamento.addCadastroCliente(cliente1);
 * gerenciamento.addCadastroCliente(cliente2);
 * 
 */