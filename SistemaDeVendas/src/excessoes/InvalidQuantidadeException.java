package excessoes;
public class InvalidQuantidadeException extends Exception{
    public InvalidQuantidadeException(){
    }

    public InvalidQuantidadeException(String msg){
        super(msg);
    }
}
