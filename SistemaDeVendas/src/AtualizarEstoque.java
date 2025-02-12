import java.util.Scanner;

import excessoes.InvalidCodigoException;
import excessoes.InvalidNameException;
import excessoes.InvalidPrecoException;
import excessoes.InvalidQuantidadeException;
import excessoes.LimiteEstoqueException;
import excessoes.ProdutoVencidoException;

@SuppressWarnings("resource")
public class AtualizarEstoque {
    Verificador verificador = new Verificador();
    private ControladorDeEstoque controlador = new ControladorDeEstoque();

    public AtualizarEstoque(ControladorDeEstoque controlador) {
        this.controlador = controlador;
    }

    public void atualizarNome() {
        System.out.println("Digite o codigo do produto que quer renomear ou '0' para sair: ");
        Scanner scanner = new Scanner(System.in);
        boolean produtoEncontrado = false;
        int codigo = scanner.nextInt();
        scanner.nextLine();
        try {
            if (codigo == 0) {
                System.out.println("Saindo...");
            } else {
                verificador.verificarCodigo(codigo);
                for (Produto produto : controlador.getProdutos()) {
                    if (produto.getCodigo() == codigo) {
                        System.out.println("Tem certeza que quer renomear (" + produto.getNome() + ") (s/n)?");
                        String opcao = scanner.nextLine();
                        verificador.verificarResposta(opcao);
                        if (opcao.equals("n")) {
                            System.out.println("Saindo...");
                            return;
                        }
                        produtoEncontrado = true;
                        System.out.println("Digite um novo nome para o(a) (" + produto.getNome() + ") (0 para sair)");
                        String nome = scanner.nextLine();
                        verificador.verificarNome(nome);
                        produto.setNome(nome); // atualiza a quantidade do produto
                        System.out.println("Nome atualizado com sucesso!");
                        break; // produto encontrado, sai do loop
                    }
                }
                if (!produtoEncontrado) {
                    System.out.println("Produto com esse código nao existe!");
                }
            }
        } catch (InvalidCodigoException e) {
            System.out.println(e.getMessage());
        } catch (InvalidNameException e) {
            System.out.println(e.getMessage());
        }
    }

    public void atualizarQuantidade() {
        System.out.println("Digite o codigo do produto que quer reabastecer ou '0' para sair: ");
        Scanner scanner = new Scanner(System.in);
        boolean produtoEncontrado = false;
        int codigo = scanner.nextInt();
        scanner.nextLine();
        try {
            if (codigo == 0) {
                System.out.println("Saindo...");
            } else {
                verificador.verificarCodigo(codigo);
                for (Produto produto : controlador.getProdutos()) {
                    if (produto.getCodigo() == codigo) {
                        System.out.println("Tem certeza que quer reabastecer (" + produto.getNome() + ") (s/n)?");
                        String opcao = scanner.nextLine();
                        verificador.verificarResposta(opcao);
                        if (opcao.equals("n")) {
                            System.out.println("Saindo...");
                            return;
                        }
                        produtoEncontrado = true;
                        System.out.println("Digite a nova quantidade: ");
                        int quantidade = scanner.nextInt();
                        verificador.verificarQuantidade(quantidade, produto.getLimiteEstoque());
                        produto.setQuantidade(quantidade); // atualiza a quantidade do produto
                        System.out.println("Estoque atualizado com sucesso!");
                        break; // quantidade atualizada, sai do loop
                    }
                }
                if (!produtoEncontrado) {
                    System.out.println("Produto com esse código nao existe!");
                }
            }
        } catch (InvalidCodigoException e) {
            System.out.println(e.getMessage());
        } catch (InvalidQuantidadeException e) {
            System.out.println(e.getMessage());
        }
    }

    public void atualizarPreco() {
        System.out.println("Digite o codigo do produto que quer mudar o preco ou '0' para sair: ");
        Scanner scanner = new Scanner(System.in);
        boolean produtoEncontrado = false;
        int codigo = scanner.nextInt();
        scanner.nextLine();
        try {
            if (codigo == 0) {
                System.out.println("Saindo...");
            } else {
                verificador.verificarCodigo(codigo);
                for (Produto produto : controlador.getProdutos()) {
                    if (produto.getCodigo() == codigo) {
                        System.out.println("Tem certeza que quer mudar o preco de (" + produto.getNome() + ") (s/n)?");
                        String opcao = scanner.nextLine();
                        verificador.verificarResposta(opcao);
                        if (opcao.equals("n")) {
                            System.out.println("Saindo...");
                            return;
                        }
                        produtoEncontrado = true;
                        System.out.println("Digite um novo preço para este produto: ");
                        double preco = scanner.nextDouble();
                        verificador.verificarPreco(preco);
                        produto.setPreco(preco); // atualiza o preço do produto
                        System.out.println("Preco atualizado com sucesso!");
                        break; // preço atualizado, sai do loop
                    }
                }
                if (!produtoEncontrado) {
                    System.out.println("Produto com esse código nao existe!");
                }
            }
        } catch (InvalidCodigoException e) {
            System.out.println(e.getMessage());
        } catch (InvalidPrecoException e) {
            System.out.println(e.getMessage());
        }
    }

    public void AtualizarLimite() {
        System.out.println("Digite o codigo do produto que quer mudar o limite de estoque ou '0' para sair: ");
        Scanner scanner = new Scanner(System.in);
        boolean produtoEncontrado = false;
        int codigo = scanner.nextInt();
        scanner.nextLine();
        try {
            if (codigo == 0) {
                System.out.println("Saindo...");
            } else {
                verificador.verificarCodigo(codigo);
                for (Produto produto : controlador.getProdutos()) {
                    if (produto.getCodigo() == codigo) {
                        System.out.println("Tem certeza que quer mudar limite de (" + produto.getNome() + ") (s/n)?");
                        String opcao = scanner.nextLine();
                        verificador.verificarResposta(opcao);
                        if (opcao.equals("n")) {
                            System.out.println("Saindo...");
                            return;
                        }
                        produtoEncontrado = true;
                        System.out.println("Digite um novo limite de estoque para este produto: ");
                        int limiteEstoque = scanner.nextInt();
                        verificador.verificarLimiteDeEstoque(limiteEstoque, produto.getQuantidade());
                        produto.setLimiteDeEstoque(limiteEstoque);// atualiza o limite de estoque do produto
                        System.out.println("Limite de estoque atualizado com sucesso!");
                        break; // limite de estoque atualizado, sai do loop
                    }
                }
                if (!produtoEncontrado) {
                    System.out.println("Produto com esse código nao existe!");
                }
            }
        } catch (InvalidCodigoException e) {
            System.out.println(e.getMessage());
        } catch (LimiteEstoqueException e) {
            System.out.println(e.getMessage());
        }
    }

    public void atualizarDescricao() {
        System.out.println("Digite o codigo do produto que quer mudar a descricao ou '0' para sair: ");
        Scanner scanner = new Scanner(System.in);
        boolean produtoEncontrado = false;
        int codigo = scanner.nextInt();
        scanner.nextLine();
        try {
            if (codigo == 0) {
                System.out.println("Saindo...");
            } else {
                verificador.verificarCodigo(codigo);
                for (Produto produto : controlador.getProdutos()) {
                    if (produto.getCodigo() == codigo) {
                        System.out
                                .println("Tem certeza que quer mudar descricao de (" + produto.getNome() + ") (s/n)?");
                        String opcao = scanner.nextLine();
                        verificador.verificarResposta(opcao);
                        if (opcao.equals("n")) {
                            System.out.println("Saindo...");
                            return;
                        }
                        produtoEncontrado = true;
                        System.out.println("Digite uma nova descricao para este produto: ");
                        String descricao = scanner.nextLine();
                        verificador.verificarNome(descricao);
                        produto.setDescricao(descricao); // atualiza a descrição do produto
                        System.out.println("Descricao atualizada com sucesso!");
                        break; // descriçao atualizada, sai do loop
                    }
                }
                if (!produtoEncontrado) {
                    System.out.println("Produto com esse código nao existe!");
                }
            }
        } catch (InvalidCodigoException e) {
            System.out.println(e.getMessage());
        } catch (InvalidNameException e) {
            System.out.println(e.getMessage());
        }
    }

    public void atualizarDataDeValidade() {
        System.out.println("Digite o codigo do produto que quer mudar a data de validade ou '0' para sair: ");
        Scanner scanner = new Scanner(System.in);
        boolean produtoEncontrado = false;
        int codigo = scanner.nextInt();
        scanner.nextLine();
        try {
            if (codigo == 0) {
                System.out.println("Saindo...");
            } else {
                verificador.verificarCodigo(codigo);
                for (Produto produto : controlador.getProdutos()) {
                    if (produto instanceof ProdutoPerecivel) { // verifica se é perecivel
                        ProdutoPerecivel produtoPerecivel = (ProdutoPerecivel) produto;
                        if (produtoPerecivel.getCodigo() == codigo) {
                            System.out.println("Tem certeza que quer mudar data de validade de (" + produto.getNome()
                                    + ") (s/n)?");
                            String opcao = scanner.nextLine();
                            verificador.verificarResposta(opcao);
                            if (opcao.equals("n")) {
                                System.out.println("Saindo...");
                                return;
                            }
                            produtoEncontrado = true;
                            System.out.println("Digite uma nova data de validade para este produto:(dd/mm/yyyy)");
                            String dataDeValidade = scanner.nextLine();
                            verificador.verificarDataValidade(dataDeValidade);
                            produtoPerecivel.setDataDeValidade(dataDeValidade); // atualiza a data de validade do
                                                                                // produto
                            System.out.println("Data de validade  atualizado com sucesso!");
                            break; // data atualizada, sai do loop
                        }
                    }
                }
                if (!produtoEncontrado) {
                    System.out.println("Produto com esse código nao existe!");
                }
            }
        } catch (InvalidCodigoException e) {
            System.out.println(e.getMessage());
        } catch (ProdutoVencidoException e) {
            System.out.println(e.getMessage());
        }
    }
}
