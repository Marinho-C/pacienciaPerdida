package excessoes;

// Exceção para valores monetários inválidos
public class InvalidValorException extends Exception {
    public InvalidValorException(String mensagem) {
        super(mensagem);
    }
}