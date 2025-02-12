import java.io.*;
import java.util.List;

public class ManipularArquivo {
    private String nomeArquivo;
    private ControladorDeEstoque controlador; // instancia privada do manipulador para ser usada


    //construtor base
    public ManipularArquivo(String nomeArquivo, ControladorDeEstoque controlador){
        this.nomeArquivo = nomeArquivo;
        this.controlador = controlador;
        criarArquivo();
    }

    private void criarArquivo(){ //cria arquivo que é chamado somente no construtor
        File arquivo = new File(nomeArquivo);
        try{
            if(arquivo.createNewFile()){
                System.out.println("Arquivo criado com sucesso: "  + arquivo.getName());
            }
        }catch(IOException e){
            System.out.println("Erro ao ler arquivo! " + e.getMessage());
        }
    }

    public void carregarProdutos() { // le o arquivo csv ja existente e carrega o que tem nele para as variaveis do programa
        try (BufferedReader maca = new BufferedReader(new FileReader(nomeArquivo))) {
            String linha;
            boolean cabecalho = true;  
            int maiorCodigo = 1000; 
            while ((linha = maca.readLine()) != null) {
                if (cabecalho) {
                    cabecalho = false;
                    continue; // Pula o cabeçalho
                }
                //apos verificar a linha, atribue o conteudo da linha (que estao separadas por ",") às variaveis
                String[] dados = linha.split(","); 
                int codigo = Integer.parseInt(dados[0]);
                if(codigo > maiorCodigo){
                    maiorCodigo = codigo;
                }
                String nome = dados[1];
                int quantidade = Integer.parseInt(dados[2]);
                double preco = Double.parseDouble(dados[3]);
                String descricao = dados[4].isEmpty() ? null : dados[4];;
                int limiteEstoque = Integer.parseInt(dados[5]);
                String dataDeValidade = dados.length > 6 ? dados[6] : null;
                //checa se foram apenas 5 variaveis, se sim, setta data de validade como null, indicando prod nao perecivel
    
                Produto produto;
                if (dataDeValidade != null && !dataDeValidade.isEmpty()) { // checa se dvalidade for nula, se nao, é perecivel
                    produto = new ProdutoPerecivel(nome, quantidade, preco, dataDeValidade, limiteEstoque);
                } else {// cria produto comum
                    produto = new Produto(nome, quantidade, preco, limiteEstoque);
                }
                produto.setCodigo(codigo);
                produto.setDescricao(descricao);
                Produto.setProximoCodigo(maiorCodigo + 1);
                controlador.aumentarLista(produto);//adiciona o produto carregado do arquivo a lista do controlador para ser modificado
            }
        }catch (IOException e) {
            System.out.println("Erro ao ler arquivo: " + e.getMessage());
        }
    }

    public void salvarArquivo() {
        List<Produto> produtos = controlador.getProdutos();
        try (BufferedWriter pera = new BufferedWriter(new FileWriter(nomeArquivo, false))) {
            // escreve o cabeçalho do CSV
            pera.write("Código,Nome,Quantidade,Preço,Descrição,Limite de Estoque,Data de Validade");
            pera.newLine();
    
            // escreve os dados de cada produto
            for (Produto produto : produtos) {
                String descricao = produto.getDescricao() != null ? produto.getDescricao() : ""; 
                String linha = produto.getCodigo() + "," +
                               produto.getNome() + "," +
                               produto.getQuantidade() + "," +
                               produto.getPreco() + "," +
                               descricao + "," +
                               produto.getLimiteEstoque();

    
                // se o produto for perecível, adiciona a data de validade
                if (produto instanceof ProdutoPerecivel) {
                    linha += "," + ((ProdutoPerecivel) produto).getDataDeValidade();
                } else {
                    linha += ","; // adiciona uma vírgula vazia para produtos não perecíveis
                }
                pera.write(linha);
                pera.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao sobrescrever o arquivo: " + e.getMessage());
        }
    }

    public void imprimirDados() {
        try (BufferedReader alface = new BufferedReader(new FileReader(nomeArquivo))) {
            String linha;
            while ((linha = alface.readLine()) != null) {
                System.out.println(linha);
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler arquivo:" + e.getMessage());
        }
    }

}
