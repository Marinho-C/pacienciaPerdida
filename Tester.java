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
                    // Ai é VIP, ai que tá bom. A pessoa vai ganhar desconto
                    novoCliente = new ClienteVip(nome, cpf, endereco, telefone, email, senha);
                    System.out.println("Cliente VIP cadastrado com sucesso!");
                } else {
                    // Ai não é Vip, não tem problema, fazemos o cadastro também
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

            // Aqui pergunta se deseja cadastrar um novo cliente
            System.out.print("\nDeseja cadastrar outro cliente? (S/N): ");
            String resposta = scanner.nextLine();
            if (resposta.equalsIgnoreCase("N")) {
                continuar = false; // se não quiser vamos embora. Acaba o loop
            }
        }

        // O que é isso? É o menu para gerenciar os clientes:
        int opcao;
        do {
            System.out.println("\n---- MENU ----");
            System.out.println("1 - Listar Clientes");
            System.out.println("2 - Buscar Cliente por ID");
            System.out.println("3 - Remover Cliente por ID");
            System.out.println("4 - Verificar se é VIP");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            // Aqui é a checagem pra ver se o pessoal vai errar na hora de digitar.
            try {
                opcao = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida! Digite um número.");
                opcao = -1; // Força a repetição do menu
            }

            switch (opcao) {
                case 1:
                    // Lista todos os clientes:
                    gerenciamento.listarClientes();
                    break;
                case 2:
                    // Busca um cliente pelo ID:
                    System.out.print("Digite o ID do cliente: ");
                    String idBusca = scanner.nextLine(); // Agora é int, não String

                    CadastroCliente encontrado = gerenciamento.buscarCliente(String.valueOf(idBusca));
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
                case 4:
                    System.out.print("Digite o CPF do cliente para verificar se é VIP: ");
                    String cpfVip = scanner.nextLine();
                    if (gerenciamento.verificarClienteVip(cpfVip)) {
                        System.out.println("O cliente é VIP!");
                    } else {
                        System.out.println("O cliente não é VIP.");
                    }
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
