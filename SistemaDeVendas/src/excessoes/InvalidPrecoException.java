package excessoes;
public class InvalidPrecoException extends Exception{
    public InvalidPrecoException(){
    }

    public InvalidPrecoException(String msg){
        super(msg);
    }
}