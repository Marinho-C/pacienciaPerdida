import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ManipularArquivoVendas {
    private String nomeArquivo = "vendas.csv";

    // Método para escrever um pedido no CSV
    public void escreverCSV(Pedido pedido) {
        try (FileWriter escrever = new FileWriter(nomeArquivo, true);
                BufferedWriter bw = new BufferedWriter(escrever)) {
            bw.write(pedido.formatarArqVendas());
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para ler todos os pedidos do CSV
    public List<String> lerCSV() {
        List<String> pedidos = new ArrayList<>();
        try (BufferedReader ler = new BufferedReader(new FileReader(nomeArquivo))) {
            String linha;
            while ((linha = ler.readLine()) != null) {
                pedidos.add(linha);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pedidos;
    }

    // Método para atualizar um pedido no CSV (baseado no ID)
    public void atualizarCSV(int idPedido, String novaLinha) {
        List<String> linhas = new ArrayList<>();
        try (BufferedReader ler = new BufferedReader(new FileReader(nomeArquivo))) {
            String linha;
            while ((linha = ler.readLine()) != null) {
                if (linha.startsWith(idPedido + ";")) {
                    linha = novaLinha;
                }
                linhas.add(linha);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (FileWriter escritor = new FileWriter(nomeArquivo);
                BufferedWriter bw = new BufferedWriter(escritor)) {
            for (String l : linhas) {
                bw.write(l);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void gerarRelatorio(String periodo) {
        // Leitura dos pedidos do arquivo CSV
        List<String> pedidosCSV = lerCSV();

        // Armazenar os pedidos filtrados por período
        List<String> pedidosNoPeriodo = new ArrayList<>();

        // Iterar sobre os pedidos e filtrar com base no período
        for (String pedidoLinha : pedidosCSV) {
            String[] campos = pedidoLinha.split(";"); // Separando a linha por ';'

            if (campos.length >= 2) {
                String dataPedido = campos[1]; // A data está no segundo campo

                // Aqui, usamos startsWith() para garantir que a data comece com o mês/ano
                if (dataPedido.startsWith(periodo)) {
                    pedidosNoPeriodo.add(pedidoLinha);
                }
            }
        }

        // Gerar o relatório com os pedidos no período
        if (pedidosNoPeriodo.isEmpty()) {
            System.out.println("Não há pedidos no período " + periodo);
        } else {
            System.out.println("Relatório de Vendas - Período: " + periodo);
            for (String pedido : pedidosNoPeriodo) {
                System.out.println(pedido);
            }
        }
    }

}
