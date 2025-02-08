package excessoes;

// Exceção para CPF inválido
public class InvalidCPFException extends Exception {
    public InvalidCPFException(String mensagem) {
        super(mensagem);
    }
}