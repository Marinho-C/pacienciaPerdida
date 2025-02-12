package excessoes;
public class InvalidNameException extends Exception{
    public InvalidNameException(){
    }

    public InvalidNameException(String msg){
        super(msg);
    }
}
