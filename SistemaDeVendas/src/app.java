import java.util.Scanner;


public class app {
    public static void main(String[] args){
        ControladorDeEstoque controlador = new ControladorDeEstoque();
        AtualizarEstoque atualizador = new AtualizarEstoque(controlador);
        ManipularArquivo manipulador = new ManipularArquivo("produtos.csv", controlador);
        Scanner scanner = new Scanner(System.in);
        manipulador.carregarProdutos();
       
        while(true){
            System.out.println("Menu:\n1-adicionar produtos\n2-excluir produto\n3-Exibir produtos\n4-salvar e sair");
            int opcao = scanner.nextInt();
            scanner.nextLine();
            switch(opcao){
                case 1:
                controlador.adicionarProduto();
                break;

                case 2:
                controlador.excluirProduto();
                break;

                case 3:
                controlador.imprimirProdutos(controlador);
                break;
                case 4:
                manipulador.salvarArquivo();
                scanner.close();
                System.exit(0);

                case 5:
                atualizador.atualizarNome();
                break;
                default:
                System.out.println("Opçao invalida! tente novamente");
            }
        }
    }
}
