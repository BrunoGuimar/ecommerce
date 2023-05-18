package demo.models.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Document(collection = "pedidos")
public class Pedido {

    @Id
    private String id;
    private String nomeCliente;
    private String descricao;
    private double valorTotal;
    private LocalDate dataPedido;
    private List<Produto> produtos;

    // Construtores (vazio e com todos os atributos)
    public Pedido() {
        this.dataPedido = LocalDate.now();
    }

    public Pedido(String nomeCliente, String descricao, double valorTotal, List<Produto> produtos) {
        this.nomeCliente = nomeCliente;
        this.descricao = descricao;
        this.valorTotal = valorTotal;
        this.produtos = produtos;
        this.dataPedido = LocalDate.now();
    }

    // Getters e Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }
    public LocalDate getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(LocalDate dataPedido) {
        this.dataPedido = dataPedido;
    }
    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;

    }
}
