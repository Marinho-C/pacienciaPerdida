package excessoes;
public class ProdutoVencidoException extends Exception{
    public ProdutoVencidoException(){
    }

    public ProdutoVencidoException(String msg){
        super(msg);
    }
}
