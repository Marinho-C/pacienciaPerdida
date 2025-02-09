package excessoes;

// Exceção para nome inválido
public class InvalidNameException extends Exception {
    public InvalidNameException(String mensagem) {
        super(mensagem);
    }
}
