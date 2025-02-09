package excessoes;

// Exceção para telefone inválido
public class InvalidTelefoneException extends Exception {
    public InvalidTelefoneException(String mensagem) {
        super(mensagem);
    }
}
