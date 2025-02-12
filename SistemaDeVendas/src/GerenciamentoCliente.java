import java.util.ArrayList;
import java.util.List;

public class GerenciamentoCliente {
    // Lista pra guardar os clientes cadastrados:
    private List<CadastroCliente> clientes;

    // Construtor:
    public GerenciamentoCliente() {
        this.clientes = new ArrayList<>(); // Inicia a lista de clientes
    }

    // Método pra adicionar um cliente na lista:
    public void addCadastroCliente(CadastroCliente clientes) {
        this.clientes.add(clientes);
        System.out.println("Cliente " + clientes.getNome() + " adicionado com sucesso!!");
    }

    // Método pra listar todos os clientes:
    public void listarClientes() {
        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado.");
            return;
        }

        System.out.println("Lista de Clientes:");
        for (CadastroCliente cliente : clientes) {
            System.out.println("ID: " + cliente.getIdCliente() + " - Nome: " + cliente.getNome());
        }
    }

    public CadastroCliente buscarClientePorCPF(String cpf) {
        for (CadastroCliente cliente : clientes) {
            if (cliente.getCpf().equals(cpf)) { // Busca pelo CPF
                return cliente;
            }
        }
        return null; // Se não achar, retorna null.
    }

    public boolean verificarClienteVip(String cpf) {
        CadastroCliente cliente = buscarClientePorCPF(cpf);
        if (cliente != null && cliente instanceof ClienteVip) {
            return true; // O cliente é VIP
        }
        return false; // O cliente não é VIP
    }

}