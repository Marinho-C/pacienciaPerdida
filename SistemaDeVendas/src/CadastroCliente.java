
public class CadastroCliente extends Cliente {

    // atributos:
    private static int contadorClientes = 1000; // Começa de 1000 e vai aumentando
    private String idCliente; // Esse ID é gerado automaticamente, nem precisa se preocupar.
    private String enderecoCliente; // Onde o cliente mora, uai!
    private String telefoneCliente; // O número pra ligar pro cliente.
    private String emailCliente; // O e-mail do cliente, pra mandar umas promoções.
    private String senhaCliente; // A senha do cliente

    private Verificador verificador; // objeto (famoso fiscal)

    // construtores:
    public CadastroCliente(String nome, String cpf, String enderecoCliente, String telefoneCliente,
            String emailCliente, String senhaCliente) throws Exception {
        super(nome, cpf);
        verificador = new Verificador(); // Inicia o fiscal pra verificar tudo
        verificador.verificarNome(nome);
        verificador.verificarCPF(cpf);
        verificador.verificarEndereco(enderecoCliente);
        verificador.verificarTelefone(telefoneCliente); // Olha se o telefone tá no formato certo.
        verificador.verificarEmail(emailCliente); // Verifica se o e-mail é válido.

        // Por que usamos String.valueOf()?
        // Porque precisavamos transformar o contador de clientes (um número) em uma
        // String para armazenar como ID do cliente....
        this.idCliente = String.valueOf(contadorClientes++);
        this.enderecoCliente = enderecoCliente;
        this.telefoneCliente = telefoneCliente;
        this.emailCliente = emailCliente;
        this.senhaCliente = senhaCliente;
    }

    // gets & sets:

    public String getIdCliente() {
        return idCliente; // Só retorna o ID do cliente, sem frescura.
    }
    // oxi, por que não temos o set no ID? Porque essa trem está criando automatico

    public String getEnderecoCliente() {
        return enderecoCliente; // Retorna o endereço do cliente.
    }

    public void setEnderecoCliente(String enderecoCliente) throws Exception {
        verificador.verificarEndereco(enderecoCliente); // Verifica se o endereço tá certo antes de atualizar.
        this.enderecoCliente = enderecoCliente; // Atualiza o endereço.
    }

    public String getTelefoneCliente() {
        return telefoneCliente;
    }

    public void setTelefoneCliente(String telefoneCliente) throws Exception {
        verificador.verificarTelefone(telefoneCliente);
        this.telefoneCliente = telefoneCliente;
    }

    public String getEmailCliente() {
        return emailCliente;
    }

    public void setEmailCliente(String emailCliente) throws Exception {
        verificador.verificarEmail(emailCliente);
        this.emailCliente = emailCliente;
    }

    public String getSenhaCliente() {
        return senhaCliente;
    }

    // Oxi, por que o set da senha não tem 'throws Exception'? Porque a senha pode
    // ser qualquer coisa
    public void setSenhaCliente(String senhaCliente) {
        this.senhaCliente = senhaCliente;
    }

    // Método pra mostrar os dados do cliente:

    @Override

    public String toString() {
        return String.format("ID: %s | Nome: %s | Contato: %s", idCliente, this.getNome(), telefoneCliente);
    }

}