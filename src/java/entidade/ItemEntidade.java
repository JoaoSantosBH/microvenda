/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidade;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author joaosantos
 */
@Entity
@Table(name = "item")
@NamedQueries({
    @NamedQuery(name = "ItemEntidade.findAll", query = "SELECT i FROM ItemEntidade i")})
public class ItemEntidade implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "numero_pedido")
    private Long numeroPedido;
    @Column(name = "id_usuario")
    private Integer idUsuario;
    @Column(name = "id_cliente")
    private Integer idProduto;

    @Column(name = "id_produto")
    private Integer idCliente;
    @Column(name = "nome")
    private String nome;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valor_venda")
    private Float valorVenda;
    @Column(name = "preco_custo_item")
    private Float precoCustoItem;
    
    @Column(name = "qtde")
    private Integer qtde;

    public Float getPrecoCustoItem() {
        return precoCustoItem;
    }

    public void setPrecoCustoItem(Float precoCustoItem) {
        this.precoCustoItem = precoCustoItem;
    }

    public ItemEntidade() {
    }

    public ItemEntidade(Long numeroPedido) {
        this.numeroPedido = numeroPedido;
    }

    public Long getNumeroPedido() {
        return numeroPedido;
    }

    public void setNumeroPedido(Long numeroPedido) {
        this.numeroPedido = numeroPedido;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Integer idProduto) {
        this.idProduto = idProduto;
    }
    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Float getValorVenda() {
        return valorVenda;
    }

    public void setValorVenda(Float valorVenda) {
        this.valorVenda = valorVenda;
    }

    public Integer getQtde() {
        return qtde;
    }

    public void setQtde(Integer qtde) {
        this.qtde = qtde;
    }



   
    
}
