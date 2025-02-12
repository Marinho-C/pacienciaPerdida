package excessoes;
public class LimiteEstoqueException extends Exception{
    public LimiteEstoqueException(){
    }

    public LimiteEstoqueException(String msg){
        super(msg);
    }

}
