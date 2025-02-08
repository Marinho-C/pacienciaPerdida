import java.util.regex.Pattern;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import excessoes.*;

public class Verificador {

    // Método para verificar CPF (deve conter exatamente 11 dígitos numéricos)
    public void verificarCPF(String cpf) throws InvalidCPFException {
        if (!cpf.matches("\\d{11}")) {
            throw new InvalidCPFException("CPF inválido! Deve conter exatamente 11 dígitos numéricos.");
        }
    }

    // Método para verificar telefone (formato esperado: (XX)XXXXX-XXXX ou
    // (XX)XXXX-XXXX)
    public void verificarTelefone(String telefone) throws InvalidTelefoneException {
        if (!telefone.matches("\\(\\d{2}\\)\\d{4,5}-\\d{4}")) {
            throw new InvalidTelefoneException("Telefone inválido! Use o formato (XX)XXXXX-XXXX ou (XX)XXXX-XXXX.");
        }
    }

    // Método para verificar e-mail (padrão básico de e-mail válido)
    public void verificarEmail(String email) throws InvalidEmailException {
        String regex = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$";
        if (!Pattern.matches(regex, email)) {
            throw new InvalidEmailException(
                    "E-mail inválido! Insira um e-mail no formato correto (exemplo: usuario@email.com).");
        }
    }

    // Método para verificar nome (não pode estar vazio ou ter apenas espaços)
    public void verificarNome(String nome) throws InvalidNameException {
        if (nome == null || nome.trim().isEmpty()) {
            throw new InvalidNameException("Nome inválido! O campo não pode estar vazio.");
        }
    }

    // Método para verificar endereço (não pode estar vazio)
    public void verificarEndereco(String endereco) throws InvalidEnderecoException {
        if (endereco == null || endereco.trim().isEmpty()) {
            throw new InvalidEnderecoException("Endereço inválido! O campo não pode estar vazio.");
        }
    }

    // Método para verificar valores monetários (não podem ser negativos)
    public void verificarValor(double valor) throws InvalidValorException {
        if (valor < 0) {
            throw new InvalidValorException("Valor inválido! O valor não pode ser negativo.");
        }
    }

    // Método para verificar a quantidade de itens (não pode ser negativa)
    public void verificarQuantidade(int quantidade) throws InvalidQuantidadeException {
        if (quantidade < 0) {
            throw new InvalidQuantidadeException("Quantidade inválida! Não pode ser negativa.");
        }
    }

    // Método para verificar a validade de uma data
    public void verificarDataValidade(String dataDeValidade) throws ProdutoVencidoException {
        DateTimeFormatter FORMATO = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try {
            LocalDate dataValida = LocalDate.parse(dataDeValidade, FORMATO);
            if (dataValida.isBefore(LocalDate.now())) {
                throw new ProdutoVencidoException("Produto já está vencido!");
            }
        } catch (DateTimeParseException e) {
            System.out.println("Data inválida!");
        }
    }

    // Método para verificar se o código é válido (deve ter 4 dígitos)
    public void verificarCodigo(int codigo) throws InvalidCodigoException {
        if (codigo < 1000 || codigo > 9999) {
            throw new InvalidCodigoException("Código inválido! Deve conter exatamente 4 dígitos.");
        }
    }

    // Método para verificar se a resposta do usuário é válida ('s' ou 'n')
    public void verificarResposta(String opcao) throws IllegalArgumentException {
        if (opcao.length() != 1) {
            throw new IllegalArgumentException("Digite apenas um caractere!");
        }
        if (opcao.charAt(0) != 's' && opcao.charAt(0) != 'n') {
            throw new IllegalArgumentException("Digite apenas 's' ou 'n'!");
        }
    }
}
