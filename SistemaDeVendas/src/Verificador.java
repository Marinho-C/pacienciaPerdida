import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;

import excessoes.*;
import excessoes.InvalidCPFException;
import excessoes.InvalidCodigoException;
import excessoes.InvalidEmailException;
import excessoes.InvalidEnderecoException;
import excessoes.InvalidPrecoException;
import excessoes.InvalidTelefoneException;
import excessoes.InvalidValorException;
import excessoes.LimiteEstoqueException;
import excessoes.ProdutoVencidoException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

//CLASSE CRIADA PARA FACILITAR E PREVENIR MUITA REPETICAO DE CODIGO DESNESCESSARIA!!!!
public class Verificador {

    public void verificarDataValidade(String dataDeValidade) throws ProdutoVencidoException {
        // CONSTANTE PARA DECLARAR FORMATACAO!!
        DateTimeFormatter FORMATO = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        // checando se a data esta correta
        try {
            LocalDate dataValida = LocalDate.parse(dataDeValidade, FORMATO);
            if (dataValida.isBefore(LocalDate.now())) {
                throw new ProdutoVencidoException("Produto ja esta vencido!");
            }
        } catch (DateTimeParseException e) {
            System.out.println("Data invalida!");
        }
    }

    public void verificarCodigo(int codigo) throws InvalidCodigoException {
        if (codigo < 1000 && codigo > 9999) { // checa se o codigo é valido (possue 4 digitos)
            throw new InvalidCodigoException("Digite um codigo valido!");
        }
    }

    // classes p/ checagem autoexplicativas
    public void verificarNome(String nome) throws InvalidNameException {
        if (nome.isBlank()) {
            throw new InvalidNameException("Nao pode estar vazio!");
        }
    }

    public void verificarPreco(double preco) throws InvalidPrecoException {
        if (preco < 0) {
            throw new InvalidPrecoException("Preco nao pode ser negativo!");
        }
    }

    public void verificarQuantidade(int quantidade, int limiteEstoque) throws InvalidQuantidadeException {
        if (quantidade < 0 || quantidade > limiteEstoque) {
            throw new InvalidQuantidadeException("Quantidade Invalida!");
        }
    }

    public void verificarLimiteDeEstoque(int limiteEstoque, int quantidade) throws LimiteEstoqueException {
        if (limiteEstoque < 0 || limiteEstoque < quantidade) {
            throw new LimiteEstoqueException("Limite de estoque invalido!");
        }
    }

    public void verificarResposta(String opcao) throws IllegalArgumentException {
        if (opcao.length() != 1) {
            ;
            throw new IllegalArgumentException("Digite apenas um caractere!");
        }
        if (opcao.charAt(0) != 's' && opcao.charAt(0) != 'n') {
            throw new IllegalArgumentException("Digite apenas 's' ou 'n'!");
        }
    }

    // O que é o matches? é um metodo que ajuda a saber se os dados que colocamos
    // seguem o formato 'padrão' exigido
    // Método p/ verificar CPF (deve conter exatamente 11 dígitos)
    public void verificarCPF(String cpf) throws InvalidCPFException {
        if (!cpf.matches("\\d{11}")) {
            throw new InvalidCPFException("CPF inválido! Deve conter exatamente 11 dígitos numéricos.");
        }
    }

    // Metodo p/ telefone
    public void verificarTelefone(String telefone) throws InvalidTelefoneException {
        // temos obrigação 2 digitos DDD, espaço e os outros 9
        if (!telefone.matches("\\d{2} \\d{9}")) {
            throw new InvalidTelefoneException("Telefone inválido! Use o formato XX XXXXXXXXX.");
        }

    }

    // Método p/ verificar e-mail no padrão básico de e-mail válido
    public void verificarEmail(String email) throws InvalidEmailException {
        String regex = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$";
        if (!Pattern.matches(regex, email)) {
            throw new InvalidEmailException(
                    "E-mail inválido! Insira um e-mail no formato correto (exemplo: usuario@email.com).");
        }
    }

    // Método p/ verificar endereço. Novamente, não pode ser vazio
    public void verificarEndereco(String endereco) throws InvalidEnderecoException {
        if (endereco == null || endereco.trim().isEmpty()) {
            throw new InvalidEnderecoException("Endereço inválido! O campo não pode estar vazio.");
        }
    }

    // Método p/ verificar valores monetários (não podem ser negativos)
    public void verificarValor(double valor) throws InvalidValorException {
        if (valor < 0) {
            throw new InvalidValorException("Valor inválido! O valor não pode ser negativo.");
        }
    }

    // Método p/ verificar a quantidade de itens e eles não podem ser negativos
    public void verificarQuantidade(int quantidade) throws InvalidQuantidadeException {
        if (quantidade < 0) {
            throw new InvalidQuantidadeException("Quantidade inválida! Não pode ser negativa.");
        }
    }

}