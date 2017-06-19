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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author joaosantos
 */
@Entity
@Table(name = "areceber")
@NamedQueries({
    @NamedQuery(name = "AreceberEntidade.findAll", query = "SELECT a FROM AreceberEntidade a")})
public class AreceberEntidade implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_areceber")
    private Integer idAreceber;
    @Column(name = "id_venda")
    private Integer idVenda;
    @Column(name = "id_usuario")
    private Integer idUsuario;
    @Column(name = "id_cliente")
    private Integer idCliente;
    @Column(name = "data")
    private String data;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valor")
    private Float valor;
    @Column(name = "numero_parcela")
    private Integer numeroParcela;

    public AreceberEntidade() {
    }

    public AreceberEntidade(Integer idAreceber) {
        this.idAreceber = idAreceber;
    }

    public Integer getIdAreceber() {
        return idAreceber;
    }

    public void setIdAreceber(Integer idAreceber) {
        this.idAreceber = idAreceber;
    }

    public Integer getIdVenda() {
        return idVenda;
    }

    public void setIdVenda(Integer idVenda) {
        this.idVenda = idVenda;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Float getValor() {
        return valor;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }

    public Integer getNumeroParcela() {
        return numeroParcela;
    }

    public void setNumeroParcela(Integer numeroParcela) {
        this.numeroParcela = numeroParcela;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAreceber != null ? idAreceber.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AreceberEntidade)) {
            return false;
        }
        AreceberEntidade other = (AreceberEntidade) object;
        if ((this.idAreceber == null && other.idAreceber != null) || (this.idAreceber != null && !this.idAreceber.equals(other.idAreceber))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidade.AreceberEntidade[ idAreceber=" + idAreceber + " ]";
    }
    
}
