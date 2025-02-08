package excessoes;

// Exceção para e-mail inválido
public class InvalidEmailException extends Exception {
    public InvalidEmailException(String mensagem) {
        super(mensagem);
    }
}
