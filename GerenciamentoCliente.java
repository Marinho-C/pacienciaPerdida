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

    // Método pra buscar um cliente pelo ID:
    public CadastroCliente buscarCliente(String id) {
        for (CadastroCliente cliente : clientes) {
            if (cliente.getIdCliente().equals(id)) { // Se o ID bater, retorna o cliente
                return cliente;
            }
        }
        return null; // Se não achar, retorna null.
    }

    // Método pra remover um cliente pelo ID:
    public boolean removerCliente(String id) {
        CadastroCliente clienteEncontrado = buscarCliente(id);
        if (clienteEncontrado != null) {
            clientes.remove(clienteEncontrado);
            System.out.println("Cliente removido com sucesso.");
            return true;
        } else {
            System.out.println("Cliente não encontrado.");
            return false;
        }
    }

    // Método pra verificar se o cliente é VIP:
    public boolean verificarClienteVip(String cpf) {
        for (CadastroCliente cliente : clientes) { // Agora percorre CadastroCliente
            if (cliente.getCpf().equals(cpf) && cliente instanceof ClienteVip) {
                return true; // O cliente é VIP
            }
        }
        return false; // O cliente não é VIP
    }

}